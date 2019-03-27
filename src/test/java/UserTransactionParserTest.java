import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import rapidprocessor.ticketBatch.TicketBatch;
import rapidprocessor.transaction.UserTransaction;
import rapidprocessor.transaction.parser.UserTransactionParser;
import rapidprocessor.user.User;

public class UserTransactionParserTest {
    // String line = "XX_UUUUUUUUUUUUUUU_TT_CCCCCCCCC";
    String line = "01 user2SS         SS 001000.00";
    User ss = new User("userSS         ", "AA", 2000);
    User bs = new User("userBS         ", "AA", 2000);
    List<User> users = new ArrayList<User>();
    List<TicketBatch> tickets = new ArrayList<>();

    @Test
    public void testUserTransactionParser() {
        users.add(ss);
        users.add(bs);

        UserTransactionParser parser = new UserTransactionParser();
        UserTransaction userTransaction = parser.parse(line, tickets, users);
        
        // UserTransaction userTransact2ion = parser.parse(line, tickets, users);
        assertEquals("user2SS", userTransaction.getUsernameVal());
        assertEquals("SS", userTransaction.getUserTypeVal().toString());
        assertEquals("1000.00", userTransaction.getCreditVal().toString());
        assertEquals("CREATE", userTransaction.getTransactionType().toString());
        assertEquals("01 user2SS         SS 001000.00", userTransaction.getTransactionString());

    }

    
}
