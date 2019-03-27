import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import rapidprocessor.user.User;
import rapidprocessor.user.User.UserType;


public class UserTest {

    User user;
    
    @Before
    public void setUp() {
        user = new User("UUUUUUUUUUUUUUU", "AA", 10);
    }

    @Test
    public void User() {
        

        String  name = "UUUUUUUUUUUUUUU",
                str = "UUUUUUUUUUUUUUU AA 000010.00",
                type = "AA",
                balance = "10";

        assertEquals(name, user.getUsername());
        assertEquals(type, user.getUserType().getCode());
        assertEquals(balance, user.getUserBalance().toString());
        assertEquals(str, user.toString());
    }
    
    @Test
    public void testSetUsername() {
        String name = "DDDDDDDDDDDDDDD";

        user.setUsername(name);
        assertEquals(user.getUsername(), name);
    }

    @Test
    public void testSetUserType() {
        UserType type = User.UserType.ADMIN;

        user.setUserType(type);
        assertEquals(type, user.getUserType());
    }
    
    @Test
    public void setUserBalance() {
        int balance = 10;
        
        user.setUserBalance(balance);
        assertEquals(new BigDecimal(10), user.getUserBalance());
    }
}
