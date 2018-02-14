package flight;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import Exceptions.InvalidFlightCode;
import core.Flight;
import name_plane.Plane;

public class FlightTest {

	private Flight c1;
	
	@Before
	public void setup() throws Exception {
		c1 = new Flight("A1234","London","AEGEAN", new Plane (140,300,500));
	}
	
	@Test
	public void testGetFlightcode() throws InvalidFlightCode {
	
		 String expected1 = "A1234";
	     String actual1 = c1.getFlightcode();
		 assertEquals(expected1, actual1);
	}

	
	@Test
	public void testSetFlightcode() throws InvalidFlightCode {
		 String expected1 = "A1235";
	     c1.setFlightcode("A1235");
		 assertEquals(expected1, c1.getFlightcode());
	}
	
	@Test(expected = InvalidFlightCode.class)
	public  void invalidflightcodesupplied() throws InvalidFlightCode {
		c1.setFlightcode("1320A");
	}
	
	
	@Test
	public void testGetFlightdestination() throws Exception {
		 String expected1 = "London";
	     String actual1 = c1.getFlightdestination();
		 assertEquals(expected1, actual1);
	}
	
	@Test
	public void testSetFlightdestination() throws Exception {
		 String expected1 = "Athens";
	     c1.setFlightdestination("Athens");
		 assertEquals(expected1, c1.getFlightdestination());
	}
	
	@Test
	public void testGetCarrier() throws Exception {
		 String expected1 = "AEGEAN";
	     String actual1 = c1.getCarrier();
		 assertEquals(expected1, actual1);
	}
	
	@Test
	public void testSetCarrier() throws Exception {
		String expected1 = "BA";
	     c1.setCarrier("BA");
		 assertEquals(expected1, c1.getCarrier());	
	}
	
	@Test
	public void testSetplane() throws Exception {
		Plane expected1 = new Plane (140,200,600);
	     c1.setPlane(expected1);
		 assertEquals(expected1, c1.getPlane());	
	}
}
