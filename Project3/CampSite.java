package Project3;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public abstract class
CampSite implements Serializable {

    /** This verifies during deserialization that the
       sender and receiver of an object are compatible */
    private static final long serialVersionUID = 1L;

    /** The name of the person that is checking in */
    protected String guestName;

    /** The date of when the person is checking in */
    protected GregorianCalendar checkIn;

    /** The date of when the person should check out,
     *  based on estimates in checkIn time.
     */
    protected GregorianCalendar estimatedCheckOut;

    /** The date when the person actually checks out */
    protected GregorianCalendar actualCheckOut;

    public CampSite() {
    }

    public abstract double getCost(GregorianCalendar checkOut);

    public CampSite(String guestName,
                    GregorianCalendar checkIn,
                    GregorianCalendar estimatedCheckOut,
                    GregorianCalendar actualCheckOut) {
        this.guestName = guestName.trim();
        this.checkIn = checkIn;
        this.estimatedCheckOut = estimatedCheckOut;
        this.actualCheckOut = actualCheckOut;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName.trim();
    }

    public GregorianCalendar getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(GregorianCalendar checkIn) {
        this.checkIn = checkIn;
    }

    public GregorianCalendar getEstimatedCheckOut() {
        return estimatedCheckOut;
    }

    public void setEstimatedCheckOut(GregorianCalendar estimatedCheckOut) {
        this.estimatedCheckOut = estimatedCheckOut;
    }

    public GregorianCalendar getActualCheckOut() {
        return actualCheckOut;
    }

    public void setActualCheckOut(GregorianCalendar actualCheckOut) {
        this.actualCheckOut = actualCheckOut;
    }

    // following code used for debugging only
    // IntelliJ using the toString for displaying in debugger.
    @Override
    public String toString() {
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

        String checkInOnDateStr;
        if (getCheckIn() == null)
            checkInOnDateStr = "";
        else
            checkInOnDateStr = formatter.format(getCheckIn().getTime());

        String  estCheckOutStr;
        if (getEstimatedCheckOut() == null)
            estCheckOutStr = "";
        else
            estCheckOutStr = formatter.format(getEstimatedCheckOut().getTime());

        String checkOutStr;
        if (getActualCheckOut() == null)
            checkOutStr = "";
        else
            checkOutStr = formatter.format(getActualCheckOut().getTime());

        return "CampSite{" +
                "guestName='" + guestName + '\'' +
                ", checkIn=" + checkInOnDateStr +
                ", estimatedCheckOut=" + estCheckOutStr +
                ", actualCheckOut=" + checkOutStr +
                '}';
    }

    public boolean isRV() {
        return this instanceof RV;
    }

    public boolean isTent() {
        return this instanceof TentOnly;
    }

    public String CompareTentThenRV() {

        if (this instanceof TentOnly) {
            return "a";
        }

        if (this instanceof RV) {
            return "b";
        }

        return "c";
    }

}
