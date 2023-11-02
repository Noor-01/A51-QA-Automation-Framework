import org.testng.annotations.Test;

public class LoginTests extends BaseTest {
    @Test
    public void loginValidEmailValidPassword() {

        navigateToPage();
        provideEmail("demo@class.com");
        providePassword("te$t$tudent");
        clickSubmit();
        isAvatarDisplayed();
    }
}