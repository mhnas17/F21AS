package flight;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import Exceptions.InvalidFlightCode;
import core.Flight;
import name_plane.Plane;

public class FlightTest {

	@Test
	public void testGetFlightcode() throws InvalidFlightCode {
	
		 String expected1 = "A1234";
		 Flight c1 = new Flight("A1234","London","AEGEAN", new Plane (140,300,500));;
	     String actual1 = c1.getFlightcode();
		 assertEquals(expected1, actual1);
	}

	
	@Test
	public void testSetFlightcode() throws InvalidFlightCode {
		 String expected1 = "A1235";
		 Flight c1 = new Flight("A1234","London","AEGEAN", new Plane (140,300,500));;
	     c1.setFlightcode("A1235");
		 assertEquals(expected1, c1.getFlightcode());
	}
	
	@Test(expected = InvalidFlightCode.class)
	public  void invalidflightcodesupplied() throws InvalidFlightCode {
		Flight c = new Flight("1320A","London","AEGEAN", new Plane (140,300,500));
	}
	
	
	
	
}
