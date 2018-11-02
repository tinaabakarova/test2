package ru.aplana.java.classes;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class RgsSteps extends CommonSteps {
    private static final String BASE_RGS_URL = "https://www.rgs.ru/";
    private static final By openInsuranceNavBarButtonLocator = By.xpath("//*[@id=\"main-navbar-collapse\"]//a[contains(text(),'Страхование')]");
    private static final By navBarLocator = By.className("container-rgs-main-menu-links");
    private static final String categoryFormat = "//*[@id='rgs-main-menu-insurance-dropdown']//a[contains(text(),'%s')]";
    private static final By countNavBarButtonLocator = By.xpath("//*[@class='thumbnail thumbnail-md text-center']//a[contains(text(),'Рассчитать')]");
    private static final By multipleTripsButtonLocator = By.xpath("(//button[@type='button'])[2]");
    private static final By inputCountryFieldLocator = By.xpath("//*[@id=\"Countries\"]");
    private static final By selectCountryFieldLocator = By.xpath("//*[@id=\"ArrivalCountryList\"]");
    private static final By selectDateFieldLocator = By.xpath("//div[contains(@class,'form-group') and contains(@class, 'margin-top-ms-1')]/input");
    private static final By howManyDaysButtonLocator = By.xpath("//label[text()=' Не более 90 дней ']");
    private static final By nameInputFieldLocator = By.xpath("//div[@class='form-group']/input[@class='form-control']");
    private static final By bornDataInputFieldLocator = By.xpath("//div[contains(@class, 'form-group') and contains(@class, 'pull-left')]/input");
    private static final By activeRestButtonLocator = By.xpath("(//div[contains(@class, 'toggle') and contains(@class, 'off') and contains(@class, 'toggle-rgs')])[1]");
    private static final By iAgreeCheckBoxLocator = By.xpath("//*[@class='adaptive-checkbox-label'][text()=' Я согласен на обработку моих персональных данных в целях расчета страховой премии. ']");
    private static final By finalCountButtonLocator = By.xpath("//div[@class='form-footer']/button[@type='submit']");
    private static final By allConditionsFieldLocator = By.xpath("//div[contains(@class,'programs') and contains(@class, 'programs-three')]");
    private static final By insuranceConditionsFieldLocator = By.xpath("//span[@class='text-bold'][text()=' Многократные поездки в течение года ']");
    private static final By countryConditionsFieldLocator = By.xpath("//strong[@data-bind='text: Name'][text()='Шенген']");
    private static final By nameConditionsFieldLocator = By.xpath("//span[text()='Застрахованный ']/following-sibling::*/span/strong");
    private static final By dataConditionsFieldLocator = By.xpath("//span[text()='Застрахованный ']/following-sibling::*/span/span/strong");
    private static final By activeRestConditionsFieldLocator = By.xpath("//span[text()='Активный отдых или спорт']/parent::*/following-sibling::*/span[@class='text-bold']");

    public RgsSteps() {
        BASE_URL = BASE_RGS_URL;
    }

    public void openInsuranceNavBar(){
        click(openInsuranceNavBarButtonLocator);
        waitForVisible(navBarLocator);
    }

    public void chooseCategory(String categoryName){
       By categoryLocator = By.xpath(String.format(categoryFormat, categoryName));
       click(categoryLocator);
    }

    private void clickCountButton(By locator){
        WebElement countButton = findByLocator(locator);
        jse.executeScript("arguments[0].scrollIntoView(true);", countButton);
        countButton.click();
    }

    public void clickFirstCountButton(){
        clickCountButton(countNavBarButtonLocator);
    }

    public void checkPageHeader(String expectedText){
        WebElement header = findByXpath("//*[@class='page-header']");
        checkElementText(header, expectedText);
    }

    public void clickMultipleTripsButton(){
        waitForVisible(multipleTripsButtonLocator);
        click(multipleTripsButtonLocator);
    }

    public void inputCountry(String country){
        WebElement inputCountry = findByLocator(inputCountryFieldLocator);
        inputCountry.click();
        inputCountry.sendKeys(country);
        inputCountry.sendKeys(Keys.ARROW_DOWN);
        inputCountry.sendKeys(Keys.ENTER);
    }

    public void selectCountry(String country){
        WebElement selectCountry = findByLocator(selectCountryFieldLocator);
        waitForVisible(selectCountryFieldLocator);
        Select countrySelector = new Select(selectCountry);
        countrySelector.selectByVisibleText(country);
    }

    public void inputData(String data){
        WebElement inputData = findByLocator(selectDateFieldLocator);
        waitForClicable(inputData);
        inputData.sendKeys(" " + data);
    }

    public void setHowManyDays(){
        WebElement howManyDaysButton = findByLocator(howManyDaysButtonLocator);
        waitForClicable(howManyDaysButton);
        howManyDaysButton.click();
    }

    public void setNameAndDataBorn(String name, String bornDate){
        WebElement nameInput = findByLocator(nameInputFieldLocator);
        WebElement dataInput = findByLocator(bornDataInputFieldLocator);
        nameInput.sendKeys(name);
        dataInput.sendKeys(" " + bornDate);
    }

    public void clickActiveRestButton(){
        WebElement activeRestButton = findByLocator(activeRestButtonLocator);
        jse.executeScript("arguments[0].scrollIntoView(true);", activeRestButton);
        activeRestButton.click();
    }

    public void clickIAgreeCheckBox() throws InterruptedException {
        waitForClicable(iAgreeCheckBoxLocator);
        Thread.sleep(1000);
        click(iAgreeCheckBoxLocator);
        Thread.sleep(1000);
    }

    public void clickFinalCountButton(){
        clickCountButton(finalCountButtonLocator);
    }

    public void checkConditions(String country, String name, String data){
        waitForVisible(allConditionsFieldLocator);
        WebElement insuranceConditions = findByLocator(insuranceConditionsFieldLocator);
        jse.executeScript("arguments[0].scrollIntoView(true);", insuranceConditions);
        checkElementText(insuranceConditions, "Многократные поездки в течение года");
        checkElementText(findByLocator(countryConditionsFieldLocator), country);
        checkElementText(findByLocator(nameConditionsFieldLocator), name);
        checkElementText(findByLocator(dataConditionsFieldLocator), data);
        checkElementText(findByLocator(activeRestConditionsFieldLocator), "Включен");

    }
}
