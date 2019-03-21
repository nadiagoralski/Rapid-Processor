import java.util.List;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import rapidprocessor.ticketBatch.TicketBatch;
import rapidprocessor.transaction.RefundTransaction;
import rapidprocessor.transaction.parser.RefundTransactionParser;
import rapidprocessor.user.User;

public class RefundTransactionParserTest {

    String line = "05 userSS          userBS          000050.00";
    User ss = new User("userSS         ", "AA", 10);
    User bs = new User("userBS         ", "AA", 10);
    List<User> users = new ArrayList<User>();
    List<TicketBatch> tickets = new ArrayList<TicketBatch>();
    
    

    @Test
    public void testRefundTransactionParse() {
        
        String ticketTitle = "title", SellerName = "userSS         ";
        Integer ticketQty = 10, ticketPrice = 10;

        TicketBatch ticket = new TicketBatch(ticketTitle, SellerName, ticketQty, ticketPrice);

        tickets.add(ticket);

        users.add(ss);
        users.add(bs);

        RefundTransactionParser parser = new RefundTransactionParser();

        RefundTransaction newRefund = parser.parse(line, tickets, users);

        assertEquals("05 userSS          userBS          000050.00", newRefund.getTransactionString());
        assertEquals("05", newRefund.getTransactionType());

    }

    // @Test
    // public void testRefundTransactionParseOne() {
    //     String line = "01 UUUUUUUUUUUUUUU SSSSSSSSSSSSSSS 000010.00";
    //     User u = new User("UUUUUUUUUUUUUUU", "AA", 10);
        
    //     List<TicketBatch> tickets = new ArrayList<>();
    //     List<User> users = new ArrayList<>();
    //     TicketBatch ticket = new TicketBatch(ticketTitle, ticketName, ticketQty, ticketPrice);

    //     tickets.add(ticket);

    //     RefundTransactionParser parser = new RefundTransactionParser(line, );


    // }
}
