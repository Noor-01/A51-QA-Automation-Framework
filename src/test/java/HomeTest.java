import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.swing.*;
import java.util.List;

public class HomeTest extends BaseTest {

    //Tests start here
    @Test
    public void playSongWithContextClick() {
        //Login
        provideEmail("demo@class.com");
        providePassword("te$t$tudent");
        clickSubmit();
        isAvatarDisplayed();

        //Choose All Songs List
        chooseAllSongsList();
        //Right/Context Click
        contextClickFirstSong();
        //Choose Play
        choosePlayOption();
        //Assertion
        Assert.assertTrue(isSongPlaying());
    }
    @Test
    public void hoverOverPlayBtn() throws InterruptedException {

        //Login
        provideEmail("demo@class.com");
        providePassword("te$t$tudent");
        clickSubmit();
        Thread.sleep(2000);
        //Hover
        //Assertion
        Assert.assertTrue(hoverOverPlay().isDisplayed());
    }
    @Test
    public void countPlaylistSongs() throws InterruptedException {
        //Login
        provideEmail("noor.alam@testpro.io");
        providePassword("te$t$tudent");
        clickSubmit();
        Thread.sleep(2000);
        //Choose playlist by name
        choosePlaylistByName("Melody");
        Thread.sleep(2000);
        //Playlist details
        //Display All Songs
        displayAllSongs();
        Thread.sleep(2000);
        //Assertion
        Assert.assertTrue(getPlaylistDetails().contains(String.valueOf(countSongs())));
    }

    //Tests end here

    // Helper methods start here

    public void chooseAllSongsList() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='songs']"))).click();
    }
    public void contextClickFirstSong() {
       WebElement firstSongElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".all-songs tr.song-item:nth-child(1)")));
       actions.contextClick(firstSongElement).perform();
    }
    public void choosePlayOption() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li.playback"))).click();
    }
    public boolean isSongPlaying() {
        WebElement soundBars =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[alt='Sound bars']")));
        return soundBars.isDisplayed();
    }
    public WebElement hoverOverPlay() {
        WebElement play = driver.findElement(By.cssSelector("span.play"));
        actions.moveToElement(play).perform();
        return wait.until(ExpectedConditions.visibilityOf(play));
    }
    public void choosePlaylistByName(String playlistName){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'"+playlistName+"')]"))).click();
    }

    public int countSongs(){
        return driver.findElements(By.cssSelector("section#playlistWrapper td.title")).size();
    }

    public String getPlaylistDetails(){
        return driver.findElement(By.cssSelector("span.meta.text-secondary span.meta")).getText();
    }

    public void displayAllSongs(){
        List<WebElement> songList = driver.findElements(By.cssSelector("section#playlistWrapper td.title"));
        System.out.println("Number of Songs found: "+countSongs());
        for (WebElement e: songList){
            System.out.println(e.getText());
        }
    }
}


