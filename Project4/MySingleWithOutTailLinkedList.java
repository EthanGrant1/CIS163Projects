package Project4;

import jdk.dynalink.NamedOperation;

import java.io.Serializable;

/******************************************************
 * This class creates a LinkedList of CampSite objects
 * and will be used in the already existing CampSite
 * database code.
 */
public class MySingleWithOutTailLinkedList implements Serializable {
    /** The first Node in the list */
    private Node top;
    /** How many Nodes are in the list */
    public int size;

    /******************************************************
     * This is the constructor method for the LinkedList
     */
    public MySingleWithOutTailLinkedList() {
        // There is no first Node in the list
        top = null;
        // Size is zero
        size = 0;
    }

    /******************************************************
     * This method returns the size of the LinkedList
     *
     * @return the number of Nodes that are in the LinkedList
     */
    public int size() {
        // Initializes a counter variable
        int counter = 0;

        // Creates a temporary node that starts at the top
        // of the list
        Node temp = top;

        // While loop to iterate over the entire list
        while (temp != null) {
            // Counter increases
            counter++;
            // Temp is equal to the next Node in the list
            temp = temp.getNext();
        }
        // Sets the global variable size to the counter
        size = counter;
        // Returns the size of the list
        return size;
    }

    /******************************************************
     * This method makes the list empty
     */
    public void clear() {
        // There is no longer a first Node in the list, and
        // all other pointers and Nodes get deleted
        top = null;
        // The size of the list is zero
        size = 0;
    }

    /******************************************************
     * This method adds a Node to the list with and the
     * CampSite provided is the data that goes in that Node.
     * In addition, this method also automatically sorts the
     * list by Tents first, then RVs by estimated CheckOut
     * dates.
     *
     * @param s is the CampSite that will be the Node's "data"
     */
    public void add(CampSite s) {
       /*
       Requirement for this step: When you write the add method, you are required to sort
       by Tenters first (ordered by estimatedCheckOut) and by RV second (ordered by
       estimatedCheckOut). For this step, you need not worry about two estimatedCheckOut
       dates being equals. (See the final step regarding a change in this requirement).
        (Suggestion, once your code is working for this step, back it up, and proceed on.)
        */
        Node temp = top;
        Node temp2 = top;
        boolean added = false;


        // Case 0: there is no list, just add it
        if (temp == null) {
            top = new Node(s, null);
            return;
        }

        if (s instanceof TentOnly) {

            // Case 1: Just add to top if top is currently an RV
            if(top.getData() instanceof RV)
                top = new Node(s, top);

            // Case 2: If s's checkOut is before top's, make it the new top
            else if(s.getEstimatedCheckOut().before(top.getData().getEstimatedCheckOut())){
                top = new Node(s, top);
            }

            // Case 3: If s's checkOut is the same as top's compare the names
            else if(s.getEstimatedCheckOut().equals(top.getData().getEstimatedCheckOut())){
                if(s.getGuestName().compareTo(top.getData().getGuestName()) < 0)
                    top = new Node(s, top);
                else
                    top.setNext(new Node(s, top.getNext()));
            }

            // Case 4: If s's checkOut is after top's and there is nothing after
            // top or an RV after, make s top's next Node
            else if(s.getEstimatedCheckOut().after(top.getData().getEstimatedCheckOut())
                    && top.getNext() == null || s.getEstimatedCheckOut().after(top.getData().getEstimatedCheckOut())
                    && top.getNext().getData() instanceof RV){
                top.setNext(new Node(s, top.getNext()));
            }

            // Case 5: If s's checkOut is after top's but there are more Tents
            else if(s.getEstimatedCheckOut().after(top.getData().getEstimatedCheckOut())
                    && top.getNext().getData() instanceof TentOnly){

                while (temp.getNext() != null && !(temp.getNext().getData() instanceof RV)
                        && added == false) {

                    // Case 5a: s has a checkOut date between 2 other tent's checkOut dates
                    if(s.getEstimatedCheckOut().after(temp.getData().getEstimatedCheckOut())
                            && s.getEstimatedCheckOut().before(temp.getNext().getData().getEstimatedCheckOut())){
                        temp.setNext(new Node(s, temp.getNext()));
                        added = true;
                    }

                    // FIX ME Case 5b: s has a checkOut somewhere in the middle and shares
                    // it with another tent

                    temp = temp.getNext();
                }

                // Case 5c: If the tent has not been added yet and temp's next is null or an RV, just add it
                if(added == false)
                    temp.setNext(new Node(s, temp.getNext()));
            }
        }

        else if (s instanceof RV) {
            temp = top;

            while (temp.getNext() != null) {
                temp = temp.getNext();
            }

            if (s.getEstimatedCheckOut().compareTo(temp.getData().getEstimatedCheckOut()) > 0) {
                temp.setNext(new Node(s, temp.getNext()));
            }

            else if (s.getEstimatedCheckOut().compareTo(temp.getData().getEstimatedCheckOut()) < 0) {
                while (temp2.getNext() != temp) {
                    temp2 = temp2.getNext();
                }
                temp = temp2;
                temp.setNext(new Node(s, temp.getNext()));
            }

            else {
                temp.setNext(new Node(s, temp.getNext()));
            }
        }

//        // Case 0: No list, just add the CampSite
//        if (top == null) {
//            top = new Node(s, null);
//            return;
//        }
//
//        // Case 1: Only RVs in the list and adding a Tent
//        // Add Tent to the top
//        if (s instanceof TentOnly && top.getData() instanceof RV) {
//            top = new Node(s, top);
//            return;
//        }
//
//        // Case 2: Only Tents in the list and adding an RV
//        // Add RV to the bottom
//        if (s instanceof RV && top.getData() instanceof TentOnly) {
//            Node temp = top;
//            //Loop until you get to the bottom
//            while(temp.getNext() != null) {
//                temp = temp.getNext();
//            }
//            temp.setNext(new Node(s, null));
//            return;
//        }
//
//        // Case 3: If s is Tent and the top of the list is an Tent,
//        // and the guest name of s compared to top is less than top
//        if (s instanceof TentOnly && top.getData() instanceof TentOnly
//                && s.getGuestName().compareTo(top.getData().getGuestName()) < 0) {
//            Node temp = new Node(s, top);
//            top = temp;
//            return;
//        }
//
//        // Case 4: If s is a Tent and the top of the list is an Tent,
//        // and the guest name of s compared to top is less than top
//        if (s instanceof TentOnly && top.getData() instanceof TentOnly
//                && s.getGuestName().compareTo(top.getData().getGuestName()) > 0) {
//            Node temp = new Node(s, null);
//            top.setNext(temp);
//            temp.setNext(top.getNext().getNext());
//            return;
//        }
//
//        // Case 5: If s is an RV and the top of the list is an RV,
//        // and the guest name of s compared to top is less than top
//        if (s instanceof RV && top.getData() instanceof RV
//                && s.getGuestName().compareTo(top.getData().getGuestName()) < 0) {
//            Node temp = new Node(s, top);
//            top = temp;
//            return;
//        }
//
//        // Case 6: If s is an RV and the top of the list is an RV,
//        // and the guest name of s compared to top is more than top
//        if (s instanceof RV && top.getData() instanceof RV
//                && s.getGuestName().compareTo(top.getData().getGuestName()) > 0) {
//            Node temp = new Node(s, null);
//            top.setNext(temp);
//            temp.setNext(top.getNext().getNext());
//            return;
//        }
    }

    /******************************************************
     * This method removes a Node from the list, given the
     * index number of the Node.
     *
     * @param index is the number of the Node in the list
     *
     * @return the removed CampSite
     */
    public CampSite remove(int index) {
        // Case 0: There is no list to remove
        if (top == null) {
            return null;
        }

        // Case 1: Index out of bounds
        if (index < 0) {
            return null;
        }

        // Case 2: Index is greater than the size
        // of the list
        if (index >= size()) {
            return null;
        }

        // Now the index is within bounds of the list

        // Case 3: Removing the top
        if (index == 0) {
            top = top.getNext();
            return top.getData();
        }

        // Case 4: Multiple items in the list
        Node temp = top;
        // Loops to find the Node before the one
        // you want to delete
        for (int i = 0; i < (index - 1); i++) {
            temp = temp.getNext();
        }
        // The CampSite that is to be removed
        Node removedCampSite = new Node(temp.getData(), temp.getNext());

        // Sets temp's "next" pointer to 2 indices
        // above it's current position
        temp.setNext(temp.getNext().getNext());

        // returns the removed CampSite
        return removedCampSite.getData();
    }

    /******************************************************
     * This method returns a CampSite at a given index number
     *
     * @param index is the number of the Node in the list
     *
     * @return the CampSite at the given index
     */
    public CampSite get(int index) {
        // Case 0: There is no list
        if (top == null) {
            return null;
        }

        // Case 1: A list exists
        else {
            // Instantiates a counter variable
            int counter = 0;
            // Creates a temporary Node that starts at the
            // top of the list
            Node temp = new Node(top.getData(), top.getNext());

            // Iterates over the entire list until the program
            // finds which Node you want
            while (counter < index) {
                // Temp Node is equal to the next Node in
                // the list
                temp = temp.getNext();
                // Counter increases
                counter++;
            }

            // Returns the CampSite from the temp Node
            return temp.getData();
        }
    }

    /******************************************************
     * This method prints out a list of all of the Nodes
     */
    public void display() {
        // Creates a temp Node
        Node temp = top;
        // Iterates over the entire list
        while (temp != null) {
            // Prints the Node's data to the terminal
            System.out.println(temp.getData());
            // Temp is equal to the next Node in the list
            temp = temp.getNext();
        }
    }

    /******************************************************
     * This method returns a String of the default
     * constructor
     *
     * @return a String of the default constructor
     */
    @Override
    public String toString() {
        return "MySingleWithOutTailLinkedList{" +
                "top=" + top +
                ", size=" + size +
                '}';
    }
}