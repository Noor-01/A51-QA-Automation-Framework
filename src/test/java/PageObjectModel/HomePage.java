package PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    public HomePage(WebDriver givenDriver) {
        super(givenDriver);
    }

    @FindBy(css = "img.avatar")
    WebElement avatarIcon;

    @FindBy(xpath = "//section[@id='playlists']//li[8]")
    WebElement selectPlaylist;
    @FindBy(css = "[name='name']")
    WebElement playlistInputField;
    @FindBy(css = "div.success.show")
    WebElement renamePlaylistSuccessMsg;

    public HomePage doubleClickPlaylist() {
        doubleClick((By) selectPlaylist);
        return this;
    }

    public HomePage enterNewPlaylistName(String newPlaylistName) {
        playlistInputField.sendKeys(Keys.chord(Keys.CONTROL, "A", Keys.BACK_SPACE));
        playlistInputField.sendKeys(newPlaylistName);
        playlistInputField.sendKeys(Keys.ENTER);
        return this;
    }

    public String getRenamePlaylistSuccessMsg() {
        return renamePlaylistSuccessMsg.getText();
    }

    public WebElement getUserAvatar() {
        return avatarIcon;
    }
}
