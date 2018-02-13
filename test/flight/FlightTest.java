package flight;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import core.Flight;
import name_plane.Plane;

public class FlightTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		
	}
 
	@After
	public void tearDown() throws Exception {
	}

	@Rule
    public ExpectedException thrown = ExpectedException.none();

	
	@Test
	public void testFlight() {
		
	}

	@Test
	public void testGetFlightcode() {
		 String expected1 = "A1320";
		 Flight b = new Flight("A1320","Heraklion","AEGEAN", new Plane(140,3,500));
		 String actual1 = b.getFlightcode();
		 assertEquals(expected1, actual1);
	}

	@Test
	public void testSetFlightcode() {
		  String expected1 = "A1321";
		  Flight b = new Flight("A1320","Heraklion","AEGEAN", new Plane(140,3,500));
		  b.setFlightcode("A1321");
		  assertEquals(expected1, b.getFlightcode());
	}

	/*
	@Test
	public void testGetFlightdestination() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetFlightdestination() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCarrier() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetCarrier() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPlane() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetPlane() {
		fail("Not yet implemented");
	}
*/
}
