package ru.aplana.test;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class autotest {
    private static WebDriver driver;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "src\\test\\driver\\chromedriver.exe");
        driver = new ChromeDriver();

        //driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
        driver.get("https://www.rgs.ru/");
    }

    @Test
    public void userLogin() throws InterruptedException {
        //step 2
        WebElement insuranceNavBarButton = driver.findElement(By.xpath("//*[@id=\"main-navbar-collapse\"]//a[contains(text(),'Страхование')]"));
        insuranceNavBarButton.click();
        //step 3
        WebElement travelingAbroadNavBarButton = driver.findElement(By.xpath("//*[@id='rgs-main-menu-insurance-dropdown']//a[contains(text(),'Выезжающим за рубеж')]"));
        travelingAbroadNavBarButton.click();
        //step4
        WebElement countNavBarButton = driver.findElement(By.xpath("//*[@class='thumbnail thumbnail-md text-center']//a[contains(text(),'Рассчитать')]"));
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].scrollIntoView(true);", countNavBarButton);
        countNavBarButton.click();
        //step5
        WebElement pageHeader = driver.findElement(By.xpath("//*[@class='page-header']"));
        String expectedText = "Страхование выезжающих за рубеж";
        Assert.assertEquals("Заголовок не соответствует ожидаемому",pageHeader.getText(), expectedText);

        //step6
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[@type='button'])[2]")));
        WebElement multipleTripsButton = driver.findElement(By.xpath("(//button[@type='button'])[2]"));
        multipleTripsButton.click();

        //step7, 8
        WebElement inputCountry = driver.findElement(By.xpath("//*[@id=\"Countries\"]"));
        inputCountry.click();
        inputCountry.sendKeys("Шенген");
        inputCountry.sendKeys(Keys.ARROW_DOWN);
        inputCountry.sendKeys(Keys.ENTER);



        WebElement selectCountry = driver.findElement(By.xpath("//*[@id=\"ArrivalCountryList\"]"));
        wait.until(ExpectedConditions.visibilityOf(selectCountry));
        Select countrySelector = new Select(selectCountry);
        countrySelector.selectByVisibleText("Испания");


        WebElement selectDate = driver.findElement(By.xpath("//div[contains(@class,'form-group') and contains(@class, 'margin-top-ms-1')]/input"));
        wait.until(ExpectedConditions.elementToBeClickable(selectDate));
        selectDate.sendKeys(" 01.11.2018");


        WebElement howManyDaysButton = driver.findElement(By.xpath("//label[text()=' Не более 90 дней ']"));
        wait.until(ExpectedConditions.elementToBeClickable(howManyDaysButton));
        howManyDaysButton.click();

        WebElement nameInputField = driver.findElement(By.xpath("//div[@class='form-group']/input[@class='form-control']"));
        nameInputField.sendKeys("Vasya Pupkin");

        WebElement bornDataInputField = driver.findElement(By.xpath("//div[contains(@class, 'form-group') and contains(@class, 'pull-left')]/input"));
        bornDataInputField.sendKeys(" 04.05.1980");

        WebElement activeRestButton = driver.findElement(By.xpath("(//div[contains(@class, 'toggle') and contains(@class, 'off') and contains(@class, 'toggle-rgs')])[1]"));
        jse.executeScript("arguments[0].scrollIntoView(true);", activeRestButton);
        activeRestButton.click();

        WebElement iAgreeCheckBox = driver.findElement(By.xpath("//*[@class='adaptive-checkbox-label'][text()=' Я согласен на обработку моих персональных данных в целях расчета страховой премии. ']"));
        wait.until(ExpectedConditions.elementToBeClickable(iAgreeCheckBox));
        Thread.sleep(1000);
        iAgreeCheckBox.click();
        Thread.sleep(1000);


        //step 9
        WebElement countButton = driver.findElement(By.xpath("//div[@class='form-footer']/button[@type='submit']"));
        jse.executeScript("arguments[0].scrollIntoView(true);", countButton);
        countButton.click();


        //step10 //Asserts
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'programs') and contains(@class, 'programs-three')]")));
        WebElement insuranceConditions = driver.findElement(By.xpath("//span[@class='text-bold'][text()=' Многократные поездки в течение года ']"));
        jse.executeScript("arguments[0].scrollIntoView(true);", insuranceConditions);
        Assert.assertEquals("Многократные поездки в течение года", insuranceConditions.getText());

        WebElement countryConditions = driver.findElement(By.xpath("//strong[@data-bind='text: Name'][text()='Шенген']"));
        Assert.assertEquals("Шенген", countryConditions.getText());

        WebElement nameConditions = driver.findElement(By.xpath("//span[text()='Застрахованный ']/following-sibling::*/span/strong"));
        Assert.assertEquals("VASYA PUPKIN", nameConditions.getText());

        WebElement dataConditions = driver.findElement(By.xpath("//span[text()='Застрахованный ']/following-sibling::*/span/span/strong"));
        Assert.assertEquals("04.05.1980", dataConditions.getText());

        WebElement activeRestConditions = driver.findElement(By.xpath("//span[text()='Активный отдых или спорт']/parent::*/following-sibling::*/span[@class='text-bold']"));
        Assert.assertEquals("Включен", activeRestConditions.getText());
    }

    @AfterClass
    public static void tearDown() {
       // driver.quit();
    }
}
