package rapidprocessor.util;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import rapidprocessor.user.User;


/**
 * UserUtil class
 * Handles user file processing
 */
public class UserUtil {
	Logger logger = LogManager.getLogger(this.getClass().getName());
	RapidProperties properties = new RapidProperties();

	/**
	 * Default constructor for UserUtil
	 */
	public UserUtil() {
	}

	/**
	 * Reads available users from file
	 * @return
	 */
	public List<User> getUserData() {
		logger.info("reading file...");

		String fileName = properties.getProperty("user_account_filepath");
		String line;

		// places all file contents in memory
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());
		List<User> users = new ArrayList<User>();

		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);

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
<<<<<<< HEAD
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
=======
		} finally {
			try {
				// Try to lose any open readers
				if (br != null) {
					br.close();
>>>>>>> 6c643ba5dabadd36b774ec3e6b88159c8f2690d9
				}

<<<<<<< HEAD
		for (UserTransaction transaction : transactions) {
			String username = transaction.getUsernameVal();
			// Only update if the user was not deleted

			if (!deletedUsers.contains(username)) {
				if (Transaction.TransactionType.CREATE.equals(transaction.getTransactionType())) {
					// Write new users to file
					users.add(new User(username, transaction.getUserTypeVal(), transaction.getCreditVal()));
					usersToWrite.add(new User(username, transaction.getUserTypeVal(), transaction.getCreditVal()));
				} else if (Transaction.TransactionType.ADD_CREDIT.equals(transaction.getTransactionType())) {
					// Update existing users balance
					for (User user : users) {
						if (user.getUsername().equals(username)) {
							user.setUserBalance(user.getUserBalance().add(transaction.getCreditVal()));
							users.add(user);
							usersToWrite.add(user);
						}
					}
=======
				if (fr != null) {
					fr.close();
>>>>>>> 6c643ba5dabadd36b774ec3e6b88159c8f2690d9
				}
			} catch (IOException ioe) {
				logger.error(ioe);
			}
		}
<<<<<<< HEAD
		// not entering loop for some reason
		// users.get(0).setUserBalance(users.get(0).getUserBalance().add(transactions.get(0).getCreditVal()));
=======

>>>>>>> 6c643ba5dabadd36b774ec3e6b88159c8f2690d9
		return users;
	}

	/**
	 * User file writer
	 *
	 * Writes out all the updated users to a file.
	 * @param usersToWrite list of users to write
	 */
	public void updateUserDatabase(List<User> usersToWrite) {
		logger.info("updating to file");

		String fileName = properties.getProperty("user_account_filepath");
		StringBuilder data = new StringBuilder();

		// Get and place all file contents in memory
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());

		for (User user : usersToWrite) {
			data.append(user.toString()).append("\n");
		}

		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);

			bw.write(data.toString());
			bw.close();

		} catch (Exception e) {
			logger.error(e);
			// TODO: handle exception
		} finally {
			try {
				// Try to lose any open writers
				if (bw != null) {
					bw.close();
				}

				if (fw != null) {
					fw.close();
				}
			} catch (IOException ioe) {
				logger.error(ioe);
			}
		}
	}

}