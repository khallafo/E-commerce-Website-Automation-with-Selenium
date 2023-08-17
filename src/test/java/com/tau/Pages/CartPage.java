package com.tau.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class CartPage extends PageBase{
    public CartPage(WebDriver driver)
    {
        super(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @FindBy(css = "input[title=\"Qty\"]")
    WebElement QTY;
    @FindBy(css = "button.button.btn-update")
    WebElement updatebtn;
    @FindBy(className = "error-msg")
    public WebElement errormsg;
    @FindBy(id = "empty_cart_button")
    WebElement emptybtn;
    @FindBy(xpath = "/html/body/div/div/div[2]/div/div/div[2]/p[1]")
    public WebElement emptymsg;
    @FindBy(css = "button[title=\"Proceed to Checkout\"]")
    WebElement proceed;
    @FindBy(xpath = "/html/body/div/div/div[2]/div/div/div/div[3]/div/table/tfoot/tr/td[2]/strong/span")
    public WebElement grandTotal;
    @FindBy(id = "coupon_code")
    WebElement couponCode;
    @FindBy(css = "button[title=\"Apply\"]")
    WebElement applyLink;
    public void setQTY(String input)
    {
        wait.until(ExpectedConditions.visibilityOf(QTY));
        QTY.clear();
        QTY.sendKeys(input);
        updatebtn.click();
    }
    public void EmptyCart()
    {
        emptybtn.click();
    }
    public void ProceedToCheckout()
    {
        proceed.click();
    }
    public void InterCouponCode(String input)
    {
        couponCode.clear();
        couponCode.sendKeys(input);
        applyLink.click();
    }
}