package rapidprocessor.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import rapidprocessor.ticketBatch.TicketBatch;

import java.io.*;
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

		BufferedReader br = null;
		FileReader fr = null;
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);

			while ((line = br.readLine()) != null) {
				// Parse the file line and add TicketBatch object to list of available tickets
				String eventTitle = StringUtils.trimToEmpty(line.substring(0, Constants.MAX_EVENT_TITLE_LENGTH - 1));
				String sellerName = StringUtils.trimToEmpty(line.substring(26, 26+Constants.MAX_USERNAME_LENGTH));
				Integer quantityAvailable = Integer.parseInt(line.substring(42, 45));
				BigDecimal price = new BigDecimal(line.substring(46).trim());
				ticketBatch.add(new TicketBatch(eventTitle, sellerName, quantityAvailable, price));
			}

			// close the buffer
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
		return ticketBatch;
	}


	/**
	 * Ticket Batch file writer
	 *
	 * Writes out all the updated tickets to a file.
	 * @param ticketsToWrite list of tickets to write to file
	 */
	public void updateTicketBatchDatabase(List<TicketBatch> ticketsToWrite) {
		logger.info("updating to file");

		String fileName = properties.getProperty("available_tickets_filepath");
		StringBuilder data = new StringBuilder();

		// Get and place all file contents in memory
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());

		for (TicketBatch ticket : ticketsToWrite) {
			data.append(ticket.toString()).append("\n");
		}
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);

			// write to file
			bw.write(data.toString());

			// close writer
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