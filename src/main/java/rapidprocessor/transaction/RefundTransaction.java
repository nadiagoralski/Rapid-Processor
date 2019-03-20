package rapidprocessor.transaction;

import org.apache.commons.lang3.StringUtils;
import rapidprocessor.user.User;
import rapidprocessor.util.Constants;

import java.math.BigDecimal;

public class RefundTransaction implements Transaction {
    private String transactionString;
    private TransactionType transactionType = TransactionType.REFUND;

    /**
     * XX_UUUUUUUUUUUUUUU_SSSSSSSSSSSSSSS_CCCCCCCCC
     * @param buyerUsername
     * @param sellerUsername
     * @param amount
     */
    public RefundTransaction(String buyerUsername, String sellerUsername, BigDecimal amount) {
        transactionString = transactionType.getCode() + " " +
                StringUtils.rightPad(buyerUsername, Constants.MAX_USERNAME_LENGTH) + " " +
                StringUtils.rightPad(sellerUsername, Constants.MAX_USERNAME_LENGTH) + " " +
                StringUtils.leftPad(amount.setScale(2, BigDecimal.ROUND_HALF_UP).toString().replace(".", ""), 9, "0");
    }

    /**
     * Get transaction type
     * @return the transaction type
     */
    public TransactionType getTransactionType() {
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
