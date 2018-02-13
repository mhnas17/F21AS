package core;
import Exceptions.InvalidBookingReference;
import GUI.NewGui;
import name_plane.Name;
import name_plane.Plane;

public class Manager {
 
	public static void main(String[] args) throws InvalidBookingReference {
		
				 
		// TODO Auto-generated method stub
		//Data s= new Data();
		//s.process("textfile1", "textfile2");
		//NewGui demo = new NewGui();
		Booking b = new Booking("AF115",new Name("Minadakis George"),"A1320",false);
		Plane AB250 = new Plane(140,30,500); 
		Flight c = new Flight("A13204","Heraklion","AEGEAN", AB250);
		 
	}

}
