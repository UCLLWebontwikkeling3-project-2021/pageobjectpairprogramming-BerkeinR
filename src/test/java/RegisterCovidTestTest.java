import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import pages.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by  Reinoud Berkein
 */

public class RegisterCovidTestTest {
    private static WebDriver driver;
    private final String path = "http://localhost:8080/Controller";
    private static String useridRandom;
    private static String password;

    @BeforeClass
    public static void createPerson(){
        System.setProperty("webdriver.chrome.driver", "/Applications/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        driver = new ChromeDriver(options);

        int random = (int)(Math.random() * 1000 + 1);

        useridRandom = "test"+random;
        password = "1234";
        SignUpPage signUpPage = PageFactory.initElements(driver, SignUpPage.class);
        signUpPage.setUserId(useridRandom);
        signUpPage.setFirstName("Jan");
        signUpPage.setLastName("Janssens");
        signUpPage.setEmail("jan.janssens@hotmail.com");
        signUpPage.setPassword(password);

        signUpPage.submitValid();

        driver.quit();
    }

    @Before
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "/Applications/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        driver = new ChromeDriver(options);

        LoginHomePage loginPage = PageFactory.initElements(driver, LoginHomePage.class);
        loginPage.setUserId(useridRandom);
        loginPage.setPassword(password);
        loginPage.validLogin();
    }

    @After
    public void clean() {
        driver.quit();
    }


    @Test
    public void test_RegisterTest_AllFieldsFilledInCorrectly_TestRegistered(){
        Date today = new Date();
        String date = new SimpleDateFormat("dd/MM/yyyy").format(today);

        RegisterCovidTestPage covidTestPage = PageFactory.initElements(driver, RegisterCovidTestPage.class);
        covidTestPage.setDate(date);

        ContactOverviewPage contactOverviewPage =  covidTestPage.submitValid();

        Assert.assertTrue(contactOverviewPage.getTitle().contains("Contacts"));
    }

    @Test
    public void test_RegisterTest_WordFilledIn_ErrorMessageGiven(){
        String date = "test123";
        RegisterCovidTestPage covidTestPage = PageFactory.initElements(driver, RegisterCovidTestPage.class);
        covidTestPage.setDate(date);

        covidTestPage.submitInvalid();

        Assert.assertTrue(covidTestPage.hasErrorMessage("The date pattern is not valid"));
        Assert.assertTrue(covidTestPage.hasStickyDate(date));
    }

    @Test
    public void test_RegisterTest_IncorrectPattern_ErrorMessageGiven(){
        Date today = new Date();
        String date = new SimpleDateFormat("yyyy/MM/dd").format(today);

        RegisterCovidTestPage covidTestPage = PageFactory.initElements(driver, RegisterCovidTestPage.class);
        covidTestPage.setDate(date);

        covidTestPage.submitInvalid();

        Assert.assertTrue(covidTestPage.hasErrorMessage("The date pattern is not valid"));
        Assert.assertTrue(covidTestPage.hasStickyDate(date));
    }

    @Test
    public void test_RegisterTest_EmptyField_ErrorMessageGiven(){
        String date = "";

        RegisterCovidTestPage covidTestPage = PageFactory.initElements(driver, RegisterCovidTestPage.class);
        covidTestPage.setDate(date);

        covidTestPage.submitInvalid();

        Assert.assertTrue(covidTestPage.hasErrorMessage("No date given"));
        Assert.assertTrue(covidTestPage.hasEmptyDate());
    }


    @Test
    public void test_RegisterTest_FutureDate_ErrorMessageGiven(){
        Date today = new Date();
        Date tomorrow = new Date(today.getTime() + (1000 * 60 * 60 * 24));
        String date = new SimpleDateFormat("dd/MM/yyyy").format(tomorrow);

        RegisterCovidTestPage covidTestPage = PageFactory.initElements(driver, RegisterCovidTestPage.class);
        covidTestPage.setDate(date);

        covidTestPage.submitInvalid();

        Assert.assertTrue(covidTestPage.hasErrorMessage("Future date given"));
        Assert.assertTrue(covidTestPage.hasStickyDate(date));
    }

    @Test
    public void test_RegisterTest_NotAuthenticated_ErrorMessageGiven(){
        driver.get(path+"?action=logout");
        LoginHomePage homePage = PageFactory.initElements(driver, LoginHomePage.class);

        Assert.assertEquals("Login",homePage.getSubtitle());
        RegisterCovidTestPage covidTestPage = PageFactory.initElements(driver, RegisterCovidTestPage.class);


        Assert.assertEquals("Not authenticated",covidTestPage.getHeader());
    }
}
