import static org.junit.Assert.assertNotEquals;

import java.util.List;

import org.junit.Test;

import rapidprocessor.ticketBatch.TicketBatch;
import rapidprocessor.user.User;
import rapidprocessor.util.TicketUtil;
import rapidprocessor.util.TransactionUtil;
import rapidprocessor.util.UserUtil;

public class TransactionUtilTest {
    static TransactionUtil transactionUtil = null;
    static UserUtil userUtil = new UserUtil();
    static TicketUtil ticketUtil = new TicketUtil();


    // test is failing because the tickets getTicketBatchData() isn't returning anything.
    @Test
    public void init() {

        List<TicketBatch> tickets = ticketUtil.getTicketBatchData();
        List<User> users = userUtil.getUserData();

        transactionUtil.init(tickets, users);
        // assertEquals("expected", "actual");
        assertNotEquals(transactionUtil.getAvailableUsers(), null);
        assertNotEquals(transactionUtil.getAvailableTickets(), null);
    }
}
