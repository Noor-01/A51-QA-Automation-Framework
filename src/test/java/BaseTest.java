import PageObjectModel.HomePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import java.time.Duration;
import java.util.List;

public class BaseTest {
    public static WebDriver driver = null;
    public static String BaseURL = "https://qa.koel.app/";
    public static WebDriverWait wait = null;
    public static Actions actions = null;
    String NewPlaylistName = "Renamed Playlist";
    String updatedPlaylistMsg = "Updated playlist \"Renamed Playlist.\"";

    @BeforeSuite
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    @Parameters({"BaseURL"})
    public void launchBrowser(String BaseURL) {
        //Added ChromeOptions argument below to fix websocket error
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
        driver.manage().window().maximize();
        navigateToPage();
    }
    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }

    public void navigateToPage() {
        driver.get(BaseURL);
    }

    public void login() {
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[type='email']")));
        emailField.clear();
        emailField.sendKeys("noor.alam@testpro.io");
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[type='password']")));
        passwordField.clear();
        passwordField.sendKeys("te$t$tudent");
        WebElement submit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[type='submit']")));
        submit.click();
    }
    public boolean isAvatarDisplayed() {
        WebElement avatarIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='avatar']")));
        return avatarIcon.isDisplayed();
    }
    public void doubleClickPlaylist() {
        WebElement playlistElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@id='playlists']//li[7]")));
        actions.doubleClick(playlistElement).perform();
    }
    public void enterNewPlaylistName() {
        WebElement playlistInputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[name='name']")));
        playlistInputField.sendKeys(Keys.chord(Keys.CONTROL,"A", Keys.BACK_SPACE));
        playlistInputField.sendKeys(NewPlaylistName);
        playlistInputField.sendKeys(Keys.ENTER);
    }
    public String getRenamePlaylistSuccessMsg() {
        WebElement notification = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.success.show")));
        return notification.getText();
    }
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
        WebElement soundBars = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[alt='Sound bars']")));
        return soundBars.isDisplayed();
    }
    public WebElement HoverOverPlayBtn() {
        WebElement play = driver.findElement(By.cssSelector("[data-testid='play-btn']"));
        actions.moveToElement(play).perform();
        return wait.until(ExpectedConditions.visibilityOf(play));
    }
    public void choosePlaylistByName(String playlistName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'" + playlistName + "')]"))).click();
    }
    public int countSongs() {
        return driver.findElements(By.cssSelector("section#playlistWrapper td.title")).size();
    }
    public String getPlaylistDetails() {
        return driver.findElement(By.cssSelector("span.meta.text-secondary span.meta")).getText();
    }
    public void displayAllSongs() {
        List<WebElement> songList = driver.findElements(By.cssSelector("section#playlistWrapper td.title"));
        System.out.println("Number of Songs found: " + countSongs());
        for (WebElement e : songList) {
            System.out.println(e.getText());
        }

    }
}