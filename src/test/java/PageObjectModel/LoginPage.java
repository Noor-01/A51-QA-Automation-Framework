package PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver givenDriver) {
        super(givenDriver);
    }
    //Page Factory Locators:
    @FindBy(css = "[type='email']")
    WebElement emailField;

    @FindBy(css = "[type='password']")
    WebElement passwordField;

    @FindBy(css = "[type='submit']")
    WebElement clickSubmit;

    //Page Object Model Locators:
    private By emailTxtField = By.cssSelector("[type='email']");
    private By passworTxtdField = By.cssSelector("[type='password']");
    private By submitLoginButton = By.cssSelector("[type='submit']");


    //Page Factory Method:
    public LoginPage provideEmail(String email) {
        emailField.sendKeys(email);
        return this;
    }
    public LoginPage providePassword(String password) {
        passwordField.sendKeys(password);
        return this;
    }
    public LoginPage clickSubmitBtn() {
        clickSubmit.click();
        return this;
    }
    public boolean isAvatarDisplayed() {
        WebElement avatarIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='avatar']")));
        return avatarIcon.isDisplayed();
    }

    //Page Object Model Method:

    public void provideEmailToLogin(String email) {
        findElement(emailTxtField).sendKeys(email);
    }
    public void providePasswordToLogin(String password) {
        findElement(passworTxtdField).sendKeys(password);
    }
    public void clickSubmitToLogin() {
        findElement(submitLoginButton).click();
    }
//    public void login() {
//        provideEmail("noor.alam@testpro.io");
//        providePassword("te$t$tudent");
//        clickSubmit();
//    }

}

