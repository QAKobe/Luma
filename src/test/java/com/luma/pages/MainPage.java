package com.luma.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class MainPage {

    public MainPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[contains(.,'Gear')]")
    WebElement gear;

    @FindBy(xpath = "//a[contains(.,'Bags')]")
    WebElement bags;

    public void clickOnBags(WebDriver driver) throws InterruptedException {

        Thread.sleep(2000);
        Actions actions = new Actions(driver);
        actions.moveToElement(gear).click(bags).build().perform();

    }


}
