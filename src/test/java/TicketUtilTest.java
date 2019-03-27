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
        

        assertNotEquals("","");
    }
}
