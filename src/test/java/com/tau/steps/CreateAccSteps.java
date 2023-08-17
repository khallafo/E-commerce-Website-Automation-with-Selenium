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

public class CreateAccSteps {
    HomePage homePage;
    LoginPage loginPage;
    CreateAccountPage createAccountPage;
    AccountPage accountPage;
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
    @When("User navigates to the Create Account page")
    public void userNavigatesToCreateAccountPage() {
        homePage.Open_login_CreateAccPage();
        loginPage = new LoginPage(driver);
        loginPage.Open_CreateAccPage();
        createAccountPage = new CreateAccountPage(driver);
    }

    @And("User enters {string}, {string}, {string}, {string}, {string}, and {string}")
    public void userEntersAccountInformation(String firstName, String lastName, String username, String email, String password, String confirmPassword) {
        createAccountPage.CreateAccount(firstName, lastName, username, email, password, confirmPassword);
    }

    @And("User submits the registration form")
    public void userSubmitsRegistrationForm() {
        accountPage = new AccountPage(driver);
        // Your code to submit the registration form
    }

    @Then("User should be redirected to the Dashboard")
    public void userIsRedirectedToDashboard() {
        Assert.assertEquals(accountPage.title.getText(), "MY DASHBOARD");
    }

    @And("User should see a success message")
    public void userSeesSuccessMessage() {
        Assert.assertTrue(accountPage.sucMsg.getText().contains("Thank you for registering"));
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
