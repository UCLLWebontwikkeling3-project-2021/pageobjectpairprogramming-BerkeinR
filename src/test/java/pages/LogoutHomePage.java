package pages;

/**
 * Created by  Reinoud Berkein
 */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogoutHomePage extends HomePage{

    @FindBy(id = "Logout")
    private WebElement btnLogOut;

    public LogoutHomePage(WebDriver driver) {
        super(driver);
    }

    public LoginHomePage logout() {
        btnLogOut.click();
        return PageFactory.initElements(getDriver(), LoginHomePage.class);
    }
}
