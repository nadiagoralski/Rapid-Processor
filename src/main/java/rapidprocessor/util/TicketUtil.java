package rapidprocessor.util;

import rapidprocessor.ticketBatch.TicketBatch;
import rapidprocessor.transaction.TicketTransaction;
import rapidprocessor.transaction.Transaction;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
/**
 * TicketUtil
 * Processes
 */
public class TicketUtil {

    /*
     * Lists of tickets to write
     */
    List<TicketBatch> ticketsToWrite = new ArrayList<TicketBatch>();

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
		System.out.println("reading file...");

		String fileName = "file/tickets.db", line;

		// Get and place all file contents in memory
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());
		List<TicketBatch> ticketBatch = new ArrayList<TicketBatch>();

		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			while ((line = br.readLine()) != null) {
				ticketBatch.add(new TicketBatch(line));
			}

			br.close();

		} catch (Exception e) {
			// TODO: handle exception

			System.out.println(e.toString());
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
	private void updateTicketCount(List<TicketBatch> tickets, TicketTransaction transaction, Integer difference) {
		for (TicketBatch ticket : tickets) {

			if (Transaction.TransactionType.BUY.equals(transaction.getTransactionType())) {
				ticket.setQuantityAvailable(ticket.getQuantityAvailable() + difference);
			} else if (Transaction.TransactionType.SELL.equals(transaction.getTransactionType())) {
				ticket.setQuantityAvailable(ticket.getQuantityAvailable() - difference);
			}

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
			// TODO: Implement calculation; placeholder is 5
			updateTicketCount(ticketBatch, transaction, 5);
		}
	}

    /**
     * Ticket Batch file writer
     *
     * Writes out all the updated tickets to a file.
     * @param ticketBatch
     */
	public void updateTicketBatchDatabase(List<TicketBatch> ticketBatch) {
		System.out.println("updating to file");

		String fileName = "file/tickets.db";
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