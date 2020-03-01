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

public class ListModel extends AbstractTableModel {
    private ArrayList<CampSite> listCampSites;
    private ArrayList<CampSite> fileredListCampSites;

    private ScreenDisplay display;

    private String[] columnNamesCurrentPark = {"Guest Name", "Est. Cost",
            "Check In Date", "Est. Check Out Date ", "Max Power", "Num. of Tenters"};

    private String[] columnNamesforCheckouts = {"Guest Name", "Est. Cost",
            "Check In Date", "Actual Check Out Date ", " Real Cost"};

    private String[] columnNamesOverDueScreen = {"Guest Name", "Est. Cost",
            "Check in Date", "Est. Check out Date", "Days OverDue"};

    private DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    private String date;

    public GregorianCalendar enteredOverDueDate;

    public ListModel() {
        super();
        display = CurrentParkStatus;
        listCampSites = new ArrayList<CampSite>();
        UpdateScreen();
        createList();
    }

    public void setDisplay(ScreenDisplay selected) {
        display = selected;
        UpdateScreen();
    }

    private void UpdateScreen() {
        switch (display) {
            case CurrentParkStatus:
                fileredListCampSites = (ArrayList<CampSite>) listCampSites.stream().
                        filter(n -> n.actualCheckOut == null).collect(Collectors.toList());

                // Note: This uses Lambda function
                Collections.sort(fileredListCampSites, (n1, n2) -> n1.getGuestName().compareTo(n2.guestName));
                break;

            case CheckOutGuest:
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

            case OverDueScreen:
                fileredListCampSites = (ArrayList<CampSite>) listCampSites.stream()
                        .filter(n -> n.getEstimatedCheckOut() != null)
                        .collect(Collectors.toList());

                Collections.sort(fileredListCampSites, Comparator.comparing(CampSite::getEstimatedCheckOut));
                break;

            default:
                throw new RuntimeException("upDate is in undefined state: " + display);
        }
        fireTableStructureChanged();
    }

    @Override
    public String getColumnName(int col) {
        switch (display) {
            case CurrentParkStatus:
                return columnNamesCurrentPark[col];
            case CheckOutGuest:
                return columnNamesforCheckouts[col];
            case OverDueScreen:
                return columnNamesOverDueScreen[col];
        }
        throw new RuntimeException("Undefined state for Col Names: " + display);
    }

    @Override
    public int getColumnCount() {
        switch (display) {
            case CurrentParkStatus:
                return columnNamesCurrentPark.length;
            case CheckOutGuest:
                return columnNamesforCheckouts.length;
            case OverDueScreen:
                return columnNamesOverDueScreen.length;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public int getRowCount() {
        return fileredListCampSites.size();     // returns number of items in the arraylist
    }

    @Override
    public Object getValueAt(int row, int col) {
        switch (display) {
            case CurrentParkStatus:
                return currentParkScreen(row, col);
            case CheckOutGuest:
                return checkOutScreen(row, col);
            case OverDueScreen:
                return OverDueScreen(row, col);
        }
        throw new IllegalArgumentException();
    }

    private Object currentParkScreen(int row, int col) {
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
            SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            for(int i = 0; i < listCampSites.size(); i++){
                CampSite site = listCampSites.get(i);
                String checkInString = df.format(site.checkIn.getTime());
                String estimatedCheckOutString = df.format(site.estimatedCheckOut.getTime());
                String actualCheckOutString = "null";
                if(site.actualCheckOut != null)
                    actualCheckOutString = df.format(site.actualCheckOut.getTime());

                if(site instanceof TentOnly){
                    out.println(site.guestName + "," + checkInString + "," +
                            estimatedCheckOutString + "," + actualCheckOutString +
                            "," + ((TentOnly) site).getNumberOfTenters());
                }
                else if(site instanceof RV){
                    out.println(site.guestName + "," + checkInString + "," +
                            estimatedCheckOutString + "," + actualCheckOutString +
                            "," + ((RV) site).getPower());
                }
            }
            out.close();
        } catch(Exception e){
            throw new RuntimeException("Saving problem! " + display);
        }
    }

    public void loadText(String filename){
        try {
            FileInputStream fis = new FileInputStream(filename);
            Scanner scnr = new Scanner(fis);
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