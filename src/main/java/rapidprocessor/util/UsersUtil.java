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

	/**
	 * used to update ticket database
	 */
	public void updateUserDB(User[] users) {
		//TODO: Implement
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
	public List<User> updateUserslList(List<User> users, List<UserTransaction> transactions) {


		// update users
		//TODO: Clean this up

		// Find all deleted users
		for (UserTransaction transaction : transactions) {
			if (transaction.getTransactionType().equals("02") ) {
				// if delete
				for (User user : users) {
					if (user.getUsername().equals(transaction.getUsername())) {
						// Add username to list of deleted users
						deletedUsers.add(user.getUsername());
					}
				}
			}
		}

		for (UserTransaction transaction : transactions) {
			// Only update if the user was not deleted
			if (!deletedUsers.contains(transaction.getUsername())) {
				if (transaction.getTransactionType().equals("01")) {
					usersToWrite.add(new User(transaction.getUsername(), transaction.getUserType(), transaction.getUserBalance()));
				} else if (transaction.getTransactionType().equals("06")) {
					for (User user : users) {
						if (user.getUsername().equals(transaction.getUsername())) {

							user.setUserBalance(user.getUserBalance() + transaction.getCreditBalance());
							usersToWrite.add(user);
						}
					}
				}
			}
		}
		
		return users;
	}



	/**
	 * Overrites the ticket file with the ticketBatch list
	 */
	public void updateUserDatabase(List<User> users) {
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