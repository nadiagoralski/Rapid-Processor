import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import rapidprocessor.ticketBatch.TicketBatch;
import rapidprocessor.transaction.Transaction;
import rapidprocessor.transaction.UserTransaction;
import rapidprocessor.user.User;
import rapidprocessor.util.TicketUtil;
import rapidprocessor.util.UserUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TicketUtilTest {
    List<TicketBatch> tickets = new ArrayList<>();
    List<User> users = new ArrayList<>();
    TicketUtil tu = new TicketUtil();
    UserUtil uu = new UserUtil();

    @Test
    public void getTicketBatchData() {

        List<TicketBatch>tickets = t.getTicketBatchData();

        this.tickets = tu.getTicketBatchData();
        TicketBatch t = this.tickets.get(0);

        assertEquals("admin", t.getUsername());
        assertEquals("AA", t.getUserType().getCode());
        assertEquals("1000.00", t.getUserBalance().toString());
    }

    @Test
    public void updateTicketBatch() {
        TicketBatch tu = new TicketBatch();
        List<UserTransaction> transactionList = new ArrayList<>();

        this.users = uu.getUserData();

        UserTransaction transaction = new UserTransaction(Transaction.TransactionType.ADD_CREDIT, users.get(0),
                new BigDecimal(990));

        transactionList.add(transaction);

        //this.users = uu.updateUsersList(users, transactionList);
        assertEquals("1990.00", users.get(0).getUserBalance().toString());
    }

    @Test
    public void updateTicketBatchDatabase() {

        TicketBatch tu = new TicketBatch();
        List<UserTransaction> transactionList = new ArrayList<>();

        this.users = uu.getUserData();

        UserTransaction transaction = new UserTransaction(Transaction.TransactionType.ADD_CREDIT, users.get(0),
                new BigDecimal(990));

        transactionList.add(transaction);

        this.users = uu.updateUsersList(users, transactionList);

        uu.updateUserDatabase();
        this.users = uu.getUserData();

        assertNotEquals("1000.00", users.get(0).getUserBalance().toString());
    }
}
