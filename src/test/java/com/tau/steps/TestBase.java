package com.tau.steps;
import com.tau.Pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
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
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class TestBase {
    public static WebDriver driver;
    public WebDriverWait wait;
    public JavascriptExecutor executor;
    @Before()
    public void openUrl()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://live.techpanda.org/");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    @After()
    public void take_screenShot(ITestResult result) throws IOException {
        if(ITestResult.FAILURE == result.getStatus())
        {
            System.out.println("Failed!");
            System.out.println("Taking Screenshot...");
            TakesScreenshot sc=(TakesScreenshot) driver;
            File photo=sc.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(photo,new File("./screenshots/"+result.getName()+".png"));
        }
        driver.quit();
    }
}
