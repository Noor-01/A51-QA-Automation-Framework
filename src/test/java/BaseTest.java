import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

public class BaseTest {
    public static WebDriver driver = null;
    public static String url = null;
//    public static WebDriverWait wait = null;
//    public static Actions actions = null;
    private static final ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();

    @BeforeSuite
    static void setupClass() {
        //WebDriverManager.firefoxdriver().setup();
    }
    @BeforeMethod
    @Parameters({"BaseURL"})
    public void setupBrowser(String BaseURL) throws MalformedURLException {
        threadDriver.set(pickBrowser(System.getProperty("browser")));
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        url = BaseURL;
        navigateToPage();
    }
    public static WebDriver getDriver() {
        return threadDriver.get();
    }

//    @BeforeMethod
//    @Parameters({"BaseURL"})
//    public void launchBrowser(String BaseURL) throws MalformedURLException {
//
//        driver = pickBrowser(System.getProperty("browser"));
//        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        actions = new Actions(driver);
//        driver.manage().window().maximize();
//        navigateToPage();
//    }
    public static WebDriver pickBrowser(String browser) throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        String gridURL = "http://10.0.0.111:4444";

        switch(browser) {
            case "firefox":    //To run tests from terminal:  gradle clean test -Dbrowser=firefox
                WebDriverManager.firefoxdriver().setup();
                return driver = new FirefoxDriver();

            case "MicrosoftEdge":   //To run tests from terminal:  gradle clean test -Dbrowser=MicrosoftEdge
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--remote-allow-origins=*");
                return driver = new EdgeDriver(edgeOptions);

            // Selenium Grid:
            case "grid-edge":   //To run tests from terminal:  gradle clean test -Dbrowser=grid-edge
                caps.setCapability("browserName", "MicrosoftEdge");
                return driver = new RemoteWebDriver(URI.create(gridURL).toURL(), caps);

            case "grid-firefox":  //To run tests from terminal:  gradle clean test -Dbrowser=grid-firefox
                caps.setCapability("browserName", "firefox");
                return driver = new RemoteWebDriver(URI.create(gridURL).toURL(), caps);

            case "grid-chrome":   //To run tests from terminal:  gradle clean test -Dbrowser=grid-chrome
                caps.setCapability("browserName", "chrome");
                return driver = new RemoteWebDriver(URI.create(gridURL).toURL(), caps);

            case "cloud":
                return lambdaTest();

            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*");
                return driver = new ChromeDriver(chromeOptions);
        }
    }

    public static WebDriver lambdaTest() throws MalformedURLException {
//        String username = "noor.alamtestpro";
//        String accessKey = "f55WRYooU7ZIRWuHQQTbJ56AZ9PzmgBzcD2t6YAGZNpkqRINWU";
//        String hub = "@hub.lambdatest.com/wd/hub";
//
//        DesiredCapabilities caps = new DesiredCapabilities();
//        caps.setCapability("platform", "Windows 10");
//        caps.setCapability("browserName", "Chrome");
//        caps.setCapability("version", "120.0");
//        caps.setCapability("resolution", "1024x768");
//        caps.setCapability("build", "TestNG with Java");
//        caps.setCapability("name", this.getClass().getName());
//        caps.setCapability("plugin", "git-testng");
//        return new RemoteWebDriver(new URL("https://" + username + ":" + accessKey + hub), caps);

// Alternative Setup:
          String hubURL = "https://hub.lambdatest.com/wd/hub";

        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName("Windows 10");
        browserOptions.setBrowserVersion("120.0");
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("username", "noor.alamtestpro");
        ltOptions.put("accessKey", "f55WRYooU7ZIRWuHQQTbJ56AZ9PzmgBzcD2t6YAGZNpkqRINWU");
        ltOptions.put("project", "Untitled");
        ltOptions.put("selenium_version", "4.0.0");
        ltOptions.put("w3c", true);
        browserOptions.setCapability("LT:Options", ltOptions);

        return new RemoteWebDriver(new URL(hubURL), browserOptions);
    }
    // This lambdaTest() method returns an instance of WebDriver for remote testing using the LambdaTest service.

//    @AfterMethod
//    public void closeBrowser() {
//        driver.quit();
//    }
    @AfterMethod
    public void tearDown() {
        threadDriver.get().close();
        threadDriver.remove();
    }
    public void navigateToPage() {
        driver.get(url);
    }

}
//The tearDown() method is executed after each method (@AfterMethod),
//adn its purpose is to close the WebDriver and remove its instance from ThreadLocal.