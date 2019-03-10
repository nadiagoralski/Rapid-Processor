package rapidprocessor.util;

import rapidprocessor.transaction.Transaction;

public class Constants {
    public static String END_OF_FILE_LINE = "END";
    public static String ERROR_PREFIX = "ERROR: ";

    public static Integer MAX_USERNAME_LENGTH = 15;
    public static Integer MAX_EVENT_TITLE_LENGTH = 25;
    
    
    public static Transaction.TransactionType END_OF_SESSION = Transaction.TransactionType.END_OF_SESSION;
    public static Transaction.TransactionType CREATE = Transaction.TransactionType.CREATE;
    public static Transaction.TransactionType DELETE = Transaction.TransactionType.DELETE;
    public static Transaction.TransactionType SELL = Transaction.TransactionType.SELL;
    public static Transaction.TransactionType BUY = Transaction.TransactionType.BUY;
    public static Transaction.TransactionType REFUND = Transaction.TransactionType.REFUND;
    public static Transaction.TransactionType ADD_CREDIT = Transaction.TransactionType.ADD_CREDIT;


}
