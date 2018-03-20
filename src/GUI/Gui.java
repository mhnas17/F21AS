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
public class Gui  extends JFrame  implements Observer
{
    private CheckInDesk queue;
    private int numCusts;
    private PassengerList custList = new PassengerList();
    //GUI components
    JButton processButton;
    private JTextArea customers ;

    
    /**
     * Create the frame with its panels.
     */
    public Gui(CheckInDesk queue)
    {
        this.queue = queue;
        queue.addObserver(this);
        //custList = queue.getListOfCustomers();
        numCusts = custList.getSize();

        
        //set up window title
        setTitle("Auction");
        //ensure program ends when window closes
		setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(100,600);
        setLocation(10,20);
 
        
        //add button panel and result field to the content pane
        Container contentPane = getContentPane();
        contentPane.add(createNorthPanel(), BorderLayout.NORTH);
        contentPane.add(createCustPanel(), BorderLayout.CENTER);
        //pack and set visible
        pack();
        setVisible(true);
    }
    
   
    private JPanel createCustPanel() {
    	//cheating - know there are 6 customers
    	JPanel custPanel = new JPanel(new GridLayout (3,2));
		//customers  = new JTextArea [numCusts];
		customers  = new JTextArea(15,80);
		//customers [0]= 
		/*for (int i = 0; i < numCusts; i++) {
			customers [i]= new JTextArea(15,80);
			//monospaced allows nice tabular layout
			customers[i].setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
			customers [i].setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.LIGHT_GRAY));
			custPanel.add(customers[i]);
		}*/
		custPanel.add(customers);
		return custPanel;
    }

    private JPanel createNorthPanel() {
        //north panel shows the button to start processing
        processButton = new JButton("Open auction");
        JPanel northPanel = new JPanel();
        northPanel.add(processButton);
        return northPanel;
    }
    
    /////////////////////////////////////////////////////
    //MVC pattern - allows listeners to be added
    public void addProcessBidsListener(ActionListener al) {
        processButton.addActionListener(al);
    }
    
    public void disableProcessButton() {
    	processButton.setEnabled(false);
    }
    /////////////////////////////////////////////////////////

    //OBSERVER pattern - must provide update methods
    //synchronized blocks access to sync methods of the same object until finished
    //possibly investigate SwingWorker
    //for each customer, store bidlist into correct panel
    public synchronized void update(Observable o, Object args) {
    	String report = queue.queueReport();
		customers.setText(report);
			
    }

}
