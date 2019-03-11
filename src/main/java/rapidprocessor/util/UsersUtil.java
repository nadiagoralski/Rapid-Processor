package rapidprocessor.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import rapidprocessor.transaction.Transaction;
import rapidprocessor.user.User;


/**
 * users
 */
public class UsersUtil {
	/**
	 * Default constructor for UserUtil
	 */
	public UsersUtil() {
	}

	/**
	 * used to update ticket database
	 */
	public void updateUserDB(User[] users) {

	}

	public List<User> getUserData() {

		System.out.println("reading file...");

		String fileName = "file/users.db", line;

		// places all file contents in memory
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile();
		List<User> users = new ArrayList<User>();

		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			while ((line = br.readLine()) != null) {
				users.add(new User(line));
			}

			br.close();

		} catch (Exception e) {
			// TODO: handle exception

			System.out.println(e.toString());
		}

		return users;
	}

	private List<User> deleteUser(List<User> users, Transaction transaction) {
		
		for (User user : users) {
			if (user.getUsername().equals(transaction.getUsername())) {
				
				users.remove(user);
				break;
			}
		}
		return users;
	}
	
	private List<User> updateUserCredit(List<User> users, Transaction transaction) {
		
		for (User user : users) {
			if ( user.getUsername().equals(transaction.getUsername() )) {
				
				user.setUserBalance( user.getUserBalance() + transaction.getCreditBalance() );
				break;
			}
		}
		return users;
	}
	/**
	 * 
	 * Simple function to just update the User list from the
	 * daily transaction file
	 * then returns new updated list
	 */
	public List<User> updateUserslList(List<User> users, List<Transaction> transactions) {


		// update users
		for (Transaction transaction : transactions) {
			if ( transaction.getTransactionType().equals("01") ) {
				User user = new User(transaction.getUsername(), transaction.getUserType(), transaction.getUserBalance());
				users.add(user);
			}
			else if ( transaction.getTransactionType().equals("02") ) { // if delete
				users = deleteUser(users, transaction);
			}
			else if ( transaction.getTransactionType().equals("06") ) {
				users = updateUserCredit(users, transaction);
			}
		}
		
		return users;
	}



	/**
	 * Overrites the ticket file with the ticketBatch list
	 */
	public void updateUserDatabase(List<User> users) {
		System.out.println("updating to file");

		String fileName = "./src/main/java/rapidprocessor/util/tickets.db";
		String data = users.toString();
		File file = new File(fileName);

		for (TicketBatch user : users) {
			data += user.toString() + "\n";
		}

		try {
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);

			bw.write(data);
			bw.close();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}