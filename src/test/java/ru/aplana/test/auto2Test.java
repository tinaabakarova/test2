package ru.aplana.test;

import org.junit.*;
import ru.aplana.java.classes.RgsSteps;


public class auto2Test {
    RgsSteps user = new RgsSteps();

    @Before
    public void before(){
        user.startUp();
    }

    @After
    public void after(){
        user.endTest();
    }

    @Test
    public void checkAllConditions() throws InterruptedException {
        user.openInsuranceNavBar();
        user.chooseCategory("Выезжающим за рубеж");
        user.clickFirstCountButton();
        user.checkPageHeader("Страхование выезжающих за рубеж");
        user.clickMultipleTripsButton();
        user.inputCountry("Шенген");
        user.selectCountry("Испания");
        user.inputData("05.11.2018");
        user.setHowManyDays();
        user.setNameAndDataBorn("Vasya Pupkin", "04.05.1980");
        user.clickActiveRestButton();
        user.clickIAgreeCheckBox();
        user.clickFinalCountButton();
        user.checkConditions("Шенген", "VASYA PUPKIN", "04.05.1980");
    }
}
