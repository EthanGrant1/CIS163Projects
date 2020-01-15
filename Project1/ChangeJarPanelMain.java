package Project1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ChangeJarPanelMain extends JPanel {

    private JMenuItem quitItem;
    private JMenuItem suspendItem;

    public ChangeJarPanelMain (JMenuItem quitItem, JMenuItem suspendItem) {
        JPanel panel = new JPanel();
        panel.add(new ChangeJarPanel());
        panel.add(new ChangeJarPanel());
        panel.add(new ChangeJarPanel());
        add(panel);

        this.quitItem = quitItem;
        this.suspendItem = suspendItem;

        quitItem.addActionListener(new Mylistener());
        suspendItem.addActionListener(new Mylistener());
    }

    private class Mylistener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            if(e.getSource() == quitItem){
                System.exit(1);
            }
            if(e.getSource() == suspendItem){
                ChangeJar.mutate(suspendItem.isSelected());
            }
        }

    }
}

