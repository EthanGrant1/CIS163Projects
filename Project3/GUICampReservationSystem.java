package Project3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;

/*****************************************************************
 *
 *  Campers Reservation System
 *
 *****************************************************************/
public class GUICampReservationSystem extends JFrame implements ActionListener{
    /** Holds menu bar */
    private JMenuBar menus;

    /** menus in the menu bar */
    private JMenu fileMenu;
    private JMenu actionMenu;

    /** menu items in each of the menus */
    private JMenuItem openSerItem;
    private JMenuItem exitItem;
    private JMenuItem saveSerItem;
    private JMenuItem openTextItem;
    private JMenuItem saveTextItem;
    private JMenuItem reserveRVItem;
    private JMenuItem reserveTentOnlyItem;
    private JMenuItem checkOutItem;

    private JMenuItem currentParkItemScn;
    private JMenuItem checkOUtItemScn;
    private JMenuItem overDueItemScn;
    private JMenuItem sortTentRvItemScn;
    private JMenuItem sortRvTentItemScn;

    private JPanel panel;

    /** Holds the list engine */
    private ListModel DList;

    /** Holds jTable */
    private JTable jTable;

    /** Scroll pane */
    private JScrollPane scrollList;

    public String OverDueDate;
    public GregorianCalendar OverDue;
    public SimpleDateFormat df;
    public Date formattedDate;

    /*****************************************************************
     *
     * A constructor that starts a new GUI1024 for the rental store
     *
     *****************************************************************/
    public GUICampReservationSystem(){
        //adding menu bar and menu items
        menus = new JMenuBar();
        fileMenu = new JMenu("File");
        actionMenu = new JMenu("Action");
        openSerItem = new JMenuItem("Open File");
        exitItem = new JMenuItem("Exit");
        saveSerItem = new JMenuItem("Save File");
        openTextItem = new JMenuItem("Open Text");
        saveTextItem = new JMenuItem("Save Text");
        reserveRVItem = new JMenuItem("Reserve a RV Site");
        reserveTentOnlyItem = new JMenuItem("Reserve a TentOnly Site");
        checkOutItem = new JMenuItem("CheckOut of TentOnly or RV");

        currentParkItemScn = new JMenuItem("Current Park Screen");
        checkOUtItemScn = new JMenuItem("Check Out Screen");
        overDueItemScn = new JMenuItem("OverDue Screen");
        sortRvTentItemScn = new JMenuItem("Sort RV, Tent Screen");
        sortTentRvItemScn = new JMenuItem("Sort Tent, RV Screen");

        //adding items to bar
        fileMenu.add(openSerItem);
        fileMenu.add(saveSerItem);
        fileMenu.addSeparator();
        fileMenu.add(openTextItem);
        fileMenu.add(saveTextItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        fileMenu.addSeparator();
        fileMenu.add(currentParkItemScn);
        fileMenu.add(checkOUtItemScn);
        fileMenu.add(overDueItemScn);
        fileMenu.add (sortRvTentItemScn);
        fileMenu.add(sortTentRvItemScn);

        actionMenu.add(reserveRVItem);
        actionMenu.add(reserveTentOnlyItem);
        actionMenu.addSeparator();
        actionMenu.add(checkOutItem);

        menus.add(fileMenu);
        menus.add(actionMenu);

        //adding actionListener
        openSerItem.addActionListener(this);
        saveSerItem.addActionListener(this);
        openTextItem.addActionListener(this);
        saveTextItem.addActionListener(this);
        exitItem.addActionListener(this);
        reserveRVItem.addActionListener(this);
        reserveTentOnlyItem.addActionListener(this);
        checkOutItem.addActionListener(this);

        currentParkItemScn.addActionListener(this);
        checkOUtItemScn.addActionListener(this);
        overDueItemScn.addActionListener(this);
        sortRvTentItemScn.addActionListener(this);
        sortTentRvItemScn.addActionListener(this);

        setJMenuBar(menus);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        DList = new ListModel();
        jTable = new JTable(DList);
        scrollList = new JScrollPane(jTable);
        panel.add(scrollList);
        add(panel);
        scrollList.setPreferredSize(new Dimension(800,300));

        setVisible(true);
        setSize(950,450);
    }

    public void actionPerformed(ActionEvent e) {
        Object comp = e.getSource();

        if (currentParkItemScn == comp) {
            DList.setDisplay(ScreenDisplay.CurrentParkStatus);
        }

        if (checkOUtItemScn == comp) {
            DList.setDisplay(ScreenDisplay.CheckOutGuest);
        }

        if (overDueItemScn == comp) {
            df = new SimpleDateFormat("MM/dd/yyyy");
            df.setLenient(false);

            OverDueDate =
                    JOptionPane.showInputDialog(null,
                            "\t\t\t" + "Enter Date \n \n" +
                                    "\t\t\t" + "Warning \n" +
                                    "Must have EXACT syntax with slashes " +
                                    "and correct format (ex: M/d/yyyy or MM/dd/yyyy)");

            try {
                if (OverDueDate == null) {
                    JOptionPane.showMessageDialog(null, "Cancelled the OverDue Date"
                            + " entry. Closing this window.");
                    DList.setDisplay(ScreenDisplay.CurrentParkStatus);
                }

                else {
                    formattedDate = df.parse(OverDueDate);
                    OverDue = new GregorianCalendar();

                    OverDue.setTime(formattedDate);
                    DList.setEnteredOverDueDate(OverDue);

                    DList.setDisplay(ScreenDisplay.OverDueScreen);
                }
            }

            catch (NumberFormatException | ParseException f) {
                JOptionPane.showMessageDialog(null, "Error in parsing date. " +
                        "Please try again.");

                DList.setDisplay((ScreenDisplay.CurrentParkStatus));
            }
        }

        if (sortRvTentItemScn == comp) {
            DList.setDisplay(ScreenDisplay.SortByRVTent);
        }

        if (sortTentRvItemScn == comp) {
            DList.setDisplay(ScreenDisplay.SortByTentRV);
        }

        if (openSerItem == comp || openTextItem == comp) {
            JFileChooser chooser = new JFileChooser();
            int status = chooser.showOpenDialog(null);
            if (status == JFileChooser.APPROVE_OPTION) {
                String filename = chooser.getSelectedFile().getAbsolutePath();
                if (openSerItem == comp)
                    DList.loadDatabase(filename);
                else if(openTextItem == comp)
                    DList.loadText(filename);
            }
        }

        if (saveSerItem == comp || saveTextItem == comp) {
            JFileChooser chooser = new JFileChooser();
            int status = chooser.showSaveDialog(null);
            if (status == JFileChooser.APPROVE_OPTION) {
                String filename = chooser.getSelectedFile().getAbsolutePath();
                if (saveSerItem == comp)
                    DList.saveDatabase(filename);
                else if(saveTextItem == comp)
                    DList.saveText(filename);
            }
        }

        //MenuBar options
        if(exitItem == comp){
            System.exit(1);
        }
        if(reserveRVItem == comp){
            RV RV = new RV();
            ReservationRVDialog dialog = new ReservationRVDialog(this, RV);
            if(dialog.getCloseStatus() == ReservationRVDialog.OK){
                DList.add(RV);
            }
        }
        if(reserveTentOnlyItem == comp){
            TentOnly tentOnly = new TentOnly();
            ReservationTentOnlyDialog dialog = new ReservationTentOnlyDialog(this, tentOnly);
            if(dialog.getCloseStatus() == ReservationTentOnlyDialog.OK){
                DList.add(tentOnly);
            }
        }

        if (checkOutItem == comp) {
            int index = jTable.getSelectedRow();
            if (index != -1) {
                GregorianCalendar dat = new GregorianCalendar();

                CampSite unit = DList.get(index);

                CheckOutOnDialog dialog = new CheckOutOnDialog(this, unit);

                try {
                    JOptionPane.showMessageDialog(null,
                            "  Be sure to thank " + unit.getGuestName() +
                                    "\n for camping with us and the price is:  " +
                                    unit.getCost(unit.getActualCheckOut()) +
                                    " dollars");
                    DList.upDate(index, unit);
                }catch(Exception e1){
                }
            }
        }
    }

    public static void main(String[] args) {
        new GUICampReservationSystem();
    }
}