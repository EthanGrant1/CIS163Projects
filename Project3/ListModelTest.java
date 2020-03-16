package Project3;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.*;

public class ListModelTest {

    private DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void setDisplay() {
        ListModel list1 = new ListModel();

        ArrayList<CampSite> sites = list1.getListCampSites();

        // Setting the screen to current park
        ScreenDisplay test = ScreenDisplay.CurrentParkStatus;
        list1.setDisplay(test);

        // Checking that the filtered list of campsites all have no
        // actual checkout date
        for (int i = 0; i < list1.getFileredListCampSites().size(); i++) {
            assert(list1.getFileredListCampSites().get(i).getActualCheckOut() == null);
        }

        // Setting the screen to the checked out guests screen
        test = ScreenDisplay.CheckOutGuest;
        list1.setDisplay(test);

        // Checking that the filtered list of campsites all have an
        // actual checkout date
        for (int i = 0; i < list1.getFileredListCampSites().size(); i++) {
            assert(list1.getFileredListCampSites().get(i).getActualCheckOut() != null);
        }

        // Setting the screen to Sort by Tent / RV screen
        test = ScreenDisplay.SortByTentRV;
        list1.setDisplay(test);

        // Checking that the first 4 campsites are tents
        for (int i = 0; i <= 3; i++) {
            assert(list1.getFileredListCampSites().get(i) instanceof TentOnly);
        }

        // Setting the screen to Sort by RV / Tent screen
        test = ScreenDisplay.SortByRVTent;
        list1.setDisplay(test);

        // Checking that the first 4 campsites are RVs
        for (int i = 0; i <= 3; i++) {
            assert(list1.getFileredListCampSites().get(i) instanceof RV);
        }
    }

    @Test (expected = RuntimeException.class)
    public void setDisplayError() {
        ListModel list1 = new ListModel();
        ScreenDisplay test = null;
        list1.setDisplay(test);
    }

    @Test
    public void getColumnCount() {
        ListModel list1 = new ListModel();

        ArrayList<CampSite> sites = list1.getListCampSites();

        // Setting the screen to current park
        ScreenDisplay test = ScreenDisplay.CurrentParkStatus;
        list1.setDisplay(test);

        // Checking that there are 6 parameters shown
        assertEquals(list1.getColumnCount(), 6);

        // Setting the screen to the checked out guests screen
        test = ScreenDisplay.CheckOutGuest;
        list1.setDisplay(test);

        // Checking that there are 5 parameters shown
        assertEquals(list1.getColumnCount(), 5);

        // Setting the screen to Sort by Tent / RV screen
        test = ScreenDisplay.SortByTentRV;
        list1.setDisplay(test);

        // Checking that there are 6 parameters shown
        assertEquals(list1.getColumnCount(), 6);

        // Setting the screen to Sort by RV / Tent screen
        test = ScreenDisplay.SortByRVTent;
        list1.setDisplay(test);

        // Checking that there are 6 parameters shown
        assertEquals(list1.getColumnCount(), 6);
    }

    @Test
    public void getRowCount() {
        ListModel list1 = new ListModel();

        ArrayList<CampSite> sites = list1.getListCampSites();

        // Setting the screen to current park
        ScreenDisplay test = ScreenDisplay.CurrentParkStatus;
        list1.setDisplay(test);

        // Checks that there are 8 campsites that are in the current park
        assertEquals(8, list1.getRowCount());

        // Setting the screen to the checked out guests screen
        test = ScreenDisplay.CheckOutGuest;
        list1.setDisplay(test);

        // Checks that there are 2 campsites that have checked out
        assertEquals(2, list1.getRowCount());
    }

    @Test
    public void getValueAt() {
    }

    @Test
    public void getColumnName() {
    }

    @Test
    public void remove() {
    }

    @Test
    public void add() {
    }

    @Test
    public void get() {
    }

    @Test
    public void getAbsolute() {
    }

    @Test
    public void upDate() {
    }

    //Tests saving an ArrayList of campsites to a special file
    @Test
    public void saveDatabase() throws ParseException {
        GregorianCalendar g1 = new GregorianCalendar();
        g1.setTime(formatter.parse("2/22/2020"));
        GregorianCalendar g2 = new GregorianCalendar();
        g2.setTime(formatter.parse("2/23/2020"));

        RV rv = new RV("Test", g1, g2, null, 10);

        ListModel list1 = new ListModel();
        list1.add(rv);

        ListModel list2 = new ListModel();

        list1.saveDatabase("SaveDatabaseTest");
        list2.loadDatabase("SaveDatabaseTest");

        assertEquals(list1.getListCampSites(), list2.getListCampSites());
    }

    //Tests loading an ArrayList of campsites from a special file
    @Test
    public void loadDatabase() throws ParseException {
        GregorianCalendar g1 = new GregorianCalendar();
        g1.setTime(formatter.parse("2/22/2020"));
        GregorianCalendar g2 = new GregorianCalendar();
        g2.setTime(formatter.parse("2/23/2020"));

        RV rv = new RV("Test", g1, g2, null, 10);

        ListModel list1 = new ListModel();
        list1.add(rv);

        list1.saveDatabase("LoadDatabaseTest");

        ListModel list2 = new ListModel();
        list2.add(rv);

        list1 = new ListModel();
        list1.loadDatabase("LoadDatabaseTest");

        assertEquals(list1.getListCampSites(), list2.getListCampSites());
    }

    //Tests saving an ArrayList of campsites to a text file
    @Test
    public void saveAsText() throws ParseException {
        GregorianCalendar g1 = new GregorianCalendar();
        g1.setTime(formatter.parse("2/22/2020"));
        GregorianCalendar g2 = new GregorianCalendar();
        g2.setTime(formatter.parse("2/23/2020"));

        RV rv = new RV("Test", g1, g2, null, 10);

        ListModel list1 = new ListModel();
        list1.add(rv);

        ListModel list2 = new ListModel();

        list1.saveText("SaveAsTextTest.txt");
        list2.loadText("SaveAsTextTest.txt");

        assertEquals(list1.getListCampSites(), list2.getListCampSites());
    }

    //Tests loading an ArrayList of campsites from a text file
    @Test
    public void loadFromText() throws ParseException {
        GregorianCalendar g1 = new GregorianCalendar();
        g1.setTime(formatter.parse("2/22/2020"));
        GregorianCalendar g2 = new GregorianCalendar();
        g2.setTime(formatter.parse("2/23/2020"));

        RV rv = new RV("Test", g1, g2, null, 10);

        ListModel list1 = new ListModel();
        list1.add(rv);

        list1.saveText("LoadTextTest.txt");

        ListModel list2 = new ListModel();
        list2.add(rv);

        list1 = new ListModel();
        list1.loadText("LoadTextTest.txt");

        assertEquals(list1.getListCampSites(), list2.getListCampSites());
    }

    //Tests createList() to make sure the default campsites are correct
    @Test
    public void createList() throws ParseException {
        ListModel list1 = new ListModel();
        list1.setListCampSites(new ArrayList<CampSite>());
        list1.createList();

        ArrayList<CampSite> sites = list1.getListCampSites();

        GregorianCalendar g1 = new GregorianCalendar();
        GregorianCalendar g2 = new GregorianCalendar();
        GregorianCalendar g3 = new GregorianCalendar();
        GregorianCalendar g4 = new GregorianCalendar();
        GregorianCalendar g5 = new GregorianCalendar();
        GregorianCalendar g6 = new GregorianCalendar();

        Date d1 = formatter.parse("1/20/2020");
        g1.setTime(d1);
        Date d2 = formatter.parse("12/22/2020");
        g2.setTime(d2);
        Date d3 = formatter.parse("12/20/2019");
        g3.setTime(d3);
        Date d4 = formatter.parse("3/25/2020");
        g4.setTime(d4);
        Date d5 = formatter.parse("1/20/2010");
        g5.setTime(d5);
        Date d6 = formatter.parse("3/29/2020");
        g6.setTime(d6);

        assertEquals(sites.get(0), new TentOnly("T1", g3, g2,null,4));
        assertEquals(sites.get(1), new TentOnly("T2", g5, g3,null, 5));
        assertEquals(sites.get(2), new TentOnly("T3", g3, g1, g1,7));
        assertEquals(sites.get(3), new TentOnly("T1", g3,g1, null, 8));
        assertEquals(sites.get(4), new TentOnly("T1", g5,g3, null, 8));
        assertEquals(sites.get(5), new RV("RV1",g4,g6,null, 1000));
        assertEquals(sites.get(6), new RV("RV2",g5,g3,null, 1000));
        assertEquals(sites.get(7), new RV("RV3",g5,g4,g3, 1000));
        assertEquals(sites.get(8), new RV("RV2", g3,g1,null, 2000));
        assertEquals(sites.get(9), new RV("RV2", g3,g6,null, 2000));
    }
}