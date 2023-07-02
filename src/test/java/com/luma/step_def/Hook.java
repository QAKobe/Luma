package com.luma.step_def;

import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import utils.ConfigReader;
import utils.DriverHelper;

public class Hook {

    WebDriver driver = DriverHelper.getDriver();

    @Before
    public void starter(){

        driver.get(ConfigReader.readProperty("url"));

    }

}
