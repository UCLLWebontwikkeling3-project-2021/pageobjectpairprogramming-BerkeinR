package pages;

/**
 * Created by  Reinoud Berkein
 */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class Page {

    WebDriver driver;
    String path = "http://localhost:8080/Controller";

    @FindBy(css = "h1")
    private WebElement header;

    public Page (WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public String getPath() {
        return path;
    }

    public String getTitle () {
        return driver.getTitle();
    }

    public String getHeader(){
        return header.getText();
    }

}