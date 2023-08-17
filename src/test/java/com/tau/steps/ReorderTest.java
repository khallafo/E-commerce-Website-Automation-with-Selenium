package com.tau.steps;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.tau.Pages.*;
import java.time.Duration;

public class ReorderTest extends TestBase{
    HomePage homePage;
    LoginPage loginPage;
    AccountPage accountPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    OrderSuccessPage orderSuccessPage;

    @Test
    public void VerifyReorder_Product()
    {
        homePage=new HomePage(driver);
        homePage.Open_login_CreateAccPage();
        loginPage=new LoginPage(driver);
        loginPage.LogIn("roma89@mail.com","000000");
        accountPage=new AccountPage(driver);
        accountPage.ReorderProduct();
        cartPage=new CartPage(driver);
        cartPage.setQTY("10");
        System.out.println("Grand Total = "+cartPage.grandTotal.getText());
        Assert.assertTrue(cartPage.grandTotal.getText()!="$620.00");
        cartPage.ProceedToCheckout();
        checkoutPage=new CheckoutPage(driver);
        checkoutPage.SelectBillingInfo();
        checkoutPage.SetCheckout("shoubra el kheima","cairo","542896","25286","California","egypt");
        //wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(checkoutPage.contiBtn2));
        checkoutPage.setCheckout2();
        wait.until(ExpectedConditions.elementToBeClickable(checkoutPage.orderBtn));
        checkoutPage.SetOrder();
        orderSuccessPage=new OrderSuccessPage(driver);
        System.out.println(" Your order number for your record = "+orderSuccessPage.orderNum.getText());
    }
}