package core;

import Exceptions.NegativeNumbers;
import name_plane.Name;

public class Passenger {
	
	private String bookingreference;
	private Name name;
	private double weight;
	private double length;
	private double width;
	private double height;
	
	public Passenger(String bookingreference,Name name,double weight, double length, double width, double height){
		
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
	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}
	
	
}
