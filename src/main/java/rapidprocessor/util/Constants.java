package rapidprocessor.util;

import rapidprocessor.transaction.Transaction;

/**
 * Constants
 * Defines constant values used throughout the system
 */
public class Constants {
    /*
     * File strings
     */
    public static String END_OF_FILE_LINE = "END";
    public static String ERROR_PREFIX = "ERROR: ";

    /*
     * Maximum lengths
     */
    public static Integer MAX_USERNAME_LENGTH = 15;
    public static Integer MAX_EVENT_TITLE_LENGTH = 25;


    /*
     * Major Transaction Types
     */
    public static String TRANSACTION_REFUND = "REFUND";
    public static String TRANSACTION_TICKET = "TICKET";
    public static String TRANSACTION_USER = "USER";

}
