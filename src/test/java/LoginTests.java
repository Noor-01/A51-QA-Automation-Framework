import PageObjectModel.HomePage;
import PageObjectModel.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    @Test
    public void loginValidEmailPasswordTest() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        loginPage.provideEmail("demo@class.com");
        loginPage.providePassword("te$t$tudent");
        loginPage.clickSubmit();

        Assert.assertTrue(homePage.getUserAvatar().isDisplayed());
    }

//    @Test
//    public void loginValidEmailPassword() {
//
//        login();
//        isAvatarDisplayed();
//    }

}
