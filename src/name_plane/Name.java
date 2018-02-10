package name_plane;
public class Name {
		
    //instance variables
	private String firstName;
	private String middleName;
	private String lastName;

	/**
	 * constructor to create object with first, middle and last name
	 * @param fName first name
	 * @param mName middle name
	 * @param lName last name
	 */
	public Name(String fName, String mName, String lName) {
		setFirstName(fName);
		setMiddleName(mName);
		setLastName(lName);
	}
	
	/**
	 * constructor to generate name from full name
	 * @param fullName full name
	 */
	public Name(String fullName) {
		try {

		int space1 = fullName.indexOf(' ');
		firstName = fullName.substring(0, space1);
		int space2 = fullName.lastIndexOf(' ');
		if (space1 == space2)
			middleName = "";
		else
			middleName = fullName.substring(space1 + 1, space2);
		lastName = fullName.substring(space2 + 1);

	}
		catch (StringIndexOutOfBoundsException sofb) {
			String error = "You have entered an owner name at the csv file using incorect format";
			System.out.println(error);
			System.exit(0);
		}
		
	}

	/**
	 * returns the first name
	 * @return first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * sets the first name
	 * @param firstName the new first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * returns the middle name
	 * @return middle name
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * sets the middle name
	 * @param middleName the new middle name
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * returns the last name
	 * @return last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * sets the last name
	 * @param lastName the new last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * returns the first and last name only
	 * @return first and last name
	 */
	public String getFirstAndLastName() {
		return firstName + " " + lastName;
	}

	/**
	 * returns either first name then space then last name,
	 * or first name then space then middle name then space and then last name
	 * @return full name
	 */
	public String getFullName() {
		String result = firstName + " ";
		if (middleName.equals("") == false) {
			result += middleName + " ";
		}
		result += lastName;
		return result;
	}

	
	/**
	 * returns the initials of the cabin owner separated by dots
	 * @return initials of cabin owner
	 */
	public String getInitials() {

		String firstInitial = firstName.substring(0, 1);
		String result = firstInitial + ".";
		if (middleName.equals("") == false) {
			String middleInitial = middleName.substring(0, 1);
			result += middleInitial + ".";
		}
		String lastInitial = lastName.substring(0, 1);
		result += lastInitial;
		return result;
	}
}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

