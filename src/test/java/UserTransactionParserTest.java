import java.util.List;

import org.junit.Test;

import rapidprocessor.user.User;

public class UserTransactionParserTest {
    String line = "userSS          SS 001000.00";
    String line2 = "userSS          SS 001000.00";
    User ss = new User("userSS         ", "AA", 2000);
    User bs = new User("userBS         ", "AA", 2000);
    List<User> users = new ArrayList<User>();

    @Test
    public void testUserTransactionParser() {

        String ticketTitle = "title", SellerName = "userSS         ";
        Integer ticketQty = 10, ticketPrice = 110;

        TicketBatch ticket = new TicketBatch(ticketTitle, SellerName, ticketQty, ticketPrice);

        tickets.add(ticket);
        
        users.add(ss);
        users.add(bs);

        TicketTransactionParser parser = new TicketTransactionParser();

        TicketTransaction ticketTransaction = parser.parse(line, tickets, users);

        assertEquals("Test Event Title         ", "ticketTransaction.getEventTitleVal()");
        assertEquals("userSS         ", ticketTransaction.getSellerNameVal());
        assertEquals("005", ticketTransaction.getQuantityVal());
        assertEquals("110", ticketTransaction.getPriceVal());
        assertEquals("04", ticketTransaction.getTransactionType());
        assertEquals("04 Test Event Title          userSS          005 110.00", ticketTransaction.getTransactionString());
    }
}
