package com.luma.step_def;

import com.luma.pages.CartPage;
import com.luma.pages.MainPage;
import com.luma.pages.ProductListPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import utils.DriverHelper;


import java.util.Map;

public class LumaStepDefs {


    WebDriver driver = DriverHelper.getDriver();

    MainPage mainPage = new MainPage(driver);
    CartPage cartPage = new CartPage(driver);
    ProductListPage productListPage = new ProductListPage(driver);

    @When("User lands on main page user hovers over Gear and clicks Bags")
    public void user_lands_on_main_page_user_hovers_over_gear_and_clicks_bags() throws InterruptedException {
        mainPage.clickOnBags(driver);
    }

    @Then("User clicks on list view button and chooses price from sort by button and cliks show all")
    public void user_clicks_on_list_view_button_and_chooses_price_from_sort_by_button_and_cliks_show() throws InterruptedException {
        productListPage.listSortAndShowAll(driver);
    }

    @Then("User validates whether the price option from the dropdown is showing in ascending order")
    public void user_validates_whether_the_price_option_from_the_dropdown_is_showing_in_ascending_order() {
        productListPage.validateSortOrder();
    }

    @Then("User adds one product to cart and clicks cart sign then clicks viewEdit cart link")
    public void user_adds_one_product_to_cart_and_clicks_cart_sign_then_clicks_view_edit_cart_link() throws InterruptedException {
        productListPage.addProduct();
    }

    @Then("User verifies country is United States and chooses state provides zipcode")
    public void user_verifies_country_is_united_states_and_chooses_state_provides_zipcode(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
        Map<String, String> data = dataTable.asMap();
        cartPage.ChooseCountryState(data.get("State"), data.get("zipCode"));

    }

    @Then("User clicks on radio button for Flat Rate and Best Way retrieves Order Total for both")
    public void user_clicks_on_radio_button_for_flat_rate_and_Best_Way_retrieves_order_total() throws InterruptedException {

        cartPage.compareRecords(driver);
    }

}
