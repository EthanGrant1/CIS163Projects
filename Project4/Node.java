package Project4;

import java.io.Serializable;

public class Node implements Serializable {
		private CampSite data;
		private Node next;

    public Node(CampSite data, Node next) {
        this.data = data;
        this.next = next;
    }

    public CampSite getData() {
        return data;
    }

    public void setData(CampSite data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return ":" + data.guestName + " " + next;
    }
}
