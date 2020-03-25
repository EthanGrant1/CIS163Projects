package Project4;

import java.util.Calendar;
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

        double cost = 10;

        GregorianCalendar gTemp = new GregorianCalendar();
//        Date d = checkOut.getTime();
//        gTemp.setTime(d);
        gTemp = (GregorianCalendar) checkOut.clone();

        while (gTemp.after(checkIn)) {
            cost += 20;
            if (power > 1000)
                cost += 10;
            gTemp.add(Calendar.DATE, -1);

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