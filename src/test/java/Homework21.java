import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework21 extends BaseTest {

    @Test
    public void renamePlayList() {

        login();
        doubleClickPlaylist();
        enterNewPlaylistName();
        Assert.assertEquals(getRenamePlaylistSuccessMsg(), updatedPlaylistMsg);
    }
}