package Project3;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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

    //Test saving with illegal characters
    @Test (expected = RuntimeException.class)
    public void saveDatabaseError(){
        ListModel list1 = new ListModel();
        list1.saveDatabase("|/*?");
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

    //Tests trying to load a nonsense file
    @Test (expected = RuntimeException.class)
    public void loadDatabaseError() throws IOException {
        PrintWriter out = new PrintWriter(
                new BufferedWriter(new FileWriter("LoadDatabaseError")));

        out.println("uaobguiebg");
        out.close();

        ListModel list1 = new ListModel();
        list1.loadDatabase("LoadDatabaseError");
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

    //Tests saving with illegal characters
    @Test (expected = RuntimeException.class)
    public void saveTextError(){
        ListModel list1 = new ListModel();
        list1.saveText("|/*?.txt");
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

    //Tests loading a completely original file
    @Test
    public void loadFromText2() throws IOException, ParseException {
        PrintWriter out = new PrintWriter(
                new BufferedWriter(new FileWriter("LoadTextTest2.txt")));

        out.println("2");
        out.println("Project3.TentOnly");
        out.println("Test1");
        out.println("2/22/2020");
        out.println("2/23/2020");
        out.println("null");
        out.println("3");

        out.println("Project3.RV");
        out.println("Test2");
        out.println("2/23/2020");
        out.println("2/24/2020");
        out.println("2/24/2020");
        out.println("100");

        out.close();

        GregorianCalendar g1 = new GregorianCalendar();
        g1.setTime(formatter.parse("2/22/2020"));
        GregorianCalendar g2 = new GregorianCalendar();
        g2.setTime(formatter.parse("2/23/2020"));
        GregorianCalendar g3 = new GregorianCalendar();
        g3.setTime(formatter.parse("2/24/2020"));

        TentOnly test1 = new TentOnly("Test1", g1, g2, null, 3);
        RV test2 = new RV("Test2", g2, g3, g3, 100);

        ListModel list1 = new ListModel();
        list1.loadText("LoadTextTest2.txt");
        ArrayList<CampSite> sites = list1.getListCampSites();

        assertEquals(2, sites.size());
        assertEquals(test1, sites.get(0));
        assertEquals(test2, sites.get(1));

    }

    //Tests trying to load a nonsense file
    @Test (expected = RuntimeException.class)
    public void loadTextNonsenseError() throws IOException {
        PrintWriter out = new PrintWriter(
                new BufferedWriter(new FileWriter("LoadTextError.txt")));

        out.println("uaobguiebg");
        out.close();

        ListModel list1 = new ListModel();
        list1.loadText("LoadDatabaseError.txt");
    }

    //Tests when the estimated check out is before the check in
    @Test (expected = RuntimeException.class)
    public void loadTextEstCheckOutEarlyError() throws IOException{
        PrintWriter out = new PrintWriter(
                new BufferedWriter(new FileWriter("EstCheckOutBeforeCheckIn.txt")));

        out.println("1");
        out.println("Project3.TentOnly");
        out.println("Test1");
        out.println("2/22/2020");
        out.println("2/21/2020");
        out.println("null");
        out.println("3");
        out.close();

        ListModel list1 = new ListModel();
        list1.loadText("EstCheckOutBeforeCheckIn.txt");

    }

    //Tests when the actual check out is before the check in
    @Test (expected = RuntimeException.class)
    public void loadTextActualCheckOutEarlyError() throws IOException{
        PrintWriter out = new PrintWriter(
                new BufferedWriter(new FileWriter("ActualCheckOutBeforeCheckIn.txt")));

        out.println("1");
        out.println("Project3.TentOnly");
        out.println("Test1");
        out.println("2/22/2020");
        out.println("2/23/2020");
        out.println("2/21/2020");
        out.println("3");
        out.close();

        ListModel list1 = new ListModel();
        list1.loadText("ActualCheckOutBeforeCheckIn.txt");
    }

    //Tests when a negative number of tenters/power is given
    @Test (expected = RuntimeException.class)
    public void loadNegativeError() throws IOException{
        PrintWriter out = new PrintWriter(
                new BufferedWriter(new FileWriter("NegativeLoad.txt")));

        out.println("1");
        out.println("Project3.TentOnly");
        out.println("Test1");
        out.println("2/22/2020");
        out.println("2/23/2020");
        out.println("null");
        out.println("-1");
        out.close();

        ListModel list1 = new ListModel();
        list1.loadText("NegativeLoad.txt");
    }

    //Tests when the first line gives the incorrect number of sites in the file
    @Test (expected = RuntimeException.class)
    public void loadIncorrectNumSitesError() throws IOException{
        PrintWriter out = new PrintWriter(
                new BufferedWriter(new FileWriter("LoadIncorrectNumSites.txt")));

        out.println("2");
        out.println("Project3.TentOnly");
        out.println("Test1");
        out.println("2/22/2020");
        out.println("2/23/2020");
        out.println("null");
        out.println("3");
        out.close();

        ListModel list1 = new ListModel();
        list1.loadText("LoadIncorrectNumSites.txt");
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

        assertEquals(10, sites.size());
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