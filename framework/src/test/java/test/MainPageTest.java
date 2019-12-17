package test;

import service.CarBookingCreator;
import test.java.model.CarBooking;
import test.java.test.CommonConditions;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MainPageTest extends CommonConditions {
    private MainPageTest MainPage = new MainPageTest(driver);


    @Test (testName = "Testcase 1: Can’t return the car at the same time as take")
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

    @Test (testName = "Testcase 2: Can not take a car in a city where there is no service of this company")
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

    @Test (testName = "Testcase 3: Can not rent the car for a long time")
    public void cantRentForLongTime() {
        CarBooking CarBooking = CarBookingCreator.withRentalLocation();
        MainPage.openPage()
                .inputRentalLocation(CarBooking.getRentalLocation())
                .inputRentalDate(CarBooking.getDateNow())
                .inputReturnDate(CarBooking.getDateNow().plusDays(35))
                .selectCar();
        Assert.assertEquals("An error occurred while searching. Please provide a new quotation", MainPage.getErrorMessage());
    }

    @Test (testName = "Testcase 4: Rent a car for half an hour.")
    public void rentForHalfAnHour() {
        CarBooking CarBooking = CarBookingCreator.withRentalLocationAndCloserTime();
        MainPage.openPage()
                .inputRentalLocation(CarBooking.getRentalLocation())
                .selectRentalTime(CarBooking.getRentalTime())
                .selectReturnTime(CarBooking.getReturnTime())
                .selectCar();
        Assert.assertEquals("You will pay both for rent for 24 hours, because this is the policy of the company", MainPage.getErrorMessage());
    }

    @Test (testName = "Testcase 5: Can not book car without rental location")
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

    @Test (testName = "Testcase 6: Booking car for a long distance and short period of time")
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
        Assert.assertEquals("The Rental Location you have selected is Sold Out during the dates requested. Please try another Avis location for renting.", MainPage.getErrorMessage());
    }

    @Test (testName = "Testcase 8: Can not rent a car if you are not 19 years old")
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

    @Test (testName = "Testcase 9: Can not rent a car if you are not 19 years old")
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


    @Test (testName = "Testcase 10: returnTimeGreaterThanRentalTimeCarBooking")
    public void returnTimeGreaterThanRentalTimeCarBooking() {
        CarBooking bookingCar = CarBookingCreator.withRentalLocation();
        MainPage.openPage()
                .inputRentalLocation(bookingCar.getRentalLocation())
                .inputRentalDate(bookingCar.getDateNow())
                .inputReturnDate(bookingCar.getDateNow())
                .selectRentalTime(bookingCar.getRentalTime().plusHours(4))
                .selectReturnTime(bookingCar.getRentalTime().plusHours(2))
                .selectCar();
        Assert.assertEquals("Your return time has already passed. Please select a new time", MainPage.getErrorMessage());
    }

}
