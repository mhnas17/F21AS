import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class NewGui extends JFrame implements ActionListener {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ButtonGroup roleButtons = new ButtonGroup();
	JButton checkIn = new JButton("Check-in");
	JButton report = new JButton("Report");
	JTextField lastName = new JTextField(30);
	JTextField bkngRef = new JTextField(30);
	JTextField height = new JTextField(5);
	JTextField width = new JTextField(5);
	JTextField length = new JTextField(5);
	JTextField weight = new JTextField(5);
	JCheckBox luggage = new JCheckBox("Luggage");
	
	public NewGui() {
		setTitle("Check In");
		setupSouthPanel();
		setupNorthPanel();
		setupCenterPanel();
		pack();
		setVisible(true);
	}
	
	private void setupCenterPanel() {
		JPanel centerPanel = new JPanel();

		centerPanel.setLayout(new GridBagLayout());
				
		centerPanel.add(new JLabel("Weight "));
		centerPanel.add(weight);
		centerPanel.add(new JLabel("Height "));
		centerPanel.add(height);
		centerPanel.add(new JLabel("Length "));
		centerPanel.add(length);
		centerPanel.add(new JLabel("Width "));
		centerPanel.add(width);
		
		this.add(centerPanel, BorderLayout.CENTER);
	}

	private void setupSouthPanel() {
		JPanel southPanel = new JPanel();
		
		southPanel.setLayout(new GridLayout(1, 1));
		
		southPanel.add(checkIn);
		checkIn.addActionListener(this);
		southPanel.add(report);
		
		this.add(southPanel, BorderLayout.SOUTH);
	}

	private void setupNorthPanel() {
		JPanel northPanel = new JPanel();
		
		northPanel.setLayout(new BoxLayout(northPanel,BoxLayout.Y_AXIS));
		
		northPanel.add(new JLabel("Last Name "));
		northPanel.add(lastName);
		northPanel.add(new JLabel("Booking Refernce Number "));
		northPanel.add(bkngRef);
		northPanel.add(luggage);

		this.add(northPanel, BorderLayout.NORTH);
	}

	public void actionPerformed(ActionEvent event ) {	
		if (event.getSource() == checkIn) {
			JOptionPane.showMessageDialog(this, "Check in complete! have a pleasant flight!");
    	
		}
	}	
}