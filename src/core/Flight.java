package core;
import Exceptions.InvalidFlightCode;
import name_plane.Plane;
import java.util.Observable;


public class Flight extends Observable{

	private String flightcode;
	private String flightdestination;
	private String carrier;
	private Plane plane;
	private boolean timer = false;
		
	
	/**
	 * constructor of an object of type Flight
	 * @param flightcode the flight code for each flight
	 * @param flightdestination the destination of the flight
	 * @param carrier the name of the company that operates the flight
	 * @param plane the plane type 
	 * @throws InvalidFlightCode 
	 */
	public Flight(String flightcode, String flightdestination, String carrier, Plane plane) throws InvalidFlightCode {

		setFlightcode(flightcode);
		setFlightdestination(flightdestination);
		setCarrier(carrier);
		setPlane(plane);
	}

	/**
	 * @return the flight code of the flight
	 */
	public String getFlightcode() {
		return flightcode;
	}

	/**
	 * @param flightcode
	 * @throws InvalidFlightCode 
	 */
	public void setFlightcode(String flightcode) throws InvalidFlightCode {
		if(!validflightcode(flightcode)) throw new InvalidFlightCode(flightcode);
			
		this.flightcode = flightcode;
	}

	/**
	 * @return the destination of the flight
	 */
	public String getFlightdestination() {
		return flightdestination;
	}

	/**
	 * sets the flight destination to the value provided in the parameter
	 * @param flightdestination
	 */
	public void setFlightdestination(String flightdestination)  {
		this.flightdestination = flightdestination;
	}

	/**
	 * @return the name of the flight carrier
	 */
	public String getCarrier() {
		return carrier;
	}

	/**
	 * sets the name of the flight carrier to the name provided in the parameter
	 * @param carrier
	 */
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	/**
	 * @return the object of type Plane
	 */
	public Plane getPlane() {
		return plane;
	}

	/**
	 * sets the Plane to the object provided in the parameter
	 * @param plane
	 * @throws IndalidPlaneType 
	 */
	public void setPlane(Plane plane) {
		
		this.plane = plane;
	}

	/**
	 * return true if the flight code provided in the parameter is in a valid format.
	 * return false if the flight code provided in the parameter is in a wrong format.
	 * @param b
	 * @return
	 */
	private boolean validflightcode(String b) {
		int size = b.trim().length();
		if (size != 5) {
			return false;
		}
		char ch = b.charAt(0);
		if (!isULetter(ch)) {
			return false;
		}

		for (int i = 1; i < size; i++) {
			ch = b.charAt(i);
			if (isNotNumber(ch)) {
				return false;
			}
			
		}
		return true;
	}

	/**
	 * @param ch character 
	 * @return true if the character provided in the parameter is a number and false if it is not
	 */
	private boolean isNotNumber(char ch) {
		return ch < '0' || ch > '9';
	}

	/**
	 * @param ch character
	 * @return true if the character provided in the parameter is a capital letter and false otherwise 
	 */
	private boolean isULetter(char ch) {
		return ch >= 'A' && ch <= 'Z';
	}
	
	public synchronized void setTimerFinished() {
		timer =true;
		notifier("Flight has departed!");
	}
	
	public boolean getTimerFinish() {
		return timer;
	}
	
	public synchronized void notifier(String x) {		
		setChanged();
		notifyObservers(x);
    	clearChanged();    	
	}
			
}
	

