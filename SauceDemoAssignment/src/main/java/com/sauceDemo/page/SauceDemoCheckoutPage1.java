package com.sauceDemo.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SauceDemoCheckoutPage1 {

    public static String CHECKOUT_STEP_1_URL = "https://www.saucedemo.com/checkout-step-one.html";

    @FindBy(xpath = "//*[@id=\"checkout_info_container\"]/div/form/input[1]")
    private WebElement firstNameField;

    @FindBy(xpath = "//*[@id=\"checkout_info_container\"]/div/form/input[2]")
    private WebElement lastNameField;

    @FindBy(xpath = "//*[@id=\"checkout_info_container\"]/div/form/input[3]")
    private WebElement zipCodeField;

    @FindBy(className = "cart_checkout_link")
    private WebElement cartcheckoutLink;

    public WebDriver driver;

    public SauceDemoCheckoutPage1(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterNameDetails(String firstName, String lastName, String zipCode) {
        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        zipCodeField.sendKeys(zipCode);
        cartcheckoutLink.click();
    }

}
