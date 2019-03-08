package rapidprocessor.util;

import rapidprocessor.ticketBatch.TicketBatch;
import java.io.BufferedReader;
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

	public TicketBatch[] getTicketBatchData() {

		System.out.println("reading file...");

		String fileName = "./src/main/java/rapidprocessor/util/tickets.db", line;
		
		// places all file contents in memory
		File file = new File(fileName);
		List<TicketBatch> TicketBatchs = new ArrayList<TicketBatch>();

		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			while ((line = br.readLine()) != null) {

				String TicketBatchEventTitle = line.substring(0, 19);
				String TicketBatchSellerName = line.substring(20, 33);
				Integer TicketBatchQty = Integer.parseInt(line.substring(34, 37).trim());
				BigDecimal TicketBatchPrice = new BigDecimal(line.substring(38, line.length()).trim());

				TicketBatch TicketBatch = new TicketBatch(TicketBatchEventTitle, TicketBatchSellerName, TicketBatchQty, TicketBatchPrice);
				
				TicketBatchs.add(TicketBatch);

			}

			br.close();

		} catch (Exception e) {
			// TODO: handle exception

			System.out.println(e.toString());
		}

		System.out.println(TicketBatchs);

		return TicketBatchs.toArray(new TicketBatch[TicketBatchs.size()]);
	}
}