package rapidprocessor.transaction;

import rapidprocessor.ticketBatch.TicketBatch;
import rapidprocessor.user.User;

import java.math.BigDecimal;

public class Transaction {
    /**
     * Valid transaction types
     * 00 - End of Session
     * 01 - Create
     * 02 - Delete
     * 03 - Sell
     * 04 - Buy
     * 05 - Refund
     * 06 - Add Credit
     */
    public enum TransactionType {
        END_OF_SESSION("00"),
        CREATE("01"),
        DELETE("02"),
        SELL("03"),
        BUY("04"),
        REFUND("05"),
        ADD_CREDIT("06");

        String code; // transaction code

        /**
         * Default constructor for TransactionType enum
         * @param code
         */
        TransactionType(String code) {
            this.code = code;
        }
    }

    /**
     * The transaction type
     */
    private TransactionType transactionType;
    /**
     * The transaction string to be written
     */
    private String transactionString;

    /**
     * Getter for transaction string
     * @return the transaction string
     */
    public String getTransactionString() {
        return transactionString;
    }

    /**
     * Setter for transaction string
     * @param transactionString
     */
    public void setTransactionString(String transactionString) {
        this.transactionString = transactionString;
    }


}
