import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;
import rapidprocessor.ticketBatch.TicketBatch;
import rapidprocessor.transaction.TicketTransaction;
import rapidprocessor.transaction.Transaction;
import rapidprocessor.util.TicketUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TicketUtilTest {
    List<TicketBatch> tickets = new ArrayList<TicketBatch>();

    @Before
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
        //Get ticket batch data
        TicketBatch tu = new TicketBatch();
        List<TicketBatch> transactionList = new ArrayList<>();

        //this.tickets = tu.getTicketBatchData();
        //TicketTransaction transaction = new TicketTransaction(Transaction.TransactionType.BUY, tickets.get(0).eventTitle, tickets.get(0).getUserBalance(), new BigDecimal(990));

        //update ticket batch
        //transactionList.add(transaction);
        //this.tickets = tu.updateUsersList(tickets, transactionList);
        //assertEquals("1990.00", tickets.get(0).getPrice().toString());


    }

    @Test
    public void updateTicketBatchDatabase() {

        TicketBatch tu = new TicketBatch();
        List<TicketTransaction> transactionList = new ArrayList<>();

        //tu.updateTicketBatchDatabase();

        assertNotEquals("","");
    }
}
