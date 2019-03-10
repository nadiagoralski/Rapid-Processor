package rapidprocessor.util;

import rapidprocessor.transaction.AddCredit;
import rapidprocessor.transaction.Buy;
import rapidprocessor.transaction.Create;
import rapidprocessor.transaction.Delete;
import rapidprocessor.transaction.EndOfSession;
import rapidprocessor.transaction.Refund;
import rapidprocessor.transaction.Sell;
import rapidprocessor.transaction.Transaction;
import rapidprocessor.user.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * transactionUtil
 */
public class transactionUtil {

	/**
	 * Constructor
	 */
	public transactionUtil() {
		System.out.println("built");
	}


	/**
	 * @param Void
	 * @return List<Transaction>
	 * 
	 * will read file of daily transactions and 
	 */
	public List<Transaction> buildUserTransactionList() {

		System.out.println("reading file...");

		String fileName = "./src/main/java/rapidprocessor/util/transactions.db", line;

		// places all file contents in memory
		File file = new File(fileName);
		List<Transaction> transactions = new ArrayList<Transaction>();

		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			while ((line = br.readLine()) != null) {

				String transactionCode = line.substring(0, 15);
				String userName = line.substring(3, 18);
				String userTypeCode = line.substring(18, 20);
				BigDecimal userBalance = new BigDecimal(line.substring(21, line.length()).trim());

				User user = new User(userName, userTypeCode, userBalance);
				
				if (transactionCode.equals(Constants.CREATE)) {
					
					Create create = Create(user);
					
					transactions.add(create);
				}
				else if ( transactionCode.equals(Constants.DELETE) ) {
					
					Delete delete = new Delete(user);
					
					transactions.add(transactionCode, delete);
				}
				else if ( transactionCode.equals(Constants.ADD_CREDIT) ) {
					
					AddCredit addCredit = new AddCredit(user);
					
					transactions.add(addCredit);
				}
				else {
					
					EndOfSession eof = new EndOfSession(user);

					transactions.add(eof);
				}
			}

			br.close();

		} catch (Exception e) {
			// TODO: handle exception

			System.out.println(e.toString());
		}

		// System.out.println(TicketBatchs);

		return transactions;
	}


	/**
	 * @param Void
	 * @return List<Transaction>
	 * 
	 * will read file of daily transactions and
	 */
	public List<Transaction> buildTicketTransactionList() {

		System.out.println("reading file...");

		String fileName = "./src/main/java/rapidprocessor/util/transactions.db", line;

		// places all file contents in memory
		File file = new File(fileName);
		List<Transaction> transactions = new ArrayList<Transaction>();

		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			while ((line = br.readLine()) != null) {

				String transactionCode = line.substring(0, 2);
				String transactionTicketEventTitle = line.substring(0, 19);
				String transactionTicketSellerName = line.substring(20, 33);
				Integer transactionTicketQty = Integer.parseInt(line.substring(34, 37).trim());
				BigDecimal transactionTicketPrice = new BigDecimal(line.substring(38, line.length()).trim());

				if (transactionCode.equals(Constants.BUY)) {
					
					Buy buy = new Buy(transactionTicketEventTitle, transactionTicketSellerName, transactionTicketQty, 
							transactionTicketPrice);
					transactions.add(buy);
				}
				else if ( transactionCode.equals(Constants.SELL) ) {
					
					Sell sell = new Sell(transactionTicketEventTitle, transactionTicketSellerName, transactionTicketQty, 
					transactionTicketPrice);

					transactions.add(sell);
				}

				
			}

			br.close();

		} catch (Exception e) {
			// TODO: handle exception

			System.out.println(e.toString());
		}

		// System.out.println(TicketBatchs);

		return transactions;
	}


	public List<Transaction> buildRefundTransactionList() {

		System.out.println("reading file...");

		String fileName = "./src/main/java/rapidprocessor/util/transactions.db", line;

		// places all file contents in memory
		File file = new File(fileName);
		List<Transaction> transactions = new ArrayList<Transaction>();

		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			while ((line = br.readLine()) != null) {

				String transactionCode = line.substring(0, 2);
				String transactionUsername = line.substring(0, 19);
				String transactionTicketSellerName = line.substring(20, 33);
				BigDecimal transactionCredit = new BigDecimal(line.substring(38, line.length()).trim());

				if (transactionCode.equals(Constants.REFUND)) {
					
					Refund refund = new Refund(transactionUsername, transactionTicketSellerName, transactionCredit);
					transactions.add(refund);
				}

				
			}

			br.close();

		} catch (Exception e) {
			// TODO: handle exception

			System.out.println(e.toString());
		}

		// System.out.println(TicketBatchs);

		return transactions;
	}
}