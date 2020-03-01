package Project3;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.time.temporal.ChronoUnit;

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

        // Initial cost of 0
        double cost = 0.0;

        GregorianCalendar gTemp = new GregorianCalendar();
        gTemp = (GregorianCalendar) checkOut.clone();

        while(gTemp.after(checkIn)){
            if(numberOfTenters > 10)
                cost += 20;
            else
                cost += 10;
            gTemp.add(Calendar.DATE, -1);
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