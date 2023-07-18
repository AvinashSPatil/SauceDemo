package com.sauceDemo.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SauceDemoCheckoutPage2 {

    @FindBy(className = "cart_checkout_link")
    private WebElement finishButton;


    public static String CHECKOUT_STEP_2_URL = "https://www.saucedemo.com/checkout-step-two.html";
    public WebDriver driver;

    public SauceDemoCheckoutPage2(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void finishCheckout() {
        finishButton.click();
    }


}
