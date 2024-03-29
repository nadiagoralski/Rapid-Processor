import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import rapidprocessor.user.User;
import rapidprocessor.util.UserUtil;

public class UsersUtilTest {

    List<User> users = new ArrayList<>();

    BigDecimal testUserBalance;
    UserUtil uu = new UserUtil();

    @Before
    public void setUp() {
        List<User> users = uu.getUserData();

        testUserBalance = users.get(0).getUserBalance();
    }

    @Test
    public void getUserData() {
        UserUtil uu = new UserUtil();

        List<User> userList = uu.getUserData();
        User u = new User(userList.get(0));

        //this.users = uu.getUserData();
        //User u = this.users.get(0);

        assertEquals("admin", u.getUsername());
        assertEquals("AA", u.getUserType().getCode());
        assertEquals("1000.00", u.getUserBalance().toString());
    }

    @Test
    public void updateUserDatabase() {
        //Gets users

        List<User> userList = uu.getUserData();
        User userBefore = new User(userList.get(0));

        testUserBalance = userList.get(0).getUserBalance();

        userList.get(0).setUserBalance(testUserBalance.subtract(BigDecimal.ONE));

        //Updates the user database
        uu.updateUserDatabase(userList);
        List<User> userList2 = uu.getUserData();
        User userAfter = userList2.get(0);

        assertNotEquals(userBefore.getUserBalance(), userAfter.getUserBalance());
    }
}
