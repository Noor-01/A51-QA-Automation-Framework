import PageObjectModel.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    @Test    //Page Factory:
    public void loginValidCredentials() {

        LoginPage loginPage = new LoginPage(getDriver());

        loginPage.provideEmail("demo@class.com")
                .providePassword("te$t$tudent")
                .clickSubmitBtn();

        Assert.assertTrue(loginPage.isAvatarDisplayed());

    }
    @Test    //Page Factory:
    public void loginInValidCredentials() {

        LoginPage loginPage = new LoginPage(getDriver());

        loginPage.provideEmail("dlfjs@dfskl.com")
                .providePassword("te$t$tudent")
                .clickSubmitBtn();

        Assert.assertTrue(loginPage.isAvatarDisplayed());

    }
}
