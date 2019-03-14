package rapidprocessor.transaction;

import rapidprocessor.util.Constants;

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
        END_OF_SESSION("00", Constants.TRANSACTION_USER),
        CREATE("01", Constants.TRANSACTION_USER),
        DELETE("02", Constants.TRANSACTION_USER),
        SELL("03", Constants.TRANSACTION_TICKET),
        BUY("04", Constants.TRANSACTION_TICKET),
        REFUND("05", Constants.TRANSACTION_REFUND),
        ADD_CREDIT("06", Constants.TRANSACTION_USER);

        private String code; // transaction code
        private String parseType;

        /**
         * Default constructor for TransactionType enum
         *
         * @param code
         */
        TransactionType(String code, String parseType) {
            this.code = code;
            this.parseType = parseType;
        }

        public String getCode() {
            return code;
        }

        public String getParseType() {
            return parseType;
        }

        /**
         * Converts string to TransactionType
         * 
         * @param transactionType
         * @return matching TransactionType object
         */
        public static TransactionType fromCode(String transactionType) {
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
