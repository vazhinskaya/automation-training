package test;

import page.ResultPage;
import service.CarBookingCreator;
import test.java.model.CarBooking;
import test.java.test.CommonConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ResultPageTests extends CommonConditions{
    private static final String TESTDATA_CASE_4_EXPECTED_CURRENCY = "testdata.case4.expected.currency";

    @Test(testName = "Testcase 7 : Can change currency")
    public void changingCurrency(){
        String currentCurrency = new ResultPage(driver)
                .openPage()
                .changeCurrencyToPLN()
                .getCurrentCurrency();
        assertThat(currentCurrency, is(equalTo(test.java.service.TestDataReader.getTestData(TESTDATA_CASE_4_EXPECTED_CURRENCY))));
    }
}
