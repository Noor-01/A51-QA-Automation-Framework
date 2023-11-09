package PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    public HomePage(WebDriver givenDriver) {
        super(givenDriver);
    }

    By userAvaterIcon   = By.cssSelector("img.avatar");
    By selectPlaylist = By.xpath("//section[@id='playlists']//li[8]");
    By playlistInputField = By.cssSelector("[name='name']");
    By renamePlaylistSuccessMsg = By.cssSelector("div.success.show");

    public WebElement getUserAvatar() {
        return findElement(userAvaterIcon);
    }

    public void doubleClickPlaylist() {
        //WebElement playlistElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@id='playlists']//li[8]")));
        doubleClick(selectPlaylist);
    }
    public void enterNewPlaylistName(String newPlaylistName) {
        //WebElement playlistInputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[name='name']")));
        findElement(playlistInputField).sendKeys(Keys.chord(Keys.CONTROL, "A", Keys.BACK_SPACE));
        findElement(playlistInputField).sendKeys(newPlaylistName);
        findElement(playlistInputField).sendKeys(Keys.ENTER);
    }
    public String getRenamePlaylistSuccessMsg() {
        //WebElement notification = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.success.show")));
        return findElement(renamePlaylistSuccessMsg).getText();
    }
}