package objects;

public class Passenger {
	
	private String bookingreference;
	private PassengerName name;
	private double weight;
	private double length;
	private double width;
	private double height;
	
	/**
	 * @param bookingreference booking reference
	 * @param name passenger name
	 * @param weight weight of passenger's bag
	 * @param length length of passenger's bag
	 * @param width width of passenger's bag
	 * @param height heigh of passenger's bag
	 */
	public Passenger(String bookingreference, PassengerName name, double weight, double length, double width,
			double height) {

		setBookingreference(bookingreference);
		setName(name);
		setWeight(weight);
		setLength(length);
		setWidth(width);
		setHeight(height);
	}

	/**
	 * @return booking refrence
	 */
	public String getBookingreference() {
		return bookingreference;
	}

	/**
	 * sets booking reference to the value provided in the parameter
	 * @param bookingreference 
	 */
	public void setBookingreference(String bookingreference) {
		this.bookingreference = bookingreference;
	}

	/**
	 * @return bag weight
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * sets bag weight to the value provided in the parameter
	 * @param weight
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

	/**
	 * return length of passenger's bag
	 * @return
	 */
	public double getLength() {
		return length;
	}

	/**
	 * set length of passenger's bag to the value provided in the parameter
	 * @param length
	 */
	public void setLength(double length) {
		this.length = length;
	}

	/**
	 * @return width of passenger's bag
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * set width of passenger's bag
	 * @param width
	 */
	public void setWidth(double width) {
		this.width = width;
	}

	/**
	 * @return height of passenger's bag
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * set height of passenger's bag to the value provided in the parameter
	 * @param height
	 */
	public void setHeight(double height) {
		this.height = height;
	}

	
	/**
	 * @return name of the passenger
	 */
	public PassengerName getName() {
		return name;
	}

	/**
	 * set name of the passenger to the value provided in the parameter
	 * @param name
	 */
	public void setName(PassengerName name) {
		this.name = name;
	}

	/**
	 * @return the volume of passenger's bag
	 * @throws NumberFormatException
	 */
	public double getVolume() throws NumberFormatException {
		if (length == 0 || width == 0 || height == 0)
			throw new NumberFormatException();
		// volume converted to m^3
		double volume = (length * width * height) / 1000000;
		return volume;
	}

	/**
	 * @return the excess fees for the passenger
	 * @throws NumberFormatException
	 */
	public double getExcessfees() throws NumberFormatException {
		if (weight == 0)
			throw new NumberFormatException();
		double fees = compExceessFees(weight);
		return fees;

	}

	/**
	 * calculate the excess fees for the passenger
	 * @param weight of the bag
	 * @return excess fees 
	 */
	public double compExceessFees(double weight) {
		double fees = 0;
		if (weight > 20 && weight <= 30) {
			fees = (weight - 20) * 5;
		} else if (weight > 30) {
			fees = (10 * 5) + (weight - 30) * 10;
		}
		return fees;
	}

}
