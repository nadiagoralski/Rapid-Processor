package rapidprocessor.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TransactionUtil Class
 * Handles transaction file processing
 */
public class TransactionUtil {
	Logger logger = LogManager.getLogger(this.getClass().getName());
	RapidProperties properties = new RapidProperties();
	TicketUtil ticketUtil = new TicketUtil();
	UserUtil userUtil = new UserUtil();
	String currentUserSession = "";

    /*
     * Available Tickets
     */
    List<TicketBatch> availableTickets = new ArrayList<TicketBatch>();
    /*
     * Available Users
     */
    List<User> availableUsers = new ArrayList<User>();
	/*
	 * List of processed session indexes
	 */
	List<Integer> processedSessions = new ArrayList<>();


	/**
	 * Default constructor for TransactionUtil
	 */
	public TransactionUtil() {
	}

    /**
     * Reads in Transaction file to initialize transaction util global variables and
     * @param ticketBatches list of ticket batches
     * @param users list of users
     */
	public void init(List<TicketBatch> ticketBatches, List<User> users) {
		// set ticket batches and users
	    this.availableTickets = ticketBatches;
	    this.availableUsers = users;
	    // build transaction list
		List<Transaction> transactions = buildTransactionList();


		// initialize transaction index and transaction to process sub-list
        int transactionIndex = 0;
		List<Transaction> transactionsToProcess = new ArrayList<>();

		// loop through each session
		while (transactionIndex <= transactions.size()) {
			// find the end of session to determine the current user and sub-list of transaction to process
			for (Transaction transaction : transactionsToProcess) {
				if (transaction.getTransactionType().equals(Transaction.TransactionType.END_OF_SESSION)) {
					// if session hasn't been processed, set username to current user session value
					currentUserSession = ((UserTransaction) transaction).getUsernameVal();

					// get sub-list of transactions
					transactionsToProcess = transactions.subList(transactionIndex, transactions.indexOf(transaction));
					break;
				}
			}

			// process transactions
			for (Transaction transaction : transactionsToProcess) {
				if (Constants.TRANSACTION_REFUND.equals(transaction.getTransactionType().getParseType())) {
					processRefundTransaction((RefundTransaction) transaction);
				} else if (Constants.TRANSACTION_TICKET.equals(transaction.getTransactionType().getParseType())) {
					processTicketTransaction((TicketTransaction) transaction);
				} else if (Constants.TRANSACTION_USER.equals(transaction.getTransactionType().getParseType())) {
					processUserTransaction((UserTransaction) transaction);
				}
			}

			// increment transaction index
			transactionIndex =+ transactionsToProcess.size() + 1;
		}

    }

	/**
	 * Get correct parser based on transaction type
	 * @param transactionType
	 * @return transaction parser object
	 */
	public TransactionParser getParser(Transaction.TransactionType transactionType) {
		String transactionParserClass = "";
		TransactionParser tp = null;

		try {
			// Get class
			switch (transactionType) {
				case BUY:
				case SELL:
					transactionParserClass = "rapidprocessor.transaction.parser.TicketTransactionParser";
					break;
				case ADD_CREDIT:
				case CREATE:
				case DELETE:
				case END_OF_SESSION:
					transactionParserClass = "rapidprocessor.transaction.parser.UserTransactionParser";
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
				tp = (TransactionParser)  Class.forName(transactionParserClass).newInstance();
			}

		} catch (Exception e) {
			logger.error(e);
		}

		return tp;
	}


	/**
	 * @return List<Transaction>
	 * 
	 * will read file of daily transactions and 
	 */
	private List<Transaction> buildTransactionList() {
		logger.info("reading file...");

		// Initialize file name and line variables
		String fileName = properties.getProperty("transaction_filepath");
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
				transactionParser = getParser(Transaction.TransactionType.fromCode(line.substring(0, 2)));


				if (transactionParser != null) {
					transactions.add(transactionParser.parse(line, availableTickets, availableUsers));
				}
			}

		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
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

		return transactions;
	}

	/**
	 * Processes Refund Transactions
	 */
	public void processRefundTransaction(RefundTransaction refundTransaction) {
		// get buyer and seller from available users
		User buyer = availableUsers.stream().filter(user -> refundTransaction.getBuyerNameVal().equals(user.getUsername())).findFirst().orElse(null);
		User seller = availableUsers.stream().filter(user -> refundTransaction.getSellerNameVal().equals(user.getUsername())).findFirst().orElse(null);

		// update account balances
		buyer.setUserBalance(buyer.getUserBalance().add(refundTransaction.getCreditVal()));
		seller.setUserBalance(seller.getUserBalance().subtract(refundTransaction.getCreditVal()));


		// update users in available users list
		availableUsers.stream()
				.map(user -> refundTransaction.getBuyerNameVal().equals(user.getUsername()) ? buyer : user)
				.collect(Collectors.toList());
		availableUsers.stream()
				.map(user -> refundTransaction.getSellerNameVal().equals(user.getUsername()) ? seller : user)
				.collect(Collectors.toList());
	}

	/**
	 * Processes Ticket Transactions
	 */
	public void processTicketTransaction(TicketTransaction ticketTransaction) {
		// get seller from available users and update their account balance
		User seller = availableUsers.stream().filter(user -> ticketTransaction.getSellerNameVal().equals(user.getUsername())).findFirst().orElse(null);

		// get ticket from available tickets
		TicketBatch ticketBatch = availableTickets.stream()
				.filter(ticket -> ticketTransaction.getEventTitleVal().equals(ticket.getEventTitle()) && ticketTransaction.getSellerNameVal().equals(ticket.getSellerName()))
				.findFirst().orElse(null);

		if (Transaction.TransactionType.BUY.equals(ticketTransaction.getTransactionType())) {
			// update buyer credit
			User buyer = availableUsers.stream().filter(user -> currentUserSession.equals(user.getUsername())).findFirst().orElse(null);
			buyer.setUserBalance(buyer.getUserBalance().subtract(ticketTransaction.getPriceVal()));

			//update seller credit
			seller.setUserBalance(seller.getUserBalance().add(ticketTransaction.getPriceVal()));

			// update users in available users list
			availableUsers.stream()
					.map(user -> currentUserSession.equals(user.getUsername()) ? buyer : user)
					.collect(Collectors.toList());
			availableUsers.stream()
					.map(user -> ticketTransaction.getSellerNameVal().equals(user.getUsername()) ? seller : user)
					.collect(Collectors.toList());

			// update ticket quantity
			ticketBatch.setQuantityAvailable(ticketBatch.getQuantityAvailable() - ticketTransaction.getQuantityVal());
		} else if (Transaction.TransactionType.SELL.equals(ticketTransaction.getTransactionType())) {
			// add new ticket batch
			availableTickets.add(new TicketBatch(ticketTransaction.getEventTitleVal(), ticketTransaction.getSellerNameVal(), ticketTransaction.getQuantityVal(), ticketTransaction.getPriceVal()));
		}

		// update ticket in available ticket list
		availableTickets.stream()
				.map(ticket -> ticketTransaction.getEventTitleVal().equals(ticket.getEventTitle()) && ticketTransaction.getSellerNameVal().equals(ticket.getSellerName())
								? ticketBatch : ticket)
				.collect(Collectors.toList());
	}

	/**
	 * Processes User Transactions
	 */
	public void processUserTransaction(UserTransaction userTransaction) {
		if (Transaction.TransactionType.CREATE.equals(userTransaction.getTransactionType())) {
			// add user to the list of users
			availableUsers.add(new User(userTransaction.getUsernameVal(), userTransaction.getUserTypeVal(), userTransaction.getCreditVal()));
		} else if (Transaction.TransactionType.DELETE.equals(userTransaction.getTransactionType())) {
			// remove user from list of users
			availableUsers.removeIf(userObj -> userTransaction.getUsernameVal().equals(userObj.getUsername()));
		} else if (Transaction.TransactionType.ADD_CREDIT.equals(userTransaction.getTransactionType())) {
			// get user from available users
			User user = availableUsers.stream().filter(userObj -> userTransaction.getUsernameVal().equals(userObj.getUsername())).findFirst().orElse(null);
			// add credit value to user account
			user.setUserBalance(user.getUserBalance().add(userTransaction.getCreditVal()));
		}

	}

	/**
	 * @return the availableTickets
	 */
	public List<TicketBatch> getAvailableTickets() {
		return availableTickets;
	}

	/**
	 * @return the availableUsers
	 */
	public List<User> getAvailableUsers() {
		return availableUsers;
	}

}