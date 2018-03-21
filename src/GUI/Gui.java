package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import CheckinThread.WaitingQueue;

import java.util.*;

import core.PassengerList;
import CheckinThread.*;

/**
 * Simple GUI for Auction application
 */
public class Gui extends JFrame implements Observer {
	private WaitingQueue wait;
	private int numCusts;
	private PassengerList custList = new PassengerList();
	// GUI components
	JButton processButton;
	private JTextArea waitingQueue;
	private JTextArea [] desks;

	/**
	 * Create the frame with its panels.
	 */
	public Gui(WaitingQueue wait) {
		this.wait = wait;
		wait.addObserver(this);

		// custList = queue.getListOfCustomers();
		numCusts = custList.getSize();

		// set up window title
		setTitle("Auction");
		// ensure program ends when window closes
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(100, 600);
		setLocation(10, 20);

		// add button panel and result field to the content pane
		Container contentPane = getContentPane();
		contentPane.add(createSouthPanel(), BorderLayout.SOUTH);
		contentPane.add(createCenterPanel(), BorderLayout.CENTER);
		contentPane.add(createNorthPanel(), BorderLayout.NORTH);
		// pack and set visible
		pack();
		setVisible(true);
	}

	private JPanel createNorthPanel() {
		JPanel northPanel = new JPanel(new GridLayout(1, 3));

		return northPanel;
	}

	private JPanel createSouthPanel() {
		JPanel southPanel = new JPanel(new GridLayout(1, 3));

		waitingQueue = new JTextArea(15, 80);

		southPanel.add(waitingQueue);

		return southPanel;
	}

	private JPanel createCenterPanel() {
		JPanel northPanel = new JPanel();
		
		desks = new JTextArea [2];

		for (int i = 0; i < 2; i++) {
			desks[i] = new JTextArea(15, 80);
			desks[i].setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
			desks[i].setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.LIGHT_GRAY));
			northPanel.add(desks[i]);
		}
		return northPanel;
	}

	/////////////////////////////////////////////////////
	// MVC pattern - allows listeners to be added
	public void addProcessBidsListener(ActionListener al) {
		processButton.addActionListener(al);
	}

	public void disableProcessButton() {
		processButton.setEnabled(false);
	}
	/////////////////////////////////////////////////////////

	// OBSERVER pattern - must provide update methods
	// synchronized blocks access to sync methods of the same object until finished
	// possibly investigate SwingWorker
	// for each customer, store bidlist into correct panel
	public synchronized void update(Observable o, Object args) {

		waitingQueue.setText(args.toString());
		String report = wait.checkInReport();
		if (Thread.currentThread().getName().equals("1")) {
			desks[0].setText(report);
		} else if (Thread.currentThread().getName().equals("2")) {
			desks[1].setText(report);
		}

		// desk2.setText(t);
	}

}
