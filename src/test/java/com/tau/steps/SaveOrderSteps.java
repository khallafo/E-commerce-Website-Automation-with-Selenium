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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class SaveOrderSteps{
    HomePage homePage;
    LoginPage loginPage;
    AccountPage accountPage;
    MyOrdersPage myOrdersPage;
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
    @And("User navigates to the Orders page")
    public void userNavigatesToOrdersPage() {
        accountPage.Open_OrdersPage();
        myOrdersPage = new MyOrdersPage(driver);
    }

    @And("User views an order")
    public void userViewsAnOrder() {
        myOrdersPage.ViewOrder();
    }

    @And("User prints the order as PDF")
    public void userPrintsOrderAsPDF() {

        myOrdersPage.PrintOrder();
    }

    @Then("User saves a screenshot of the printed order")
    public void userSavesScreenshotOfPrintedOrder() throws IOException, InterruptedException {
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }
        Thread.sleep(5000);
        TakesScreenshot sc = (TakesScreenshot) driver;
        File screen = sc.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screen, new File("./screenshots/VerifyPrint.png"));
    }
    @After()
    public void tearDown() {
        driver.quit();
    }
}
