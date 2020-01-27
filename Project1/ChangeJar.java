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
    public ChangeJar (double amount) {
        // Throws Illegal Argument if the amount given is less than 0
        if (amount < 0) {
            throw new IllegalArgumentException();
        }

        // Converts the double to a String
        String s = Double.toString(amount);

        // Sweeps over the String to check for more than 2 numbers after
        // the decimal place
        if (s.contains(".") && s.length() - s.indexOf('.') > 3) {
            throw new IllegalArgumentException();
        }

        // Casts the double value as an int and converts to pennies
        // Note: Casting as an int preserves the value of the double
        // and removes the risk of rounding errors.
        int tempPennies = (int) (amount*100);
        // Uses a helper method to convert pennies back to proper change
        this.Convert(tempPennies);
    }

    /******************************************************************
     *
     *   This constructor creates a Change Jar from an existing
     *    Change Jar.
     *
     * @param other is an existing Change Jar
     *
     */

    public ChangeJar(ChangeJar other) {
            // Creates a ChangeJar from the "other" ChangeJar's coins
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

            // Counter for the number of Decimal points in the String
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
        if(amount.contains(".") && (amount.length() - amount.indexOf(".")) > 3) {
            throw new IllegalArgumentException();
        }

        // Parses the String and converts into a double
        double temp = Double.parseDouble(amount);

        // Throws an Illegal Argument if the parsed temp double is less than 0
        if (temp < 0) {
            throw new IllegalArgumentException();
        }

        // Casts the double value as an int and converts to pennies
        // Note: Casting as an int preserves the value of the double
        // and removes the risk of rounding errors.
        int tempPennies = (int) (temp * 100);
        // Uses a helper method to convert pennies back to proper change
        this.Convert(tempPennies);
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

        if (dimes < 0) {
            throw new IllegalArgumentException();
        }

        if (nickels < 0) {
            throw new IllegalArgumentException();
        }

        if (pennies < 0) {
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
     *
     */
    private static int convertToPennies (ChangeJar temp) {
        return (temp.quarters * 25) + (temp.dimes * 10) + (temp.nickels * 5) + temp.pennies;
    }

    /********************************************
     * This method converts pennies to change from
     * biggest to smallest
     *
     * @param numPennies is the number of pennies
     *                   convert.
     */
    private void Convert(int numPennies) {
        // Throws Illegal Argument if the amount of pennies is less than 0
        if (pennies < 0) {
            throw new IllegalArgumentException();
        }

        // Takes the number of pennies and divides them evenly across
        // the change in the ChangeJar from biggest to smallest
        quarters = numPennies / 25;
        numPennies = numPennies - (quarters * 25);
        dimes = numPennies / 10;
        numPennies = numPennies - (dimes * 10);
        nickels = numPennies / 5;
        numPennies = numPennies - (nickels * 5);
        pennies = numPennies;
    }

    /*********************************************
     *
     * @param selected : A boolean that can be set to either true or false
     *
     * mutate: instance variable that determines if mutation functions can happen or not
     */
    public static void mutate(boolean selected) {
        // If "selected" is true, then mutate becomes true
        if (selected) {
           mutate = true;
        }

        // If "selected" is false, then mutate becomes false
        if (!selected) {
            mutate = false;
        }
    }

    /***********************************
     * This method returns the current state
     * of mutate
     *
     * @return is the state of mutate
     */
    public static boolean getMutate() {
        return mutate;
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
     * This method returns true if the Object given is
     * a ChangeJar and is equal to the value of "this"
     * ChangeJar
     *
     * @param s is an object of any type
     * @return will be true if the object is both a
     * ChangeJar and the amount is equal to the amount in temp
     */
    public boolean equals(Object s) {
        // Checks if the Object s is not null
        if(s != null) {
            // Change if s is a ChangeJar Object
            if (s instanceof ChangeJar) {
                // Casts variable temp as a ChangeJar equal to s
                ChangeJar temp = (ChangeJar) s;
                // Returns true if the value of temp and "this" ChangeJar are equal
                return temp.getAmount() == this.getAmount();
            }
        }

        // Returns false if any of the conditional statements above return false
        return false;
    }

    /***********************************************************
     * This method compares two ChangeJar objects and checks if
     * they are equal
     *
     * @param s is a ChangeJar object
     * @param s1 is another ChangeJar object
     * @return will be true if the value of s and s1 are both the same amount
     */
    public static boolean equals(ChangeJar s, ChangeJar s1) {
        // Checks if either ChangeJar is not null
        if (s != null && s1 != null) {
            // Returns true if the amount in each ChangeJar is equal
            return s.getAmount() == s1.getAmount();
        }

        // Returns false if either ChangeJar is null
        return false;
    }

    /**************************************************************
     * This method compares the value of "this" ChangeJar with another
     * and returns an integer based on if "this" ChangeJar is bigger, smaller,
     * or equal to s
     *
     * @param s is a ChangeJar object
     * @return will be 1 if the value of "this" ChangeJar is greater than the value of s
     * OR will be -1 if the value of "this" ChangeJar is less than the value of s
     * OR will be 0 if the value of "this" ChangeJar is equal to the value of s
     */
    public int compareTo(ChangeJar s) {

        // Comparing the value of "this" ChangeJar with s
            if(this.getAmount() > s.getAmount()) {
                return 1;
            }

            else if(this.getAmount() < s.getAmount()) {
                return -1;
            }

            else {
                return 0;
            }


    }

    /*************************************************************
     * This method compares the value of two ChangeJar objects
     *
     * @param jar1 is a ChangeJar object
     * @param jar2 is another ChangeJar object
     * @return will be 1 if the value of jar1 is greater than the value of jar2
     * OR will be -1 if the value of jar1 is less than the value of jar2
     * OR will be 0 if the value of jar1 is equal to the value of jar2
     */
    public static int compareTo(ChangeJar jar1, ChangeJar jar2)  {

        // Checks the value of the two ChangeJar objects
        if (jar1.getAmount() > jar2.getAmount()) {
            return 1;
        }

        else if (jar1.getAmount() < jar2.getAmount()) {
            return -1;
        }

        else {
            return 0;
        }

    }

    /***********************************************************************
     * This method takes out a number of quarters, dimes, nickels, and pennies
     * from "this" ChangeJar
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

        if (mutate) {

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

        if (mutate) {

            // Checks if the ChangeJar given is null or not
            if (other != null) {
                takeOut(other.quarters, other.dimes, other.nickels, other.pennies);
            }

            //Throws Illegal Argument when ChangeJar is null
            else {
                throw new IllegalArgumentException();
            }

        }
    }

    /*************************************************
     * This method takes out the amount specified (double)
     *
     * @param amount is the amount being taken out
     * @return is the value in the ChangeJar after takeOut
     *
     * @throws IllegalArgumentException if there are more
     * than two numbers after the decimal place OR if the
     * amount given as parameter is negative OR if the amount
     * of quarters, dimes, nickels, or pennies in the new
     * ChangeJar are negative.
     */
    public ChangeJar takeOut (double amount) {

        // Converts the double amount given to a String
        String s = Double.toString(amount);

        // Throws Illegal Argument if the amount of numbers after the
        // decimal place is greater than 2
        if (s.contains(".") && s.length() - s.indexOf('.') > 3) {
            throw new IllegalArgumentException();
        }

        // Throws Illegal Argument if the amount given is less than 0
        if (amount < 0) {
            throw new IllegalArgumentException();
        }

        // Checks if mutation is on or off
        if (mutate) {
            // Casts the given amount as an int to conserve value
            // and remove risk of rounding errors
            int givenAmount = (int) (amount*100);

            // Four nested for loops to check for every possible
            // combination of quarters, dimes, nickels, and pennies
            for(int p = 0; p <= this.pennies; p++) {
                for(int n = 0; n <= this.nickels; n++) {
                    for(int d = 0; d <= this.dimes; d++) {
                        for(int q = 0; q <= this.quarters; q++) {

                            // This integer is changed to a permutation
                            // of a ChangeJar
                            int remove = (q * 25) + (d * 10) +
                                    (n * 5) + p;

                            // Subtracts the number of quarters, dimes
                            // nickels and pennies if the amount to
                            // remove is the same as the amount given
                            if (remove == givenAmount) {

                                this.quarters -= q;
                                this.dimes -= d;
                                this.nickels -= n;
                                this.pennies -= p;

                                return new ChangeJar(q, d, n, p);
                            }
                        }
                    }
                }
            }
        }

        else {
            return null;
        }

        return null;
    }

    /*****************************************************
     * Creates a concatenated String of ChangeJar values
     *
     * @return is the String of all money in the ChangeJar
     */
    public String toString() {

        // Creates a String from the values of quarters, dimes, nickels, and pennies
        String s = this.quarters + " Quarter" + ((quarters != 1) ? "s" : "") + "\n"
                 + this.dimes + " Dime" + ((dimes != 1) ? "s" : "") + "\n"
                 + this.nickels + " Nickel" + ((nickels != 1) ? "s" : "") + "\n"
                 + this.pennies + " Penn" + ((pennies == 1) ? "y" : "") + ((pennies != 1) ? "ies" : "");
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
        if (mutate) {

            this.pennies -= 1;

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

            // Adds the given value of Quarters, Dimes, Nickels, and Pennies
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

        // Reads the file

        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter(
                    fileName)));
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        // Reads the value of quarters, dimes, nickels, and pennies
        // to the file

        out.println(quarters);
        out.println(dimes);
        out.println(nickels);
        out.println(pennies);
        out.close();
    }

    /**********************************************
     * Loads the written file you previously saved
     *
     * @param fileName is the same name of the saved file
     *
     * @throws IllegalArgumentException if any of the
     * scanned integers are less than 0.
     */
    public void load(String fileName) {

        // Loads the file
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(fileName));
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Creates temporary ints for the value of quarters
        // dimes, nickels, and pennies that were scanned in
        // from the given file
        int scanQuarters = scanner.nextInt();
        int scanDimes = scanner.nextInt();
        int scanNickels = scanner.nextInt();
        int scanPennies = scanner.nextInt();

        // Throws an Illegal Argument if any of the scanned ints
        // are negative
        if (scanQuarters < 0 || scanDimes < 0 || scanNickels < 0 ||
        scanPennies < 0) {
            throw new IllegalArgumentException();
        }

        // Changes the value of quarters, dimes, nickels, and pennies
        // to the ints that were scanned from the file
        quarters = scanQuarters;
        dimes = scanDimes;
        nickels = scanNickels;
        pennies = scanPennies;
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
        ChangeJar s = new ChangeJar("2.82");
        System.out.println("2.82 Amount: \n" + s);

        s = new ChangeJar("8");
        System.out.println("8 Amount: \n" + s);

        s = new ChangeJar(".28");
        System.out.println(".28 Amount: \n" + s);

        s = new ChangeJar(1);
        System.out.println("1 Amount: \n" + s);

        s = new ChangeJar();
        System.out.println("0 Amount: \n" + s);

        s = new ChangeJar(1.22);
        System.out.println("1.22 Amount: \n" + s);

        ChangeJar s1 = new ChangeJar(s);
        System.out.println("1.22 Amount: \n" + s1);

        System.out.println("Equals True: \n" + s.equals(s1));

        s1 = new ChangeJar();
        System.out.println("Equals False: \n" + s.equals(s1));

        s = new ChangeJar();
        s1 = new ChangeJar();
        System.out.println("Equals True: \n" + ChangeJar.equals(s,s1));

        s = new ChangeJar(0.01);
        System.out.println("Equals False: \n" + ChangeJar.equals(s,s1));

        s = new ChangeJar(1.00);
        s1 = new ChangeJar();
        System.out.println("Compare 1: \n" + s.compareTo(s1));

        s = new ChangeJar(1.00);
        s1 = new ChangeJar(1.00);
        System.out.println("Compare 0: \n" + s.compareTo(s1));

        s = new ChangeJar();
        s1 = new ChangeJar(1.00);
        System.out.println("Compare -1: \n" + s.compareTo(s1));

        s = new ChangeJar(1,1,1,1);
        s.takeOut(1,1,1,1);
        System.out.println("0 TakeOut: \n" + s);

        s = new ChangeJar(1.00);
        s1 = new ChangeJar(1.00);
        s.takeOut(s1);
        System.out.println("0 TakeOut ChangeJar: \n" + s);

        s = new ChangeJar();
        s.add(1,1,1,1);
        System.out.println("0.41 Add: \n" + s.getAmount());

        s = new ChangeJar();
        s1 = new ChangeJar(1,1,1,1);
        s.add(s1);
        System.out.println("0.41 Add ChangeJar: \n" + s.getAmount());

        s = new ChangeJar();
        for (int i = 0; i < 100; i++) {
            s.inc();
        }
        System.out.println("100 Pennies Inc: \n" + s);

        s = new ChangeJar();
        s1 = new ChangeJar(1,1,1,1);
        s1.save("file");
        s.load("file");
        System.out.println("0.41 Save and Load \n" + s);

        s = new ChangeJar(1,1,1,1);
        s.takeOut(0.41);
        System.out.println("0 TakeOut Double \n" + s);

        s1 = new ChangeJar();
        System.out.println("0 Amount: \n" + s1);

        s1.add(1,1,1,100);
        System.out.println("1,1,1,100 Amount: \n" + s1);

        ChangeJar s2 = new ChangeJar(4,0,0,0);
        s2.add(0,0,0,100);
        for (int i = 0; i < 100; i++)
            s2.dec();
        System.out.println("amount: \n" + s2);
    }

    /*************************************
     * Getter method for the number of
     * quarters in the ChangeJar
     *
     * @return The number of quarters in the current ChangeJar
     */
    public int getQuarters() {
        return this.quarters;
    }

    /*****************************************************
     * Setter method for the number of quarters in
     * the ChangeJar
     *
     * @param quarters is the number of quarters you want
     *                 in the ChangeJar
     *
     * @throws IllegalArgumentException if quarters is less than 0
     */
    public void setQuarters(int quarters) {
        // Throws an Illegal Argument if the value of Quarters are
        // less than 0
        if (quarters < 0) {
            throw new IllegalArgumentException();
        }

        // Checks if mutation is on or off
        if (mutate) {
            this.quarters = quarters;
        }
    }

    /*******************************************
     * Getter method for the number of dimes
     * in the ChangeJar
     *
     * @return The number of dimes in the ChangeJar
     */
    public int getDimes() {
        return this.dimes;
    }

    /***********************************************
     * Setter method for the number of dimes in the
     * ChangeJar
     *
     * @param dimes is the number of Dimes in the
     *              ChangeJar you want.
     *
     * @throws IllegalArgumentException if dimes is less than 0
     */
    public void setDimes(int dimes) {
        // Throws an Illegal Argument if the value of dimes
        // is less than 0
        if (dimes < 0) {
            throw new IllegalArgumentException();
        }

        // Checks if mutation is on or off
        if (mutate) {
            this.dimes = dimes;
        }
    }

    /************************************
     * Getter method for the number of nickels
     * in the ChangeJar
     *
     * @return the number of nickels
     */
    public int getNickels() {
        return this.nickels;
    }

    /**************************************************
     * Setter for the number of nickels in the ChangeJar
     *
     * @param nickels is the number of nickels you want
     *                in the ChangeJar
     *
     * @throws IllegalArgumentException if nickels is less than 0.
     */
    public void setNickels(int nickels) {
        // Throws an Illegal Argument if the value of nickels
        // is less than 0
        if (nickels < 0) {
            throw new IllegalArgumentException();
        }

        // Checks if mutation is on or off
        if (mutate) {
            this.nickels = nickels;
        }
    }

    /****************************************************
     * Getter method for the number of pennies in the ChangeJar
     *
     * @return the number of pennies in the ChangeJar
     */
    public int getPennies() {
        return this.pennies;
    }

    /******************************************************
     * Setter method for the number of pennies in the
     * ChangeJar
     *
     * @param pennies is the number of pennies that you want
     *                in the ChangeJar
     *
     * @throws IllegalArgumentException if pennies is less than 0.
     */
    public void setPennies(int pennies) {

        // Throws an Illegal Argument if the value of pennies
        // is less than 0
        if (pennies < 0) {
            throw new IllegalArgumentException();
        }

        // Checks if mutation is on or off
        if (mutate) {
            this.pennies = pennies;
        }
    }
}
