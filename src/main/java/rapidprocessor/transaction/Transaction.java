package rapidprocessor.transaction;

public interface Transaction {
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
    enum TransactionType {
        END_OF_SESSION("00"),
        CREATE("01"),
        DELETE("02"),
        SELL("03"),
        BUY("04"),
        REFUND("05"),
        ADD_CREDIT("06");

        private String code; // transaction code

        /**
         * Default constructor for TransactionType enum
         *
         * @param code
         */
        TransactionType(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }

    /**
     * Getter for transaction type
     * @return the transaction string
     */
    TransactionType getTransactionType();

    /**
     * Getter for transaction string
     * @return the transaction string
     */
    String getTransactionString();


}
