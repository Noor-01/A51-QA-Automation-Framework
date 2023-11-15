import PageObjectModel.HomePage;
import PageObjectModel.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {
    @Test
    public void loginValidEmailPassword() {

        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        loginPage.provideEmail("demo@class.com")
                 .providePassword("te$t$tudent")
                 .clickSubmitBtn();

        Assert.assertTrue(homePage.getUserAvatar().isDisplayed());
    }
}
