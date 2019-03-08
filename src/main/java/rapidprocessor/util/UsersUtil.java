package rapidprocessor.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import rapidprocessor.user.User;


/**
 * users
 */
public class UsersUtil {
	/**
	 * Constructor
	 */
	public UsersUtil() {
		System.out.println("built");
	}


	public User[] getUserData() {
		
		System.out.println("reading file...");
		
		String fileName = "./src/main/java/rapidprocessor/util/users.db",
					line;

		// places all file contents in memory
		File file = new File(fileName);
		String path = file.getPath();
		List<User> users = new ArrayList<User>();

		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			while ( (line = br.readLine()) != null ) {
				
				String userName = line.substring(0, 15);
				String userTypeCode = line.substring(16, 18);
				BigDecimal userBalance = new BigDecimal(line.substring(19, line.length()).trim());

				User user = new User(userName, userTypeCode, userBalance);
				users.add(user);

			}
			
			br.close();

		} catch (Exception e) {
			//TODO: handle exception

			System.out.println(e.toString());
		}

		System.out.println(users);
		
		return users.toArray(new User[ users.size() ]);
	}


}