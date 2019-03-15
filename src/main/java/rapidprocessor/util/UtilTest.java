package rapidprocessor.util;

import java.util.List;
import java.math.BigDecimal;
import java.util.ArrayList;

import rapidprocessor.ticketBatch.TicketBatch;
import rapidprocessor.transaction.TicketTransaction;
import rapidprocessor.transaction.Transaction;

/**
 * UserTest
 */
public class UtilTest {

	public static void main(String[] args) {

		// UsersUtil u = new UsersUtil();
		TicketUtil t = new TicketUtil();

		TicketBatch newTicket = new TicketBatch("Test2              ", "STDuser      ", 99, new BigDecimal(11.00));
		List<TicketTransaction> transactions = new ArrayList<TicketTransaction>();
		// transactions.add("03 New Event           new           002 001.00");

		// User[] users = u.getUserData();
		List<TicketBatch> tickets = t.getTicketBatchData();

		t.updateTicketBatch(tickets, transactions);

		t.updateTicketBatchDatabase();

		System.out.println(tickets.toString());
	}
}
