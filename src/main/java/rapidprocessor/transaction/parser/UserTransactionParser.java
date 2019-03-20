package rapidprocessor.transaction.parser;

import org.apache.commons.lang3.StringUtils;
import rapidprocessor.ticketBatch.TicketBatch;
import rapidprocessor.transaction.Transaction;
import rapidprocessor.transaction.UserTransaction;
import rapidprocessor.user.User;
import rapidprocessor.util.Constants;

import java.math.BigDecimal;
import java.util.List;

public class UserTransactionParser implements TransactionParser {
    /**
     * Parses user transaction line
     * @param fileLine formatted XX_UUUUUUUUUUUUUUU_TT_CCCCCCCCC
     * @return UserTransaction
     */
    public UserTransaction parse(String fileLine, List<TicketBatch> availableTickets, List<User> availableUsers) {
        // Get string values
        String transactionCode = fileLine.substring(0, 2);
        String username = fileLine.substring(3, 18).trim();
        //String userTypeCode = fileLine.substring(18, 20);
        BigDecimal userBalance = new BigDecimal(fileLine.substring(21).trim());

        // Find user objects matching username, get transaction type and credit value
        Transaction.TransactionType transactionType = Transaction.TransactionType.fromCode(transactionCode);
        User user = availableUsers.stream().filter(userInList -> username.equals(userInList.getUsername())).findFirst().orElse(null);
        BigDecimal credit = userBalance;

        return new UserTransaction(transactionType, user, credit);
    }
}
