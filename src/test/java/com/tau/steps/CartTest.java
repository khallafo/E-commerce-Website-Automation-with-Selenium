package com.tau.steps;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.tau.Pages.*;
public class CartTest extends TestBase{
    HomePage homePage;
    LoginPage loginPage;
    AccountPage accountPage;
    MobilePage mobilePage;
    CartPage cartPage;

    @Test(priority = 2)
    public void Verify_QTY()
    {
        homePage=new HomePage(driver);
        homePage.Open_mobilePage();
        mobilePage=new MobilePage(driver);
        mobilePage.addToCart_Sony();
        cartPage=new CartPage(driver);
        cartPage.setQTY("15000");
        Assert.assertEquals(cartPage.errormsg.getText(),"Some of the products cannot be ordered in requested quantity.");
        cartPage.EmptyCart();
        Assert.assertEquals(cartPage.emptymsg.getText(),"You have no items in your shopping cart.");
    }
    @Test(priority = 1)
    public void VerifyDiscount()
    {
        homePage=new HomePage(driver);
        homePage.Open_login_CreateAccPage();
        loginPage=new LoginPage(driver);
        loginPage.LogIn("mahmoudkhallaf277@gmail.com","123456");
        accountPage=new AccountPage(driver);
        accountPage.Open_MobilePage();
        mobilePage=new MobilePage(driver);
        mobilePage.addToCart_Iphone();
        cartPage=new CartPage(driver);
        cartPage.setQTY("1");
        cartPage.InterCouponCode("GURU50");
        executor=(JavascriptExecutor)driver;
        executor.executeScript("scrollBy(0,300)");
        Assert.assertEquals(cartPage.grandTotal.getText(),"$480.00");
    }
}
