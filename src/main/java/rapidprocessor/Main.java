package rapidprocessor;

import rapidprocessor.ticketBatch.TicketBatch;
import rapidprocessor.transaction.RefundTransaction;
import rapidprocessor.transaction.UserTransaction;
import rapidprocessor.user.User;
import rapidprocessor.util.TicketUtil;
import rapidprocessor.util.TransactionUtil;
import rapidprocessor.util.UsersUtil;

import java.util.List;

/**
 * Main
 * Main processing class
 */
public class Main {
    // Global utils
    static TransactionUtil transactionUtil = null;
    static UsersUtil usersUtil = new UsersUtil();
    static TicketUtil ticketUtil = new TicketUtil();

    public static void main(String[] args) {
        // Get current available tickets and users
        List<TicketBatch> tickets = ticketUtil.getTicketBatchData();
        List<User> users = usersUtil.getUserData();

        // Read in data from ticket, transaction, and user files
        ticketUtil = new TicketUtil();
        transactionUtil = new TransactionUtil();
        transactionUtil.init(tickets, users);
        usersUtil = new UsersUtil();

        try {
//            for (RefundTransaction refundTransaction : transactionUtil.getRefundTransactions()) {
//                // TODO: implement
//                // will affect the user file
//            }

            // Process Ticket Transactions
            //ticketUtil.updateTicketBatch(tickets, transactionUtil.getTicketTransactions());


            //ticketUtil.updateTicketBatchDatabase();

            // Process User Transactions
            //usersUtil.updateUsersList(users, transactionUtil.getUserTransactions());
            //usersUtil.updateUserDatabase();


        } catch (Exception e) {
            // TODO: Implement Logging class for messages
            // TODO: handle error
            e.printStackTrace();
        }
    }
}
