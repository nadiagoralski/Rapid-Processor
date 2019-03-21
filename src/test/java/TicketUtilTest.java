import static org.junit.Assert.assertEquals;

import org.junit.Test;
import rapidprocessor.ticketBatch.TicketBatch;
import rapidprocessor.transaction.TicketTransaction;
import rapidprocessor.util.TicketUtil;

import java.util.ArrayList;
import java.util.List;

public class TicketUtilTest {
    List<TicketBatch> tickets = new ArrayList<TicketBatch>();

    public void setUp() {
        
    }
    @Test
    public void getTicketBatchData() {
        TicketUtil tu = new TicketUtil();

        tickets = tu.getTicketBatchData();

        TicketBatch t = this.tickets.get(0);

        assertEquals("admin", t.getEventTitle());
        assertEquals("AA", t.getSellerName());
        assertEquals("1000.00", t.getPrice().toString());
    }

    @Test
    public void updateTicketBatch() {
        TicketBatch tu = new TicketBatch();
        List<ticketBatch> transactionList = new ArrayList<>();

        this.tickets = tu.getTicketBatchData();

        TicketTransaction transaction = new TicketTransaction(TransactionType.BUY, tickets.get(0).eventTitle, tickets.get(0).getUserBalance(), new BigDecimal(990));

        transactionList.add(transaction);

        this.users = uu.updateUsersList(users, transactionList);
        assertEquals("1990.00", users.get(0).getUserBalance().toString());
    }

    @Test
    public void updateTicketBatchDatabase() {

        TicketBatch tu = new TicketBatch();
        List<UserTransaction> transactionList = new ArrayList<>();

        this.users = uu.getUserData();

        UserTransaction transaction = new UserTransaction(TransactionType.ADD_CREDIT, users.get(0),
                new BigDecimal(990));

        transactionList.add(transaction);

        this.users = uu.updateUsersList(users, transactionList);

        uu.updateUserDatabase();
        this.users = uu.getUserData();

        assertNotEquals("1000.00", users.get(0).getUserBalance().toString());
    }
}
