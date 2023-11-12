import PageObjectModel.HomePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import java.time.Duration;

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
        driver.get(url);
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
        WebElement avatarIcon = driver.findElement(By.cssSelector("[class='avatar']"));
        return avatarIcon.isDisplayed();
    }
    public void clickPlayNextSongButton() {
        WebElement playNextSongBtn = driver.findElement(By.cssSelector("[data-testid='play-next-btn']"));
        playNextSongBtn.click();
    }
    public void clickPlaySongButton() {
        WebElement playSongBtn = driver.findElement(By.xpath("//span[@data-testid='play-btn']"));
        playSongBtn.click();
    }
    public boolean isSoundBarsDisplayed() {
        WebElement soundBars = driver.findElement(By.cssSelector("[alt='Sound bars']"));
        return soundBars.isDisplayed();
    }

}
