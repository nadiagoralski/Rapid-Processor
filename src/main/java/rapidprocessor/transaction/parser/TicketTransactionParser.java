package rapidprocessor.transaction.parser;

import org.apache.commons.lang3.StringUtils;
import rapidprocessor.ticketBatch.TicketBatch;
import rapidprocessor.transaction.TicketTransaction;
import rapidprocessor.transaction.Transaction;
import rapidprocessor.user.User;
import rapidprocessor.util.Constants;

import java.math.BigDecimal;
import java.util.List;

/**
 * TicketTransactionParser
 *
 * Parses transaction file line to a TicketTransaction
 */
public class TicketTransactionParser implements TransactionParser {
    /**
     * Parses Ticket transaction line
     * @param fileLine XX_EEEEEEEEEEEEEEEEEEEEEEEEE_SSSSSSSSSSSSSSS_TTT_PPPPPP
     * @param availableTickets list of available tickets
     * @param availableUsers list of available users
     * @return TicketTransaction
     */
    public TicketTransaction parse(String fileLine, List<TicketBatch> availableTickets, List<User> availableUsers) {
        // Get string values for event title and seller username
        String eventTitle = StringUtils.trimToEmpty(fileLine.substring(2, 3 + Constants.MAX_EVENT_TITLE_LENGTH));
        String sellerUsername = StringUtils.trimToEmpty(fileLine.substring(29, 30 + Constants.MAX_USERNAME_LENGTH));

        // set transaction type, quantity, and price
        Transaction.TransactionType transactionType = Transaction.TransactionType.fromCode((fileLine.substring(0, 2)));
        Integer quantity = new Integer(fileLine.substring(45, 48));
        BigDecimal price = new BigDecimal(fileLine.substring(49));

        return new TicketTransaction(transactionType, eventTitle, sellerUsername, quantity, price);
    }
}
