package com.tau.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SonyPage extends PageBase{
    public SonyPage(WebDriver driver)
    {
        super(driver);
    }
    @FindBy(id = "product-price-1")
    public WebElement sonyPrice;
    //By.cssSelector("span.price")

}
