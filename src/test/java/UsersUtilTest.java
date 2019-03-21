import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import rapidprocessor.transaction.Transaction;
import rapidprocessor.transaction.UserTransaction;
import rapidprocessor.transaction.Transaction.TransactionType;
import rapidprocessor.user.User;
import rapidprocessor.util.UserUtil;

public class UsersUtilTest {

    List<User> users = new ArrayList<>();

    @Test
    public void getUserData() {
        UserUtil uu = new UserUtil();

        this.users = uu.getUserData();
        User u = this.users.get(0);
        
        assertEquals("admin", u.getUsername());
        assertEquals("AA", u.getUserType().getCode());
        assertEquals("1000.00", u.getUserBalance().toString());
    }

    @Test
    public void updateUsersList() {
        UserUtil uu = new UserUtil();
        List<UserTransaction> transactionList = new ArrayList<>();
        
        this.users = uu.getUserData();
        
        UserTransaction transaction = new UserTransaction(TransactionType.ADD_CREDIT, users.get(0), new BigDecimal(990));
        
        
        transactionList.add(transaction);
        
        this.users = uu.updateUsersList(users, transactionList);
        assertEquals("1990.00", users.get(0).getUserBalance().toString());
    }

    @Test
    public void updateUserDatabase() {

        UserUtil uu = new UserUtil();
        List<UserTransaction> transactionList = new ArrayList<>();

        this.users = uu.getUserData();

        UserTransaction transaction = new UserTransaction(TransactionType.ADD_CREDIT, users.get(0), new BigDecimal(990));
        
        transactionList.add(transaction);
        
        this.users = uu.updateUsersList(users, transactionList);
        
        uu.updateUserDatabase();
        this.users = uu.getUserData();

        assertNotEquals("1000.00", users.get(0).getUserBalance().toString());
    }
}
