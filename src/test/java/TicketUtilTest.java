import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;
import rapidprocessor.ticketBatch.TicketBatch;
import rapidprocessor.transaction.TicketTransaction;
import rapidprocessor.util.TicketUtil;

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
        List<TicketBatch> TicketBatchList = tu.getTicketBatchData();
        assertEquals("expected", TicketBatchList.get(0));
        
        // edit ticket
        TicketBatch ticketBefore = new TicketBatch(TicketBatchList.get(0));
        TicketBatchList.get(0).setEventTitle("Nick is Back!");

        // Updates the TicketBatch database
        tu.updateTicketBatchDatabase(TicketBatchList);
        List<TicketBatch> TicketBatchList2 = tu.getTicketBatchData();
        
        TicketBatch ticketAfter = TicketBatchList2.get(0);

        // assertNotEquals(ticketBefore.getEventTitle(), ticketAfter.getEventTitle());
    }
}
