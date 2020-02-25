package Project3;

import java.util.Calendar;
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

        double cost = 0.0;

        if (this.getNumberOfTenters() < 10 &&
                this.checkIn.get(Calendar.YEAR) == this.actualCheckOut.get(Calendar.YEAR)) {

                for (int i = this.checkIn.get(Calendar.DAY_OF_YEAR);
                     i <= this.actualCheckOut.get(Calendar.DAY_OF_YEAR); i++) {

                    cost += 10;

                }
        }

        if (this.getNumberOfTenters() > 10 &&
                this.checkIn.get(Calendar.YEAR) == this.actualCheckOut.get(Calendar.YEAR)) {

                for (int i = this.checkIn.get(Calendar.DAY_OF_YEAR);
                     i <= this.actualCheckOut.get(Calendar.DAY_OF_YEAR); i++) {

                    cost += 20;

                }

        }

        if (this.getNumberOfTenters() < 10 &&
                this.checkIn.get(Calendar.YEAR) < this.actualCheckOut.get(Calendar.YEAR)) {

                for (int i = this.checkIn.get(Calendar.YEAR);
                     i <= this.actualCheckOut.get(Calendar.YEAR); i++) {

                    for (int j = this.checkIn.get(Calendar.DAY_OF_YEAR);
                         j <= this.actualCheckOut.get(Calendar.DAY_OF_YEAR); j++) {

                        cost += 10;

                    }
                }

        }

        if (this.getNumberOfTenters() > 10 &&
                (this.checkIn.get(Calendar.YEAR) < this.actualCheckOut.get(Calendar.YEAR))) {

                for (int i = this.checkIn.get(Calendar.YEAR);
                     i <= this.actualCheckOut.get(Calendar.YEAR); i++) {

                    for (int j = this.checkIn.get(Calendar.DAY_OF_YEAR);
                         j <= this.actualCheckOut.get(Calendar.DAY_OF_YEAR); j++) {

                        cost += 20;

                    }
                }

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
