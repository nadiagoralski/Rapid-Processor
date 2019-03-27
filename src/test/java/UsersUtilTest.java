import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import rapidprocessor.user.User;
import rapidprocessor.util.UserUtil;

public class UsersUtilTest {

    List<User> users = new ArrayList<>();

    @Test
    //Not working
    public void getUserData() {
        UserUtil uu = new UserUtil();

        this.users = uu.getUserData();
        User u = this.users.get(0);

        assertEquals("admin", u.getUsername());
        assertEquals("AA", u.getUserType().getCode());
        assertEquals("1000.00", u.getUserBalance().toString());
    }

    @Test
    public void updateUserDatabase() {

        //Gets users
        UserUtil uu = new UserUtil();

        List<User> userList = uu.getUserData();
        User ub4 = new User(userList.get(0));
        userList.get(0).setUsername("Nick");

        //Updates the user database
        uu.updateUserDatabase(userList);
        List<User> userList2 = uu.getUserData();
        User ub5 = userList2.get(0);

        assertNotEquals(ub4.getUsername(), ub5.getUsername());

    }
}
