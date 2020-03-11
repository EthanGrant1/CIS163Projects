package Project3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ReservationRVDialog extends JDialog implements ActionListener {
    private JTextField txtGuestName;
    private JTextField txtDateCheckin;
    private JTextField txtDateCheckout;
    private JTextField txtPowerSupplied;
    private JButton okButton;
    private JButton cancelButton;
    private int closeStatus, parsedPower;
    private RV rv;
    public static final int OK = 0;
    public static final int CANCEL = 1;

    /*********************************************************
     Instantiate a Custom Dialog as 'modal' and wait for the
     user to provide data and click on a button.

     @param parent reference to the JFrame application
     @param rv an instantiated object to be filled with data
     *********************************************************/

    public ReservationRVDialog(JFrame parent, RV rv) {
        // call parent and create a 'modal' dialog
        super(parent, true);
        this.rv = rv;

        setTitle("RV Reservation Dialog Box");
        closeStatus = CANCEL;
        setSize(500,200);

        // prevent user from closing window
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        // instantiate and display two text fields
        txtGuestName = new JTextField("Ethan",30);
        txtDateCheckin = new JTextField(25);
        txtDateCheckout = new JTextField(25);
        txtPowerSupplied = new JTextField("1500", 15);

        Calendar currentDate = Calendar.getInstance();
        SimpleDateFormat formatter= new SimpleDateFormat("MM/dd/yyyy"); //format it as per your requirement
        String dateNow = formatter.format(currentDate.getTime());
        currentDate.add(Calendar.DATE, 1);
        String datetomorrow = formatter.format(currentDate.getTime());

        txtDateCheckin.setText(dateNow);
        txtDateCheckout.setText(datetomorrow);

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new GridLayout(4,2));

        textPanel.add(new JLabel("Name of RVer: "));
        textPanel.add(txtGuestName);
        textPanel.add(new JLabel("Date on Check in: "));
        textPanel.add(txtDateCheckin);
        textPanel.add(new JLabel("Date on Check out (est.): "));
        textPanel.add(txtDateCheckout);
        textPanel.add(new JLabel("Power to be Supplied"));
        textPanel.add(txtPowerSupplied);

        getContentPane().add(textPanel, BorderLayout.CENTER);

        // Instantiate and display two buttons
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        okButton.addActionListener(this);
        cancelButton.addActionListener(this);

        setVisible (true);
    }

    /**************************************************************
     Respond to either button clicks
     @param e the action event that was just fired
     **************************************************************/
    public void actionPerformed(ActionEvent e) {

        JButton button = (JButton) e.getSource();

        // if OK clicked the fill the object
        if (button == okButton) {
            try {
                // save the information in the object
                closeStatus = OK;
                SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                df.setLenient(false);

                Date d1 = null;
                Date d2 = null;

                GregorianCalendar gregTemp = new GregorianCalendar();

                try {
                    d1 = df.parse(txtDateCheckin.getText());
                }

                catch (ParseException e1) {
                    JOptionPane.showMessageDialog(null, "The program " +
                            "has encountered an error. \n ParseException - 'Incorrect Date Format' or " +
                            "'Non-Integer'. \n Please try again.");

                    throw new IllegalArgumentException();
                }

                gregTemp = new GregorianCalendar();

                try {
                    d2 = df.parse(txtDateCheckout.getText());
                }

                catch (ParseException e1) {
                    JOptionPane.showMessageDialog(null, "The program " +
                            "has encountered an error. \n ParseException - 'Incorrect Date Format' or " +
                            "'Non-Integer'. \n Please try again.");

                    throw new IllegalArgumentException();
                }

                if (d2.before(d1)) {
                    JOptionPane.showMessageDialog(null, "The program " +
                            "has encountered an error. \n IllegalArgumentException - 'Check Out " +
                            "Before Check In Date'. \n Please try again.");

                    throw new IllegalArgumentException();
                }

                else {
                    gregTemp.setTime(d1);
                    rv.setCheckIn(gregTemp);

                    gregTemp.setTime(d2);
                    rv.setEstimatedCheckOut(gregTemp);
                }

                if (txtGuestName.getText().equals("") || txtGuestName.getText() == null) {
                    JOptionPane.showMessageDialog(null, "The program " +
                            "has encountered an error. \n IllegalArgumentException - 'Blank Field' " +
                            "or 'Null Field'. \n Please try again.");

                    throw new IllegalArgumentException();
                }

                else {
                    rv.setGuestName(txtGuestName.getText());
                }

                try {
                    parsedPower = Integer.parseInt(txtPowerSupplied.getText());
                }

                catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(null, "The program " +
                            "has encountered an error. \n NumberFormatException - 'Non-Integer' " +
                            "or 'Blank Field' or 'Null Field'. \n Please try again.");

                    throw new IllegalArgumentException();
                }

                if (parsedPower > 0) {
                    rv.setPower(parsedPower);
                }

                else {
                    JOptionPane.showMessageDialog(null, "The program " +
                            "has encountered an error. \n IllegalArgumentException - 'Non-Integer' " +
                            "or 'Negative Integer' or 'Blank Field' or 'Null Field' " +
                            "\n Please try again.");

                    throw new IllegalArgumentException();
                }

            }

            catch (Throwable f) {
                JOptionPane.showMessageDialog(null, "Action could not " +
                        "not be completed because error(s) occurred. \n Please try again.");
            }
        }

        // make the dialog disappear
        dispose();
    }

    /**************************************************************
     Return a String to let the caller know which button
     was clicked

     @return an int representing the option OK or CANCEL
     **************************************************************/
    public int getCloseStatus(){
        return closeStatus;
    }
}