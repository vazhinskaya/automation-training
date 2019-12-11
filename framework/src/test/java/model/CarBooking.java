package test.java.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class CarBooking {
    private String rentalLocation;
    private String returnLocation;
    private LocalDate dateNow;
    private LocalTime rentalTime;
    private LocalTime returnTime;

    public CarBooking(String rentalLocation, String returnLocation) {
        this.rentalLocation = rentalLocation;
        this.returnLocation = returnLocation;
        this.dateNow = LocalDate.now();
        this.rentalTime = LocalTime.NOON;
    }

    public CarBooking(String rentalLocation, String returnLocation, LocalTime localTime, LocalTime returnTime) {
        this.rentalLocation = rentalLocation;
        this.returnLocation = returnLocation;
        this.dateNow = LocalDate.now();
        this.rentalTime = localTime;
        this.returnTime = returnTime;
    }

    public String getRentalLocation() {

        return rentalLocation;
    }

    public void setRentalLocation(String rentalLocation) {
        this.rentalLocation = rentalLocation;
    }

    public String getReturnLocation() {
        return returnLocation;
    }

    public void setReturnLocation(String returnLocation) {
        this.returnLocation = returnLocation;
    }

    public LocalDate getDateNow() {
        return dateNow;
    }

    public void setDateNow(LocalDate dateNow) {
        this.dateNow = dateNow;
    }

    public LocalTime getRentalTime() {
        return rentalTime;
    }

    public void setRentalTime(LocalTime rentalTime) {
        this.rentalTime = rentalTime;
    }

    public LocalTime getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(LocalTime returnTime) {
        this.returnTime = returnTime;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        CarBooking that = (CarBooking) obj;

        if (getRentalLocation () != null ? !getRentalLocation().equals(that.getRentalLocation()) : that.getRentalLocation() != null)
            return false;
        if (getReturnLocation() != null ? !getReturnLocation().equals(that.getReturnLocation()) : that.getReturnLocation() != null)
            return false;
        if (getDateNow() != null ? !getDateNow().equals(that.getDateNow()) : that.getDateNow() != null) return false;
        if (getRentalTime() != null ? !getRentalTime().equals(that.getRentalTime()) : that.getRentalTime() != null)
            return false;
        return getReturnTime() != null ? getReturnTime().equals(that.getReturnTime()) : that.getReturnTime() == null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = getRentalLocation() != null ? getRentalLocation().hashCode() : 0;
        result = prime * result + (getReturnLocation() != null ? getReturnLocation().hashCode() : 0);
        result = prime * result + (getDateNow() != null ? getDateNow().hashCode() : 0);
        result = prime * result + (getRentalTime() != null ? getRentalTime().hashCode() : 0);
        result = prime * result + (getReturnTime() != null ? getReturnTime().hashCode() : 0);
        return result;
    }
}
