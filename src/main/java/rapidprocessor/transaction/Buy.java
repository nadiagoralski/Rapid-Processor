package rapidprocessor.transaction;

import org.apache.commons.lang3.StringUtils;
import rapidprocessor.ticketBatch.TicketBatch;
import rapidprocessor.util.Constants;

public class Buy implements Transaction {
    private String transactionString;
    private TransactionType transactionType =  TransactionType.BUY;

    /**
     * XX_EEEEEEEEEEEEEEEEEEE_SSSSSSSSSSSSS_TTT_PPPPPP
     * @param ticket
     * @param buyAmount
     */
    public Buy(TicketBatch ticket, Integer buyAmount) {
        transactionString = transactionType.getCode() + " " +
                StringUtils.rightPad(ticket.getEventTitle(), Constants.MAX_EVENT_TITLE_LENGTH) + " ";
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
