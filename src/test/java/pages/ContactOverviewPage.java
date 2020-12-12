package pages;

/**
 * Created by  Reinoud Berkein
 */

import org.openqa.selenium.WebDriver;

public class ContactOverviewPage extends PersonPage {
    public ContactOverviewPage(WebDriver driver) {
        super(driver);
        getDriver().get(getPath()+"?action=contacts");
    }
}
