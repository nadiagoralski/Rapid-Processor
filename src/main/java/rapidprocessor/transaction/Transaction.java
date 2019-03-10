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
        
        /**
         * Converts string to TransactionType
         * 
         * @param TransactionType
         * @return matching TransactionType object
         */
        public static TransactionType fromString(String transactionType) {
            if (END_OF_SESSION.code.equals(transactionType)) {
                return END_OF_SESSION;
            } else if (CREATE.code.equals(transactionType)) {
                return CREATE;
            } else if (DELETE.code.equals(transactionType)) {
                return DELETE;
            } else if (SELL.code.equals(transactionType)) {
                return SELL;
            } else if (BUY.code.equals(transactionType)) {
                return BUY;
            } else if (REFUND.code.equals(transactionType)) {
                return REFUND;
            } else if (ADD_CREDIT.code.equals(transactionType)) {
                return ADD_CREDIT;
            }
            return null;
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
