import org.junit.Test;

import rapidprocessor.ticketBatch.TicketBatch;

public class TicketUtilTest {
    List<TicketBatch> tickets = new ArrayList<>();

    @Test
    public void getTicketBatchData() {
        TicketBatch tu = new TicketBatch();

        List<TicketBatch>tickets = t.getTicketBatchData();

        this.tickets = tu.getUserData();
        TicketBatch t = this.tickets.get(0);

        assertEquals("admin", t.getUsername());
        assertEquals("AA", t.getUserType().getCode());
        assertEquals("1000.00", t.getUserBalance().toString());
    }

    @Test
    public void updateTicketBatch() {

    }

    @Test
    public void updateTicketBatchDatabase() {
    }
}
