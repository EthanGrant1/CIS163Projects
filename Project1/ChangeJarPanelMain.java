package Project1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;


public class ChangeJarPanelMain extends JPanel {

    private JMenuItem quitItem;
    private JMenuItem suspendItem;
    private JButton mutationButton;

    JLabel amount;
    NumberFormat fmt = NumberFormat.getCurrencyInstance();

    public static ChangeJar staticJar = new ChangeJar(100,2,3,4);

    public ChangeJarPanelMain (JMenuItem quitItem, JMenuItem suspendItem) {
        JPanel panel = new JPanel();
        panel.add(new ChangeJarPanel());
        panel.add(new ChangeJarPanel());
        panel.add(new ChangeJarPanel());
        add(panel);

        mutationButton = new JButton("Mutation Methods: On");
        add(mutationButton);

        amount = new JLabel("Static Jar: " + fmt.format(staticJar.getAmount()));
        add(amount);

        this.quitItem = quitItem;
        this.suspendItem = suspendItem;

        quitItem.addActionListener(new Mylistener());
        suspendItem.addActionListener(new Mylistener());
        mutationButton.addActionListener(new Mylistener());
    }

    private class Mylistener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            if(e.getSource() == quitItem){
                System.exit(1);
            }
            if(e.getSource() == suspendItem){
                ChangeJar.mutate(suspendItem.isSelected());
            }

            if (e.getSource() == mutationButton) {
                ChangeJar.mutate(!ChangeJar.getMutate());

                if(ChangeJar.getMutate()) {
                    mutationButton.setText("Mutation Methods: On");
                }

                else {
                    mutationButton.setText("Mutation Methods: Off");
                }
            }
        }
    }
}

