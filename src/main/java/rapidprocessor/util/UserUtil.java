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
			logger.error(e);
		} finally {
			try {
				// Try to lose any open readers
				if (br != null) {
					br.close();
				}

				if (fr != null) {
					fr.close();
				}
			} catch (IOException ioe) {
				logger.error(ioe);
			}
		}

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