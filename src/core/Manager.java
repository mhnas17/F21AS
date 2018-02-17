package core;
import java.util.InputMismatchException;
import java.io.*;
import Exceptions.InvalidBookingReference;
import GUI.NewGui;
import name_plane.Name;
import name_plane.Plane;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.HashMap;

public class Manager {
 
	public static void main(String[] args) throws Exception {
		
				 
		// TODO Auto-generated method stub
		//Data s= new Data();
		//s.process("textfile1", "textfile2");
		//NewGui demo = new NewGui();
		Booking b = new Booking("AF115",new Name("Minadakis George"),"A1320",false);
		Flight c = new Flight("A13201","London","AEGEAN", new Plane (140,300,500));
	}
	

	

}
