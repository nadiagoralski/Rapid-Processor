import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import rapidprocessor.transaction.UserTransaction;
import rapidprocessor.transaction.parser.UserTransactionParser;
import rapidprocessor.user.User;

public class UserTransactionParserTest {
    String line = "userSS          SS 001000.00";
    User ss = new User("userSS         ", "AA", 2000);
    User bs = new User("userBS         ", "AA", 2000);
    List<User> users = new ArrayList<User>();

    @Test
    public void testUserTransactionParser() {
        users.add(ss);
        users.add(bs);

        UserTransactionParser parser = new UserTransactionParser();

        UserTransaction userTransaction = parser.parse(line, users);

        assertEquals("userSS         ", userTransaction.getUsernameVal());
        assertEquals("SS", userTransaction.getUserTypeVal());
        assertEquals("110", userTransaction.getCreditVal());
        assertEquals("04", userTransaction.getTransactionType());
        assertEquals("04 Test Event Title          userSS          005 110.00", userTransaction.getTransactionString());
    }
}
