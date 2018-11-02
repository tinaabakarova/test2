package ru.aplana.java.classes;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

public class CommonSteps {
    WebDriver driver;
    String BASE_URL;
    JavascriptExecutor jse;
    WebDriverWait wait;


    public WebDriver getDriver() {
        return driver;
    }

    public void startUp(){
        System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
        driver = new ChromeDriver();
        jse = (JavascriptExecutor)driver;
        wait = new WebDriverWait(driver, 20);

        //driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
        driver.get(BASE_URL);
    }

    public void endTest(){
        driver.quit();
    }

    public void waitForVisible(By locator){
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForClicable(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForClicable(By locator){
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement findByXpath(String xpath){
        return driver.findElement(By.xpath(xpath));
    }

    public WebElement findByLocator(By locator){
        return driver.findElement(locator);
    }

    void click(By locator){
        findByLocator(locator).click();
    }

    void click(String xpath){
        findByXpath(xpath).click();
    }

    void checkElementText(WebElement element, String expectedText){
        Assert.assertEquals("Значения текст не соотвествует ожидаемому",
                expectedText, element.getText());
    }
}
