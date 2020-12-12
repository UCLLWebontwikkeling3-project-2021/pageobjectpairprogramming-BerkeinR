package pages;

/**
 * Created by  Reinoud Berkein
 */
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginHomePage extends  HomePage{

    @FindBy(id = "userid")
    private WebElement userIdField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "Login")
    private WebElement btnLogIn;

    public LoginHomePage(WebDriver driver) {
        super(driver);
    }

    public void setUserId(String userId) {
        userIdField.clear();
        userIdField.sendKeys(userId);
    }
    public void setPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }
    public LogoutHomePage validLogin() {
        btnLogIn.click();
        return PageFactory.initElements(getDriver(), LogoutHomePage.class);
    }

    public void invalidLogin() {
        btnLogIn.click();
    }

    public boolean hasErrorMessage (String message) {
        WebElement errorMsg = getDriver().findElement(By.cssSelector(".error > div > p"));
        return (message.equals(errorMsg.getText()));
    }

    public boolean hasEmptyUserId () {
        return userIdField.getAttribute("value").isEmpty();
    }

    public boolean hasEmptyPassword () {
        return passwordField.getAttribute("value").isEmpty();
    }

}
