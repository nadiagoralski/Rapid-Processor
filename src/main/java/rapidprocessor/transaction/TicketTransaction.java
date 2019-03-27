package rapidprocessor.transaction;

import org.apache.commons.lang3.StringUtils;
import rapidprocessor.util.Constants;

import java.math.BigDecimal;

public class TicketTransaction implements Transaction {
    /*
     * The title of event
     */
    private String eventTitleVal;
    /*
     * The seller's username
     */
    private String sellerNameVal;
    /*
     * The quantity of tickets
     */
    private Integer quantityVal;
    /*
     * The cost of the ticket
     */
    private BigDecimal priceVal;

    /*
     * Transaction string
     * formatted XX_EEEEEEEEEEEEEEEEEEE_SSSSSSSSSSSSS_TTT_PPPPPP
     */
    private String transactionString;
    /*
     * Transaction type
     * BUY
     * SELL
     */
    private Transaction.TransactionType transactionType;


    /**
     * Create new TicketTransaction object
     * XX_EEEEEEEEEEEEEEEEEEEEEEEEE_SSSSSSSSSSSSSSS_TTT_PPPPPP
     * @param transactionType the transaction type
     * @param eventTitle the event title
     * @param sellerName the seller's username
     * @param quantity the quantity of tickets
     * @param price the price of the ticket
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
                StringUtils.leftPad(this.quantityVal.toString(), 3, "0") + " " +
                StringUtils.leftPad(this.priceVal.toString(), 6, "0");
    }

    /**
     * Get event title value
     * @return the event title
     */
    public String getEventTitleVal() {
        return eventTitleVal;
    }


    /**
     * Get seller's username value
     * @return the seller's username
     */
    public String getSellerNameVal() {
        return sellerNameVal;
    }

    /**
     * Get ticket quantity value
     * @return the ticket quantity
     */
    public Integer getQuantityVal() {
        return quantityVal;
    }

    /**
     * Get ticket price value
     * @return the ticket price
     */
    public BigDecimal getPriceVal() {
        return priceVal;
    }

    /**
     * Get transaction type
     * @return the transaction type
     */
    public TransactionType getTransactionType() {
        return transactionType;
    }

    /**
     * The transaction string
     * @return the transaction string
     */
    public String getTransactionString() {
        return transactionString;
    }
}
