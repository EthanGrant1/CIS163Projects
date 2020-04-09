package Project4;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class MySingleWithOutTailLinkedListTest {

    /******************************************************
     * This method tests the size method in the linked list
     * class.
     */
    @Test
    public void size() {
        // Creating a linked list
        MySingleWithOutTailLinkedList list = new MySingleWithOutTailLinkedList();
        // Random elements for creating the list
        Random rand = new Random();
        int randomPick;
        CampSite randomCamp = new CampSite() {
            @Override
            public double getCost(GregorianCalendar checkOut) {
                return 0;
            }
        };
        // Some dummy variable calendars
        GregorianCalendar checkin = new GregorianCalendar(2020,Calendar.APRIL,9);
        GregorianCalendar checkout = new GregorianCalendar(2020, Calendar.APRIL, 10);

        // Creates 100 random campsites
        for (int i = 0; i < 100; i++) {
            randomPick = rand.nextInt(2);

            if (randomPick == 0) {
                randomCamp = new TentOnly("T" + i, checkin, checkout, checkout, 10);
            }
            if (randomPick == 1) {
                randomCamp = new RV("RV" + i, checkin, checkout, checkout, 1000);
            }
            list.add(randomCamp);
            // Checks if the size of the list is counted correctly every time a campsite is added
            assertEquals(i + 1, list.size());
        }
    }

    /******************************************************
     * This method tests the clear method in the linked list
     * class.
     */
    @Test
    public void clear() {
        // Creating a linked list
        MySingleWithOutTailLinkedList list = new MySingleWithOutTailLinkedList();
        // Random elements for creating the list
        Random rand = new Random();
        int randomPick;
        CampSite randomCamp = new CampSite() {
            @Override
            public double getCost(GregorianCalendar checkOut) {
                return 0;
            }
        };
        // Some dummy variable calendars
        GregorianCalendar checkin = new GregorianCalendar(2020,Calendar.APRIL,9);
        GregorianCalendar checkout = new GregorianCalendar(2020, Calendar.APRIL, 10);

        // Creates 100 random campsites
        for (int i = 0; i < 100; i++) {
            randomPick = rand.nextInt(2);

            if (randomPick == 0) {
                randomCamp = new TentOnly("T" + i, checkin, checkout, checkout, 10);
            }
            if (randomPick == 1) {
                randomCamp = new RV("RV" + i, checkin, checkout, checkout, 1000);
            }
            list.add(randomCamp);
        }
        // Checks that there are 100 campsites in the list
        assertEquals(100, list.size());
        // Clears the list
        list.clear();
        // Checks that the list size is now 0
        assertEquals(0, list.size());
    }

    /******************************************************
     * This method tests the add method in the linked list
     * class.
     */
    @Test
    public void add() {
    }

    /******************************************************
     * This method tests the remove method in the linked list
     * class.
     */
    @Test
    public void remove() {
        // Creating a linked list
        MySingleWithOutTailLinkedList list = new MySingleWithOutTailLinkedList();
        // Random elements for creating the list
        Random rand = new Random();
        // Some dummy variable calendars
        GregorianCalendar checkin = new GregorianCalendar(2020,Calendar.APRIL,9);
        GregorianCalendar checkout = new GregorianCalendar(2020, Calendar.APRIL, 10);
        // The campsite that will be returned by the remove method
        CampSite removed;

        // Testing error cases

        // No list
        removed = list.remove(0);
        // There is no list to remove
        assert (removed == null);

        // Campsites are added to the list
        TentOnly tent = new TentOnly("T" + rand.nextInt(100),checkin, checkout, checkout, rand.nextInt(100));
        RV rv = new RV("RV" + rand.nextInt(100), checkin, checkout, checkout, rand.nextInt(10000));
        list.add(tent);
        list.add(rv);

        // Index out of bounds

        // Less than 0
        removed = list.remove(-100);
        assert (removed == null);

        // Greater than or equal to the list size
        removed = list.remove(100);
        assert (removed == null);

        // Removing the top
        CampSite top = list.get(0);
        removed = list.remove(0);
        assert (removed == top);

        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                removed = new TentOnly("T" + rand.nextInt(100),checkin, checkout, checkout, rand.nextInt(100));
            }
            else {
                removed = new RV("RV" + rand.nextInt(100), checkin, checkout, checkout, rand.nextInt(10000));
            }
            list.add(removed);
        }

        // Removing a campsite from the first half of
        // the list and checks its parameters
        removed = list.remove(2);
        assert (removed instanceof TentOnly &&
                removed.getGuestName().contains("T"));

        // Removing a campsite from the latter half of
        // the list and checks its parameters
        removed = list.remove(17);
        assert (removed instanceof RV &&
                removed.getGuestName().contains("RV"));
    }

    /******************************************************
     * This method tests the get method in the linked list
     * class.
     */
    @Test
    public void get() {
    }
}