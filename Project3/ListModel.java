package Project3;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.time.temporal.ChronoUnit;

import static Project3.ScreenDisplay.CurrentParkStatus;
import static Project3.ScreenDisplay.SortByRVTent;

public class ListModel extends AbstractTableModel {

    /** An ArrayList of CampSite objects that's used
        to list all of the Tents or RVs in the CampSite */
    private ArrayList<CampSite> listCampSites;

    /** An ArrayList of CampSite objects that is used
        with Lambda functions and filtering with Streams*/
    private ArrayList<CampSite> fileredListCampSites;

    /** What screen is currently on display in the GUI */
    private ScreenDisplay display;

    /** An array of Strings for the headers of each column on the
        Current Park screen */
    private String[] columnNamesCurrentPark = {"Guest Name", "Est. Cost",
            "Check In Date", "Est. Check Out Date ", "Max Power", "Num. of Tenters"};

    /** An array of Strings for the headers of each column on the
     Checkout screen */
    private String[] columnNamesforCheckouts = {"Guest Name", "Est. Cost",
            "Check In Date", "Actual Check Out Date ", " Real Cost"};

    /** An array of Strings for the headers of each column on the
     Overdue screen */
    private String[] columnNamesOverDueScreen = {"Guest Name", "Est. Cost",
            "Check in Date", "Est. Check out Date", "Days OverDue"};

    /** An array of Strings for the headers of each column on the
     Sort by RV / Tent screen */
    private String[] columnNamesSortByRVTent = {"Guest Name", "Est. Cost",
            "Check In Date", "Est. Check Out Date ", "Max Power", "Num. of Tenters"};

    /** An array of Strings for the headers of each column on the
     Sort by Tent / RV screen */
    private String[] columnNamesSortByTentRV = {"Guest Name", "Est. Cost",
            "Check In Date", "Est. Check Out Date ", "Max Power", "Num. of Tenters"};

    /** A formatter used to change parsed dates into the correct format */
    private DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

    /** The date used when comparing Checkouts in the Overdue screen */
    public GregorianCalendar enteredOverDueDate;

    /***********************************************************
     * A default constructor for the ListModel class
     */
    public ListModel() {
        // Used to get all of the methods that are inherited
        // from the super class AbstractTableModel
        super();

        // Default screen is the Current Park screen
        display = CurrentParkStatus;

        // Instantiates the list of Tents or RVs on the campsite
        listCampSites = new ArrayList<CampSite>();

        // Updates the screen to whatever "display" currently is
        UpdateScreen();

        // Creates a default database of Tents and RVs
        createList();
    }

    /***************************************************
     * Sets the screen on the GUI to whatever "display"
     * is.
     *
     * @param selected is the screen you want to be displayed
     */
    public void setDisplay(ScreenDisplay selected) {
        display = selected;

        // A method to check which screen display currently
        // is
        UpdateScreen();
    }

    /****************************************************
     * Uses a switch statement to check which screen should
     * be displayed, then updates the screen.
     *
     * @throws RuntimeException if the display does not match
     * any of the screens and a screen update cannot take place.
     */
    private void UpdateScreen() {
        // Switch statement for display
        switch (display) {

            // The current park excluding all of the people who are
            // checked out
            case CurrentParkStatus:
                // A stream to filter the list of CampSite objects
                fileredListCampSites = (ArrayList<CampSite>) listCampSites.stream()

                        // Filters the CampSite objects that do not have an actual checkout
                        // date (the ones that are still at the site)
                        .filter(n -> n.actualCheckOut == null)
                        .collect(Collectors.toList());

                // Note: This uses Lambda function
                Collections.sort(fileredListCampSites, (n1, n2) -> n1.getGuestName()
                        .compareTo(n2.guestName));
                break;

            // The people who are already checked out of the site
            case CheckOutGuest:

                // Filters the CampSite objects that have an actual checkout
                // date (ones that are no longer on the site)
                fileredListCampSites = (ArrayList<CampSite>) listCampSites.stream().
                        filter(n -> n.getActualCheckOut() != null).collect(Collectors.toList());

                // Note: This uses an anonymous class.
                Collections.sort(fileredListCampSites, new Comparator<CampSite>() {
                    @Override
                    public int compare(CampSite n1, CampSite n2) {
                        return n1.getGuestName().compareTo(n2.guestName);
                    }
                });

                break;

                // The overdue screen used to track if people are
                // still in need of checking out after a specified period
            case OverDueScreen:

                // Filters the CampSite objects that have an estimated checkout date
                // (this is just an extra precaution in case an error occurs)
                fileredListCampSites = (ArrayList<CampSite>) listCampSites.stream()
                        .filter(n -> n.getEstimatedCheckOut() != null)
                        .collect(Collectors.toList());

                // Uses a Lambda function variant to sort the Campsites by checkout date
                Collections.sort(fileredListCampSites, Comparator.comparing(CampSite::getEstimatedCheckOut));
                break;

                // The sort by RV / Tent screen
            case SortByRVTent:

                // Filters the CampSite objects that do not have an actual checkout date
                // (ones that are still at the site)
                fileredListCampSites = (ArrayList<CampSite>) listCampSites.stream()
                        .filter(n -> n.actualCheckOut == null)
                        .collect(Collectors.toList());

                // Sorts the CampSite objects using a Lambda function

                // Checks if there are CampSite objects that are Tents
                Collections.sort(fileredListCampSites, Comparator.comparing(CampSite::isTent)
                        // Sorts the CampSites by name
                        .thenComparing(CampSite::getGuestName)
                        // Sorts to put the RV CampSites first
                        .thenComparing(CampSite::isRV)
                        // Sorts the CampSites by name again
                        .thenComparing(CampSite::getGuestName)
                        // If there are CampSites that have the same name,
                        // it sorts them by estimated checkout date instead
                        .thenComparing(CampSite::getEstimatedCheckOut));

                break;

                // The sort by Tent / RV screen
            case SortByTentRV:

                // Filters the CampSites that don't have an actual checkout date
                // (ones that are still at the site)
                fileredListCampSites = (ArrayList<CampSite>) listCampSites.stream()
                        .filter(n -> n.actualCheckOut == null)
                        .collect(Collectors.toList());

                // Uses an anonymous class to sort the CampSites
                Collections.sort(fileredListCampSites, new Comparator<CampSite>() {
                    @Override
                    // Overridden method from Comparator
                    public int compare(CampSite o1, CampSite o2) {

                        // Uses a helper method to compare the type of CampSite object
                        int typeCompare = o1.CompareTentThenRV().compareTo(o2.CompareTentThenRV());

                        // Sorts by Tent, then RV if there are 2 objects found that
                        // are not the same type
                        if (typeCompare != 0) {
                            return typeCompare;
                        }

                        // After sorting by type of CampSite, the compare method
                        // will then sort by guest name
                        int nameCompare = o1.getGuestName().compareTo(o2.getGuestName());

                        if (nameCompare != 0) {
                            return nameCompare;
                        }

                        // If there are instances of both the type of CampSite
                        // and the name being the same, the compare method will
                        // then sort by estimated checkout date instead.
                        return o1.getEstimatedCheckOut().compareTo(o2.estimatedCheckOut);
                    }
                });

                break;

                // A default action that is taken if the screen does not match any
                // of the above instances
            default:
                throw new RuntimeException("upDate is in undefined state: " + display);
        }
        fireTableStructureChanged();
    }

    /***************************************************
     * This method gets the column name from the array
     * of Strings and displays them on the screen
     *
     * @param col is the number of columns on the given
     *            screen.
     *
     * @return the name of the column from the String
     * array.
     *
     * @throws RuntimeException if none of the cases for
     * the display are properly met.
     */
    @Override
    public String getColumnName(int col) {
        switch (display) {
            // The current park
            case CurrentParkStatus:
                return columnNamesCurrentPark[col];
            // The people who have checked out
            case CheckOutGuest:
                return columnNamesforCheckouts[col];
            // The overdue screen
            case OverDueScreen:
                return columnNamesOverDueScreen[col];
            // The sort by RV / Tent screen
            case SortByRVTent:
                return columnNamesSortByRVTent[col];
            // The sort by Tent / RV screen
            case SortByTentRV:
                return columnNamesSortByTentRV[col];
        }
        throw new RuntimeException("Undefined state for Col Names: " + display);
    }

    /***************************************************
     * This method gets the number of columns, given the
     * array of Strings.
     *
     * @return the length of the array of Strings.
     *
     * @throws IllegalArgumentException if there is no
     * array of Strings that is being read.
     */
    @Override
    public int getColumnCount() {
        switch (display) {
            // The current park
            case CurrentParkStatus:
                return columnNamesCurrentPark.length;
            // The people who have checked out
            case CheckOutGuest:
                return columnNamesforCheckouts.length;
            // The overdue screen
            case OverDueScreen:
                return columnNamesOverDueScreen.length;
            // The sort by RV / Tent screen
            case SortByRVTent:
                return columnNamesSortByRVTent.length;
            // The sort by Tent / RV screen
            case SortByTentRV:
                return columnNamesSortByTentRV.length;
        }
        throw new IllegalArgumentException();
    }

    /***************************************************
     * This method gets the number of CampSite objects
     * that are in the filtered arrayList.
     *
     * @return the number of CampSite objects
     */
    @Override
    public int getRowCount() {
        // returns number of items in the arrayList
        return fileredListCampSites.size();
    }

    /***************************************************
     * This method gets the current value of a table's
     * cell, given the row and column number
     *
     * @param row is the number of the CampSite object
     * @param col is the parameter of the CampSite object
     *
     * @return the value of the cell within the table
     *
     * @throws IllegalArgumentException if the switch
     * for display does not reach any the screens
     */
    @Override
    public Object getValueAt(int row, int col) {
        switch (display) {
            // The current park
            case CurrentParkStatus:
                return currentParkScreen(row, col);
            // The people who have checked out
            case CheckOutGuest:
                return checkOutScreen(row, col);
            // The overdue screen
            case OverDueScreen:
                return OverDueScreen(row, col);
            // The sort by RV / Tent screen
            case SortByRVTent:
                return SortByRVTentScreen(row, col);
            // The sort by Tent / RV screen
            case SortByTentRV:
                return SortByTentRVScreen(row, col);

        }
        throw new IllegalArgumentException();
    }

    /***************************************************
     * This method creates the table for the current
     * park given the row and column number
     *
     * @param row is the number of the CampSite object
     * @param col is the parameter of the CampSite object
     *
     * @return what should go into the table cell
     *
     * @throws RuntimeException if the switch statement for
     * column reaches the default statement
     */
    private Object currentParkScreen(int row, int col) {
        // Switch statement for each of the CampSite's parameters
        switch (col) {
            // Returns the guest name of the CampSite
            case 0:
                return (fileredListCampSites.get(row).guestName);

            // Returns the cost of the CampSite reservation
            case 1:
                return (fileredListCampSites.get(row).getCost(fileredListCampSites.
                        get(row).estimatedCheckOut));

            // Returns the CampSite's check in date
            case 2:
                return (formatter.format(fileredListCampSites.get(row).checkIn.getTime()));

            // Returns the CampSite's estimated check out date
            case 3:
                // If the CampSite does not have an estimated check out date
                if (fileredListCampSites.get(row).estimatedCheckOut == null)
                    return "-";

                return (formatter.format(fileredListCampSites.get(row).estimatedCheckOut.
                        getTime()));

            // There is no case 4 because there are 2 different cases
            // depending on if the CampSite object is a Tent or RV
            case 4:

            // Returns the number of tenters or wattage of the RV
            // (depending on the type of CampSite)
            case 5:
                // Returns the wattage of the RV if the CampSite
                // object is an RV
                if (fileredListCampSites.get(row) instanceof RV)
                    if (col == 4)
                        return (((RV) fileredListCampSites.get(row)).getPower());
                    else
                        return "";

                // Returns the number of tenters if the CampSite
                // object is a Tent
                else {
                    if (col == 5)
                        return (((TentOnly) fileredListCampSites.get(row)).
                                getNumberOfTenters());
                    else
                        return "";
                }
            // Default case if the cases do not match the given row or column
            default:
                throw new RuntimeException("Row,col out of range: " + row + " " + col);
        }
    }

    private Object checkOutScreen(int row, int col) {
        switch (col) {
            case 0:
                return (fileredListCampSites.get(row).guestName);

            case 1:
                return (fileredListCampSites.
                        get(row).getCost(fileredListCampSites.get(row).
                        estimatedCheckOut));
            case 2:
                return (formatter.format(fileredListCampSites.get(row).checkIn.
                        getTime()));

            case 3:
                return (formatter.format(fileredListCampSites.get(row).actualCheckOut.
                        getTime()));

            case 4:
                return (fileredListCampSites.
                        get(row).getCost(fileredListCampSites.get(row).
                        actualCheckOut
                ));

            default:
                throw new RuntimeException("Row,col out of range: " + row + " " + col);
        }
    }

    private Object OverDueScreen(int row, int col) {
        switch (col) {
            case 0:
                return (fileredListCampSites.get(row).guestName);

            case 1:
                return (fileredListCampSites.
                        get(row).getCost(fileredListCampSites.get(row).
                        estimatedCheckOut));

            case 2:
                return (formatter.format(fileredListCampSites.get(row).checkIn.
                        getTime()));

            case 3:
                if (fileredListCampSites.get(row).estimatedCheckOut == null)
                    return "-";

                return (formatter.format(fileredListCampSites.get(row).estimatedCheckOut.
                        getTime()));

            case 4:
                GregorianCalendar checkOutTemp, overDueTemp;
                enteredOverDueDate = getEnteredOverDueDate();

                int dayCounter = 0;

                checkOutTemp = (GregorianCalendar) fileredListCampSites.get(row).estimatedCheckOut
                        .clone();
                overDueTemp = (GregorianCalendar) enteredOverDueDate.clone();


                if (checkOutTemp.after(overDueTemp)) {
                    while (checkOutTemp.after(overDueTemp)) {
                        dayCounter--;
                        checkOutTemp.add(Calendar.DATE, -1);
                    }
                }

                if (overDueTemp.after(checkOutTemp)) {
                    while (overDueTemp.after(checkOutTemp)) {
                        dayCounter++;
                        overDueTemp.add(Calendar.DATE, -1);
                    }
                }

                return dayCounter;

            default:
                throw new RuntimeException("Row,col out of range: " + row + " " + col);

        }
    }

    private Object SortByRVTentScreen (int row, int col) {
        switch (col) {
            case 0:
                return (fileredListCampSites.get(row).guestName);

            case 1:
                return (fileredListCampSites.get(row).getCost(fileredListCampSites.
                        get(row).estimatedCheckOut));

            case 2:
                return (formatter.format(fileredListCampSites.get(row).checkIn.getTime()));

            case 3:
                if (fileredListCampSites.get(row).estimatedCheckOut == null)
                    return "-";

                return (formatter.format(fileredListCampSites.get(row).estimatedCheckOut.
                        getTime()));

            case 4:
            case 5:
                if (fileredListCampSites.get(row) instanceof RV)
                    if (col == 4)
                        return (((RV) fileredListCampSites.get(row)).getPower());
                    else
                        return "";

                else {
                    if (col == 5)
                        return (((TentOnly) fileredListCampSites.get(row)).
                                getNumberOfTenters());
                    else
                        return "";
                }
            default:
                throw new RuntimeException("Row,col out of range: " + row + " " + col);
        }
    }

    private Object SortByTentRVScreen (int row, int col) {
        switch (col) {
            case 0:
                return (fileredListCampSites.get(row).guestName);

            case 1:
                return (fileredListCampSites.get(row).getCost(fileredListCampSites.
                        get(row).estimatedCheckOut));

            case 2:
                return (formatter.format(fileredListCampSites.get(row).checkIn.getTime()));

            case 3:
                if (fileredListCampSites.get(row).estimatedCheckOut == null)
                    return "-";

                return (formatter.format(fileredListCampSites.get(row).estimatedCheckOut.
                        getTime()));

            case 4:
            case 5:
                if (fileredListCampSites.get(row) instanceof RV)
                    if (col == 4)
                        return (((RV) fileredListCampSites.get(row)).getPower());
                    else
                        return "";

                else {
                    if (col == 5)
                        return (((TentOnly) fileredListCampSites.get(row)).
                                getNumberOfTenters());
                    else
                        return "";
                }
            default:
                throw new RuntimeException("Row,col out of range: " + row + " " + col);
        }
    }

    public void add(CampSite a) {
        listCampSites.add(a);
        UpdateScreen();
    }

    public CampSite get(int i) {
        return fileredListCampSites.get(i);
    }

    public void upDate(int index, CampSite unit) {
        UpdateScreen();
    }

    public void saveDatabase(String filename) {
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(listCampSites);
            os.close();
        } catch (IOException ex) {
            throw new RuntimeException("Saving problem! " + display);

        }
    }

    public void loadDatabase(String filename) {
        listCampSites.clear();

        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream is = new ObjectInputStream(fis);

            listCampSites = (ArrayList<CampSite>) is.readObject();
            UpdateScreen();
            is.close();
        } catch (Exception ex) {
            throw new RuntimeException("Loading problem: " + display);

        }
    }

    public void saveText(String filename){
        try{
            PrintWriter out = new PrintWriter(
                    new BufferedWriter(new FileWriter(filename)));
            out.println(listCampSites.size());
            for(int i = 0; i < listCampSites.size(); i++){
                CampSite site = listCampSites.get(i);
                out.println(site.getClass().getName());
                out.println(site.getGuestName());
                out.println(formatter.format(site.checkIn.getTime()));
                out.println(formatter.format(site.estimatedCheckOut.getTime()));

                if(site.actualCheckOut != null)
                    out.println(formatter.format(site.actualCheckOut.getTime()));
                else
                    out.println("null");

                if(site instanceof TentOnly)
                    out.println(((TentOnly) site).getNumberOfTenters());
                else if(site instanceof RV)
                    out.println(((RV) site).getPower());

            }
            out.close();
        } catch(Exception e){
            throw new RuntimeException("Saving problem! " + display);
        }
    }

    public void loadText(String filename){
        listCampSites.clear();
        try {
            FileInputStream fis = new FileInputStream(filename);
            Scanner scnr = new Scanner(fis);
            int numSites = Integer.parseInt(scnr.nextLine());
            for(int i = 0; i < numSites; i++){
                GregorianCalendar checkIn = new GregorianCalendar();
                GregorianCalendar estimatedCheckOut = new GregorianCalendar();
                GregorianCalendar actualCheckOut = new GregorianCalendar();

                String siteType = scnr.nextLine();
                String guestName = scnr.nextLine();
                checkIn.setTime(formatter.parse(scnr.nextLine()));
                estimatedCheckOut.setTime(formatter.parse(scnr.nextLine()));
                String temp = scnr.nextLine();
                if(temp.equals("null"))
                    actualCheckOut = null;
                else
                    actualCheckOut.setTime(formatter.parse(temp));

                int tentersOrPower = Integer.parseInt(scnr.nextLine());
                if(tentersOrPower < 0)
                    throw new IllegalArgumentException();

                if(siteType.equals("Project3.RV")) {
                    RV rv1 = new RV(guestName, checkIn, estimatedCheckOut, actualCheckOut, tentersOrPower);
                    listCampSites.add(rv1);
                }
                else if(siteType.equals("Project3.TentOnly")) {
                    TentOnly t1 = new TentOnly(guestName, checkIn, estimatedCheckOut, actualCheckOut, tentersOrPower);
                    listCampSites.add(t1);
                }
            }
            UpdateScreen();
        }
        catch(Exception e) {
            throw new RuntimeException("Loading problem: " + display);
        }
    }

    public void createList() {
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        GregorianCalendar g1 = new GregorianCalendar();
        GregorianCalendar g2 = new GregorianCalendar();
        GregorianCalendar g3 = new GregorianCalendar();
        GregorianCalendar g4 = new GregorianCalendar();
        GregorianCalendar g5 = new GregorianCalendar();
        GregorianCalendar g6 = new GregorianCalendar();

        try {
            Date d1 = df.parse("1/20/2020");
            g1.setTime(d1);
            Date d2 = df.parse("12/22/2020");
            g2.setTime(d2);
            Date d3 = df.parse("12/20/2019");
            g3.setTime(d3);
            Date d4 = df.parse("3/25/2020");
            g4.setTime(d4);
            Date d5 = df.parse("1/20/2010");
            g5.setTime(d5);
            Date d6 = df.parse("3/29/2020");
            g6.setTime(d6);

            TentOnly tentOnly1 = new TentOnly("T1", g3, g2,null,4);
            TentOnly tentOnly11 = new TentOnly("T1", g3,g1, null, 8);
            TentOnly tentOnly111 = new TentOnly("T1", g5,g3, null, 8);
            TentOnly tentOnly2 = new TentOnly("T2", g5, g3,null, 5);
            TentOnly tentOnly3 = new TentOnly("T3", g3, g1, g1,7);

            RV RV1 = new RV("RV1",g4,g6,null, 1000);
            RV RV2 = new RV("RV2",g5,g3,null, 1000);
            RV RV22 = new RV("RV2", g3,g1,null, 2000);
            RV RV222 = new RV("RV2", g3,g6,null, 2000);
            RV RV3 = new RV("RV3",g5,g4,g3, 1000);

            add(tentOnly1);
            add(tentOnly2);
            add(tentOnly3);
            add(tentOnly11);
            add(tentOnly111);

            add(RV1);
            add(RV2);
            add(RV3);
            add(RV22);
            add(RV222);

        } catch (ParseException e) {
            throw new RuntimeException("Error in testing, creation of list");
        }
    }

    public GregorianCalendar getEnteredOverDueDate() {
        return enteredOverDueDate;
    }

    public void setEnteredOverDueDate(GregorianCalendar enteredOverDueDate) {
        this.enteredOverDueDate = enteredOverDueDate;
    }

}