package Project2;

import javax.swing.*;

public class Surround4 {
	/**
	 * @param args
	 */
	public static void main (String[] args)
	{
		/*  The JMenuBar is associated with the frame. The first step
		 *  is to create the menu items, and file menu.
		 */
		JMenuBar menus;
		JMenu fileMenu;
		JButton undoButton;
		JMenuItem quitItem, newGameItem;
		JFrame frame = new JFrame ("Surround Game");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

		fileMenu = new JMenu("File");
		newGameItem = new JMenuItem("New Game");
		quitItem = new JMenuItem("Quit");
		undoButton = new JButton("Undo");

		fileMenu.add(newGameItem);
		fileMenu.add(quitItem);


		menus = new JMenuBar();
		menus.add(fileMenu);
		menus.add(undoButton);

		frame.setJMenuBar(menus);

		Surround4Panel panel = new
				Surround4Panel(quitItem, newGameItem, undoButton);
		frame.add(panel);
		frame.setSize(600, 600);
		frame.setVisible(true);
	}
}