package com.tau.steps;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import com.tau.Pages.*;
import io.cucumber.java.en.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class CartTestSteps  {
    HomePage homePage;
    LoginPage loginPage;
    AccountPage accountPage;
    MobilePage mobilePage;
    CartPage cartPage;
    private WebDriver driver;
    public WebDriverWait wait;
    public JavascriptExecutor executor;

    @Before()
    public void setUp() throws Exception {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        // Maximize the browser window
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @Given("the user is on the login page")
    public void userIsOnLoginPage() {
        driver.get("http://live.techpanda.org/");
    }
    @When("^the user adds a Sony mobile to the cart$")
    public void addToCartSony() {
        homePage = new HomePage(driver);
        homePage.Open_mobilePage();
        mobilePage = new MobilePage(driver);
        mobilePage.addToCart_Sony();
    }

    @When("^the user sets the quantity to (\\d+)$")
    public void setQuantity(String quantity) {
        cartPage.setQTY(quantity);
    }

    @Then("^an error message should be displayed for exceeding the quantity limit$")
    public void verifyQuantityErrorMessage() {
        Assert.assertEquals(cartPage.errormsg.getText(), "Some of the products cannot be ordered in requested quantity.");
    }

    @Then("^the user's cart should be empty$")
    public void verifyEmptyCart() {
        Assert.assertEquals(cartPage.emptymsg.getText(), "You have no items in your shopping cart.");
    }

    @When("^the user logs in with valid credentials$")
    public void loginWithCredentials() {
        homePage = new HomePage(driver);
        homePage.Open_login_CreateAccPage();
        loginPage = new LoginPage(driver);
        loginPage.LogIn("mahmoudkhallaf277@gmail.com", "123456");
    }

    @When("^the user adds an iPhone to the cart$")
    public void addToCartiPhone() {
        accountPage = new AccountPage(driver);
        accountPage.Open_MobilePage();
        mobilePage = new MobilePage(driver);
        mobilePage.addToCart_Iphone();
    }

    @When("^the user applies the discount coupon code$")
    public void applyDiscountCoupon() {
        cartPage = new CartPage(driver);
        cartPage.setQTY("1");
        cartPage.InterCouponCode("GURU50");
        executor = (JavascriptExecutor) driver;
        executor.executeScript("scrollBy(0,300)");
    }

    @Then("^the grand total in the cart should be correct$")
    public void verifyGrandTotal() {
        Assert.assertEquals(cartPage.grandTotal.getText(), "$480.00");
    }
    @After()
    public void tearDown() {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File("F://work//testing//automation//projects//tau-cucumber-project//screenshoots/manager_id.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        driver.quit();
    }
}
