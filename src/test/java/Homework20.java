import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework20 extends BaseTest {

    @Test
    public void deletePlaylist() {

        String confirmationMessage = "Deleted playlist \"Homework19.\"";

        provideEmail("noor.alam@testpro.io");
        providePassword("te$t$tudent");
        clickSubmit();
        clickPlaylist();
        clickDeletePlaylistBtn();
        Assert.assertEquals(deletedPlaylistConfirmationMessage(), confirmationMessage);
    }
}

