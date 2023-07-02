package utils;


import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Set;

public class BrowserUtils {
    public static void selectBy(WebElement location, String value, String methodName) {

        Select select = new Select(location);

        switch (methodName) {
            case "text":
                select.selectByVisibleText(value);
                break;
            case "value":
                select.selectByValue(value);
                break;
            case "index":
                select.selectByIndex(Integer.parseInt(value));
                break;
            default:
                System.out.println("Method name is not available, use text, value or index");
        }
    }

    public static String getText(WebElement element) {
        return element.getText().trim();
    }

    public static String getTitleWithJS(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js.executeScript("return document.title").toString();
    }

    public static void clickWithJS(WebDriver driver, WebElement element) {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", element);
    }

    public static void scrollViewWithJS(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true)", element);
    }

    public static void scrollWithPoint(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Point point = element.getLocation();
        int xCoordinate = point.getX();
        int yCoordinate = point.getY();
        js.executeScript("window.scrollTo(" + xCoordinate + "," + yCoordinate + ")");
    }

    public static void switchById(WebDriver driver, String mainId) {

        Set<String> allPageIds = driver.getWindowHandles();
        for (String id : allPageIds) {
            if (!id.equals(mainId)) {
                driver.switchTo().window(id);
            }
        }


    }

    public static void switchByTitle(WebDriver driver, String title) {
        Set<String> allPagesIds = driver.getWindowHandles();
        for (String id : allPagesIds) {
            driver.switchTo().window(id);
            if (driver.getTitle().contains(title)) {
                break;
            }

        }


    }

    //takes screenshot for investigation purposes. Call it when needed
    public static void getScreenShot(WebDriver driver, String packageName) {
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String location = System.getProperty("user.dir") + "/src/java/screenshot/" + packageName + "/";
        try {
            String fileName = "screenshot_" + System.currentTimeMillis() + ".png";
            FileUtils.copyFile(file, new File(location + fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void takeScreenShot(Scenario scenario, WebDriver driver) {

        Date currentDate = new Date();
        String screenShotFileName = currentDate.toString().replace(":", "-");
        if (!scenario.isFailed()) {
            File screenShotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(screenShotFile, new File("src/test/java/screenshot/" + screenShotFileName + ".png"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
