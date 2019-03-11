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
        // Get string values for buyer username and seller username
        String username = StringUtils.trimToEmpty(fileLine.substring(4, 4 + Constants.MAX_USERNAME_LENGTH));

        // Find user objects matching username, get transaction type and credit value
        Transaction.TransactionType transactionType = Transaction.TransactionType.fromCode((fileLine.substring(0, 2)));
        User user = availableUsers.stream().filter(userInList -> username.equals(userInList.getUsername())).findFirst().orElse(null);
        BigDecimal credit = new BigDecimal(fileLine.substring(30, fileLine.length()));

        return new UserTransaction(transactionType, user);
    }
}
