package core;
import Exceptions.InvalidFlightCode;
import name_plane.Plane;


public class Flight {

	private String flightcode;
	private String flightdestination;
	private String carrier;
	private Plane plane;
		
	
	/**
	 * @param flightcode
	 * @param flightdestination
	 * @param carrier
	 * @param plane
	 * @throws InvalidFlightCode 
	 */
	public Flight(String flightcode, String flightdestination, String carrier, Plane plane) throws InvalidFlightCode {

		setFlightcode(flightcode);
		setFlightdestination(flightdestination);
		setCarrier(carrier);
		setPlane(plane);

	}

	/**
	 * @return
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
	 * @return
	 */
	public String getFlightdestination() {
		return flightdestination;
	}

	/**
	 * @param flightdestination
	 */
	public void setFlightdestination(String flightdestination)  {
		this.flightdestination = flightdestination;
	}

	/**
	 * @return
	 */
	public String getCarrier() {
		return carrier;
	}

	/**
	 * @param carrier
	 */
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	/**
	 * @return
	 */
	public Plane getPlane() {
		return plane;
	}

	/**
	 * @param plane
	 * @throws IndalidPlaneType 
	 */
	public void setPlane(Plane plane) {
		
		this.plane = plane;
	}

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

	private boolean isNotNumber(char ch) {
		return ch < '0' || ch > '9';
	}

	private boolean isULetter(char ch) {
		return ch >= 'A' && ch <= 'Z';
	}

			
}
	

