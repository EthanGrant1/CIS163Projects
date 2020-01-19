package Project1;

import java.io.*;
import java.util.*;

/**
 *
 * This class simulates a ChangeJar
 *
 *
 * 	 NOTE: MUCH MORE CODING IS NEEDED IN THESE METHODS, and you
 * 	 will need to comply with the Java Style Guide.
 * 	 HOWEVER,  HERE IS SOME STARTING CODE.
 *
 * @author Ethan Grant
 */

public class ChangeJar {

    /** The number of quarters in the current Jar */
    private int quarters;

    /** The number of dimes in the current Jar */
    private int dimes;

    // Now you do the rest of the instance variables using the
    // Java Style guide.

    /** The number of nickels in the current Jar */
    private int nickels;

    /** The number of pennies in the current Jar */
    private int pennies;

    /** Determines whether mutation methods are on or off */
    private static boolean mutate = true;

    /******************************************************************
     *  The is the default constructor for ChangeJar
     */

    public ChangeJar() {
        //All instance variables are set to 0
        quarters = 0;
        dimes = 0;
        nickels = 0;
        pennies = 0;
    }

    /************************************************************************
     * This method creates a ChangeJar object using a double as parameter
     *
     * @param amount is the amount being added to the ChangeJar
     *
     * @throws IllegalArgumentException when amount given is less than 0
     */
    public ChangeJar(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException();
        }
        // Quarters is a casted integer value equal to the amount divided by a quarter's worth
        quarters = (int) (amount / 0.25);
        // The value in quarters is then subtracted from the total (so we have the leftovers)
        amount = amount - (quarters * 0.25);

        // The same process is repeated for dimes, nickels, and pennies
        dimes = (int) (amount / 0.10);
        amount = amount - (dimes * 0.10);

        nickels = (int) (amount / 0.05);
        amount = amount - (nickels * 0.05);

        pennies = (int) (amount / 0.01);
    }

    /******************************************************************
     *
     *   This constructor creates a Change Jar from an existing
     *    Change Jar.
     *
     * @param other is an existing Change Jar
     *
     * @throws IllegalArgumentException if any of the values in the provided
     * ChangeJar are less than 0
     */

    public ChangeJar(ChangeJar other) {
            if (other.quarters < 0 || other.dimes < 0 || other.nickels < 0
            || other.pennies < 0) {
                throw new IllegalArgumentException();
            }

            quarters = other.quarters;
            dimes = other.dimes;
            nickels = other.nickels;
            pennies = other.pennies;
    }

    /**********************************************************************
     * This method creates a ChangeJar from a String
     *
     * @param amount is a String that will be the amount in the ChangeJar
     *
     * @throws IllegalArgumentException when a character in the amount String
     * is not a number or a decimal point OR when the number of decimal points
     * in the String is greater than 1 OR when the number of numbers after the
     * decimal points is greater than 2 OR if the value of temp is less than 0.
     */
    public ChangeJar (String amount) {
        int Decimals = 0;
        // Uses a for loop to sweep the amount String for decimal points
        for(int i = 0; i < amount.length(); i++) {

            // Illegal argument for if a character in the amount String is not a
            // number of decimal point.

            if(!Character.isDigit(amount.charAt(i)) && amount.charAt(i) != '.') {
                throw new IllegalArgumentException();
            }

            if(amount.charAt(i) == '.') {
                Decimals++;
            }

            // Counts the number of decimal points and checks if it's greater than 1
            // In this case the program throws an Illegal Argument.
            if(Decimals > 1) {
                throw new IllegalArgumentException();
            }
        }

        // Throws an Illegal Argument if the number of integers after the
        // decimal point is greater than 2
        if(amount.contains(".") && (amount.indexOf('.') - amount.length()) > 2) {
            throw new IllegalArgumentException();
        }

        double temp = Double.parseDouble(amount);

        // Throws an Illegal Argument if the parsed temp double is less than 0
        if (temp < 0) {
            throw new IllegalArgumentException();
        }

        // The amount of quarters is casted as an int to Quarters.
        quarters = (int) (temp / 0.25);
        // temp is then given the value of the remainder.
        temp = temp - (quarters * 0.25);

        // The same process is repeated for dimes, nickels, and pennies
        dimes = (int) (temp / 0.10);
        temp = temp - (dimes * 0.10);

        nickels = (int) (temp / 0.05);
        temp = temp - (nickels * 0.05);

        pennies = (int) (temp / 0.01);
    }

    /******************************************************************
     *
     *   This constructor creates a Change Jar from with some
     *   initial values for Quarters, Dimes, Nickels, and Pennies.
     **
     * @param quarters is the number of quarters to start with.
     * @param dimes is the number of dimes to start with.
     * @param nickels is the number of nickels to start with.
     * @param pennies is the number of pennies to start with.
     *
     * @throws IllegalArgumentException when the number of Quarters,
     * Dimes, Nickels, or Pennies is less than 0.
     *
     */
    public ChangeJar(int quarters, int dimes, int nickels, int pennies) {
        super();

        //Throws IllegalArgumentExceptions if amount of money is negative
        if (quarters < 0) {
            throw new IllegalArgumentException();
        }

        if(dimes < 0) {
            throw new IllegalArgumentException();
        }

        if(nickels < 0) {
            throw new IllegalArgumentException();
        }

        if(pennies < 0) {
            throw new IllegalArgumentException();
        }

        //Changes the instance variables if they are greater or equal to zero
        if (quarters >= 0) {
            this.quarters = quarters;
        }

        if (dimes >= 0) {
            this.dimes = dimes;
        }

        if (nickels >= 0) {
            this.nickels = nickels;
        }

        if (pennies >= 0) {
            this.pennies = pennies;
        }

    }

// REMEMBER to use the Java Style Guide for the rest of your code.


    /*******************************************************************
     *
     * @param temp is a ChangeJar object
     * @return The value of money in the ChangeJar converted to pennies
     *
     * @throws IllegalArgumentException if the given ChangeJar has any negative
     * values in it.
     */
    private static int convertToPennies (ChangeJar temp) {

        if(temp.quarters < 0 || temp.dimes < 0 || temp.nickels < 0 || temp.pennies < 0) {
            throw new IllegalArgumentException();
        }

        return (temp.quarters * 25) + (temp.dimes * 10) + (temp.nickels * 5) + temp.pennies;
    }

    /*********************************************
     *
     * @param selected : A boolean that can be set to either true or false
     *
     * mutate: instance variable that determines if mutation functions can happen or not
     */
    public static void mutate(boolean selected) {
        if (selected) {
           mutate = true;
        }

        if (!selected) {
            mutate = false;
        }
    }

//    /*************************************
//     *
//     * @param s is a ChangeJar object
//     * @return is a boolean that determines if the ChangeJar provided matches "this" ChangeJar
//     */
//    public boolean equals(ChangeJar s) {
//        if (s != null) {
//            if (s.getAmount() == this.getAmount()) {
//                return true;
//            }
//        }
//        return false;
//    }

    /*************************************************
     *
     * @param s is an object of any type
     * @return will be true if the object is both a
     * ChangeJar and the amount is equal to the amount in temp
     */
    public boolean equals(Object s) {
        if(s != null) {
            if (s instanceof ChangeJar) {
                ChangeJar temp = (ChangeJar) s;
                return temp.getAmount() == this.getAmount();
            }
        }
        return false;
    }

    /***********************************************************
     *
     * @param s is a ChangeJar object
     * @param s1 is another ChangeJar object
     * @return will be true if the value of s and s1 are both the same amount
     */
    public static boolean equals(ChangeJar s, ChangeJar s1) {
        if (s != null && s1 != null) {
            return s.getAmount() == s1.getAmount();
        }
        return false;
    }

    /**************************************
     *
     * @param s is a ChangeJar object
     * @return will be 1 if the value of "this" ChangeJar is greater than the value of s
     * will be -1 if the value of "this" ChangeJar is less than the value of s
     * will be 0 if the value of "this" ChangeJar is equal to the value of s
     */
    public int compareTo(ChangeJar s) {
            if(this.getAmount() > s.getAmount()) {
                return 1;
            }

            else if(this.getAmount() < s.getAmount()) {
                return -1;
            }

            else if(this.getAmount() == s.getAmount()) {
                return 0;
            }
            return 0;
    }

    /*************************************************************
     *
     * @param jar1 is a ChangeJar object
     * @param jar2 is another ChangeJar object
     * @return will be 1 if the value of jar1 is greater than the value of jar2
     * will be -1 if the value of jar1 is less than the value of jar2
     * will be 0 if the value of jar1 is equal to the value of jar2
     */
    public static int compareTo(ChangeJar jar1, ChangeJar jar2)  {
        if(jar1.getAmount() > jar2.getAmount()) {
            return 1;
        }

        else if(jar1.getAmount() < jar2.getAmount()) {
            return -1;
        }

        else if(jar1.getAmount() == jar2.getAmount()) {
            return 0;
        }
        return 0;
    }

    /***********************************************************************
     *
     * @param quarters is the number of quarters you want in the ChangeJar
     * @param dimes is the number of dimes you want in the ChangeJar
     * @param nickels is the number of nickels you want in the ChangeJar
     * @param pennies is the number of pennies you want in the ChangeJar
     *
     * @throws IllegalArgumentException when the value of Quarters, Dimes,
     * Nickels, or Pennies is less than 0.
     */
    public void takeOut(int quarters, int dimes, int nickels, int pennies) {

        // Checks if mutation is on or off

        if(mutate) {

            // Illegal Argument if amount given is negative or amount after
            // subtraction is less than 0

            if (quarters < 0 || dimes < 0 || nickels < 0 || pennies < 0
                || (this.quarters - quarters) < 0 || (this.dimes - dimes) < 0
                || (this.nickels - nickels) < 0 || (this.pennies - pennies) < 0) {
                throw new IllegalArgumentException();
            }

            // If statements to check for possible negative integers after
            // subtraction of money

            if (quarters >= 0 && (this.quarters - quarters) >= 0) {
                this.quarters -= quarters;
            }

            if (dimes >= 0 && (this.dimes - dimes) >= 0) {
                this.dimes -= dimes;
            }

            if (nickels >= 0 && (this.nickels - nickels) >= 0) {
                this.nickels -= nickels;
            }

            if (pennies >= 0 && (this.pennies - pennies) >= 0) {
                this.pennies -= pennies;
            }
        }
    }

    /**************************************************
     * Takes out the value of another ChangeJar object
     *
     * @param other is another ChangeJar object
     *
     * @throws IllegalArgumentException when ChangeJar is null
     */
    public void takeOut(ChangeJar other) {

        // Checks if mutation is on or off

        if(mutate) {

            // Checks if the ChangeJar given is null or not
            if (other != null) {
                takeOut(other.quarters, other.dimes, other.nickels, other.pennies);
            }

            //Throws Illegal Argument when ChangeJar is null
            else if (other == null) {
                throw new IllegalArgumentException();
            }

        }
    }

    /*************************************************
     * This method takes out the amount specified (double)
     *
     * @param amount is the amount being taken out
     * @return is the value in the ChangeJar after takeOut
     */
    public ChangeJar takeOut (double amount) {
        return null;
    }

    /*****************************************************
     * Creates a concatenated String of ChangeJar values
     *
     * @return is the String of all money in the ChangeJar
     */
    public String toString() {

        // here is a hint
        String s = this.quarters + " Quarter" + ((quarters != 1) ? "s" : "") + "\n"
                 + this.dimes + "Dime" + ((dimes != 1) ? "s" : "") + "\n"
                 + this.nickels + "Nickel" + ((nickels != 1) ? "s" : "") + "\n"
                 + this.pennies + "Penn" + ((pennies == 1) ? "y" : "") + ((pennies != 1) ? "ies" : "");
        return s;
    }

    /**********************************************
     * Decrements the value of ChangeJar by 1 penny
     *
     * @throws IllegalArgumentException if pennies on
     * decrement is less than 0.
     */
    public void dec() {
        // Checks if mutation is on or off
        if(mutate) {

            this.pennies -= 0.01;

            // Checks if the pennies is less than 0 on decrement and throws an error

            if (this.pennies < 0) {
                this.setPennies(0);
                throw new IllegalArgumentException();
            }
        }
    }

    /*************************************************
     * Increments the value of ChangeJar by 1 penny
     *
     * Note: This method does not throw an Illegal Argument
     * for reaching the upper limits on number of pennies.
     */
    public void inc() {
        //Checks if mutation is on or off
        if (mutate) {
            this.pennies += 1;
        }
    }

    /***********************************************************
     *
     * @param quarters is the number of quarters you want to add
     * @param dimes is the number of dimes you want to add
     * @param nickels is the number of nickels you want to add
     * @param pennies is the number of pennies you want to add
     *
     * @throws IllegalArgumentException if Quarters, Dimes, Nickels,
     * or Pennies is negative.
     */
    public void add (int quarters, int dimes, int nickels, int pennies) {
        // Checks if mutation is on or off
        if (mutate) {

            // Throws Illegal Argument if Quarters, Dimes, Nickels, or
            // Pennies is negative.
            if (quarters < 0 || dimes < 0 || nickels < 0 || pennies < 0) {
                throw new IllegalArgumentException();
            }

            this.quarters += quarters;
            this.dimes += dimes;
            this.nickels += nickels;
            this.pennies += pennies;
        }
    }

    /*************************************
     *
     * @param other is a ChangeJar object
     *
     * @throws IllegalArgumentException if any values if
     * "other" are negative
     */
    public void add (ChangeJar other) {
        //Checks if mutation is on or off
        if(mutate) {
            // Throws Illegal Argument if any values given in the
            // "other" ChangeJar are negative

            if(other.quarters < 0 || other.dimes < 0
                    || other.nickels < 0 || other.pennies < 0) {
                throw new IllegalArgumentException();
            }

            // Adds the value of the other ChangeJar to "this" ChangeJar
            this.quarters += other.quarters;
            this.dimes += other.dimes;
            this.nickels += other.nickels;
            this.pennies += other.pennies;
        }
    }

    /*********************************************************
     * Saves ChangeJar data to a file
     *
     * @param fileName is the name of the file being written
     *                 to in your directory
     */
    public void save(String fileName) {

        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter(
                    fileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.close();
    }

    /**********************************************
     * Loads the written file you previously saved
     *
     * @param fileName is the same name of the saved file
     */
    public void load(String fileName) {

        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*****************************************************
     * Converts the amount in the ChangeJar to pennies
     * and returns the value
     *
     * @return is the amount in the ChangeJar, converted
     * to pennies
     */
    public double getAmount () {
        return convertToPennies(this) / 100.0;
    }

    /****************************************
     * Main method for running other methods in the class
     *
     * @param args String arguments
     */
    public static void main(String[] args) {

    }

    /*************************************
     * Getter method for the number of
     * quarters in the ChangeJar
     *
     * @return The number of quarters in the current ChangeJar
     */
    public int getQuarters() {
        return quarters;
    }

    /*****************************************************
     * Setter method for the number of quarters in
     * the ChangeJar
     *
     * @param quarters is the number of quarters you want
     *                 in the ChangeJar
     */
    public void setQuarters(int quarters) {
        this.quarters = quarters;
    }

    /*******************************************
     * Getter method for the number of dimes
     * in the ChangeJar
     *
     * @return The number of dimes in the ChangeJar
     */
    public int getDimes() {
        return dimes;
    }

    /***********************************************
     * Setter method for the number of dimes in the
     * ChangeJar
     *
     * @param dimes is the number of Dimes in the
     *              ChangeJar you want.
     */
    public void setDimes(int dimes) {
        this.dimes = dimes;
    }

    /************************************
     * Getter method for the number of nickels
     * in the ChangeJar
     *
     * @return the number of nickels
     */
    public int getNickels() {
        return nickels;
    }

    /**************************************************
     * Setter for the number of nickels in the ChangeJar
     *
     * @param nickels is the number of nickels you want
     *                in the ChangeJar
     */
    public void setNickels(int nickels) {
        this.nickels = nickels;
    }

    /****************************************************
     * Getter method for the number of pennies in the ChangeJar
     *
     * @return the number of pennies in the ChangeJar
     */
    public int getPennies() {
        return pennies;
    }

    /******************************************************
     * Setter method for the number of pennies in the
     * ChangeJar
     *
     * @param pennies is the number of pennies that you want
     *                in the ChangeJar
     */
    public void setPennies(int pennies) {
        this.pennies = pennies;
    }


}
