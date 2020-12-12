package pages;

/**
 * Created by  Reinoud Berkein
 */

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

public class PersonOverviewPage extends Page {

    public PersonOverviewPage(WebDriver driver) {
        super(driver);
        getDriver().get(getPath()+"?action=overview");
    }

    public boolean containsUserWithEmail(String email) {
        ArrayList<WebElement> listItems=(ArrayList<WebElement>) getDriver().findElements(By.cssSelector("td"));
        for (WebElement listItem:listItems) {
            if (listItem.getText().contains(email)) {
                return true;
            }
        }
        return false;
    }
}
