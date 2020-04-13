package Project4;

import jdk.dynalink.NamedOperation;

import java.io.Serializable;

/******************************************************
 * This class creates a LinkedList of CampSite objects
 * and will be used in the already existing CampSite
 * database code.
 */
public class MySingleWithOutTailLinkedList<E> implements Serializable {
    /** The first Node in the list */
    private Node<E> top;
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
        Node<E> temp = top;

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

        // The temp pointer used to add campsites to the list
        Node<E> temp = top;
        // Whether or not a campsite has been added or not
        boolean added = false;


        // Case 0: there is no list, just add it
        if (temp == null) {
            top = new Node<E>((E)s, null);
        }

        // Adding a tent using a helper method
        else if (s instanceof TentOnly) {
            addTent(s, temp, added);
        }

        // Adding an RV using a helper method
        else if (s instanceof RV) {
            addRV(s, temp, added);
        }
    }

    /******************************************************
     * This method adds a campsite node to the list of
     * type RV
     *
     * @param s is the campsite being added
     * @param added is a boolean to check if the campsite
     *              has been added or not
     */
    private void addRV(CampSite s, Node<E> temp, boolean added) {

        // Case 1: There is no list
        if (top == null) {
            top = new Node<E>((E) s, top);
        }

        // If the top of the list is an RV
        else if (top.getData() instanceof RV) {
            // Case 2: If s's checkOut is before top's, make it the new top
            if (s.getEstimatedCheckOut().before(((RV)top.getData()).getEstimatedCheckOut())) {
                top = new Node<E>((E) s, top);
            }

            // Case 3: If s's checkOut is the same as top's compare the names
            else if(s.getEstimatedCheckOut().equals(((RV)top.getData()).getEstimatedCheckOut())){
                // If s's name is less than top, make it the new top
                if(s.getGuestName().compareTo(((RV)top.getData()).getGuestName()) < 0) {
                    top = new Node<E>((E) s, top);
                }
                // Else, use the temp pointer
                else {
                    temp = top;
                    // Loop until s's name is no longer greater than temp's name
                    while (temp.getNext() != null &&
                            s.getEstimatedCheckOut().equals(((RV)temp.getNext().getData()).getEstimatedCheckOut()) &&
                            s.getGuestName().compareTo(((RV)temp.getNext().getData()).getGuestName()) > 0) {
                        temp = temp.getNext();
                    }
                    // Create a new Node for s
                    temp.setNext(new Node<E>((E) s, temp.getNext()));
                }
            }

            // Case 4: If s's checkOut is after top's and there is no Node after top,
            // put it next to top
            else if (s.getEstimatedCheckOut().after(((RV)top.getData()).getEstimatedCheckOut())
                    && top.getNext() == null) {
                top.setNext(new Node<E>((E) s, top.getNext()));
            }

            // Case 5: If there are more RVs after the first one, s needs to go further
            // into the list.
            else if (s.getEstimatedCheckOut().after(((RV)top.getData()).getEstimatedCheckOut())
                    && top.getNext().getData() instanceof RV){

                // Loops through all of the RVs to find the proper position for s
                while (temp.getNext() != null && (temp.getNext().getData() instanceof RV)
                        && added == false) {

                    // Case 5a: s has a checkOut date between 2 other RV's checkOut dates
                    if (s.getEstimatedCheckOut().after(((RV)temp.getData()).getEstimatedCheckOut())
                            && s.getEstimatedCheckOut().before(((RV)temp.getNext().getData()).getEstimatedCheckOut())) {
                        // Create s's Node
                        temp.setNext(new Node<E>((E) s, temp.getNext()));
                        // A campsite has been added, therefore the loop ends
                        added = true;
                    }

                    // Case 5b: s has a checkOut somewhere in the middle and shares
                    // it with another RV
                    else if (s.getEstimatedCheckOut().equals(((RV)temp.getNext().getData()).getEstimatedCheckOut())) {
                        // If s's name is less than temp's
                        if (s.getGuestName().compareTo(((RV)temp.getNext().getData()).getGuestName()) < 0) {
                            // Create s's Node
                            temp.setNext(new Node<E>((E) s, temp.getNext()));
                            // A campsite has been added, therefore the loop ends
                            added = true;
                        }
                        // Else, if s's name is greater than or equal to temp's
                        else {
                            // Loop until s's name is no longer greater than temp's
                            while (temp.getNext() != null &&
                                    s.getEstimatedCheckOut().equals(((RV)temp.getNext().getData()).getEstimatedCheckOut()) &&
                                    s.getGuestName().compareTo(((RV)temp.getNext().getData()).getGuestName()) > 0) {
                                temp = temp.getNext();
                            }
                            // Create s's Node
                            temp.setNext(new Node<E>((E) s, temp.getNext()));
                            // A campsite has been added, therefore the loop ends
                            added = true;
                        }
                    }
                    // Advances the temp pointer forward one position
                    temp = temp.getNext();
                }

                // Case 5c: If the RV has not been added yet, just add it in
                if (added == false) {
                    temp.setNext(new Node<E>((E) s, temp.getNext()));
                }
            }
        }

        // Case 6: If there are tents in the list
        else if (top.getData() instanceof TentOnly) {

            // Initializes the temp variables to be at the top of the list
            temp = top;

            // Loop to get past the tents first
            while (temp.getNext() != null && temp.getNext().getData() instanceof TentOnly) {
                temp = temp.getNext();
            }

            // Case 6a: If there are no RVs after the tents in the list,
            // just add it after the last tent
            if (temp.getNext() == null) {
                temp.setNext(new Node<E>((E) s, temp.getNext()));
            }

            // Beyond this point assumes that there are RVs in the list

            // 6b, 6c, and 6d are cases to check the first RV in the list, effectively
            // the 'top' of the RV list

            // Case 6b: If s's checkOut is before temp's, put it before temp
            else if (s.getEstimatedCheckOut().before(((RV)temp.getNext().getData()).getEstimatedCheckOut())) {
                temp.setNext(new Node<E>((E) s, temp.getNext()));
            }

            // Case 6c: If s's checkOut is the same as temp's compare the names
            else if (s.getEstimatedCheckOut().equals(((RV)temp.getNext().getData()).getEstimatedCheckOut())) {
                // If s's name is greater than temp's
                if (s.getGuestName().compareTo(((RV)temp.getNext().getData()).getGuestName()) > 0) {
                    // Loop until s's name is no longer greater than temp's
                    while (temp.getNext() != null &&
                            s.getEstimatedCheckOut().equals(((RV)temp.getNext().getData()).getEstimatedCheckOut()) &&
                            s.getGuestName().compareTo(((RV)temp.getNext().getData()).getGuestName()) > 0) {
                        temp = temp.getNext();
                    }
                }
                // Create s's Node
                temp.setNext(new Node<E>((E) s, temp.getNext()));
            }

            // Case 6d: If s's checkOut is after temp's
            else if (s.getEstimatedCheckOut().after(((RV)temp.getNext().getData()).getEstimatedCheckOut())
                    && temp.getNext().getNext() == null) {
                temp.getNext().setNext(new Node<E>((E) s, temp.getNext().getNext()));
            }


            // Case 6e: If s has an estimated checkout after the first RV
            else if (s.getEstimatedCheckOut().after(((RV)temp.getNext().getData()).getEstimatedCheckOut())
                    && temp.getNext().getNext().getData() instanceof RV) {

                // Move temp to the next RV in the list
                temp = temp.getNext();

                // Loop through the whole list of RV's to find the proper position for s
                while (temp.getNext() != null && (temp.getNext().getData() instanceof RV)
                        && added == false) {

                    // Case 5f: s has a checkOut date between 2 other RV's checkOut dates
                    if (s.getEstimatedCheckOut().after(((RV)temp.getData()).getEstimatedCheckOut())
                            && s.getEstimatedCheckOut().before(((RV)temp.getNext().getData()).getEstimatedCheckOut())) {
                        // Create s's Node
                        temp.setNext(new Node<E>((E) s, temp.getNext()));
                        // A campsite has been added, therefore the loop ends
                        added = true;
                    }

                    // Case 6g: s has a checkOut somewhere in the middle and shares
                    // it with another RV
                    else if (s.getEstimatedCheckOut().equals(((RV)temp.getNext().getData()).getEstimatedCheckOut())) {
                        // If s's guest name is before temp's
                        if (s.getGuestName().compareTo(((RV)temp.getNext().getData()).getGuestName()) < 0) {
                            // Create s's Node
                            temp.setNext(new Node<E>((E) s, temp.getNext()));
                            // A campsite has been added, therefore the loop ends
                            added = true;
                        }
                        // If s's guest name is greater than or equal to temp's
                        else {
                            // Loop until s's guest name is no longer greater than temp's
                            while (temp.getNext() != null &&
                                    s.getEstimatedCheckOut().equals(((RV)temp.getNext().getData()).getEstimatedCheckOut()) &&
                                    s.getGuestName().compareTo(((RV)temp.getNext().getData()).getGuestName()) > 0) {
                                temp = temp.getNext();
                            }
                            // Create s's Node
                            temp.setNext(new Node<E>((E) s, temp.getNext()));
                            // A campsite has been added, therefore the loop ends
                            added = true;
                        }
                    }
                    // Move temp one position further in the list
                    temp = temp.getNext();
                }

                // Case 6h: If the RV has not been added yet just add it in
                if (added == false) {
                    temp.setNext(new Node<E>((E) s, temp.getNext()));
                }
            }
        }
    }

    private void addTent(CampSite s, Node<E> temp, boolean added) {
        // Case 1: Just add to top if top is currently an RV
        if (top.getData() instanceof RV) {
            top = new Node<E>((E) s, top);
        }

        // Case 2: If s's checkOut is before top's, make it the new top
        else if (s.getEstimatedCheckOut().before(((TentOnly)top.getData()).getEstimatedCheckOut())) {
            top = new Node<E>((E) s, top);
        }

        // Case 3: If s's checkOut is the same as top's compare the names
        else if (s.getEstimatedCheckOut().equals(((TentOnly)top.getData()).getEstimatedCheckOut())) {
            // If s's name is before top's, make it the new top
            if (s.getGuestName().compareTo(((TentOnly)top.getData()).getGuestName()) < 0) {
                top = new Node<E>((E) s, top);
            }
            // Else, if s's name is greater than or equal to top's
            else {
                // temp is set equal to the top of the list
                temp = top;
                // Loop until s's name is no longer greater than temp's
                while (temp.getNext() != null && !(temp.getNext().getData() instanceof RV) &&
                        s.getEstimatedCheckOut().equals(((TentOnly)temp.getNext().getData()).getEstimatedCheckOut())
                        && s.getGuestName().compareTo(((TentOnly)temp.getNext().getData()).getGuestName()) > 0) {
                    temp = temp.getNext();
                }
                // Create s's Node
                temp.setNext(new Node<E>((E) s, temp.getNext()));
            }
        }

        // Case 4: If s's checkOut is after top's and there is nothing after
        // top or an RV after, make s top's next Node
        else if(s.getEstimatedCheckOut().after(((TentOnly)top.getData()).getEstimatedCheckOut())
                && top.getNext() == null || s.getEstimatedCheckOut().after(((TentOnly)top.getData()).getEstimatedCheckOut())
                && top.getNext().getData() instanceof RV){
            top.setNext(new Node<E>((E) s, top.getNext()));
        }

        // Case 5: If s's checkOut is after top's but there are more Tents
        else if (s.getEstimatedCheckOut().after(((TentOnly)top.getData()).getEstimatedCheckOut())
                && top.getNext().getData() instanceof TentOnly) {

            // Loop through all of the tents to find the proper position for s
            while (temp.getNext() != null && !(temp.getNext().getData() instanceof RV)
                    && added == false) {

                // Case 5a: s has a checkOut date between 2 other tent's checkOut dates
                if (s.getEstimatedCheckOut().after(((TentOnly)temp.getData()).getEstimatedCheckOut())
                        && s.getEstimatedCheckOut().before(((TentOnly)temp.getNext().getData()).getEstimatedCheckOut())) {
                    // Create s's Node
                    temp.setNext(new Node<E>((E) s, temp.getNext()));
                    // A campsite has been added, therefore the loop ends
                    added = true;
                }

                // Case 5b: s has a checkOut somewhere in the middle and shares
                // it with another tent
                else if (s.getEstimatedCheckOut().equals(((TentOnly)temp.getNext().getData()).getEstimatedCheckOut())) {
                    // If s's name is less than temp's
                    if (s.getGuestName().compareTo(((TentOnly)temp.getNext().getData()).getGuestName()) < 0) {
                        // Create s's Node
                        temp.setNext(new Node<E>((E) s, temp.getNext()));
                        // A campsite has been added, therefore the loop ends
                        added = true;
                    }
                    // If s's name is greater than or equal to temp's
                    else {
                        // Loop until s's name is no longer greater than temp's
                        while (temp.getNext() != null && !(temp.getNext().getData() instanceof RV)
                                && s.getEstimatedCheckOut().equals(((TentOnly)temp.getNext().getData()).getEstimatedCheckOut()) &&
                                s.getGuestName().compareTo(((TentOnly)temp.getNext().getData()).getGuestName()) > 0) {
                            temp = temp.getNext();
                        }
                        // Create s's Node
                        temp.setNext(new Node<E>((E) s, temp.getNext()));
                        // A campsite has been added, therefore the loop ends
                        added = true;
                    }
                }
                // Move the temp pointer one position further in the list
                temp = temp.getNext();
            }

            // Case 5c: If the tent has not been added yet, just add it in
            if(added == false) {
                temp.setNext(new Node<E>((E) s, temp.getNext()));
            }
        }
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
            CampSite removed = ((CampSite)top.getData());
            top = top.getNext();
            return removed;
        }

        // Case 4: Multiple items in the list
        Node<E> temp = top;
        // Loops to find the Node before the one
        // you want to delete
        for (int i = 0; i < (index - 1); i++) {
            temp = temp.getNext();
        }
        // The CampSite that is to be removed
        Node<E> removedCampSite = new Node<E>(temp.getData(), temp.getNext());

        // Sets temp's "next" pointer to 2 indices
        // above it's current position
        temp.setNext(temp.getNext().getNext());

        // returns the removed CampSite
        return ((CampSite)removedCampSite.getData());
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
            Node<E> temp = new Node<E>(top.getData(), top.getNext());

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
            return ((CampSite)temp.getData());
        }
    }

    /******************************************************
     * This method prints out a list of all of the Nodes
     */
    public void display() {
        // Creates a temp Node
        Node<E> temp = top;
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