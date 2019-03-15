package rapidprocessor.transaction.parser;

import org.apache.commons.lang3.StringUtils;
import rapidprocessor.ticketBatch.TicketBatch;
import rapidprocessor.transaction.RefundTransaction;
import rapidprocessor.user.User;
import rapidprocessor.util.Constants;

import java.math.BigDecimal;
import java.util.List;

public class RefundTransactionParser implements TransactionParser {

    /**
     * Parses Refund transaction line
     * @param fileLine formatted XX_UUUUUUUUUUUUUUU_SSSSSSSSSSSSSSS_CCCCCCCCC
     * @return RefundTransaction
     */
    public RefundTransaction parse(String fileLine, List<TicketBatch> availableTickets, List<User> availableUsers) {
        // Get string values for buyer username and seller username
        String buyerUsername = StringUtils.trimToEmpty(fileLine.substring(4, 4 + Constants.MAX_USERNAME_LENGTH));
        String sellerUsername = StringUtils.trimToEmpty(fileLine.substring(20, 20 + Constants.MAX_USERNAME_LENGTH));


        // Find user objects matching usernames, get credit value
        User buyer = availableUsers.stream().filter(user -> buyerUsername.equals(user.getUsername())).findFirst().orElse(null);
        User seller = availableUsers.stream().filter(user -> sellerUsername.equals(user.getUsername())).findFirst().orElse(null);
        BigDecimal credit = new BigDecimal(fileLine.substring(59, fileLine.length()));

        return new RefundTransaction(buyer, seller, credit);
    }
}
