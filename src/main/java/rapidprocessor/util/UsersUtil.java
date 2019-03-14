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
import rapidprocessor.transaction.UserTransaction;
import rapidprocessor.user.User;


/**
 * UserUtil class
 * Handles user file processing
 */
public class UsersUtil {
	/*
	 * Lists of users to write
	 */
	List<User> usersToWrite = new ArrayList<User>();

	/*
	 * Lists of deleted users
	 * to ignore when writing new users file
	 */
	List<String> deletedUsers = new ArrayList<String>();

	/**
	 * Default constructor for UserUtil
	 */
	public UsersUtil() {
	}

	public List<User> getUserData() {
		System.out.println("reading file...");

		String fileName = "file/users.db", line;

		// places all file contents in memory
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());
		List<User> users = new ArrayList<User>();

		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			while ((line = br.readLine()) != null) {
				String username = StringUtils.trimToEmpty(line.substring(0, Constants.MAX_USERNAME_LENGTH));
				String userType = StringUtils.trimToEmpty(line.substring(16, 18));
				BigDecimal userBalance = new BigDecimal(line.substring(19, line.length()).trim());
				users.add(new User(username, userType, userBalance));
			}

			br.close();

		} catch (Exception e) {
			// TODO: handle exception

			System.out.println(e.toString());
		}

		return users;
	}


	/**
	 * 
	 * Simple function to just update the User list from the
	 * daily transaction file
	 * then returns new updated list
	 */
	public List<User> updateUsersList(List<User> users, List<UserTransaction> transactions) {


		// update users
		// Find all deleted users
		for (UserTransaction transaction : transactions) {
			if (Transaction.TransactionType.DELETE.equals(transaction.getTransactionType())) {
				String username = transaction.getUsernameVal();
				User user = users.stream().filter(userObj -> username.equals(userObj.getUsername())).findFirst().orElse(null);

				if (user != null) {
					// if user found, add to deleted user lis
					deletedUsers.add(user.getUsername());
				}
			}
		}

		for (UserTransaction transaction : transactions) {
			String username = transaction.getUsernameVal();
			// Only update if the user was not deleted

			if (!deletedUsers.contains(username)) {
				if (Transaction.TransactionType.CREATE.equals(transaction.getTransactionType())) {
					// Write new users to file
					usersToWrite.add(new User(username, transaction.getUserTypeVal(), transaction.getUserBalanceVal()));
				} else if (Transaction.TransactionType.ADD_CREDIT.equals(transaction.getTransactionType())) {
					// Update existing users balance
					for (User user : users) {
						if (user.getUsername().equals(username)) {
							user.setUserBalance(user.getUserBalance().add(transaction.getUserBalanceVal()));
							usersToWrite.add(user);
						}
					}
				}
			}
		}
		
		return users;
	}



	/**
	 * User file writer
	 *
	 * Writes out all the updated users to a file.
	 */
	public void updateUserDatabase() {
		System.out.println("updating to file");

		String fileName = "file/user.db";
		StringBuilder data = new StringBuilder();

		// Get and place all file contents in memory
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());

		for (User user : usersToWrite) {
			data.append(user.toString()).append("\n");
		}

		try {
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);

			bw.write(data.toString());
			bw.close();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}