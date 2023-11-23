import PageObjectModel.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework24 extends BaseTest{

    @Test
    public void loginValidEmailPassword() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.provideEmail("demo@class.com")
                .providePassword("te$t$tudent")
                .clickSubmitBtn();

        Assert.assertTrue(loginPage.isAvatarDisplayed());
    }
}
