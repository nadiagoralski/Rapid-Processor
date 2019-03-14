package rapidprocessor.transaction;

import org.apache.commons.lang3.StringUtils;
import rapidprocessor.user.User;
import rapidprocessor.util.Constants;

import java.math.BigDecimal;

public class UserTransaction implements Transaction {
    private String transactionString;
    private Transaction.TransactionType transactionType = null;

    /**
     * XX_UUUUUUUUUUUUUUU_TT_CCCCCCCCC
     * @param user
     */
    public UserTransaction(Transaction.TransactionType transactionType, User user) {
        this.transactionType = transactionType;
        // Pad the string values with spaces and numeric values with 0's
        this.transactionString = transactionType.getCode() + " " +
                StringUtils.rightPad(user.getUsername(), Constants.MAX_USERNAME_LENGTH) + " " +
                user.getUserType() + " " +
                StringUtils.leftPad(user.getUserBalance().setScale(2, BigDecimal.ROUND_HALF_UP).toString(), 9, "0");
    }

    /**
     * Get transaction type
     * @return the transaction type
     */
    public Transaction.TransactionType getTransactionType() {
        return transactionType;
    }

    /**
     * Get formatted transaction string
     * @return the transaction string
     */
    public String getTransactionString() {
        return transactionString;
    }
}