package rapidprocessor.transaction.parser;

import org.apache.commons.lang3.StringUtils;
import rapidprocessor.ticketBatch.TicketBatch;
import rapidprocessor.transaction.TicketTransaction;
import rapidprocessor.transaction.Transaction;
import rapidprocessor.user.User;
import rapidprocessor.util.Constants;

import java.math.BigDecimal;
import java.util.List;


public class TicketTransactionParser implements TransactionParser {
    /**
     * Parses Ticket transaction line
     * @param fileLine XX_EEEEEEEEEEEEEEEEEEE_SSSSSSSSSSSSS_TTT_PPPPPP
     * @return TicketTransaction
     */
    public TicketTransaction parse(String fileLine, List<TicketBatch> availableTickets, List<User> availableUsers) {
        // Get string values for event title and seller username
        String eventTitle = StringUtils.trimToEmpty(fileLine.substring(4, 4 + Constants.MAX_EVENT_TITLE_LENGTH));
        String sellerUsername = StringUtils.trimToEmpty(fileLine.substring(20, 20 + Constants.MAX_USERNAME_LENGTH));


        // Find TicketBatch object and seller User object
        // set transaction type, quantity, and price
        Transaction.TransactionType transactionType = Transaction.TransactionType.fromCode((fileLine.substring(0, 2)));
        TicketBatch ticketBatch = availableTickets.stream().filter(ticket -> eventTitle.equals(ticket.getEventTitle())).findFirst().orElse(null);
        User seller = availableUsers.stream().filter(user -> sellerUsername.equals(user.getUsername())).findFirst().orElse(null);
        Integer quantity = new Integer(fileLine.substring(38, 40));
        BigDecimal price = new BigDecimal(fileLine.substring(42, fileLine.length()));

        return new TicketTransaction(transactionType, ticketBatch.getEventTitle(), seller.getUsername(), quantity, price);
    }
}
