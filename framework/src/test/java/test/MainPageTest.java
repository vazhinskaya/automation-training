package test;

import service.CarBookingCreator;
import test.java.model.CarBooking
;
import test.java.test.CommonConditions;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MainPageTest extends CommonConditions {
    private MainPageTest MainPage = new MainPageTest(driver);


    @Test
    public void cantReturnAtTheSameTimeAsRent() {
        CarBooking CarBooking = CarBookingCreator.withRentalLocationAndSameTime();
        MainPage.openPage()
                .inputRentalLocation(CarBooking.getRentalLocation())
                .inputRentalDate(CarBooking.getDateNow().plusDays(2))
                .inputReturnDate(CarBooking.getDateNow().plusDays(2))
                .selectRentalTime(CarBooking.getRentalTime())
                .selectReturnTime(CarBooking.getReturnTime())
                .selectCar();
        Assert.assertEquals("If rental period is shorter than 1 day, the pick-up and return times cannot be the same", MainPage.getErrorMessage());
    }


    @Test
    public void cantRentThereIsNoServiceOfThisCompany() {
        CarBooking CarBooking = CarBookingCreator.withRentalReturnImpossibleLocation();
        MainPage.openPage()
                .inputRentalLocation(CarBooking.getRentalLocation())
                .inputRentalDate(CarBooking.getDateNow().plusDays(1))
                .selectRentalTime(CarBooking.getRentalTime())
                .selectReturnTime(CarBooking.getReturnTime())
                .selectCar();
        Assert.assertEquals("No results found", MainPage.getErrorMessage());
    }

    @Test
    public void cantRentForLongTime() {
        CarBooking CarBooking = CarBookingCreator.withRentalLocation();
        MainPage.openPage()
                .inputRentalLocation(CarBooking.getRentalLocation())
                .inputRentalDate(CarBooking.getDateNow())
                .inputReturnDate(CarBooking.getDateNow().plusDays(35))
                .selectCar();
        Assert.assertEquals("An error occurred while searching. Please provide a new quotation", MainPage.getErrorMessage());
    }

    @Test
    public void rentForHalfAnHour() {
        CarBooking CarBooking = CarBookingCreator.withRentalLocationAndCloserTime();
        MainPage.openPage()
                .inputRentalLocation(CarBooking.getRentalLocation())
                .selectRentalTime(CarBooking.getRentalTime())
                .selectReturnTime(CarBooking.getReturnTime())
                .selectCar();
        Assert.assertEquals("You will pay both for rent for 24 hours, because this is the policy of the company", MainPage.getErrorMessage());
    }

    @Test
    public void CarBookingWithoutRentalLocation() {
        CarBooking CarBooking = CarBookingCreator.withRentalLocation();
        MainPage.openPage()
                .inputRentalDate(CarBooking.getDateNow())
                .inputReturnDate(CarBooking.getDateNow().plusDays(3))
                .selectRentalTime(CarBooking.getRentalTime())
                .selectReturnTime(CarBooking.getReturnTime().plusHours(3))
                .selectCar();
        Assert.assertEquals("Please enter a Pick-up Location", MainPage.getErrorMessage());
    }

    @Test
    public void CarBookingForLongDistanceAndShortPeriodOfTime() {
        CarBooking CarBooking = CarBookingCreator.withRentalLocation();
        MainPage.openPage()
                .inputRentalLocation(CarBooking.getRentalLocation())
                .inputReturnLocation(CarBooking.getRentalLocation())
                .inputRentalDate(CarBooking.getDateNow().plusDays(2))
                .inputReturnDate(CarBooking.getDateNow().plusDays(2))
                .selectRentalTime(CarBooking.getRentalTime())
                .selectReturnTime(CarBooking.getReturnTime().plusHours(1))
                .selectCar();
        Assert.assertEquals("The Rental Location you have selected is Sold Out during the dates» requested. Please try another Avis location for renting.", MainPage.getErrorMessage());
    }

    @Test
    public void cantRentIfNotYearsOld() {
        CarBooking CarBooking = CarBookingCreator.withRentalLocation();
        MainPage.openPage()
                .inputRentalLocation(CarBooking.getRentalLocation())
                .inputReturnLocation(CarBooking.getRentalLocation())
                .inputRentalDate(CarBooking.getDateNow().plusDays(5))
                .inputReturnDate(CarBooking.getDateNow().plusDays(15))
                .selectRentalTime(CarBooking.getRentalTime().plusHours(1))
                .selectReturnTime(CarBooking.getReturnTime().plusHours(1))
                .selectCar();
        Assert.assertEquals("Unfortunately you are too young to book this car", MainPage.getErrorMessage());
    }

    @Test
    public void pastRentalTimeCarBooking() {
        CarBooking CarBooking = CarBookingCreator.withRentalLocation();
        MainPage.openPage()
                .inputRentalLocation(CarBooking.getRentalLocation())
                .inputRentalDate(CarBooking.getDateNow())
                .inputReturnDate(CarBooking.getDateNow().plusDays(4))
                .selectRentalTime(CarBooking.getRentalTime().minusHours(4))
                .selectReturnTime(CarBooking.getReturnTime().plusHours(10))
                .selectCar();
        Assert.assertEquals("You selected a date/time prior to the current time", MainPage.getErrorMessage());
    }


    @Test
    public void returnTimeGreaterThanRentalTimeCarBooking() {
        CarBooking bookingCar = CarBookingCreator.withRentalLocation();
        MainPage.openPage()
                .inputRentalLocation(bookingCar.getRentalLocation())
                .inputRentalDate(bookingCar.getDateNow())
                .inputReturnDate(bookingCar.getDateNow())
                .selectRentalTime(bookingCar.getRentalTime().plusHours(4))
                .selectReturnTime(bookingCar.getRentalTime().plusHours(2))
                .selectCar();
        Assert.assertEquals("Whoops! Your return time has already passed. Please select a new time", MainPage.getErrorMessage());
    }

    @Test
    public void CarBooking() {
        CarBooking CarBooking = CarBookingCreator.withRentalLocation();
        String bookingInforentalLocation =
                MainPage.CarBooking(CarBooking).rentalLocationInfo();
        assertThat(bookingInforentalLocation, is(equalTo(CarBooking.getRentalLocation())));
    }

}
