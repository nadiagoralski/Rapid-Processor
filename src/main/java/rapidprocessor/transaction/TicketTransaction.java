package rapidprocessor.transaction;

import org.apache.commons.lang3.StringUtils;
import rapidprocessor.util.Constants;

import java.math.BigDecimal;

public class TicketTransaction implements Transaction {
    private String eventTitleVal;
    private String sellerNameVal;
    private Integer quantityVal;
    private BigDecimal priceVal;

    private String transactionString;
    private TransactionType transactionType;


    /**
     * XX_EEEEEEEEEEEEEEEEEEE_SSSSSSSSSSSSS_TTT_PPPPPP
     * @param transactionType
     * @param eventTitle
     * @param sellerName
     * @param quantity
     * @param price
     */
    public TicketTransaction(TransactionType transactionType, String eventTitle, String sellerName, Integer quantity, BigDecimal price) {
        this.eventTitleVal = eventTitle;
        this.sellerNameVal = sellerName;
        this.quantityVal = quantity;
        this.priceVal = price;

        this.transactionType = transactionType;
        this.transactionString = this.transactionType.getCode() + " " +
                StringUtils.rightPad(this.eventTitleVal, Constants.MAX_EVENT_TITLE_LENGTH) + " " +
                StringUtils.rightPad(this.sellerNameVal, Constants.MAX_USERNAME_LENGTH) + " " +
                StringUtils.leftPad(this.quantityVal.toString(), 3) + " ";
    }

    public String getEventTitleVal() {
        return eventTitleVal;
    }

    public String getSellerNameVal() {
        return sellerNameVal;
    }

    public Integer getQuantityVal() {
        return quantityVal;
    }

    public BigDecimal getPriceVal() {
        return priceVal;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public String getTransactionString() {
        return transactionString;
    }
}
