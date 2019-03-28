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
        TicketUtil tu = new TicketUtil();
        List<Transaction> transactionList = new ArrayList<>();

        this.tickets = tu.getTicketBatchData();
        TicketBatch aTicket = tickets.get(0);
        int totalBought = 5;

        TicketTransaction transaction = new TicketTransaction(Transaction.TransactionType.BUY,
                aTicket.getEventTitle(),
                aTicket.getSellerName(),
                totalBought,
                new BigDecimal(990));

        // update ticket in list
        tickets.get(0).setQuantityAvailable(aTicket.getQuantityAvailable() - totalBought);

        //update ticket batch file
        transactionList.add(transaction);
        tu.updateTicketBatchDatabase(tickets);

        // reread list
        this.tickets = tu.getTicketBatchData();
        TicketBatch updatedTicket = tickets.get(0);

        // check if the ticket quantity was updated
        assertNotEquals(aTicket.getQuantityAvailable(), updatedTicket.getQuantityAvailable());


    }

    @Test
    public void updateTicketBatchDatabase() {
        // Gets tickets
        TicketUtil tu = new TicketUtil();
        List<TicketBatch> TicketBatchList = tu.getTicketBatchData();

        Integer ticketCount = TicketBatchList.get(0).getQuantityAvailable();

        TicketBatchList.get(0).setQuantityAvailable(ticketCount - 1);

        // edit ticket
        TicketBatch ticketBefore = new TicketBatch(TicketBatchList.get(0));

        // Updates the TicketBatch database
        tu.updateTicketBatchDatabase(TicketBatchList);
        List<TicketBatch> TicketBatchList2 = tu.getTicketBatchData();

        TicketBatch ticketAfter = TicketBatchList2.get(0);

        assertNotEquals(ticketBefore.getEventTitle(), ticketAfter.getEventTitle());
    }
}
