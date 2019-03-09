package rapidprocessor.transaction;

import org.apache.commons.lang3.StringUtils;
import rapidprocessor.user.User;
import rapidprocessor.util.Constants;

import java.math.BigDecimal;

public class Refund implements Transaction {
    private String transactionString;
    private TransactionType transactionType =  TransactionType.REFUND;

    /**
     * XX_UUUUUUUUUUUUUUU_SSSSSSSSSSSSSSS_CCCCCCCCC
     * @param buyer
     * @param seller
     * @param amount
     */
    public Refund(User buyer, User seller, BigDecimal amount) {
        transactionString = transactionType.getCode() + " " +
                StringUtils.rightPad(buyer.getUsername(), Constants.MAX_USERNAME_LENGTH) + " " +
                StringUtils.rightPad(seller.getUsername(), Constants.MAX_USERNAME_LENGTH) + " " +
                StringUtils.leftPad(amount.setScale(2, BigDecimal.ROUND_HALF_UP).toString(), 9, "0");

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
