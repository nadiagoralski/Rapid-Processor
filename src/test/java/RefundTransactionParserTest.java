import java.util.List;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import rapidprocessor.ticketBatch.TicketBatch;
import rapidprocessor.transaction.RefundTransaction;
import rapidprocessor.transaction.parser.RefundTransactionParser;
import rapidprocessor.user.User;

public class RefundTransactionParserTest {
    @Test
    public void testRefundTransactionParseNone() {
        String line = "01 UUUUUUUUUUUUUUU SSSSSSSSSSSSSSS 000010.00";

        String ticketTitle = "title", ticketName = "ticketName";

        Integer ticketQty = 10, ticketPrice = 10;

        
        User u = new User("UUUUUUUUUUUUUUS", "AA", 10);
        User s = new User("SSSSSSSSSSSSSSU", "AA", 10);
        
        RefundTransaction dummyRefund = new RefundTransaction(null, null, null);

        TicketBatch ticket = new TicketBatch(ticketTitle, ticketName, ticketQty, ticketPrice);

        List<TicketBatch> tickets = new ArrayList<>();
        List<User> users = new ArrayList<User>();

        tickets.add(ticket);
        users.add(u);
        users.add(s);
        RefundTransactionParser parser = new RefundTransactionParser();

        RefundTransaction newRefund = parser.parse(line, tickets, users);

        assertEquals(dummyRefund.getTransactionString(), newRefund.getTransactionString());

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
