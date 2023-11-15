import PageObjectModel.HomePage;
import PageObjectModel.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework23 extends BaseTest {

    String NewPlaylistName = "Updated Homework23";
    String updatedPlaylistMsg = "Updated playlist \"Updated Homework23.\"";


    @Test
    public void renamePlaylist() {

        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        loginPage.provideEmail("noor.alam@testpro.io")
                .providePassword("te$t$tudent")
                .clickSubmitBtn();
        homePage.doubleClickPlaylist()
                .enterNewPlaylistName(NewPlaylistName);
        Assert.assertEquals(homePage.getRenamePlaylistSuccessMsg(), updatedPlaylistMsg);
    }

}