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
        // Initial cost of 10
        double cost = 10.0;

        // If the number of watts is less or equal to 1000
        // and the checkIn / checkOut years are the same
        if (this.getPower() <= 1000 &&
                this.checkIn.get(Calendar.YEAR) == checkOut.get(Calendar.YEAR)) {

            // For loop to add $20 to the cost for every day
            // the RV is at the camp site
            for (int i = this.checkIn.get(Calendar.DAY_OF_YEAR);
                 i < checkOut.get(Calendar.DAY_OF_YEAR); i++) {

                cost += 20;

            }
        }

        // If the number of watts is greater than 1000
        // and the checkIn / checkOut years are the same
        if (this.getPower() > 1000 &&
                this.checkIn.get(Calendar.YEAR) == checkOut.get(Calendar.YEAR)) {

            // For loop to add $30 to the cost for every day
            // the RV is at the camp site
            for (int i = this.checkIn.get(Calendar.DAY_OF_YEAR);
                 i < checkOut.get(Calendar.DAY_OF_YEAR); i++) {

                cost += 30;

            }

        }

        // If the number of watts is less than or equal to 1000,
        // the checkIn / checkOut years are not the same,
        // and the checkIn year does not need to immediately
        // roll over to the next year
        if (this.getPower() <= 1000 &&
                (this.checkIn.get(Calendar.YEAR) < checkOut.get(Calendar.YEAR)) &&
                (this.checkIn.get(Calendar.YEAR) != checkOut.get(Calendar.YEAR) - 1)) {

            // For loop to progress the number of years until it
            // matches the checkOut year
            for (int i = this.checkIn.get(Calendar.YEAR);
                 i < checkOut.get(Calendar.YEAR); i++) {

                // Adds a year's worth of money to the
                // cost depending on if the current year
                // is a leap year or not
                if (!this.checkIn.isLeapYear(i)) {
                    cost += 365*20;
                }

                if (this.checkIn.isLeapYear(i)) {
                    cost += 366*20;
                }
            }

            // For loop to add the rest of the money to the cost
            for (int j = this.checkIn.get(Calendar.DAY_OF_YEAR);
                 j < checkOut.get(Calendar.DAY_OF_YEAR); j++) {

                cost += 20;
            }

        }

        // If the number of watts is greater than 1000,
        // the checkIn / checkOut years are not the same,
        // and the checkIn year does not need to immediately
        // roll over to the next year
        if (this.getPower() > 1000 &&
                (this.checkIn.get(Calendar.YEAR) < checkOut.get(Calendar.YEAR)) &&
                (this.checkIn.get(Calendar.YEAR) != checkOut.get(Calendar.YEAR) - 1)) {

            // For loop to progress the number of years until it
            // matches the checkOut year
            for (int i = this.checkIn.get(Calendar.YEAR);
                 i < checkOut.get(Calendar.YEAR); i++) {

                // Adds a year's worth of money to the
                // cost depending on if the current year
                // is a leap year or not
                if (!this.checkIn.isLeapYear(i)) {
                    cost += 365*30;
                }

                if (this.checkIn.isLeapYear(i)) {
                    cost += 366*30;
                }
            }

            // For loop to add the rest of the money to the cost
            for(int j = this.checkIn.get(Calendar.DAY_OF_YEAR);
                j < checkOut.get(Calendar.DAY_OF_YEAR); j++) {

                cost += 30;
            }

        }

        // If the number of watts is less than or equal to 1000,
        // the checkIn / checkOut years are not the same,
        // and the checkIn year needs to immediately
        // roll over to the next year
        if (this.getPower() <= 1000 &&
                this.checkIn.get(Calendar.YEAR) == checkOut.get(Calendar.YEAR) - 1) {

            // For loop to progress the number of years until it
            // matches the checkOut year
            for (int i = this.checkIn.get(Calendar.YEAR);
                 i <= checkOut.get(Calendar.YEAR); i++) {

                // If the current year is less than the checkOut
                // year
                if (i < checkOut.get(Calendar.YEAR)) {

                    // If the current year is not a leap year
                    if (!this.checkIn.isLeapYear(i)) {

                        // For loop to add money to the cost
                        // up to the final day of the year
                        for (int j = this.checkIn.get(Calendar.DAY_OF_YEAR);
                             j <= 365; j++) {

                            cost += 20;
                        }

                    }

                    // If the current year is a leap year
                    if (this.checkIn.isLeapYear(i)) {

                        // For loop to add money to the cost
                        // up to the final day of the year
                        for (int j = this.checkIn.get(Calendar.DAY_OF_YEAR);
                             j <= 366; j++) {

                            cost += 20;
                        }

                    }
                }

                // If the checkOut year is equal to the current year
                if (i == checkOut.get(Calendar.YEAR)) {

                    // For loop to add the rest of the money to the cost
                    // until the checkOut date
                    for (int j = 1;
                         j < checkOut.get(Calendar.DAY_OF_YEAR); j++) {

                        cost += 20;
                    }
                }
            }

        }

        // If the number of watts is greater than 1000,
        // the checkIn / checkOut years are not the same,
        // and the checkIn year needs to immediately
        // roll over to the next year
        if (this.getPower() > 1000 &&
                this.checkIn.get(Calendar.YEAR) == checkOut.get(Calendar.YEAR) - 1) {

            // For loop to progress the number of years until it
            // matches the checkOut year
            for (int i = this.checkIn.get(Calendar.YEAR);
                 i <= checkOut.get(Calendar.YEAR); i++) {

                // If the current year is less than
                // the checkOut year
                if (i < checkOut.get(Calendar.YEAR)) {

                    // If the current year is not a leap year
                    if (!checkOut.isLeapYear(i)) {

                        // For loop to add money to the cost
                        // up to the final day of the year
                        for (int j = this.checkIn.get(Calendar.DAY_OF_YEAR);
                             j <= 365; j++) {

                            cost += 30;
                        }

                    }

                    // If the current year is a leap year
                    if (checkOut.isLeapYear(i)) {

                        // For loop to add money to the cost
                        // up to the final day of the year
                        for (int j = this.checkIn.get(Calendar.DAY_OF_YEAR);
                             j <= 366; j++) {

                            cost += 30;
                        }

                    }
                }

                // If the current year is equal to the
                // checkOut year
                if (i == checkOut.get(Calendar.YEAR)) {

                    // For loop to add the rest of the money to the cost
                    // until the checkOut date
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