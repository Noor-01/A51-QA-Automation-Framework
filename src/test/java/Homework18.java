import org.testng.annotations.Test;

public class Homework18 extends BaseTest {

    @Test
    public void playSong() throws InterruptedException {

        navigateToPage();
        provideEmail("demo@class.com");
        providePassword("te$t$tudent");
        clickSubmit();
        Thread.sleep(2000);
        clickPlayNextSongButton();
        clickPlaySongButton();
        Thread.sleep(2000);
        isSoundBarsDisplayed();
    }
}