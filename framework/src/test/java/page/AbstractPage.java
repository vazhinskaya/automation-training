package test.java.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractPage {
    protected final int WAIT_TIMEOUT_SECONDS = 30;
    protected WebDriver driver;

    public AbstractPage (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public abstract AbstractPage openPage();
}
