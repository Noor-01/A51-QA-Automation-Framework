import PageObjectModel.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    @Test
    public void loginValidEmailPassword() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.provideEmail("demo@class.com")
                .providePassword("te$t$tudent")
                .clickSubmitBtn();

        Assert.assertTrue(loginPage.isAvatarDisplayed());

    }
}
