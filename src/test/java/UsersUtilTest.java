import static org.junit.Assert.assertArrayEquals;

import java.awt.List;

import org.junit.Test;

import rapidprocessor.user.User;
import rapidprocessor.util.UsersUtil;

public class UsersUtilTest {


    @Test
    public void getUserData(){
        UsersUtil uu = new UsersUtil();

        List<User> ExpectedData = new
        List<User> data = uu.getUserData();

        
        assertArrayEquals(ExpectedData, data);
    }

    @Test
    public void updateUsersList() {

    }

    @Test
    public void updateUserDatabase() {

    }
}
