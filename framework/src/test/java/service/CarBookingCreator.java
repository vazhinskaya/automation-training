package service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import test.java.model.CarBooking;
import test.java.service.TestDataReader;



public class CarBookingCreator {

    public static final DateTimeFormatter DATA_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("h:mm a");
    public static final String TESTDATA_PICK_UP_LOCATION = "testdata.rental.location";
    public static final String TESTDATA_RETURN_LOCATION_FAR_AWAY = "testdata.return.location.faraway";
    public static final String TESTDATA_PICK_UP_TIME = "testdata.rental.time";
    public static final String TESTDATA_RETURN_TIME = "testdata.return.time";
    public static final String TESTDATA_PICK_UP_TIME_PLACE_CLOSER = "testdata.rental.time.place.closer";
    public static final String TESTDATA_RETURN_TIME_PLACE_CLOSER = "testdata.return.time.place.closer";
    public static final String TESTDATA_RETURN_LOCATION_IMPOSSIBLE = "testdata.return.location.impossible";
    public static final String TESTDATA_SAME_TIME = "testdata.sametime";
    public static final String TESTDATA_PICK_UP_LOCATION_TO_CHANGE = "testdata.rental.location.to_change";
    public static final String TESTDATA_RETURN_LOCATION = "testdata.return.location";
    public static final String TESTDATA_CASE_4_EXPECTED_CURRENCY = "testdata.case4.expected.currency";


    public static CarBooking withRentalLocation() {
        return new CarBooking(TestDataReader.getTestData(TESTDATA_RENTAL_LOCATION), null);
    }

    public static CarBooking withRentalReturnLocation() {
        return new CarBooking(
                TestDataReader.getTestData(TESTDATA_RENTAL_LOCATION),
                TestDataReader.getTestData(TESTDATA_RETURN_LOCATION_FAR_AWAY),
                LocalTime.parse(TestDataReader.getTestData(TESTDATA_RENTAL_TIME), TIME_FORMATTER),
                LocalTime.parse(TestDataReader.getTestData(TESTDATA_RETURN_TIME), TIME_FORMATTER));
    }

    public static CarBooking withRentalReturnLocationAndTime() {
        return new CarBooking(
                TestDataReader.getTestData(TESTDATA_RENTAL_LOCATION),
                TestDataReader.getTestData(TESTDATA_RETURN_LOCATION_FAR_AWAY),null, null);
    }

    public static CarBooking withRentalLocationAndCloserTime() {
        return new CarBooking(
                TestDataReader.getTestData(TESTDATA_RENTAL_LOCATION),null,
                LocalTime.parse(TestDataReader.getTestData(TESTDATA_RENTAL_TIME_PLACE_CLOSER), TIME_FORMATTER),
                LocalTime.parse(TestDataReader.getTestData(TESTDATA_RETURN_TIME_PLACE_CLOSER), TIME_FORMATTER));
    }

    public static CarBooking withRentalLocationAndSameTime() {
        return new CarBooking(
                TestDataReader.getTestData(TESTDATA_RENTAL_LOCATION), null,
                LocalTime.parse(TestDataReader.getTestData(TESTDATA_SAME_TIME), TIME_FORMATTER),
                LocalTime.parse(TestDataReader.getTestData(TESTDATA_SAME_TIME), TIME_FORMATTER));
    }

    public static CarBooking withRentalReturnImpossibleLocation() {
        return new CarBooking(TestDataReader.getTestData(TESTDATA_RENTAL_LOCATION), TestDataReader.getTestData(TESTDATA_RETURN_LOCATION_IMPOSSIBLE));
    }

    public static CarBooking withRentalReturnDifferentLocation() {
        return new CarBooking(TestDataReader.getTestData(TESTDATA_RENTAL_LOCATION_TO_CHANGE), TestDataReader.getTestData(TESTDATA_RETURN_LOCATION));
    }
}
