import static org.junit.Assert.assertEquals;

import java.awt.Transparency;

import org.junit.Test;

import rapidprocessor.transaction.Transaction;
import rapidprocessor.transaction.parser.TransactionParser;
import rapidprocessor.util.TransactionUtil;

public class TransactionUtilTest {

    // @Test
    // public void init() {
    // }

    @Test
    public void testGetParser() {
        TransactionUtil tu = new TransactionUtil();

        TransactionParser tp = tu.getParser(Transaction.TransactionType.ADD_CREDIT);
        String transactionParserClass = "rapidprocessor.transaction.parser.UserTransactionParser";
        // TransactionParser test = (TransactionParser) Class.forName(transactionParserClass).newInstance();
        // tp.parse(fileLine, availableTickets, availableUsers);

        assertEquals(tp, "test");
    }

    @Test
    public void getRefundTransactions() {
    }

    @Test
    public void getTicketTransactions() {
    }

    @Test
    public void getUserTransactions() {
    }
}
