package rapidprocessor.transaction;

import org.apache.commons.lang3.StringUtils;
import rapidprocessor.user.User;
import rapidprocessor.util.Constants;

import java.math.BigDecimal;

public class RefundTransaction implements Transaction {
    private String buyerNameVal;
    private String sellerNameVal;
    private BigDecimal creditVal;

    private String transactionString;
    private TransactionType transactionType = TransactionType.REFUND;

    /**
     * XX_UUUUUUUUUUUUUUU_SSSSSSSSSSSSSSS_CCCCCCCCC
     * @param buyerUsername
     * @param sellerUsername
     * @param amount
     */
    public RefundTransaction(String buyerUsername, String sellerUsername, BigDecimal credit) {
        this.buyerNameVal = buyerUsername;
        this.sellerNameVal = sellerUsername;
        this.creditVal = credit.setScale(2, BigDecimal.ROUND_HALF_UP);

        transactionString = transactionType.getCode() + " " +
                StringUtils.rightPad(this.buyerNameVal, Constants.MAX_USERNAME_LENGTH) + " " +
                StringUtils.rightPad(this.sellerNameVal, Constants.MAX_USERNAME_LENGTH) + " " +
                StringUtils.leftPad(this.creditVal.toString(), 9, "0");
    }

    public String getBuyerNameVal() {
        return buyerNameVal;
    }

    public String getSellerNameVal() {
        return sellerNameVal;
    }

    public BigDecimal getCreditVal() {
        return creditVal;
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
