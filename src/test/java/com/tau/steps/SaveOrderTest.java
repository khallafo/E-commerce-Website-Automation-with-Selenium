package com.tau.steps;
import com.tau.Pages.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class SaveOrderTest extends TestBase{
    HomePage homePage;
    LoginPage loginPage;
    AccountPage accountPage;
    MyOrdersPage myOrdersPage;
    @Test
    public void VerifySaveOrder_asPDF() throws IOException, InterruptedException {
        homePage=new HomePage(driver);
        homePage.Open_login_CreateAccPage();
        loginPage=new LoginPage(driver);
        loginPage.LogIn("mahmoudkhallaf277@gmail.com","123456");
        accountPage=new AccountPage(driver);
        accountPage.Open_OrdersPage();
        myOrdersPage=new MyOrdersPage(driver);
        //Assert.assertEquals(myOrdersPage.cells.get(0).getText(), "100019917");
        Assert.assertEquals(myOrdersPage.cells.get(4).getText(), "Pending");
        myOrdersPage.ViewOrder();
        myOrdersPage.PrintOrder();
        for(String handle: driver.getWindowHandles())
        {
            driver.switchTo().window(handle);
        }
        Thread.sleep(5000);
        TakesScreenshot sc=(TakesScreenshot) driver;
        File screen = sc.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screen,new File("./screenshots/VerifyPrint.png"));
    }
}
