package pages;

/**
 * Created by  Reinoud Berkein
 */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends Page {

    @FindBy(css = "h2")
    private WebElement subtitle;

    public HomePage (WebDriver driver) {
        super(driver);
        getDriver().get(path+"?command=home");

    }

    public String getSubtitle(){
        return subtitle.getText();
    }
}