import static org.junit.Assert.assertEquals;

import org.junit.Test;

import rapidprocessor.ticketBatch.TicketBatch;

public class TicketBatchTest {

    @Test
    public void testTicketBatch() {
        String title = "title",
                name = "name";

        Integer qty = 10,
                price = 10;
        
        TicketBatch ticket = new TicketBatch(title, name, qty, price);


        assertEquals(ticket.getEventTitle(), title);
        assertEquals(ticket.getSellerName(), name);
        assertEquals(ticket.getQuantityAvailable().toString(), qty.toString());
        assertEquals(ticket.getPrice().toString(), price.toString());
    }

    @Test
    public void testSetEventTitle() {
        String title = "title", name = "name";

        Integer qty = 10, price = 10;

        TicketBatch ticket = new TicketBatch(title, name, qty, price);

        ticket.setEventTitle("eventTitle");
        assertEquals(ticket.getEventTitle(), "eventTitle");
    }

    @Test
    public void testSetSellerName() {
        String title = "title", name = "name";

        Integer qty = 10, price = 10;

        TicketBatch ticket = new TicketBatch(title, name, qty, price);

        ticket.setSellerName("NEWNAME");
        assertEquals(ticket.getSellerName(), "NEWNAME");
    }

    @Test
    public void testSetQuantityAvailable() {
        String title = "title", name = "name";

        Integer qty = 10, price = 10;

        TicketBatch ticket = new TicketBatch(title, name, qty, price);

        ticket.setQuantityAvailable(11);
        assertEquals(ticket.getQuantityAvailable().toString(),"11");
    }

    @Test
    public void testSetPrice() {
        String title = "title", name = "name";

        Integer qty = 10, price = 10;

        TicketBatch ticket = new TicketBatch(title, name, qty, price);

        ticket.setPrice(11);

        assertEquals(ticket.getPrice().toString(), "11");
    }

    @Test
    public void testToString() {
        String title = "title", name = "name";

        Integer qty = 10, price = 10;

        TicketBatch ticket = new TicketBatch(title, name, qty, price);

        assertEquals(ticket.toString(), "title                     name            000000010 010.00");
    }
}
