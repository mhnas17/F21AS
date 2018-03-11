package core;

import name_plane.Name;

public class Passenger {
	
	private String bookingreference;
	private Name passengername;
	private double weight;
	private double length;
	private double width;
	private double height;
	
	public Passenger(String bookingreference,Name passengername,double weight, double length, double width, double height){
		
		setBookingreference(bookingreference);
		setPassengername(passengername);
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

	public Name getPassengername() {
		return passengername;
	}

	public void setPassengername(Name passengername) {
		this.passengername = passengername;
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
	
}
