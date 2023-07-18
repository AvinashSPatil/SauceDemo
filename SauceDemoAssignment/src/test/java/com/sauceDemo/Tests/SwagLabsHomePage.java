package com.sauceDemo.Tests;

import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.sauceDemo.page.SauceDemoCart;
import com.sauceDemo.page.SauceDemoCheckoutCompletePage;
import com.sauceDemo.page.SauceDemoCheckoutPage1;
import com.sauceDemo.page.SauceDemoCheckoutPage2;
import com.sauceDemo.page.SauceDemoHomePage;
import com.sauceDemo.page.SauceDemoInventoryPage;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.rmi.UnexpectedException;
import java.util.UUID;

public class SwagLabsHomePage extends TestBase {


    @org.testng.annotations.Test(dataProvider = "hardCodedBrowsers")
    public void swagLabsSmokeTests(String browser, String version, String os, Method method)
            throws MalformedURLException, InvalidElementStateException, UnexpectedException {
        this.createDriver(browser, version, os, method.getName());
        WebDriver driver = this.getWebDriver();

        this.annotate("Visiting Swag Labs Homepage...");
        SauceDemoHomePage page = SauceDemoHomePage.visitPage(driver);

        page.login("standard_user", "secret_sauce");

        //wait and assert
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.urlToBe(SauceDemoInventoryPage.INVENTORY_PAGE_URL));

        Assert.assertFalse(driver.getCurrentUrl() == SauceDemoInventoryPage.INVENTORY_PAGE_URL, "We are not logged in");

        SauceDemoInventoryPage theInvPage = new SauceDemoInventoryPage(driver);

        //now we should add an item to our cart
        theInvPage.addItemToCart();

        wait.until(ExpectedConditions.textToBePresentInElement(theInvPage.getCartItemCountElement(), "1"));

        //now we should click on the cart
        theInvPage.clickCart();

        //check we have progressed
        wait.until(ExpectedConditions.urlToBe(SauceDemoCart.CARTPAGE_URL));

        SauceDemoCart theCartpage= new SauceDemoCart(driver);
        theCartpage.confirmCheckout();

        wait.until(ExpectedConditions.urlToBe(SauceDemoCheckoutPage1.CHECKOUT_STEP_1_URL));


        SauceDemoCheckoutPage1 theCheckoutPage1 = new SauceDemoCheckoutPage1(driver);
        theCheckoutPage1.enterNameDetails("Laszlo", "Simity", "90210");

        wait.until(ExpectedConditions.urlToBe(SauceDemoCheckoutPage2.CHECKOUT_STEP_2_URL));

        SauceDemoCheckoutPage2 theCheckoutPage2 = new SauceDemoCheckoutPage2(driver);
        theCheckoutPage2.finishCheckout();

        wait.until(ExpectedConditions.urlToBe(SauceDemoCheckoutCompletePage.CHECKOUT_COMPLETE_URL));

        //Now Logout
        theInvPage.logout();

        Assert.assertFalse(driver.getCurrentUrl() == SauceDemoHomePage.HOME_PAGE_URL, "We are not logged out successfully");

    }
}
