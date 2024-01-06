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

public class ReorderSteps {
    HomePage homePage;
    LoginPage loginPage;
    AccountPage accountPage;
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


    @And("User navigates to the Account page")
    public void userNavigatesToAccountPage() {
        // Navigate to the account page
        // accountPage = new AccountPage(driver);
    }

    @And("User reorders a product")
    public void userReordersProduct() {
        accountPage.ReorderProduct();
        cartPage = new CartPage(driver);
    }

    @And("User updates the quantity to 10")
    public void userUpdatesQuantity() {
        cartPage.setQTY("10");
        System.out.println("Grand Total = " + cartPage.grandTotal.getText());
        Assert.assertNotEquals(cartPage.grandTotal.getText(), "$620.00");
    }

    @And("User proceeds to checkout")
    public void userProceedsToCheckout() {
        cartPage.ProceedToCheckout();
        checkoutPage = new CheckoutPage(driver);
    }

    @And("User selects billing information")
    public void userSelectsBillingInfo() {
        checkoutPage.SelectBillingInfo();
    }

    @And("User sets the checkout information")
    public void userSetsCheckoutInformation() {
        checkoutPage.SetCheckout("shoubra el kheima", "cairo", "542896", "25286", "California", "egypt");
    }

    @And("User proceeds with the checkout process")
    public void userProceedsWithCheckoutProcess() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutPage.contiBtn2));
        checkoutPage.setCheckout2();
        wait.until(ExpectedConditions.elementToBeClickable(checkoutPage.orderBtn));
    }

    @And("User completes the order")
    public void userCompletesTheOrder() {
        checkoutPage.SetOrder();
        orderSuccessPage = new OrderSuccessPage(driver);
    }

    @Then("User should see the order success message with order details")
    public void userSeesSuccessMessageWithOrderDetails() {
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
