package rapidprocessor;

import rapidprocessor.ticketBatch.TicketBatch;
import rapidprocessor.user.User;
import rapidprocessor.util.TicketUtil;
import rapidprocessor.util.TransactionUtil;
import rapidprocessor.util.UserUtil;

import java.util.List;

/**
 * Main
 * Main processing class
 */
public class Main {
    // Global utils
    static TransactionUtil transactionUtil = null;
    static UserUtil userUtil = new UserUtil();
    static TicketUtil ticketUtil = new TicketUtil();

    public static void main(String[] args) {
        // Get current available tickets and users
        List<TicketBatch> tickets = ticketUtil.getTicketBatchData();
        List<User> users = userUtil.getUserData();

        // Read in data from ticket, transaction, and user files
        ticketUtil = new TicketUtil();
        transactionUtil = new TransactionUtil();
        userUtil = new UserUtil();

        try {
            transactionUtil.init(tickets, users);
            // Process Ticket Transactions
            //ticketUtil.updateTicketBatchDatabase(tickets);

            // Process User Transactions
            //usersUtil.updateUserDatabase(users);


        } catch (Exception e) {
            // TODO: Implement Logging class for messages
            // TODO: handle error
            e.printStackTrace();
        }
    }
}
