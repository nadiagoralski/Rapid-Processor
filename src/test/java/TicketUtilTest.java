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
        TicketBatch tu = new TicketBatch();
        List<UserTransaction> transactionList = new ArrayList<>();

        this.users = uu.getUserData();

        UserTransaction transaction = new UserTransaction(TransactionType.ADD_CREDIT, users.get(0),
                new BigDecimal(990));

        transactionList.add(transaction);

        this.users = uu.updateUsersList(users, transactionList);
        assertEquals("1990.00", users.get(0).getUserBalance().toString());
    }

    @Test
    public void updateTicketBatchDatabase() {

        TicketBatch tu = new TicketBatch();
        List<UserTransaction> transactionList = new ArrayList<>();

        this.users = uu.getUserData();

        UserTransaction transaction = new UserTransaction(TransactionType.ADD_CREDIT, users.get(0),
                new BigDecimal(990));

        transactionList.add(transaction);

        this.users = uu.updateUsersList(users, transactionList);

        uu.updateUserDatabase();
        this.users = uu.getUserData();

        assertNotEquals("1000.00", users.get(0).getUserBalance().toString());
    }
}
