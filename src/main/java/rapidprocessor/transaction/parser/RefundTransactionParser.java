package rapidprocessor.transaction.parser;

import org.apache.commons.lang3.StringUtils;
import rapidprocessor.ticketBatch.TicketBatch;
import rapidprocessor.transaction.RefundTransaction;
import rapidprocessor.user.User;
import rapidprocessor.util.Constants;

import java.math.BigDecimal;
import java.util.List;

/**
 * RefundTransactionParser
 *
 * Parses transaction file line to a RefundTransaction
 */
public class RefundTransactionParser implements TransactionParser {

    /**
     * Parse the refund transaction from the the file
     * @param fileLine formatted XX_UUUUUUUUUUUUUUU_SSSSSSSSSSSSSSS_CCCCCCCCC
     * @param availableTickets list of available tickets
     * @param availableUsers list of available users
     * @return RefundTransaction
     */
    public RefundTransaction parse(String fileLine, List<TicketBatch> availableTickets, List<User> availableUsers) {
        // Get string values for buyer username and seller username
        String buyerUsername = StringUtils.trimToEmpty(fileLine.substring(3, 4 + Constants.MAX_USERNAME_LENGTH));
        String sellerUsername = StringUtils.trimToEmpty(fileLine.substring(19, 20 + Constants.MAX_USERNAME_LENGTH));


        // Find user objects matching usernames, get credit value
        User buyer = availableUsers.stream().filter(user -> buyerUsername.equals(user.getUsername())).findFirst().orElse(null);
        User seller = availableUsers.stream().filter(user -> sellerUsername.equals(user.getUsername())).findFirst().orElse(null);
        BigDecimal credit = new BigDecimal(fileLine.substring(36));

        return new RefundTransaction(buyer.getUsername(), seller.getUsername(), credit);
    }
}
