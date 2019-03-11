package rapidprocessor.transaction;

import org.apache.commons.lang3.StringUtils;
import rapidprocessor.ticketBatch.TicketBatch;
import rapidprocessor.util.Constants;

public class TicketTransaction implements Transaction {
    private String transactionString;
    private TransactionType transactionType =  TransactionType.BUY;


    /**
     * XX_EEEEEEEEEEEEEEEEEEE_SSSSSSSSSSSSS_TTT_PPPPPP
     * @param transactionType
     * @param ticket
     */
    public TicketTransaction(TransactionType transactionType, TicketBatch ticket) {
        transactionType = transactionType;
        transactionString = transactionType.getCode() + " " +
                StringUtils.rightPad(ticket.getEventTitle(), Constants.MAX_EVENT_TITLE_LENGTH) + " ";
    }


    public TransactionType getTransactionType() {
        return null;
    }

    public String getTransactionString() {
        return null;
    }
}
