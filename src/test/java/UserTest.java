import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import rapidprocessor.user.User;
import rapidprocessor.user.User.UserType;


public class UserTest {

    User u;

    // sets up methods for all tests within this class
    // public void setUp() {
    //     u = new User("UUUUUUUUUUUUUUU", "AA", 10);
    // }

    @Test
    public void User() {
        
        User u = new User("UUUUUUUUUUUUUUU", "AA", 10);

        String  name = "UUUUUUUUUUUUUUU",
                str = "UUUUUUUUUUUUUUU AA 000010.00",
                type = "AA",
                balance = "10";

        assertEquals(name, u.getUsername());
        assertEquals(type, u.getUserType().getCode());
        assertEquals(balance, u.getUserBalance().toString());
        assertEquals(str, u.toString());
    }
    
    @Test
    public void testSetUsername() {
        User u = new User("UUUUUUUUUUUUUUU", "AA", 10);
        String name = "DDDDDDDDDDDDDDD";

        u.setUsername(name);
        assertEquals(u.getUsername(), name);
    }

    @Test
    public void testSetUserType() {
        User u = new User("UUUUUUUUUUUUUUU", "AA", 10);
        UserType type = User.UserType.ADMIN;

        u.setUserType(type);
        assertEquals(type, u.getUserType());
    }
    
    @Test
    public void setUserBalance() {
        User u = new User("UUUUUUUUUUUUUUU", "AA", 10);
        int balance = 10;
        
        u.setUserBalance(balance);
        assertEquals(new BigDecimal(10), u.getUserBalance());
    }
}
