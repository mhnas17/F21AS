package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Exceptions.NegativeNumbers;
import core.BookingMap;
import core.FlightMap;
import core.LuggageMap;
import core.BookingLists;

public class NewGui extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ButtonGroup roleButtons = new ButtonGroup();
	JButton checkIn = new JButton("Check-in");
	JButton report = new JButton("Report");
	JButton close = new JButton("Close");
	JTextField lastName = new JTextField(30);
	JTextField bkngRef = new JTextField(30);
	JTextField height = new JTextField(5);
	JTextField width = new JTextField(5);
	JTextField length = new JTextField(5);
	JTextField weight = new JTextField(5);
	JCheckBox luggage = new JCheckBox("Luggage", false);

	private BookingMap book;
	private LuggageMap lugagges;
	private BookingLists lists;
	private FlightMap fmap;
	 
	public NewGui(BookingMap bmap, LuggageMap lmap, BookingLists blist, FlightMap fmap) {
		this.book = bmap;
		this.lugagges = lmap;
		this.lists = blist;
		this.fmap = fmap;
		setTitle("Check In");
		setupSouthPanel();
		setupNorthPanel();
		setupCenterPanel();
		pack();
		setResizable(false);
		setSize(600, 200);
		setVisible(true);
	}

	private void setupCenterPanel() {
		JPanel centerPanel = new JPanel();

		centerPanel.setLayout(new GridBagLayout());

		centerPanel.add(new JLabel("Weight "));
		centerPanel.add(weight);
		weight.setEditable(false);
		centerPanel.add(new JLabel("Height "));
		centerPanel.add(height);
		height.setEditable(false);
		centerPanel.add(new JLabel("Length "));
		centerPanel.add(length);
		length.setEditable(false);
		centerPanel.add(new JLabel("Width "));
		centerPanel.add(width);
		width.setEditable(false);

		this.add(centerPanel, BorderLayout.CENTER);
	}

	private void setupSouthPanel() {
		JPanel southPanel = new JPanel();

		southPanel.setLayout(new GridLayout(1, 1));
		report.addActionListener(this);
		southPanel.add(checkIn);
		checkIn.addActionListener(this);
		southPanel.add(report);
		southPanel.add(close);
		close.addActionListener(this);
		
		this.add(southPanel, BorderLayout.SOUTH);
	}

	private void setupNorthPanel() {
		JPanel northPanel = new JPanel();

		northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));

		northPanel.add(new JLabel("Last Name "));
		northPanel.add(lastName);
		northPanel.add(new JLabel("Booking Refernce Number "));
		northPanel.add(bkngRef);
		northPanel.add(luggage);
		luggage.addActionListener(this);

		this.add(northPanel, BorderLayout.NORTH);
	}

	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == checkIn) {

			if (luggage.isSelected() == true) {
				try {
					if (lists.searchNames(lastName.getText()) == true && lists.searchBookings(bkngRef.getText()) == true
							&& book.getValue(bkngRef.getText() + lastName.getText()).isCheckedin() == false) {

						lugagges.getValue(book.getValue(bkngRef.getText() + lastName.getText()).getFlightcode())
								.setAccum_volume(getVolume());
						lugagges.getValue(book.getValue(bkngRef.getText() + lastName.getText()).getFlightcode())
								.setAccum_weight(getWeight());
						lugagges.getValue(book.getValue(bkngRef.getText() + lastName.getText()).getFlightcode())
								.setAccum_excessfees(getExcessfees());
						lugagges.getValue(book.getValue(bkngRef.getText() + lastName.getText()).getFlightcode())
								.setAccum_numberofpassengers(1);
						book.getValue(bkngRef.getText() + lastName.getText()).setCheckedin(true);

						JOptionPane.showMessageDialog(this, "Check in complete! have a pleasant flight!");
					} else if (!lists.searchNames(lastName.getText())) {
						JOptionPane.showMessageDialog(this, "Last name doesn't exist!");
					} else if (!lists.searchBookings(bkngRef.getText())) {
						JOptionPane.showMessageDialog(this, "Booking reference doesn't exist!");
					} else if (book.getValue(bkngRef.getText() + lastName.getText()).isCheckedin() == true) {
						JOptionPane.showMessageDialog(this, "You are already checked in!");
					}
				} 
				catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(this, "Please enter your bag dimension and weight");
				}
				catch (NegativeNumbers e) {
					JOptionPane.showMessageDialog(this, "You have entered negative numbers,please try again");
				}
				catch (NullPointerException e) {
					JOptionPane.showMessageDialog(this, "Incorrect name booking reference combination!");
				}

			} else {
				try {
					if (lists.searchNames(lastName.getText()) == true && lists.searchBookings(bkngRef.getText()) == true
							&& book.getValue(bkngRef.getText() + lastName.getText()).isCheckedin() == false) {

						lugagges.getValue(book.getValue(bkngRef.getText() + lastName.getText()).getFlightcode())
								.setAccum_numberofpassengers(1);
						book.getValue(bkngRef.getText() + lastName.getText()).setCheckedin(true);
						JOptionPane.showMessageDialog(this, "Check in complete! have a pleasant flight!");

					} else if (!lists.searchNames(lastName.getText())) {
						JOptionPane.showMessageDialog(this, "Last name doesn't exist!");
					} else if (!lists.searchBookings(bkngRef.getText())) {
						JOptionPane.showMessageDialog(this, "Booking reference doesn't exist!");
					} else if (book.getValue(bkngRef.getText() + lastName.getText()).isCheckedin() == true) {
						JOptionPane.showMessageDialog(this, "You are already checked in!");
					}
				} catch (NullPointerException e) {
					JOptionPane.showMessageDialog(this, "Incorrect name booking reference combination!");
				}

			}

		}
		if (event.getSource() == report) {
			// lugagges.getReport(fmap);
			lugagges.writeToFile("Report.txt", lugagges.getReport(fmap));

		}
		
		if (event.getSource() == close) {
			String ObjButtons[] = {"Yes","Cancel"};
	        int PromptResult = JOptionPane.showOptionDialog(null,"Are you sure you want to exit?","Exit Kiosk",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
	        if(PromptResult==JOptionPane.YES_OPTION)
	        {
	        	lugagges.writeToFile("Report.txt", lugagges.getReport(fmap));
	        	 System.exit(1);
		    }

		}
		
		
		if (event.getSource() == luggage) {
			if (weight.isEditable() == false) {
				weight.setEditable(true);
				length.setEditable(true);
				width.setEditable(true);
				height.setEditable(true);
			} else {
				weight.setEditable(false);
				length.setEditable(false);
				width.setEditable(false);
				height.setEditable(false);
			}

		}

	}

	public double getVolume() throws NumberFormatException {
		if(length.getText()==null||width.getText()==null||height.getText()==null ) throw new NumberFormatException();
		double volume = Double.parseDouble(length.getText()) * Double.parseDouble(width.getText())
				* Double.parseDouble(height.getText());
		return volume;
	}

	public double getWeight() throws NumberFormatException {
		if(weight.getText()==null ) throw new NumberFormatException();
		double w = Double.parseDouble(weight.getText());
		return w;
	}

	public double getExcessfees() throws NumberFormatException {
		if(weight.getText()==null ) throw new NumberFormatException();
		double w = Double.parseDouble(weight.getText());
		if (w > 20) {
			return (w - 20) * 15;
		} else
			return 0;

	}
}