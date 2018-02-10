import name_plane.Name;
public class Booking {

	public String bookingreference;
	private Name passengername;
	private String flightcode;
	private boolean checkedin;
		
	/**
	 * @param bookingreference
	 * @param passengername
	 * @param flightcode
	 * @param checkedin
	 */
	public Booking(String bookingreference, Name passengername, String flightcode, boolean checkedin){
				
		setBookingreference(bookingreference);
		setPassengername(passengername);
		setFlightcode(flightcode);
		setCheckedin(checkedin);
		}
		
	
		
	/**
	 * @return
	 */
	public String getBookingreference() {
		return bookingreference;
	}
	
	
	
	
	/**
	 * @param bookingreference
	 */
	public void setBookingreference(String bookingreference) {
		if(bookingreference.trim().length()!=5){
			throw new IllegalStateException("Wrong");
		
		}
		this.bookingreference = bookingreference;
	}
	public Name getPassengername() {
		return passengername;
	}
	
	
	/**
	 * @param passengername
	 */
	public void setPassengername(Name passengername) {
		this.passengername = passengername;
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
	public boolean isCheckedin() {
		return checkedin;
	}
	
	
	/**
	 * @param checkedin
	 */
	public void setCheckedin(boolean checkedin) {
		this.checkedin = checkedin;
	}
	
		
}
