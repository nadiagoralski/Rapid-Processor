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
    public void updateTicketBatchDatabase() {
        // Gets tickets
        TicketUtil tu = new TicketUtil();
        List<TicketBatch> ticketBatchList = tu.getTicketBatchData();

        // get and edit ticket quantity
        TicketBatch ticketBefore = new TicketBatch(ticketBatchList.get(0));
        ticketBatchList.get(0).setQuantityAvailable(ticketBefore.getQuantityAvailable() - 1);

        // Updates the TicketBatch database
        tu.updateTicketBatchDatabase(ticketBatchList);
        ticketBatchList = tu.getTicketBatchData();

        TicketBatch ticketAfter = ticketBatchList.get(0);

        assertNotEquals(ticketBefore.getQuantityAvailable(), ticketAfter.getQuantityAvailable());
    }
}
