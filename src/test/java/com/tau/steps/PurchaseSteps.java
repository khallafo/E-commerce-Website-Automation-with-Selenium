package com.tau.steps;

import com.tau.Pages.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class PurchaseSteps {
    HomePage homePage;
    LoginPage loginPage;
    AccountPage accountPage;
    WishlistPage wishlistPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    OrderSuccessPage orderSuccessPage;
    public static WebDriver driver;
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
    @When("User opens the login or create account page")
    public void userOpensLoginOrCreateAccountPage() {
        homePage.Open_login_CreateAccPage();
        loginPage = new LoginPage(driver);
    }

    @And("User logs in with email {string} and password {string}")
    public void userLogsIn(String email, String password) {
        loginPage.LogIn(email, password);
        accountPage = new AccountPage(driver);
    }

    @And("User navigates to the Wishlist page")
    public void userNavigatesToWishlistPage() {
        accountPage.Open_WishListPage();
        wishlistPage = new WishlistPage(driver);
    }

    @And("User adds products to the cart")
    public void userAddsProductsToCart() {
        wishlistPage.AddToCart();
        cartPage = new CartPage(driver);
    }

    @And("User proceeds to checkout")
    public void userProceedsToCheckout() {
        cartPage.ProceedToCheckout();
        checkoutPage = new CheckoutPage(driver);
    }

    @And("User selects billing information if available")
    public void userSelectsBillingInfoIfAvailable() {
        try {
            if (checkoutPage.billingList.isDisplayed()) {
                checkoutPage.SelectBillingInfo();
            }
        } catch (Exception e) {
            System.out.println("No dropdown element present");
        }
    }

    @And("User sets the checkout information")
    public void userSetsCheckoutInformation() {
        checkoutPage.SetCheckout("ABC", "New York", "542896", "123456", "Florida", "United States");
    }

    @And("User verifies shipping cost")
    public void userVerifiesShippingCost() {
        wait.until(ExpectedConditions.visibilityOf(checkoutPage.shipCost));
        Assert.assertEquals(checkoutPage.shipCost.getText(), "$5.00");
    }

    @And("User proceeds with the checkout process")
    public void userProceedsWithCheckoutProcess() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutPage.contiBtn2));
        checkoutPage.setCheckout2();
    }

    @And("User verifies the total cost")
    public void userVerifiesTotalCost() {
        wait.until(ExpectedConditions.visibilityOf(checkoutPage.totalCost));
        Assert.assertEquals(checkoutPage.totalCost.getText(), "$620.00");
    }

    @And("User completes the order")
    public void userCompletesTheOrder() {
        checkoutPage.SetOrder();
        orderSuccessPage = new OrderSuccessPage(driver);
    }

    @Then("User should see a success message with order details")
    public void userSeesSuccessMessageWithOrderDetails() {
        Assert.assertTrue(orderSuccessPage.msg.getText().contains("Your order # is:"));
        System.out.println(" Your order number for your record = " + orderSuccessPage.orderNum.getText());
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
