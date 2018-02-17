package core;

import Exceptions.InvalidBookingReference;
import GUI.NewGui;
import name_plane.Name;
import name_plane.Plane;


public class Manager {
 
	public static void main(String[] args) throws Exception {
		
				 
		// TODO Auto-generated method stub
		//Data s= new Data();
		//s.process("textfile1", "textfile2");
		//NewGui demo = new NewGui();
		Booking b = new Booking("AF115",new Name("Minadakis George"),"A1320",false);
		Flight c = new Flight("A13201","London","AEGEAN", new Plane (140,300,500));
		//o phil gamietai
	}

}
