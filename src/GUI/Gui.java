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
    private WaitingQueue wait;
    private int numCusts;
    private PassengerList custList = new PassengerList();
    //GUI components
    JButton processButton;
    private JTextArea waitingQueue;
    private JTextArea desk1;
    private JTextArea desk2;
    

    
    /**
     * Create the frame with its panels.
     */
    public Gui(WaitingQueue wait)
    {
        this.wait=wait;
        wait.addObserver(this);
        
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
    	JPanel custPanel = new JPanel(new GridLayout (1,3));
		//customers  = new JTextArea [numCusts];
		waitingQueue  = new JTextArea(15,80);
		desk1 = new JTextArea(15,15);
		desk2 =new JTextArea(15,15);
		//customers [0]= 
		/*for (int i = 0; i < numCusts; i++) {
			customers [i]= new JTextArea(15,80);
			//monospaced allows nice tabular layout
			customers[i].setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
			customers [i].setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.LIGHT_GRAY));
			custPanel.add(customers[i]);
		}*/
		custPanel.add(waitingQueue);
		custPanel.add(desk1);
		custPanel.add(desk2);
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
    	
		waitingQueue.setText(args.toString());
		String report = wait.checkInReport();
		if (Thread.currentThread().getName().equals("1")) {
			desk1.setText(report);
		}
		else if (Thread.currentThread().getName().equals("2")){
			desk2.setText(report);
		}
		
		//desk2.setText(t);	
    }

}
