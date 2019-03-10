package rapidprocessor.util;

import rapidprocessor.ticketBatch.TicketBatch;
import rapidprocessor.transaction.Transaction;

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
 */
public class TicketUtil {

	/**
	 * Constructor
	 */
	public TicketUtil() {
		System.out.println("built");
	}
	
	

	public List<TicketBatch> getTicketBatchData() {

		System.out.println("reading file...");

		String fileName = "./src/main/java/rapidprocessor/util/tickets.db", line;
		
		// places all file contents in memory
		File file = new File(fileName);
		List<TicketBatch> TicketBatch = new ArrayList<TicketBatch>();

		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			while ((line = br.readLine()) != null) {

				String TicketEventTitle = line.substring(0, 19);
				String TicketSellerName = line.substring(20, 33);
				Integer TicketQty = Integer.parseInt(line.substring(34, 37).trim());
				BigDecimal TicketPrice = new BigDecimal(line.substring(38, line.length()).trim());

				TicketBatch Ticket = new TicketBatch(TicketEventTitle, TicketSellerName, TicketQty, TicketPrice);
				
				TicketBatch.add(Ticket);

			}

			br.close();

		} catch (Exception e) {
			// TODO: handle exception

			System.out.println(e.toString());
		}

		// System.out.println(TicketBatchs);

		return TicketBatch;
	}



	private List<TicketBatch> updateTicketCount(List<TicketBatch> tickets, Transaction transaction) {

		for (TicketBatch ticket : tickets) {
			boolean isTicket = ticket.getSellerName().equals(transactions.getSellerName())
					&& ticket.getEventTitle().equals(transactions.getEventTitle());

			if (isTicket && transaction.getTransactionType() == Constants.BUY) {

				ticket.setQuantityAvailable(ticket.getQuantityAvailable() + transaction.getQuantityAvailable());
				break;
			} else {
				ticket.setQuantityAvailable(ticket.getQuantityAvailable() - transaction.getQuantityAvailable());
				break;
			}

		}
		return tickets;
	}

	/**
	 * 
	 * Simple function to just update the ticketBatch list from the daily
	 * transaction file then returns new updated list
	 */
	public List<TicketBatch> updateTicketBatch(List<TicketBatch> ticketBatch, List<Transaction> transactions) {

		for (Transaction transaction : transactions) {
			ticketBatch = updateTicketCount(ticketBatch, transaction);
		}

		return ticketBatch;
	}



	public void updateTicketBatchDatabase(List<TicketBatch> ticketBatch) {
		System.out.println("updating to file");

		String fileName = "./src/main/java/rapidprocessor/util/tickets.db";
		String data = ticketBatch.toString();
		File file = new File(fileName);

		for (TicketBatch ticket : ticketBatch) {
			data += ticket.toString() + "\n";
		}

		try {
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);

			bw.write(data);
			bw.close();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}