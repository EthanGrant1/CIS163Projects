package Project1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

/**
 * This is the class that creates Panels for
 * the ChangeJar GUI.
 *
 * @author Ethan Grant
 */
public class ChangeJarPanel extends JPanel{

    private ChangeJar jar;

    NumberFormat fmt = NumberFormat.getCurrencyInstance();

    JButton takeOutButton, decButton, incButton, addButton,
            saveButton, loadButton, takeOutDoubleButton,
            equalsButton, compareToButton, toStringButton,
            takeOutChangeJarButton;

    JTextField qField, dField, nField, pField, fileField,
    doubleField;

    JLabel qLabel, dLabel, nLabel, pLabel;

    /** labels for message and credits */
    JLabel message, credits;

    public ChangeJarPanel(){

        // create the game object as well as the ChangeJarGUI Frame
        jar = new ChangeJar(100,2,3,4);

        // set the layout to GridBag
        setLayout(new GridLayout(14,2));
        setBackground(Color.lightGray);

        // get Die #2 from game and place on ChangeJarGUI

        fileField = new JTextField("Enter a file name here",3);
        add(fileField);
        add(new JLabel("File Name"));



        qField = new JTextField("0", 3);
        add(qField);
        qLabel = new JLabel(jar.getQuarters() + " " + "Quarters");
        add(qLabel);

        dField = new JTextField("0", 3);
        add(dField);
        dLabel = new JLabel(jar.getDimes() + " " + "Dimes");
        add(dLabel);

        nField = new JTextField("0", 3);
        add(nField);
        nLabel = new JLabel(jar.getNickels() + " " + "Nickels");
        add(nLabel);

        pField = new JTextField("0", 3);
        add(pField);
        pLabel = new JLabel(jar.getPennies() + " " + "Pennies");
        add(pLabel);

        takeOutButton = new JButton("Take Out");
        add(takeOutButton);

        decButton = new JButton("Decrease by 1 penny");
        add(decButton);

        incButton = new JButton("Increase by 1 penny");
        add(incButton);

        addButton = new JButton("Add Money");
        add(addButton);

        saveButton = new JButton("Save");
        add(saveButton);

        loadButton = new JButton("Load");
        add(loadButton);

        equalsButton = new JButton("Equals Static Jar");
        add(equalsButton);

        compareToButton = new JButton("Compare to Static Jar");
        add(compareToButton);

        doubleField = new JTextField("0", 3);
        add(doubleField);

        takeOutDoubleButton = new JButton("Take Out Double");
        add(takeOutDoubleButton);

        toStringButton = new JButton("To String");
        add(toStringButton);

        takeOutChangeJarButton = new JButton("Take Out ChangeJar");
        add(takeOutChangeJarButton);

        credits = new JLabel();
        credits.setText(fmt.format(jar.getAmount()));
        add(new JLabel("Total:"));
        add(credits);

        // register the listeners
        takeOutButton.addActionListener(new ButtonListener());
        decButton.addActionListener(new ButtonListener());
        incButton.addActionListener(new ButtonListener());
        addButton.addActionListener(new ButtonListener());
        saveButton.addActionListener(new ButtonListener());
        loadButton.addActionListener(new ButtonListener());
        takeOutDoubleButton.addActionListener(new ButtonListener());
        equalsButton.addActionListener(new ButtonListener());
        compareToButton.addActionListener(new ButtonListener());
        toStringButton.addActionListener(new ButtonListener());
        takeOutChangeJarButton.addActionListener(new ButtonListener());
    }


    /****************************************************************
     Inner class to respond to the user action

     ****************************************************************/
    private class ButtonListener implements ActionListener{

        public void actionPerformed(ActionEvent event){

            int quarters, dimes, nickels, pennies;

            if (event.getSource() == takeOutButton){
                try{
                    quarters = Integer.parseInt(qField.getText());
                    dimes = Integer.parseInt(dField.getText());
                    nickels = Integer.parseInt(nField.getText());
                    pennies = Integer.parseInt(pField.getText());
                    jar.takeOut(quarters,dimes,nickels,pennies);
                }
                catch(NumberFormatException io){
                    JOptionPane.showMessageDialog(null,"Enter an integer in all fields");
                }
                catch(IllegalArgumentException e){
                    JOptionPane.showMessageDialog(null,"Not enough specified coins for this operation");
                }
            }

            if (event.getSource() == decButton) {
                try {
                    jar.dec();
                    credits.setText(fmt.format(jar.getAmount()));
                    pLabel.setText(jar.getPennies() + " " + "Pennies");
                }

                catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, "ChangeJar has no more pennies");
                }
            }

            if (event.getSource() == incButton) {
                jar.inc();
                credits.setText(fmt.format(jar.getAmount()));
                pLabel.setText(jar.getPennies() + " " + "Pennies");
            }

            if (event.getSource() == addButton) {
                try {
                    quarters = Integer.parseInt(qField.getText());
                    dimes = Integer.parseInt(dField.getText());
                    nickels = Integer.parseInt(nField.getText());
                    pennies = Integer.parseInt(pField.getText());
                    jar.add(quarters,dimes,nickels,pennies);
                }

                catch(NumberFormatException io){
                    JOptionPane.showMessageDialog(null,"Enter an integer in all fields");
                }
                catch(IllegalArgumentException e){
                    JOptionPane.showMessageDialog(null,"Not enough specified coins for this operation");
                }
            }

            if (event.getSource() == saveButton) {
                jar.save(fileField.getText());
            }

            if (event.getSource() == loadButton) {
                try {
                    jar.load(fileField.getText());
                }

                catch (IllegalArgumentException o) {
                    JOptionPane.showMessageDialog(null,"Cannot find this file");
                }
            }

            if (event.getSource() == takeOutDoubleButton) {
                try {
                    double amount = Double.parseDouble(doubleField.getText());
                    jar.takeOut(amount);
                }
                catch(NumberFormatException io){
                    JOptionPane.showMessageDialog(null,"Enter double in the field");
                }
                catch(IllegalArgumentException e){
                    JOptionPane.showMessageDialog(null,"Not enough specified coins for this operation");
                }

            }

            if (event.getSource() == equalsButton) {
                    jar.equals(ChangeJarPanelMain.staticJar);

                    if (jar.equals(ChangeJarPanelMain.staticJar)) {
                        JOptionPane.showMessageDialog(null, "Jar is equal to Static Jar");
                    }

                    if (!jar.equals(ChangeJarPanelMain.staticJar)) {
                        JOptionPane.showMessageDialog(null, "Jar is not equal to Static Jar");
                    }
            }

            if (event.getSource() == compareToButton) {
                jar.compareTo(ChangeJarPanelMain.staticJar);

                if (jar.compareTo(ChangeJarPanelMain.staticJar) == 1) {
                    JOptionPane.showMessageDialog(null, "Jar is greater than Static Jar");
                }

                if (jar.compareTo(ChangeJarPanelMain.staticJar) == -1) {
                    JOptionPane.showMessageDialog(null, "Jar is less than Static Jar");
                }

                if (jar.compareTo(ChangeJarPanelMain.staticJar) == 0) {
                    JOptionPane.showMessageDialog(null, "Jar is equal to Static Jar");
                }

                if (jar.compareTo(ChangeJarPanelMain.staticJar) == 2) {
                    JOptionPane.showMessageDialog(null, "Error: Null object");
                }
            }

            if (event.getSource() == toStringButton) {
                JOptionPane.showMessageDialog(null, jar.toString());
            }

            if (event.getSource() == takeOutChangeJarButton) {
                try {
                    jar.takeOut(ChangeJarPanelMain.staticJar);
                }

                catch(IllegalArgumentException e){
                    JOptionPane.showMessageDialog(null,"Not enough specified coins for this operation");
                }
            }
            credits.setText(fmt.format(jar.getAmount()));
            qLabel.setText(jar.getQuarters() + " " + "Quarters");
            dLabel.setText(jar.getDimes() + " " + "Dimes");
            nLabel.setText(jar.getNickels() + " " + "Nickels");
            pLabel.setText(jar.getPennies() + " " + "Pennies");
        }
    }
}
