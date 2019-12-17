package page;

import test.java.page.AbstractPage;
import test.java.page.MainPage;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CarPage extends AbstractPage {
    private static final Logger logger = LogManager.getLogger(MainPage.class);
    private final String CAR_PAGE_URL = "https://www.easyterra.com/q/results";

    public CarPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(xpath = "//div[@class='et-preferred-result result--economy']")
    private WebElement rentalLocationInfo;


    @Override
    public AbstractPage openPage() {
        driver.navigate().to(CAR_PAGE_URL);
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);
        logger.log(Level.INFO, "Car page opened");
        return this;
    }

    public String rentalLocationInfo() {
        return rentalLocationInfo.getText();
    }
}


