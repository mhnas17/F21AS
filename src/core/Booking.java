package core;
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
	 * fgfgf
	 */
	public Booking(String bookingreference, Name passengername, String flightcode, boolean checkedin){
		if(!validbookingreference(bookingreference)){				
			
			throw new IllegalStateException("Booking Reference should be of format AB123");
		
		}		
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
      
		if(!validbookingreference(bookingreference)){				
			
			throw new IllegalStateException("Booking Reference should be of format AB123");
		
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
	
	
	public boolean validbookingreference(String b){
		int size = b.trim().length();
		if (size !=5 ) return false;
		char ch = b.charAt(0);
		if (!isULetter(ch)) return false;
		ch = b.charAt(1);
		if (!isULetter(ch)) return false;
		for (int i=2; i<size; i++) {
			ch = b.charAt(i);
			if(isNotNumber(ch)) {
			return false;}
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
