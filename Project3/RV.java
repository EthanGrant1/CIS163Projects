package Project3;

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
        double cost = 10.0;

        if (this.getPower() <= 1000 &&
                this.checkIn.get(Calendar.YEAR) == checkOut.get(Calendar.YEAR)) {

            for (int i = this.checkIn.get(Calendar.DAY_OF_YEAR);
                 i < checkOut.get(Calendar.DAY_OF_YEAR); i++) {

                cost += 20;

            }
        }

        if (this.getPower() > 1000 &&
                this.checkIn.get(Calendar.YEAR) == checkOut.get(Calendar.YEAR)) {

            for (int i = this.checkIn.get(Calendar.DAY_OF_YEAR);
                 i < checkOut.get(Calendar.DAY_OF_YEAR); i++) {

                cost += 30;

            }

        }

        if (this.getPower() <= 1000 &&
                (this.checkIn.get(Calendar.YEAR) < checkOut.get(Calendar.YEAR)) &&
                (this.checkIn.get(Calendar.YEAR) != checkOut.get(Calendar.YEAR) - 1)) {

            for (int i = this.checkIn.get(Calendar.YEAR);
                 i <= checkOut.get(Calendar.YEAR); i++) {

                if (i < checkOut.get(Calendar.YEAR)) {
                    if (!checkOut.isLeapYear(i)) {
                        cost += 365 * 20;
                    }

                    if (checkOut.isLeapYear(i)) {
                        cost += 366 * 20;
                    }
                }

                if (i == checkOut.get(Calendar.YEAR)) {
                    for (int j = this.checkIn.get(Calendar.DAY_OF_YEAR);
                         j < checkOut.get(Calendar.DAY_OF_YEAR); j++) {

                        cost += 20;
                    }
                }
            }

        }

        if (this.getPower() > 1000 &&
                (this.checkIn.get(Calendar.YEAR) < checkOut.get(Calendar.YEAR)) &&
                (this.checkIn.get(Calendar.YEAR) != checkOut.get(Calendar.YEAR) - 1)) {

            for (int i = this.checkIn.get(Calendar.YEAR);
                 i < checkOut.get(Calendar.YEAR); i++) {

                if (!checkOut.isLeapYear(i)) {
                    cost += 365*30;
                }

                if (checkOut.isLeapYear(i)) {
                    cost += 366*30;
                }
            }

            for(int j = this.checkIn.get(Calendar.DAY_OF_YEAR);
                j < checkOut.get(Calendar.DAY_OF_YEAR); j++) {

                cost += 30;
            }

        }

        if (this.getPower() <= 1000 &&
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

        if (this.getPower() > 1000 &&
                this.checkIn.get(Calendar.YEAR) == checkOut.get(Calendar.YEAR) - 1) {

            for (int i = this.checkIn.get(Calendar.YEAR);
                 i <= checkOut.get(Calendar.YEAR); i++) {

                if (i < checkOut.get(Calendar.YEAR)) {
                    if (!checkOut.isLeapYear(i)) {

                        for (int j = this.checkIn.get(Calendar.DAY_OF_YEAR);
                             j <= 365; j++) {

                            cost += 30;
                        }

                    }

                    if (checkOut.isLeapYear(i)) {

                        for (int j = this.checkIn.get(Calendar.DAY_OF_YEAR);
                             j <= 366; j++) {

                            cost += 30;
                        }

                    }
                }

                if (i == checkOut.get(Calendar.YEAR)) {
                    for (int j = 1;
                         j < checkOut.get(Calendar.DAY_OF_YEAR); j++) {

                        cost += 30;
                    }
                }
            }

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