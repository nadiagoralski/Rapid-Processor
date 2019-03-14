package rapidprocessor.util;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.w3c.dom.UserDataHandler;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class UsersUtilTest {
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(UsersUtil.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void getUserData(){

    }

    @Test
    public void updateUsersList() {

    }

    @Test
    public void updateUserDatabase() {

    }
}
