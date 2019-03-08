package rapidprocessor.util;

import rapidprocessor.ticketBatch.TicketBatch;
import rapidprocessor.user.User;
import rapidprocessor.util.UsersUtil;

/**
 * UserTest
 */
public class UtilTest {

	public static void main(String[] args) {

		UsersUtil u = new UsersUtil();
		TicketUtil t = new TicketUtil();

		// User[] users =  u.getUserData();
		TicketBatch[] tickets =  t.getTicketBatchData();

		// System.out.println(users[0]);
	}
}
