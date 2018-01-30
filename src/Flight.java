
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
	 */
	public Flight(String flightcode,String flightdestination,String carrier, Plane plane){
		
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
	 */
	public void setFlightcode(String flightcode) {
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
	public void setFlightdestination(String flightdestination) {
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
	 */
	public void setPlane(Plane plane) {
		this.plane = plane;
	}
		
}
