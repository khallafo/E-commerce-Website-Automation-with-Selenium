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

public class SonySteps{
    HomePage homePage;
    MobilePage mobilePage;
    SonyPage sonyPage;
    String sonyPriceOnMobilePage;
    String sonyPriceOnSonyPage;
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
    @Given("User is on the homepage")
    public void userIsOnHomepage() {
        homePage = new HomePage(driver);
        // Your code to navigate to the homepage
    }

    @When("User navigates to the Mobile page")
    public void userNavigatesToMobilePage() {
        homePage.Open_mobilePage();
        mobilePage = new MobilePage(driver);
    }

    @And("User notes the price of a Sony phone")
    public void userNotesSonyPhonePrice() {
        sonyPriceOnMobilePage = mobilePage.sonyPrice.getText();
    }

    @And("User opens the Sony phone's details page")
    public void userOpensSonyPhoneDetailsPage() {
        mobilePage.open_SonyPage();
        sonyPage = new SonyPage(driver);
        sonyPriceOnSonyPage = sonyPage.sonyPrice.getText();
    }

    @Then("User should see that the price is the same")
    public void userSeesSameSonyPhonePrice() {
        Assert.assertEquals(sonyPriceOnMobilePage, sonyPriceOnSonyPage);
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
