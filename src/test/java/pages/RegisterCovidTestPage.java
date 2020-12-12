package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by  Reinoud Berkein
 */

public class RegisterCovidTestPage extends Page {

    @FindBy(id = "date")
    private WebElement dateField;

    @FindBy(id = "registerResult")
    private WebElement btnSubmit;


    public RegisterCovidTestPage(WebDriver driver) {
        super(driver);
        getDriver().get(getPath()+"?action=registerTestResult");
    }

    public void setDate(String date) {
        dateField.clear();
        dateField.sendKeys(date);
    }

    public ContactOverviewPage submitValid() {
        btnSubmit.click();
        return PageFactory.initElements(getDriver(), ContactOverviewPage.class);
    }

    public void submitInvalid() {
        btnSubmit.click();
    }

    public boolean hasErrorMessage (String message) {
        WebElement errorMsg = getDriver().findElement(By.cssSelector("div.error > div > p"));
        return (message.equals(errorMsg.getText()));
    }

    public boolean hasStickyDate (String date){
        return date.equals(dateField.getAttribute("value"));
    }

    public boolean hasEmptyDate(){
        return dateField.getAttribute("value").isEmpty();
    }
}
