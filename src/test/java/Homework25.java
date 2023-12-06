import PageObjectModel.HomePage;
import PageObjectModel.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework25 extends BaseTest {

    String NewPlaylistName = "Updated Homework25";
    String UpdatedPlaylistMsg = "Updated playlist \"Updated Homework25.\"";

    @Test
    public void renamePlaylist() {

        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(getDriver());

        loginPage.provideEmailToLogin("noor.alam@testpro.io");
        loginPage.providePasswordToLogin("te$t$tudent");
        loginPage.clickSubmitToLogin();
        homePage.doubleClickPlaylist();
        homePage.enterNewPlaylistName(NewPlaylistName);
        Assert.assertEquals(homePage.getRenamePlaylistSuccessMsg(), UpdatedPlaylistMsg);
    }

    @Test    //Page Factory:
    public void loginValidEmailPassword() {

        LoginPage loginPage = new LoginPage(getDriver());

        loginPage.provideEmail("demo@class.com")
                .providePassword("te$t$tudent")
                .clickSubmitBtn();

        Assert.assertTrue(loginPage.isAvatarDisplayed());

    }
}
