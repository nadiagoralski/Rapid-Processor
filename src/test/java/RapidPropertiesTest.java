import static org.junit.Assert.assertEquals;

import org.junit.Test;

import rapidprocessor.util.RapidProperties;


/**
 * RapidPropertiesTest
 */
public class RapidPropertiesTest {

	

	@Test
	public void testRapidProperties() {

		RapidProperties rp = new RapidProperties();

		String filePath = rp.getProperty("available_tickets_filepath");
		String filePath2 = rp.getProperty("user_account_filepath");
		String filePath3 = rp.getProperty("transaction_filepath");

		assertEquals("file/tickets.db", filePath);
		assertEquals("file/users.db", filePath2);
		assertEquals("file/transactions.db", filePath3);

	}
}