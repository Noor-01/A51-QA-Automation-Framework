import PageObjectModel.HomePage;
import PageObjectModel.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework22 extends BaseTest {

    String NewPlaylistName = "Updated Homework22";
    String updatedPlaylistMsg = "Updated playlist \"Updated Homework22.\"";
    @Test
    public void renamePlayList() {

        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        loginPage.login();
        homePage.doubleClickPlaylist();
        homePage.enterNewPlaylistName(NewPlaylistName);
        Assert.assertEquals(getRenamePlaylistSuccessMsg(), updatedPlaylistMsg);

     }
}
