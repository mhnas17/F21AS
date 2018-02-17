package core;

import java.util.InputMismatchException;
import java.io.*;
import Exceptions.InvalidBookingReference;
import Exceptions.InvalidFlightCode;
import GUI.NewGui;
import name_plane.Name;
import name_plane.Plane;
import java.util.*;
import java.util.HashMap;

public class Manager {

	public static void main(String[] args) throws Exception {

		// TODO Auto-generated method stub
		// Data s= new Data();
		// s.process("textfile1", "textfile2");
		// NewGui demo = new NewGui();
		//Booking b = new Booking("AF115", new Name("Minadakis George"), "A1320", false);
		//Flight c = new Flight("A13201", "London", "AEGEAN", new Plane(140, 300, 500));
		Manager p= new Manager();
		
	}

public Manager() throws InvalidFlightCode
{   
	
	FlightMap entries = new FlightMap();
	BufferedReader buff = null;
	String data [] = new String[4];
	try {
	Plane p1=null;
	buff = new BufferedReader(new FileReader("flights.csv"));
	String inputLine = buff.readLine();  //read first line
	while(inputLine != null){  
		//split line into parts
		data  = inputLine.split(",");
		//create staff object
		String planeType=data[3];
		if( planeType.equals("A1")) {
			 p1=new Plane(140,1300,900);
					}
			else if(planeType.equals("A2")) {
				 p1=new Plane(200,1500,1000);
				}
	else {
		 p1=new Plane(250,1800,1200);
			}
		}
		Flight s = new Flight(data[0], data[1], data[2],p1);
		//add to list
        entries.add(data[0], s);
        //read next line
        inputLine = buff.readLine();
        System.out.println(entries.getFlight());
		}
	catch(FileNotFoundException e) {
    	System.out.println(e.getMessage());
        System.exit(1);
    }
    catch (IOException e) {
    	e.printStackTrace();
        System.exit(1);        	
    }
   
    finally  {
    	try{
    		buff.close();
    	}
    	catch (IOException ioe) {
    		//don't do anything
    	}
    }	
	
	}
   
    
}

