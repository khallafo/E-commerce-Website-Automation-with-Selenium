package com.tau.steps;
import io.cucumber.java.After;
import io.cucumber.java.Before;
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
import org.testng.annotations.Test;
import com.tau.Pages.*;
import io.cucumber.java.en.*;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class CompareTestSteps{

    HomePage homePage;
    MobilePage mobilePage;
    ComparePage comparePage;
    String currentWindow;
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
    @When("^the user navigates to the mobile category$")
    public void navigateToMobileCategory() {
        homePage = new HomePage(driver);
        homePage.Open_mobilePage();
    }

    @When("^the user compares products$")
    public void compareProducts() {
        currentWindow = driver.getWindowHandle();
        mobilePage = new MobilePage(driver);
        mobilePage.CompareProducts();
    }

    @Then("^the comparison page should display correct information$")
    public void verifyComparisonPage() {
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
            if (driver.getTitle().equals("Products Comparison List - Magento Commerce")) {
                comparePage = new ComparePage(driver);
                driver.manage().window().maximize();
                Assert.assertEquals(comparePage.pageTitle.getText(), "Compare Products");
                Assert.assertEquals(comparePage.sony.getText(), "SONY XPERIA");
                Assert.assertEquals(comparePage.iphone.getText(), "IPHONE");
                comparePage.ClosePopUp_Window();
                break;
            }
        }
    }

    @Then("^the user should be able to close the comparison popup$")
    public void closeComparisonPopup() {
        driver.switchTo().window(currentWindow);
    }

    @Then("^the user should be back on the mobile category page$")
    public void verifyMobileCategoryPage() {
        Assert.assertEquals(mobilePage.categoryTitle.getText(), "MOBILE");
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