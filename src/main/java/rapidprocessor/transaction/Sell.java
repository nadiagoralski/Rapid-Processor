package rapidprocessor.transaction;

import rapidprocessor.ticketBatch.TicketBatch;

public class Sell implements Transaction {
    private String transactionString;
    private TransactionType transactionType =  TransactionType.SELL;

    public Sell(TicketBatch sellBatch) {

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
