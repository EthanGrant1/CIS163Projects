package Project3;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class RV extends CampSite {

private int power;

    public RV() {
    }

    public RV(String guestName, GregorianCalendar checkIn, GregorianCalendar estimatedCheckOut, GregorianCalendar actualCheckOut, int power) {
        super(guestName, checkIn, estimatedCheckOut, actualCheckOut);
        this.power = power;
        }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public double getCost(GregorianCalendar checkOut) {
        Date checkInDate = checkIn.getTime();
        Date checkOutDate = checkOut.getTime();
        int days = (int) ((checkOutDate.getTime() - checkInDate.getTime()) / (1000*60*60*24));
        //FIX ME days is 99 when it should be 100 for 12/20/19 to 3/29/20
        double cost = 10;
        for(int i = 0; i < days; i++) {
            if (power > 1000)
                cost += 30;
            else
                cost += 20;
        }
        return cost;
    }

    @Override
    public String toString() {
        return "RV{" +
                "power=" + power +
                super.toString() +
                '}';
    }
}