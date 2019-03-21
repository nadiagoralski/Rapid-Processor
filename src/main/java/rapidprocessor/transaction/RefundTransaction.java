package rapidprocessor.transaction;

import org.apache.commons.lang3.StringUtils;
import rapidprocessor.user.User;
import rapidprocessor.util.Constants;

import java.math.BigDecimal;

/**
 * RefundTransaction
 * Defines a refund transaction object with accessors to
 * - buyer username value
 * - seller username value
 * - credit value
 * - transaction string
 * - transaction type
 */
public class RefundTransaction implements Transaction {
    /*
     * The buyer's username value
     */
    private String buyerNameVal;
    /*
     * The seller's username value
     */
    private String sellerNameVal;
    /*
     * The refund credit value
     */
    private BigDecimal creditVal;

    /*
     * Transaction string
     * formatted XX_UUUUUUUUUUUUUUU_SSSSSSSSSSSSSSS_CCCCCCCCC
     */
    private String transactionString;
    /*
     * Transaction type
     * REFUND
     */
    private TransactionType transactionType = TransactionType.REFUND;

    /**
     * Create new RefundTransaction object
     * @param buyerUsername the buyer's username
     * @param sellerUsername the seller's username
     * @param credit the refunded credit
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

    /**
     * Get buyer username value
     * @return the buyer's username
     */
    public String getBuyerNameVal() {
        return buyerNameVal;
    }

    /**
     * Get seller username value
     * @return the seller's username
     */
    public String getSellerNameVal() {
        return sellerNameVal;
    }

    /**
     * Get refund credit value
     * @return the refund credit
     */
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
