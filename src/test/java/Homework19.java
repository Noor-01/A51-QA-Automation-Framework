import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework19 extends BaseTest {

    @Test
    public void deletePlaylist() throws InterruptedException {

        String confirmationMessage = "Deleted playlist \"Homework19.\"";

        navigateToPage();
        provideEmail("noor.alam@testpro.io");
        providePassword("te$t$tudent");
        clickSubmit();
        Thread.sleep(2000);
        clickPlaylist();
        Thread.sleep(2000);
        clickDeletePlaylistBtn();
        Thread.sleep(2000);
        Assert.assertEquals(deletedPlaylistConfirmationMessage(), confirmationMessage);

    }
}

