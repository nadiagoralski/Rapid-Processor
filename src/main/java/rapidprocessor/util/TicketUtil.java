package rapidprocessor.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import rapidprocessor.ticketBatch.TicketBatch;
import rapidprocessor.transaction.TicketTransaction;
import rapidprocessor.transaction.Transaction;
import rapidprocessor.user.User;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * TicketUtil
 * Handles ticket file processing
 */
public class TicketUtil {
	Logger logger = LogManager.getLogger(this.getClass().getName());
	RapidProperties properties = new RapidProperties();

    /*
     * Lists of tickets to write
     */
    List<TicketBatch> ticketsToWrite = new ArrayList<TicketBatch>();

	/*
	 * List of available users
	 */
	List<User> users = new ArrayList<User>();

    /**
	 * Default constructor for TicketUtil
	 */
	public TicketUtil() {
	}

    /**
     * Reads current ticket batch file and parses them into a TicketBatch object
     * @return list of TicketBatch objects
     */
	public List<TicketBatch> getTicketBatchData() {
		logger.info("reading file...");

		String fileName = properties.getProperty("available_tickets_filepath");
		String line;

		// Get and place all file contents in memory
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());
		List<TicketBatch> ticketBatch = new ArrayList<TicketBatch>();

		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			while ((line = br.readLine()) != null) {
				String eventTitle = StringUtils.trimToEmpty(line.substring(0, Constants.MAX_EVENT_TITLE_LENGTH - 1));
				String sellerName = StringUtils.trimToEmpty(line.substring(26, 26+Constants.MAX_USERNAME_LENGTH));
				Integer quantityAvailable = Integer.parseInt(line.substring(42, 45));
				BigDecimal price = new BigDecimal(line.substring(46).trim());
				ticketBatch.add(new TicketBatch(eventTitle, sellerName, quantityAvailable, price));
			}

			br.close();

		} catch (Exception e) {
			//TODO: update error handling
			logger.error(e);
		}

		return ticketBatch;
	}


    /**
     * Updates the ticket count depending on transaction type
     * and adds the ticket item to the list of tickets to write
     *
     * If no transaction - keep ticket object as is
     * @param tickets
     * @param transaction
     * @param difference
     */
	public void updateTicketCount(List<TicketBatch> tickets, TicketTransaction transaction, Integer difference) {
		for (TicketBatch ticket : tickets) {

			if (Transaction.TransactionType.BUY.equals(transaction.getTransactionType())) {
				ticket.setQuantityAvailable(ticket.getQuantityAvailable() - difference);
						//transaction.getTicketBatch().getQuantityAvailable());
			}


//			} else if (Transaction.TransactionType.SELL.equals(transaction.getTransactionType())) {
//				ticket.setQuantityAvailable(ticket.getQuantityAvailable() - difference);
//			}

			ticketsToWrite.add(ticket);
		}
	}

    /**
     * Simple function to just update the ticketBatch list from the daily
     * transaction file then returns new updated list
     * @param ticketBatch
     * @param transactions
     */
	public void updateTicketBatch(List<TicketBatch> ticketBatch, List<TicketTransaction> transactions) {

		for (TicketTransaction transaction : transactions) {

			//updateTicketCount(ticketBatch, transaction, 5);
		}
	}

    /**
     * Ticket Batch file writer
     *
     * Writes out all the updated tickets to a file.
     */
	public void updateTicketBatchDatabase() {
		System.out.println("updating to file");


		String fileName = properties.getProperty("available_tickets_filepath");
		StringBuilder data = new StringBuilder();

		// Get and place all file contents in memory
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());

		for (TicketBatch ticket : ticketsToWrite) {
			data.append(ticket.toString()).append("\n");
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