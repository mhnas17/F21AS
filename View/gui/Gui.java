package gui;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout.SequentialGroup;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import data_structure_lists.PassengerList;
import objects.Manager;
import queue.WaitingQueue;
import report_logs.CheckedInReport;
import report_logs.Log;
import report_logs.QueueReport;
import threads.*;

/**
 * Simple GUI for Auction application
 */
public class Gui extends JFrame implements Observer {

	private Manager p;

	private WaitingQueue wait;
	QueueReport q;
	CheckedInReport r;
	private String [] temp = new String [4];
	
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
	public Gui(WaitingQueue wait, Manager p,QueueReport q,CheckedInReport r) {
		this.wait = wait;
		this.p = p;
		this.q=q;
		this.r=r;

		wait.addObserver(this);

		// custList = queue.getListOfCustomers();
		numCusts = custList.getSize();

		// set up window title
		setTitle("HWU Airport");
		// ensure program ends when window closes
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			/* When user closes the window he gets a warning window. If user clicks yes
			 * the program clears the current accommodation list, reads the updated one,
			 * writes it on a .txt file and terminates.
        	 * 
        	 */
		    @Override
		    public void windowClosing(WindowEvent we)
		    { 
		        String ObjButtons[] = {"Yes","No"};
		        int PromptResult = JOptionPane.showOptionDialog(null,"Are you sure you want to exit?\n A log file will be written of the events after exit.","Check-in",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
		        if(PromptResult==JOptionPane.YES_OPTION)
		        	
		        {
		        	Log.log(p.getFinalReport(r,q,p.getLuggageMap(),p.getFlightMap(),wait));
		        	System.exit(0);
		        	
		        }
		        
		    }
		});
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
		//addButton.addActionListener(this);

		southPanel.add(removeButton);
		//removeButton.addActionListener(this);

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
	
	public void addButton() {
		x++;

		desks[x] = new JTextArea(5, 20);
		desks[x].setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		desks[x].setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.LIGHT_GRAY));

		centerPanel.add(desks[x]);
		centerPanel.revalidate();
		centerPanel.repaint();

		createCheckInDesk(x);
		
	}
	
	public void removeButton() {
		removeCheckInDesk();

		centerPanel.remove(desks[x]);
		centerPanel.revalidate();
		centerPanel.repaint();

		x--;
	}

	
	  
	 public void addAddButtonListener(ActionListener al) {
		 addButton.addActionListener(al);
		 
	}
	 public void addRemoveButtonListener(ActionListener al) {
		 removeButton.addActionListener(al);
	}
	 
	 public void enableAddButton() {
		 addButton.setEnabled(true);
	 }
	 
	 public void disableAddButton() { 
		 addButton.setEnabled(false); 
	 }
		 
	public void disableRemoveButton() { 
		 removeButton.setEnabled(false); 
	 }
	 
	public void enableRemoveButton() {
		removeButton.setEnabled(true);
	}
	
	public int getNumberofDesks() {
		return x;
	}
	 
	 

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
		if(p.getFlightMap().getFlight("A1320").getTimerFinish()) {
		flights[0].setText(flightReport1+"\n DEPARTED");
		flights[0].setForeground(Color.red);
		
		}
		else {flights[0].setText(flightReport1);}
		if(p.getFlightMap().getFlight("B2430").getTimerFinish()) {
		flights[1].setText(flightReport2+"\n DEPARTED");
		flights[1].setForeground(Color.red);
		}
		else {flights[1].setText(flightReport2);}
		if(p.getFlightMap().getFlight("C3340").getTimerFinish()) {
		flights[2].setText(flightReport3+"\n DEPARTED");
		flights[2].setForeground(Color.red);
		}
		else {
		flights[2].setText(flightReport3);
		}
		for (int i = 0; i <= x; i++) {
			if (Thread.currentThread().getName().equals(Integer.toString(i))&&report!=temp[0]&&report!=temp[1]&&report!=temp[2]&&report!=temp[3]) {
				int deskno = Integer.parseInt(Thread.currentThread().getName())+ 1;
				desks[i].setText("Desk " + deskno + ": \n" + report);
				temp[i] =report;
			}
		}
		if (wait.getQueueSize()==0) {
			for (int i = 0; i <= x; i++) {
			
					desks[i].setText("Available for check-in");
				}
		}
		if (p.getFlightMap().getFlight("C3340").getTimerFinish()&&p.getFlightMap().getFlight("B2430").getTimerFinish()&&p.getFlightMap().getFlight("A1320").getTimerFinish()) {
			for (int i = 0; i <= x; i++) {
			
					desks[i].setText("All flights departed.\n Check-in closed");
					desks[i].setForeground(Color.red);
				}
		}
	}
}
