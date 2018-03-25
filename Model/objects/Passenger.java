package objects;

import exceptions.NegativeNumbers;

public class Passenger {
	
	private String bookingreference;
	private PassengerName name;
	private double weight;
	private double length;
	private double width;
	private double height;
	
	public Passenger(String bookingreference,PassengerName name,double weight, double length, double width, double height){
		
		setBookingreference(bookingreference);
		setName(name);
		setWeight(weight);
		setLength(length);
		setWidth(width);
		setHeight(height);
	}

	public String getBookingreference() {
		return bookingreference;
	}

	public void setBookingreference(String bookingreference) {
		this.bookingreference = bookingreference;
	}

	

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}
	
/*
	public String getLastname() {
		return lastname;
	}
*/
	public PassengerName getName() {
		return name;
	}

	public void setName(PassengerName name) {
		this.name = name;
	}
	
	public double getVolume() throws NumberFormatException {
		if (length == 0 || width == 0 || height == 0)
			throw new NumberFormatException();
		// volume converted to m^3
		double volume = (length*width*height)/1000000;
		return volume;
	}
	
	public double getExcessfees() throws NumberFormatException {
		if (weight == 0)
			throw new NumberFormatException();
		double fees = compExceessFees(weight);
		return fees;

	}
	
	public double compExceessFees(double weight) 
	{
		double fees=0;
		if(weight>20 && weight<=30) 
		{
			fees= (weight-20)*5;
		}
		else if(weight>30) 
		{
			fees=(10*5)+(weight-30)*10;
		}
		return fees;
	}
	
}
