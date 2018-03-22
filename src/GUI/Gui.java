package GUI;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.*;

import core.Manager;
import core.PassengerList;
import CheckinThread.*;

/**
 * Simple GUI for Auction application
 */
public class Gui extends JFrame implements Observer, ActionListener {

	private Manager p;

	private WaitingQueue wait;
	private int numCusts;
	private PassengerList custList = new PassengerList();
	// GUI components
	private JButton addButton = new JButton("Add");
	private JButton removeButton = new JButton("Remove");
	
	ArrayList<Thread> threads = new ArrayList<>();
	
	private JTextArea waitingQueue;

	Container contentPane = new Container();

	int x = 0;

	private JTextArea[] desks = new JTextArea[10];

	JPanel northPanel;

	/**
	 * Create the frame with its panels.
	 */
	public Gui(WaitingQueue wait, Manager p) {
		this.wait = wait;
		this.p = p;

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
		JPanel southPanel = new JPanel(new GridLayout(2, 1));

		waitingQueue = new JTextArea(15, 80);

		southPanel.add(waitingQueue);
		
		southPanel.add(addButton);
		addButton.addActionListener(this);
		
		southPanel.add(removeButton);
		removeButton.addActionListener(this);

		return southPanel;
	}

	public synchronized void createCheckInDesk(int x) {

		CheckInDesk s1 = new CheckInDesk(wait, p.getBookingMap(), p.getLuggageMap(), p.getFlightMap());
		Thread ci = new Thread(s1, Integer.toString(x));
		ci.start();
		threads.add(ci);
	}
	
	public synchronized void removeCheckInDesk() {
		
		for (Thread p : threads) {
			if (p.getName().equals(Integer.toString(x))) {
				p.interrupt();
				northPanel.remove(desks[x]);
				}
		}
		
		northPanel.revalidate();
		northPanel.repaint();
		x--;
	}

	private JPanel createCenterPanel() {
		northPanel = new JPanel();

		for (int i = 0; i <= x; i++) {
			desks[i] = new JTextArea(5, 20);
			desks[i].setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
			desks[i].setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.LIGHT_GRAY));
			northPanel.add(desks[i]);
			createCheckInDesk(i);
		}
		return northPanel;
	}

	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == addButton) {
			// Thread.this.
			x++;

			desks[x] = new JTextArea(5, 20);
			desks[x].setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
			desks[x].setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.LIGHT_GRAY));

			northPanel.add(desks[x]);
			northPanel.revalidate();
			northPanel.repaint();

			createCheckInDesk(x);
		}
		if (event.getSource() == removeButton) {
			northPanel.remove(desks[x]);
			northPanel.revalidate();
			northPanel.repaint();
			removeCheckInDesk();
			x--;		
		}
	}

	/*
	 * //////////////////////////////////////////////////// // MVC pattern - allows
	 * listeners to be added public void addProcessBidsListener(ActionListener al) {
	 * addButton.addActionListener(al); }
	 * 
	 * public void disableProcessButton() { addButton.setEnabled(false); }
	 * ////////////////////////////////////////////////////////
	 */

	// OBSERVER pattern - must provide update methods
	// synchronized blocks access to sync methods of the same object until finished
	// possibly investigate SwingWorker
	// for each customer, store bidlist into correct panel
	public synchronized void update(Observable o, Object args) {

		waitingQueue.setText(args.toString());
		String report = wait.checkInReport();

		for (int i = 0; i <= x; i++) {
			if (Thread.currentThread().getName().equals(Integer.toString(i))) { 
				 desks[i].setText(report);}
		}
	}
}
