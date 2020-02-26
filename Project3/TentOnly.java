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
                this.checkIn.get(Calendar.YEAR) == checkOut.get(Calendar.YEAR)) {

                for (int i = this.checkIn.get(Calendar.DAY_OF_YEAR);
                     i < checkOut.get(Calendar.DAY_OF_YEAR); i++) {

                    cost += 10;

                }
        }

        if (this.getNumberOfTenters() > 10 &&
                this.checkIn.get(Calendar.YEAR) == checkOut.get(Calendar.YEAR)) {

                for (int i = this.checkIn.get(Calendar.DAY_OF_YEAR);
                     i < checkOut.get(Calendar.DAY_OF_YEAR); i++) {

                    cost += 20;

                }

        }

        if (this.getNumberOfTenters() < 10 &&
                (this.checkIn.get(Calendar.YEAR) < checkOut.get(Calendar.YEAR)) &&
                (this.checkIn.get(Calendar.YEAR) != checkOut.get(Calendar.YEAR) - 1)) {

            for (int i = this.checkIn.get(Calendar.YEAR);
                 i <= checkOut.get(Calendar.YEAR); i++) {

                if (i < checkOut.get(Calendar.YEAR)) {
                    if (!checkOut.isLeapYear(i)) {
                        cost += 365 * 10;
                    }

                    if (checkOut.isLeapYear(i)) {
                        cost += 366 * 10;
                    }
                }

                if (i == checkOut.get(Calendar.YEAR)) {
                    for (int j = this.checkIn.get(Calendar.DAY_OF_YEAR);
                         j < checkOut.get(Calendar.DAY_OF_YEAR); j++) {

                        cost += 10;
                    }
                }
            }

        }

        if (this.getNumberOfTenters() > 10 &&
                (this.checkIn.get(Calendar.YEAR) < checkOut.get(Calendar.YEAR)) &&
                (this.checkIn.get(Calendar.YEAR) != checkOut.get(Calendar.YEAR) - 1)) {

            for (int i = this.checkIn.get(Calendar.YEAR);
                 i < checkOut.get(Calendar.YEAR); i++) {

                if (!checkOut.isLeapYear(i)) {
                    cost += 365*20;
                }

                if (checkOut.isLeapYear(i)) {
                    cost += 366*20;
                }
            }

            for(int j = this.checkIn.get(Calendar.DAY_OF_YEAR);
                j < checkOut.get(Calendar.DAY_OF_YEAR); j++) {

                cost += 20;
            }

        }

        if (this.getNumberOfTenters() < 10 &&
                this.checkIn.get(Calendar.YEAR) == checkOut.get(Calendar.YEAR) - 1) {

            for (int i = this.checkIn.get(Calendar.YEAR);
                 i <= checkOut.get(Calendar.YEAR); i++) {

                if (i < checkOut.get(Calendar.YEAR)) {
                    if (!checkOut.isLeapYear(i)) {

                        for (int j = this.checkIn.get(Calendar.DAY_OF_YEAR);
                        j <= 365; j++) {

                            cost += 10;
                        }

                    }

                    if (checkOut.isLeapYear(i)) {

                        for (int j = this.checkIn.get(Calendar.DAY_OF_YEAR);
                             j <= 366; j++) {

                            cost += 10;
                        }

                    }
                }

                if (i == checkOut.get(Calendar.YEAR)) {
                    for (int j = 1;
                         j < checkOut.get(Calendar.DAY_OF_YEAR); j++) {

                        cost += 10;
                    }
                }
            }

        }

        if (this.getNumberOfTenters() > 10 &&
                this.checkIn.get(Calendar.YEAR) == checkOut.get(Calendar.YEAR) - 1) {

            for (int i = this.checkIn.get(Calendar.YEAR);
                 i <= checkOut.get(Calendar.YEAR); i++) {

                if (i < checkOut.get(Calendar.YEAR)) {
                    if (!checkOut.isLeapYear(i)) {

                        for (int j = this.checkIn.get(Calendar.DAY_OF_YEAR);
                             j <= 365; j++) {

                            cost += 20;
                        }

                    }

                    if (checkOut.isLeapYear(i)) {

                        for (int j = this.checkIn.get(Calendar.DAY_OF_YEAR);
                             j <= 366; j++) {

                            cost += 20;
                        }

                    }
                }

                if (i == checkOut.get(Calendar.YEAR)) {
                    for (int j = 1;
                         j < checkOut.get(Calendar.DAY_OF_YEAR); j++) {

                        cost += 20;
                    }
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
