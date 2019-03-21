package rapidprocessor.transaction;

import org.apache.commons.lang3.StringUtils;
import rapidprocessor.user.User;
import rapidprocessor.util.Constants;

import java.math.BigDecimal;

public class UserTransaction implements Transaction {
    private String usernameVal;
    private String userTypeVal;
    private BigDecimal creditVal;

    private String transactionString;
    private Transaction.TransactionType transactionType;

    /**
     * XX_UUUUUUUUUUUUUUU_TT_CCCCCCCCC
     * @param user
     */
    public UserTransaction(Transaction.TransactionType transactionType, User user, BigDecimal credit) {
        this.transactionType = transactionType;
        // Pad the string values with spaces and numeric values with 0's
        this.usernameVal = StringUtils.rightPad(user.getUsername(), Constants.MAX_USERNAME_LENGTH);
        this.userTypeVal = user.getUserType().getCode();
        this.creditVal = credit;

        this.transactionString = this.transactionType.getCode() + " " +
            this.usernameVal + " " +
            this.userTypeVal + " " +
            StringUtils.leftPad(this.creditVal.toString().replace(".", ""), 9, "0");
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

    /**
     * Get username string value
     * @return the parsed username string
     */
    public String getUsernameVal() {
        return usernameVal;
    }

    /**
     * Get user type string value
     * @return the parsed user type as a string
     */
    public String getUserTypeVal() {
        return userTypeVal;
    }

    /**
     * Get user balance
     * @return the parsed user balance
     */
    public BigDecimal getCreditVal() {
        return creditVal;
    }
}
