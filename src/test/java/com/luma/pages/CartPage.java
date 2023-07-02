package com.luma.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import utils.BrowserUtils;

import java.util.List;

public class CartPage {


    public CartPage(WebDriver driver) {
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(xpath = "//select[@class='select']")
    List<WebElement> twoDropDowns;

    @FindBy(xpath = "//input[@name='postcode']")
    WebElement postalCode;

    @FindBy(xpath = "//input[@value='flatrate_flatrate']")
    WebElement flatRate;

    @FindBy(xpath = "//input[@value='tablerate_bestway']")
    WebElement bestWay;

    @FindBy(xpath = "//div[@id='block-shipping']//div[@role='tab']")
    WebElement summary;

    public void ChooseCountryState(String state, String zipCode) throws InterruptedException {

        Thread.sleep(4000);
        summary.click();
        Thread.sleep(2000);
        for (int i = 1; i < twoDropDowns.size(); i++) {
            BrowserUtils.selectBy(twoDropDowns.get(i), state, "text");
        }

        postalCode.sendKeys(zipCode);
    }

    public void compareRecords( WebDriver driver) throws InterruptedException {
        Thread.sleep(6000);

        if (!flatRate.isSelected()){
            flatRate.click();
            Thread.sleep(3000);
            BrowserUtils.getScreenShot(driver, "FlatRate");
        }
        Thread.sleep(4000);
        if (!bestWay.isSelected()){
            bestWay.click();
            Thread.sleep(3000);
            BrowserUtils.getScreenShot(driver, "BestWay");
        }


    }
}
