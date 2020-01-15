package Project1;

import java.io.*;
import java.util.*;

/**
 *
 * The purpose this class is to simulate a change Jar.
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

    /** A static variable for Mutate boolean */
    private static boolean mutate;

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

    /******************************************************************
     *
     *   This constructor creates a Change Jar from an existing
     *    Change Jar.
     *
     * @param other is an existing Change Jar
     */

    public ChangeJar(ChangeJar other) {
        if (other.quarters >= 0 && other.dimes >= 0 && other.nickels >= 0 && other.pennies >= 0) {
            quarters = other.quarters;
            dimes = other.dimes;
            nickels = other.nickels;
            pennies = other.pennies;
        }
        throw new IllegalArgumentException();
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

    /************************************************************
     *
     * @param temp : A ChangeJar object
     * @return : The value of the ChangeJar in pennies as an integer.
     */
    private static int convertToPennies (ChangeJar temp) {
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

    /*************************************
     *
     * @param s is a ChangeJar object
     * @return is a boolean that determines if the ChangeJar provided matches "this" ChangeJar
     */
    public boolean equals(ChangeJar s) {
        if (s != null) {
            if (s.getAmount() == this.getAmount()) {
                return true;
            }
        }
        return false;
    }

    /***********************************
     *
     * @param s is an object of any type
     * @return will be true if the object is both a ChangeJar and the amount is equal to the amount in temp
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

    /***********************************************************]
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

            if(this.getAmount() < s.getAmount()) {
                return -1;
            }

            if(this.getAmount() == s.getAmount()) {
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

        if(jar1.getAmount() < jar2.getAmount()) {
            return -1;
        }

        if(jar1.getAmount() == jar2.getAmount()) {
            return 0;
        }
        return 0;
    }

    public void takeOut(int quarters, int dimes, int nickels, int pennies) {

        if (quarters < 0 || dimes < 0 || nickels < 0 || pennies < 0) {
            throw new IllegalArgumentException();
        }

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

    public void takeOut(ChangeJar other) {
            takeOut(other.quarters, other.dimes, other.nickels, other.pennies);
    }

    public ChangeJar takeOut (double amount) {
        return null;
    }

    public String toString() {

        // here is a hint
        String s = this.quarters + " Quarter" + ((quarters != 1) ? "s" : "") + "\n"
                 + this.dimes + "Dime" + ((dimes != 1) ? "s" : "") + "\n"
                 + this.nickels + "Nickel" + ((nickels != 1) ? "s" : "") + "\n"
                 + this.pennies + "Penn" + ((pennies == 1) ? "y" : "") + ((pennies != 1) ? "ies" : "");
        return s;
    }

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


    public void load(String fileName) {

        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public double getAmount () {
        return convertToPennies(this) / 100.0;
    }


    public static void main(String[] args) {

    }

    public int getQuarters() {
        return quarters;
    }

    public void setQuarters(int quarters) {
        this.quarters = quarters;
    }

    public int getDimes() {
        return dimes;
    }

    public void setDimes(int dimes) {
        this.dimes = dimes;
    }

    public int getNickels() {
        return nickels;
    }

    public void setNickels(int nickels) {
        this.nickels = nickels;
    }

    public int getPennies() {
        return pennies;
    }

    public void setPennies(int pennies) {
        this.pennies = pennies;
    }


}
