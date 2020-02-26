package Project3;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TentOnly extends CampSite {

    private int numberOfTenters;

    public TentOnly() {
    }

    public TentOnly(String guestName,
                    GregorianCalendar checkIn,
                    GregorianCalendar estimatedCheckOut,
                    GregorianCalendar actualCheckOut,
                    int numberOfTenters) {
        super(guestName, checkIn, estimatedCheckOut, actualCheckOut);
        this.numberOfTenters = numberOfTenters;
    }

    public int getNumberOfTenters() {
        return numberOfTenters;
    }

    public void setNumberOfTenters(int numberOfTenters) {
        this.numberOfTenters = numberOfTenters;
    }

    @Override
    public double getCost(GregorianCalendar checkOut) {
        Date checkInDate = checkIn.getTime();
        Date checkOutDate = checkOut.getTime();
        int days = (int) ((checkOutDate.getTime() - checkInDate.getTime()) / (1000*60*60*24));
        double cost = 0;
        for(int i = 0; i < days; i++) {
            if (numberOfTenters > 10)
                cost += 20;
            else
                cost += 10;
        }
        return cost;
    }

    @Override
    public String toString() {
        return "TentOnly{" +
                "numberOfTenters=" + numberOfTenters +
                super.toString() +
                '}';
    }

}
