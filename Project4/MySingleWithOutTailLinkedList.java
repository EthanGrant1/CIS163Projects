package Project4;

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
