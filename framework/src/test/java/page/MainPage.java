package test.java.page;

import page.CarPage;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.LocalDate;
import java.time.LocalTime;

import static service.CarBookingCreator.DATA_FORMATTER;
import static service.CarBookingCreator.TIME_FORMATTER;


public class MainPage extends AbstractPage {
    private static final Logger logger = LogManager.getLogger(MainPage.class);
    private final String MainPage_URL = "https://www.easyterra.com/";

    @FindBy(xpath = "// div[@class='et-location-input_ et-location-input__input--invisible']")
    private WebElement rentalLocation;

    @FindBy(xpath = "// div[@class='et-location-input_ng-touched']")
    private WebElement returnLocation;

    @FindBy(xpath = "//div[@class='et-date-input ng-not-empty ng-touched']")
    private WebElement rentalDate;

    @FindBy(xpath = "//div[@class='et-time-input']")
    private WebElement rentalTime;

    @FindBy(xpath = "//div[@class='et-date-input']")
    private WebElement returnDate;

    @FindBy(xpath = "//div[@ class='et-time-input']")
    private WebElement returnTime;

    @FindBy(xpath = "//button[@class='et-search-query__submit']")
    private WebElement selectCar;

   public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public MainPage openPage() {
        driver.navigate().to(MainPage_URL);
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);
        logger.log(Level.INFO, "Main page opened");
        return this;
    }



    public MainPage inputRentalLocation(String picLoc) {
        rentalLocation.sendKeys(picLoc);
        rentalLocation.sendKeys(picLoc);
        logger.info("Filled in rental field with" + picLoc);
        return this;
    }


    public MainPage inputReturnLocation(String returnLoc) {
        returnLocation.sendKeys(returnLoc);
        logger.info("Filled in return field with" + returnLoc);
        return this;
    }

    public MainPage inputRentalDate(LocalDate picDate) {
        rentalDate.clear();
        rentalDate.sendKeys(picDate.format(DATA_FORMATTER));
        logger.info("Filled in the rental date field");
        return this;
    }

    public MainPage inputReturnDate(LocalDate dropDate) {
        returnDate.clear();
        returnDate.sendKeys(dropDate.format(DATA_FORMATTER));
        logger.info("Completed return date field");
        return this;
    }

    public MainPage selectCar() {
        selectCar.click();
        logger.info("Car selected");
        return this;
    }

    public MainPage selectRentalTime(LocalTime picTime) {
        new Select(rentalTime).selectByVisibleText(picTime.format(TIME_FORMATTER));
        logger.info("Rental time selected");
        return this;
    }

    public MainPage selectReturnTime(LocalTime dropTime) {
        new Select(returnTime).selectByVisibleText(dropTime.format(TIME_FORMATTER));
        logger.info("Return time selected");
        return this;
    }
}