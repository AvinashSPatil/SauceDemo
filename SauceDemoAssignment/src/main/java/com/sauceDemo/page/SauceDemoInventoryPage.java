package com.sauceDemo.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SauceDemoInventoryPage {
    public static String INVENTORY_PAGE_URL = "https://www.saucedemo.com/inventory.html";

    @FindBy(className = "bm-burger-button")
    private WebElement menuButton;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutLink;

    @FindBy(className = "add-to-cart-button")
    private WebElement addToCart;

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartItemCount;

    @FindBy(id = "shopping_cart_container")
    private WebElement cartLink;

    public WebDriver driver;

    public SauceDemoInventoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void addItemToCart() {
        addToCart.click();
        //should be added - but should be asserted
    }

    public int getItemsInCart() {
        String text = cartItemCount.getText();
        return Integer.parseInt(text);
    }

    public void clickCart () {
        cartLink.click();
    }

    public WebElement getCartItemCountElement() {

        return cartItemCount;
    }

    public void logout()
    {
        menuButton.click();

        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink));

        //wait until Logout link is clickable (menu expands)

        logoutLink.click();
        //logged out (hopefully)

    }

}
