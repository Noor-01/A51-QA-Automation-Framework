import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework17 extends BaseTest {

    @Test
    public void addSongToPlaylist() throws InterruptedException {

        String expectedSongAddedMessage = "Added 1 song into \"Homework17.\"";

        navigateToPage();
        provideEmail("noor.alam@testpro.io");
        providePassword("te$t$tudent");
        clickSubmit();
        Thread.sleep(2000);
        searchForSong("Dark Days");
        ViewAllButton();
        selectFirstSong();
        clickAddToButton();
        playlistSelection();

        Assert.assertEquals(getAddToPlaylistSuccessMsg(), expectedSongAddedMessage);

     }
}
