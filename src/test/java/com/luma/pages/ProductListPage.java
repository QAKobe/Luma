package com.luma.pages;

import io.cucumber.java.en_old.Ac;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.BrowserUtils;

import java.util.ArrayList;
import java.util.List;

public class ProductListPage {

    public ProductListPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@id='mode-list']")
    WebElement listView;

    @FindBy(css = "select[id='sorter']")
    WebElement sortByDropDown;

    @FindBy(xpath = "//select[@id='limiter']")
    List<WebElement> showDropDown;

    @FindBy(xpath = "//span[contains(@id,'product-price')]")
    List<WebElement> allProducts;

    @FindBy(css = "button[title='Add to Cart']")
    List<WebElement> addButton;

    @FindBy(xpath = "//div[@data-block='minicart']")
    WebElement cartButton;

    @FindBy(xpath = "//span[contains(.,'View and Edit Cart')]")
    WebElement editButton;


    public void listSortAndShowAll(WebDriver driver) throws InterruptedException {

        listView.click();

        BrowserUtils.selectBy(sortByDropDown, "Price", "text");
        Actions actions = new Actions(driver);
        actions.scrollByAmount(4000, 4000).build().perform();
        Thread.sleep(5000);
        for (int i = 1; i < showDropDown.size(); i++) {
            BrowserUtils.selectBy(showDropDown.get(i), "25", "text");
        }


    }

    public void validateSortOrder() {

        List<Double> listOfProducts = new ArrayList<>();
        List<Double> listOfSortEdPrices = new ArrayList<>();
        for (int i = 0; i < allProducts.size(); i++) {
            listOfProducts.add(Double.valueOf(allProducts.get(i).getText().replace("$", "")));
            listOfSortEdPrices.add(Double.valueOf(allProducts.get(i).getText().replace("$", "")));
        }

        for (int i = 0; i < listOfSortEdPrices.size(); i++) {
            double temp;
            for (int j = i + 1; j < listOfSortEdPrices.size(); j++) {
                if (listOfSortEdPrices.get(i) > listOfSortEdPrices.get(j)) {
                    temp = listOfSortEdPrices.get(i);
                    listOfSortEdPrices.set(i, listOfSortEdPrices.get(j));
                    listOfSortEdPrices.set(j, temp);
                }
            }
        }

        for (int i = 0; i < listOfSortEdPrices.size(); i++) {
            Assert.assertEquals(listOfProducts.get(i), listOfSortEdPrices.get(i));
        }
        System.out.println(listOfSortEdPrices + " sorted");
        System.out.println(listOfProducts + " unsorted");


    }

    public void addProduct() throws InterruptedException {
        addButton.get(0).click();
        Thread.sleep(4000);
        cartButton.click();
        Thread.sleep(4000);
        editButton.click();
    }
}
