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

public class ShareWishlistSteps{
    HomePage homePage;
    LoginPage loginPage;
    AccountPage accountPage;
    TVPage tvPage;
    WishlistPage wishlistPage;
    ShareWishlistPage shareWishlistPage;
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
    @And("User navigates to the TV page")
    public void userNavigatesToTvPage() {
        accountPage.Open_TvPage();
        tvPage = new TVPage(driver);
    }

    @And("User adds a product to wishlist")
    public void userAddsProductToWishlist() {
        tvPage.AddWishlist();
        wishlistPage = new WishlistPage(driver);
    }

    @And("User opens the Share Wishlist page")
    public void userOpensShareWishlistPage() {
        wishlistPage.Open_ShareWishlistPage();
        shareWishlistPage = new ShareWishlistPage(driver);
    }

    @And("User shares the wishlist")
    public void userSharesWishlist() {
        shareWishlistPage.shareWishlist();
    }

    @Then("User should see a success message that the wishlist has been shared")
    public void userSeesSuccessMessageForSharedWishlist() {
        Assert.assertEquals(wishlistPage.sucMsg.getText(), "Your Wishlist has been shared.");
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
