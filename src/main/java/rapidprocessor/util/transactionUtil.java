package rapidprocessor.util;

import rapidprocessor.ticketBatch.TicketBatch;
import rapidprocessor.transaction.RefundTransaction;
import rapidprocessor.transaction.TicketTransaction;
import rapidprocessor.transaction.Transaction;
import rapidprocessor.transaction.UserTransaction;
import rapidprocessor.transaction.parser.TransactionParser;
import rapidprocessor.user.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * TransactionUtil Class
 * Handles transaction file processing
 */
public class TransactionUtil {
    /**
     * Available Tickets
     */
    List<TicketBatch> availableTickets = new ArrayList<TicketBatch>();
    /**
     * Available Users
     */
    List<User> availableUsers = new ArrayList<User>();


    /**
     * Transaction Type lists
     */
    List<RefundTransaction> refundTransactions = new ArrayList<RefundTransaction>();
		List<TicketTransaction> ticketTransactions = new ArrayList<TicketTransaction>();
		List<UserTransaction> userTransactions = new ArrayList<UserTransaction>();
	/**
	 * Default constructor for TransactionUtil
     * Initialize global variables
	 */
	public TransactionUtil() {
        availableTickets = new ArrayList<TicketBatch>();
        availableUsers = new ArrayList<User>();
        refundTransactions = new ArrayList<RefundTransaction>();
        ticketTransactions = new ArrayList<TicketTransaction>();
        userTransactions = new ArrayList<UserTransaction>();
	}

    /**
     * Reads in Transaction file to initialize transaction util global variables and
     * @param ticketBatches
     * @param users
     */
	public void init(List<TicketBatch> ticketBatches, List<User> users) {
	    this.availableTickets = ticketBatches;
	    this.availableUsers = users;

        List<Transaction> transactions = buildTransactionList();
        for (Transaction transaction : transactions) {
            if (Constants.TRANSACTION_REFUND.equals(transaction.getTransactionType().getParseType())) {
                refundTransactions.add((RefundTransaction) transaction);
            } else if (Constants.TRANSACTION_TICKET.equals(transaction.getTransactionType().getParseType())) {
                ticketTransactions.add((TicketTransaction) transaction);
            } else if (Constants.TRANSACTION_USER.equals(transaction.getTransactionType().getParseType())) {
                userTransactions.add((UserTransaction) transaction);
            }
        }
    }

	/**
	 * Get correct parser based on transaction type
	 * @param transactionType
	 * @return transaction parser object
	 */
	public static TransactionParser getParser(Transaction.TransactionType transactionType) {
		String transactionParserClass = "";
		TransactionParser tp = null;

		try {
			// Get class
			switch (transactionType) {
				case BUY:
				case SELL:
					transactionParserClass = "rapidprocessor.transaction.parser.TicketBuySellParser";
					break;
				case ADD_CREDIT:
				case CREATE:
				case DELETE:
				case END_OF_SESSION:
					transactionParserClass = "rapidprocessor.transaction.parser.UserAccountParser";
					break;
				case REFUND:
					transactionParserClass = "rapidprocessor.transaction.parser.RefundTransactionParser";
					break;
				default:
					transactionParserClass = null;
					break;
			}

			if (transactionParserClass != null) {
				// If there was a class match, create the Transaction Parser
				Class classRef = Class.forName(transactionParserClass);
				tp = (TransactionParser) classRef.newInstance();
			}

		} catch (Exception e) {
			// TODO: Handle more specific exceptions
			e.printStackTrace();
		}

		return tp;
	}


	/**
	 * @return List<Transaction>
	 * 
	 * will read file of daily transactions and 
	 */
	private List<Transaction> buildTransactionList() {

		System.out.println("reading file...");

		// Initialize file name and line variables
		String fileName = "file/transactions.db";
		String line;

		// Get and place all file contents in memory
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());

		List<Transaction> transactions = new ArrayList<Transaction>();
		TransactionParser transactionParser  = null ;
		FileReader fr  = null;
		BufferedReader br = null;

		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);

			// Read each line of the file and create the Transaction objects
			while ((line = br.readLine()) != null) {
				String transactionCode = line.substring(0, 15);
				String userName = line.substring(3, 18);
				String userTypeCode = line.substring(18, 20);
				BigDecimal userBalance = new BigDecimal(line.substring(21, line.length()).trim());

				User user = new User(userName, userTypeCode, userBalance);
				transactionParser = getParser(Transaction.TransactionType.fromCode(transactionCode));


				if (transactionParser != null) {
					transactions.add(transactionParser.parse(line, availableTickets, availableUsers));
				}
			}

		} catch (Exception e) {
			// TODO: handle exception

			System.out.println(e.toString());
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
				// TODO: handle exception
				ioe.printStackTrace();
			}
		}

		return transactions;
	}

    /**
     * Return list of Refund Transactions
     * @return refundTransactions
     */
	public List<RefundTransaction> getRefundTransactions() {
		return refundTransactions;
	}

    /**
     * Return list of Ticket Transactions
     * @return ticketTransactions
     */
	public List<TicketTransaction> getTicketTransactions() {
		return ticketTransactions;
	}

    /**
     * Return list of User Transactions
     * @return userTransactions
     */
	public List<UserTransaction> getUserTransactions() {
		return userTransactions;
	}
}