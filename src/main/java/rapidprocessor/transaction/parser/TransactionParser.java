package rapidprocessor.transaction.parser;

import rapidprocessor.transaction.Transaction;

public interface TransactionParser {

    /**
     * Parse the file based on transaction type
     * @param fileLine
     * @return
     */
    public Transaction parse(String fileLine);


}
