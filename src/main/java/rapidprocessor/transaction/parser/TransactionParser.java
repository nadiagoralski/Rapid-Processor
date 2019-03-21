package rapidprocessor.transaction.parser;

import rapidprocessor.ticketBatch.TicketBatch;
import rapidprocessor.transaction.Transaction;
import rapidprocessor.user.User;

import java.util.List;

/**
 * TransactionParser
 * Interface for Transaction parsers
 */
public interface TransactionParser {

    /**
     * Parse the file based on transaction type
     * @param fileLine
     * @param availableTickets list of available tickets
     * @param availableUsers list of available users
     * @return Transaction object
     */
    public Transaction parse(String fileLine, List<TicketBatch> availableTickets, List<User> availableUsers);


}
