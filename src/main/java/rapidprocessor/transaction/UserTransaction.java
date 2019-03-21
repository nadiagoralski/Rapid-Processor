package rapidprocessor.transaction;

import org.apache.commons.lang3.StringUtils;
import rapidprocessor.util.Constants;

import java.math.BigDecimal;

public class UserTransaction implements Transaction {
    /*
     * The username
     */
    private String usernameVal;
    /*
     * The user type code
     */
    private String userTypeVal;
    /*
     * The user's credit value
     */
    private BigDecimal creditVal;

    /*
     * Transaction string
     * formatted XX_UUUUUUUUUUUUUUU_TT_CCCCCCCCC
     */
    private String transactionString;
    /*
     * Transaction type
     * END_OF_SESSION
     * CREATE
     * DELETE
     * ADD_CREDIT
     */
    private Transaction.TransactionType transactionType;


    /**
     * Create new UserTransaction object
     * @param transactionType the transaction type
     * @param username the username
     * @param userType the user type
     * @param credit the account credit
     */
    public UserTransaction(Transaction.TransactionType transactionType, String username, String userType, BigDecimal credit) {
        this.transactionType = transactionType;
        // Pad the string values with spaces and numeric values with 0's
        this.usernameVal = username;
        this.userTypeVal = userType;
        this.creditVal = credit;

        this.transactionString = this.transactionType.getCode() + " " +
                StringUtils.rightPad(this.usernameVal, Constants.MAX_USERNAME_LENGTH, " ") + " " +
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
