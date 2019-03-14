package rapidprocessor.ticketBatch;

import org.apache.commons.lang3.StringUtils;
import rapidprocessor.util.Constants;

import java.math.BigDecimal;

public class TicketBatch {
    private String eventTitle;
    private String sellerName;
    private Integer quantityAvailable = 0;
    private BigDecimal price = BigDecimal.ZERO;

    /**
     * TicketBatch default constructor
     */
    public TicketBatch() {
    }

    /**
     * TicketBatch constructor
     * @param eventTitle
     * @param sellerName
     * @param quantityAvailable
     * @param price
     */
    public TicketBatch(String eventTitle, String sellerName, Integer quantityAvailable, BigDecimal price) {
        this.eventTitle = eventTitle;
        this.sellerName = sellerName;
        this.quantityAvailable = quantityAvailable;
        this.price = price;
    }

    public TicketBatch(String fileLine) {
        this.eventTitle = StringUtils.trimToEmpty(fileLine.substring(0, Constants.MAX_EVENT_TITLE_LENGTH - 1));
        this.sellerName = StringUtils.trimToEmpty(fileLine.substring(25, 33));
        this.quantityAvailable = Integer.parseInt(fileLine.substring(34, 37));
        this.price = new BigDecimal(fileLine.substring(38, fileLine.length()).trim());
    }

    /**
     * Getter for event title
     * @return event title
     */
    public String getEventTitle() {
        return eventTitle;
    }

    /**
     * Setter for event title
     * @param eventTitle
     */
    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    /**
     * Getter for seller name
     * @return seller name
     */
    public String getSellerName() {
        return sellerName;
    }

    /**
     * Setter for seller name
     * @param sellerName
     */
    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    /**
     * Getter for quantity available
     * @return quantity of tickets available
     */
    public Integer getQuantityAvailable() {
        return quantityAvailable;
    }

    /**
     * Setter for quantity available
     * @param quantityAvailable
     */
    public void setQuantityAvailable(Integer quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    /**
     * Getter for ticket price
     * @return price of ticket
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Setter for ticket price
     * @param price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Overrides the toString() method to print out ticket information in the format
     * EEEEEEEEEEEEEEEEEEE_SSSSSSSSSSSSS_TTT_PPPPPP
     * Where
     * EEEEEEEEEEEEEEEEEEE is the event name
     * SSSSSSSSSSSSSS is the seller’s username
     * TTT Is the number of tickets for sale
     * PPPPPP is the price per ticket.
     * _ is a space
     * @return formatted user information
     */
    @Override
    public String toString() {
        // Pad the string values with spaces and numeric values with 0's
        String eventTitle = StringUtils.rightPad(this.eventTitle, 25);
        String sellerName = StringUtils.rightPad(this.sellerName, 15);
        String quantityAvailable = StringUtils.leftPad(this.quantityAvailable.toString(), 9, "0");
        String price = StringUtils.leftPad(this.price.setScale(2, BigDecimal.ROUND_HALF_UP).toString(), 6, "0");


        return (eventTitle + " " + sellerName + " " + quantityAvailable + " " + price);
    }
}
