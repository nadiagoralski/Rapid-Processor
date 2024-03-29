import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import rapidprocessor.ticketBatch.TicketBatch;
import rapidprocessor.transaction.TicketTransaction;
import rapidprocessor.transaction.parser.TicketTransactionParser;
import rapidprocessor.user.User;

public class TicketTransactionParserTest {
    
    String line;
    User ss;
    User bs;
    List<User> users;
    List<TicketBatch> tickets;

    @Before
    public void setUp() throws Exception {
        line = "04 Test Event Title          userSS          005 110.00";
        ss = new User("userSS         ", "AA", 2000);
        bs = new User("userBS         ", "AA", 2000);
        users = new ArrayList<User>();
        tickets = new ArrayList<TicketBatch>();
    }

    @Test
    public void testTicketTransactionParser() {

        String ticketTitle = "title", SellerName = "userSS         ";
        Integer ticketQty = 10, ticketPrice = 110;

        TicketBatch ticket = new TicketBatch(ticketTitle, SellerName, ticketQty, ticketPrice);

        tickets.add(ticket);
        
        users.add(ss);
        users.add(bs);

        TicketTransactionParser parser = new TicketTransactionParser();

        TicketTransaction ticketTransaction = parser.parse(line, tickets, users);

        // assertEquals("", "");
        assertEquals("Test Event Title", ticketTransaction.getEventTitleVal());
        assertEquals("userSS", ticketTransaction.getSellerNameVal());
        assertEquals("5", ticketTransaction.getQuantityVal().toString());
        assertEquals("110.00", ticketTransaction.getPriceVal().toString());
        assertEquals("BUY", ticketTransaction.getTransactionType().toString());
        assertEquals("04 Test Event Title          userSS          005 110.00", ticketTransaction.getTransactionString());
    }
}
