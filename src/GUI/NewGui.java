package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Exceptions.NegativeNumbers;
import core.BookingMap;
import core.FlightMap;
import core.LuggageMap;
import core.BookingLists;
import core.Luggage;

public class NewGui extends JFrame implements ActionListener {

	/**
	 * Creating the Gui and its components
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
	private Luggage lg;

	public NewGui(BookingMap bmap, LuggageMap lmap, BookingLists blist, FlightMap fmap, Luggage lg) {
		this.book = bmap;
		this.lugagges = lmap;
		this.lists = blist;
		this.fmap = fmap;
		this.lg = lg;
		setTitle("Check In");
		setupSouthPanel();
		setupNorthPanel();
		setupCenterPanel();
		pack();
		setResizable(false);
		setSize(600, 200);
		setVisible(true);
		setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
	}

	/**
	 * The center panel of the gui
	 */
	private void setupCenterPanel() {
		JPanel centerPanel = new JPanel();

		centerPanel.setLayout(new GridBagLayout());

		centerPanel.add(new JLabel("Weight (KG) "));
		centerPanel.add(weight);
		weight.setEditable(false);
		centerPanel.add(new JLabel("Height (cm) "));
		centerPanel.add(height);
		height.setEditable(false);
		centerPanel.add(new JLabel("Length (cm)"));
		centerPanel.add(length);
		length.setEditable(false);
		centerPanel.add(new JLabel("Width (cm) "));
		centerPanel.add(width);
		width.setEditable(false);

		this.add(centerPanel, BorderLayout.CENTER);
	}

	/**
	 * The bottom panel of the Gui
	 * 
	 */
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

	/**
	 * The top panel of the gui
	 * 
	 */
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
		/*if (event.getSource() == checkIn) {
			// if someone checks in with luggage
			if (luggage.isSelected() == true) {
				try {
					// checks if inputed last name and booking reference exist and if passenger has already checked in
					if (lists.searchNames(lastName.getText()) == true && lists.searchBookings(bkngRef.getText()) == true
							&& book.getValue(bkngRef.getText() + lastName.getText()).isCheckedin() == false) {
						// accumulates volume of luggage for the flight
						lugagges.getValue(book.getValue(bkngRef.getText() + lastName.getText()).getFlightcode())
								.setAccum_volume(getVolume());
						// accumulates weight of luggage for the flight
						lugagges.getValue(book.getValue(bkngRef.getText() + lastName.getText()).getFlightcode())
								.setAccum_weight(getWeight());
						// accumulates excess fees of flight
						lugagges.getValue(book.getValue(bkngRef.getText() + lastName.getText()).getFlightcode())
								.setAccum_excessfees(getExcessfees());
						// accumulates passengers of flight
						lugagges.getValue(book.getValue(bkngRef.getText() + lastName.getText()).getFlightcode())
								.setAccum_numberofpassengers(1);
						// sets passenger as checked in
						book.getValue(bkngRef.getText() + lastName.getText()).setCheckedin(true);
						JOptionPane.showMessageDialog(this,
								"luggage Excess fees: £" + String.format("%.2f", lg.compExceessFees(getWeight())));
						JOptionPane.showMessageDialog(this, "Check in complete! have a pleasant flight!");
						// checks if everyone has checked in and if that is the case ends the program and generates report
						if (book.getCheckInReport(book) == true) {
							JOptionPane.showMessageDialog(this, "Everyone is checked in!");
							lugagges.writeToFile("Report.txt", lugagges.getReport(fmap));
							System.exit(1);

						}
					} else if (!lists.searchNames(lastName.getText())) {
						JOptionPane.showMessageDialog(this, "Last name doesn't exist!");
					} else if (!lists.searchBookings(bkngRef.getText())) {
						JOptionPane.showMessageDialog(this, "Booking reference doesn't exist!");
					} else if (book.getValue(bkngRef.getText() + lastName.getText()).isCheckedin() == true) {
						JOptionPane.showMessageDialog(this, "You are already checked in!");
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(this, "Please enter your bag dimension and weight");
				} catch (NegativeNumbers e) {
					JOptionPane.showMessageDialog(this, "You have entered negative numbers,please try again");
				} catch (NullPointerException e) {
					JOptionPane.showMessageDialog(this, "Incorrect name booking reference combination!");
				}

			} else { 		// case that passenger has no luggage
				try {
					// checks if inputed last name and booking reference exist and if passenger has already checked in
					if (lists.searchNames(lastName.getText()) == true && lists.searchBookings(bkngRef.getText()) == true
							&& book.getValue(bkngRef.getText() + lastName.getText()).isCheckedin() == false) {
						// accumulates passengers of flight
						lugagges.getValue(book.getValue(bkngRef.getText() + lastName.getText()).getFlightcode())
								.setAccum_numberofpassengers(1);
						// sets passenger as checked in
						book.getValue(bkngRef.getText() + lastName.getText()).setCheckedin(true);
						JOptionPane.showMessageDialog(this, "Check in complete! have a pleasant flight!");
						if (book.getCheckInReport(book) == true) {
							JOptionPane.showMessageDialog(this, "Everyone is checked in!");
							lugagges.writeToFile("Report.txt", lugagges.getReport(fmap));
							System.exit(1);

						}

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
			lugagges.writeToFile("Report.txt", lugagges.getReport(fmap));
		}

		if (event.getSource() == close) {
			String ObjButtons[] = { "Yes", "Cancel" };
			int PromptResult = JOptionPane.showOptionDialog(null, "Are you sure you want to exit?", "Exit Kiosk",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, ObjButtons, ObjButtons[1]);
			if (PromptResult == JOptionPane.YES_OPTION) {
				// Writes the report on a file before closing in case the employee forgot to do
				// so
				lugagges.writeToFile("Report.txt", lugagges.getReport(fmap));
				System.exit(1);
			}

		}
		// sets fields of luggage details editable or not according to luggage checkbox
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
		}*/
	}	
}