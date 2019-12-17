package page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResultPage extends AbstractPage{
    private static final Logger logger = LogManager.getLogger(test.java.page.MainPage.class);
    private final String CAR_PAGE_URL = "https://www.easyterra.com/q/results";

    public ResultPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

        @FindBy(xpath = "// div[@class='et-popover-button__input']")
        private WebElement currencyBtn;

        @FindBy(id = "cur_PLN")
        private WebElement plnCurrency;

        @FindBy(className = "rch-icon")
        private WebElement currentCurrency;


        @Override
        public ResultPage openPage() {
            driver.navigate().to(PAGE_URL);
            logger.info("Page with results opened");
            return this;
        }

        public ResultPage changeCurrencyToPLN()
        {
            currencyBtn.click();
            plnCurrency.click();
            logger.info("currency changed to PLN-zl");
            return this;
        }

        public String getCurrentCurrency()
        {
            return currentCurrency.getText();
        }
    }

