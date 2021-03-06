package Project3;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.time.temporal.ChronoUnit;

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
        // Initial cost of 10
        double cost = 10;

        GregorianCalendar gTemp = new GregorianCalendar();
        gTemp = (GregorianCalendar) checkOut.clone();

        while(gTemp.after(checkIn)){
            if(power > 1000)
                cost += 30;
            else
                cost += 20;
            gTemp.add(Calendar.DATE, -1);
        }
        return cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        else if (o instanceof RV){
            RV rv = (RV) o;
            //Checks to make sure everything is equal
            if (this.power == rv.power &&
                    this.checkIn.equals(rv.checkIn) &&
                    this.estimatedCheckOut.equals(rv.estimatedCheckOut) &&
                    this.guestName.equals(rv.guestName)) {
                if (this.actualCheckOut == null && rv.actualCheckOut == null)
                    return true;
                else if(this.actualCheckOut != null && rv.actualCheckOut != null
                        && this.actualCheckOut.equals(rv.actualCheckOut))
                    return true;
            }
        }
        return false;
    }


    @Override
    public String toString() {
        return "RV{" +
                "power=" + power +
                super.toString() +
                '}';
    }
}