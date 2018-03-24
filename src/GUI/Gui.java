package GUI;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout.SequentialGroup;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

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
	private JButton addButton = new JButton("Open Check-in Desk");
	private JButton removeButton = new JButton("Close Check-in Desk");

	ArrayList<Thread> threads = new ArrayList<>();
	Map<String, Thread> m = new ConcurrentHashMap<String, Thread>();

	private JTextArea waitingQueue;

	Container contentPane = new Container();

	int x = 0;

	private JTextArea[] desks = new JTextArea[4];
	private JTextArea[] flights = new JTextArea[3];

	JPanel centerPanel;

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
		setTitle("El Venizelos");
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

		for (int i = 0; i < 3; i++) {
			flights[i] = new JTextArea(5, 20);
			flights[i].setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
			flights[i].setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.LIGHT_GRAY));
			northPanel.add(flights[i]);
		}

		return northPanel;
	}

	private JPanel createSouthPanel() {
		JPanel southPanel = new JPanel();

		waitingQueue = new JTextArea(15, 80);

		southPanel.add(waitingQueue);

		southPanel.add(addButton);
		addButton.addActionListener(this);

		southPanel.add(removeButton);
		removeButton.addActionListener(this);

		return southPanel;
	}

	public synchronized void createCheckInDesk(int x) {

		CheckInDesk s1 = new CheckInDesk(wait, p.getBookingMap(), p.getLuggageMap(), p.getFlightMap(), m);
		Thread ci = new Thread(s1, Integer.toString(x));
		ci.start();
		m.put(Integer.toString(x), ci);
	}

	public synchronized void removeCheckInDesk() {

		for (Thread p : m.values()) {
			if (p.getName().equals(Integer.toString(x))) {
				p.interrupt();
				m.remove(Integer.toString(x));
			}
		}
	}

	private JPanel createCenterPanel() {
		centerPanel = new JPanel();

		for (int i = 0; i <= x; i++) {
			desks[i] = new JTextArea(5, 20);
			desks[i].setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
			desks[i].setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.LIGHT_GRAY));
			centerPanel.add(desks[i]);
			createCheckInDesk(i);
		}
		return centerPanel;
	}

	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == addButton) {
			// Thread.this.
			x++;

			desks[x] = new JTextArea(5, 20);
			desks[x].setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
			desks[x].setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.LIGHT_GRAY));

			centerPanel.add(desks[x]);
			centerPanel.revalidate();
			centerPanel.repaint();

			createCheckInDesk(x);
		}
		if (event.getSource() == removeButton) {
			removeCheckInDesk();

			centerPanel.remove(desks[x]);
			centerPanel.revalidate();
			centerPanel.repaint();

			x--;
		}

		if (x == 3) {
			addButton.setEnabled(false);
		} else if (x == -1) {
			removeButton.setEnabled(false);
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
		String flightReport1 = p.getLuggageMap().FlightStatus(p.getFlightMap().getFlight("A1320"));
		String flightReport2 = p.getLuggageMap().FlightStatus(p.getFlightMap().getFlight("B2430"));
		String flightReport3 = p.getLuggageMap().FlightStatus(p.getFlightMap().getFlight("C3340"));
		flights[0].setText(flightReport1);
		flights[1].setText(flightReport2);
		flights[2].setText(flightReport3);
		for (int i = 0; i <= x; i++) {
			if (Thread.currentThread().getName().equals(Integer.toString(i))) {
				int deskno = Integer.parseInt(Thread.currentThread().getName()) + 1;
				desks[i].setText("Desk " + deskno + ": \n" + report);
			}
		}
	}
}
