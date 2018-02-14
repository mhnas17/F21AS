package booking;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import Exceptions.InvalidBookingReference;
import Exceptions.InvalidFlightCode;
import core.Booking;
import core.Flight;
import name_plane.Name;
import name_plane.Plane;

public class BookingTest {
	
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
	public void testGetterAndSetter_shouldPass() throws Exception {
		Booking b1 = new Booking("RF112", new Name("Minadakis George"), "A1320", false);
		
		assertEquals("Booking should be RF112", "RF112", b1.getBookingreference());
		b1.setBookingreference("AB123");
		assertEquals("Booking should be AB123", "AB123", b1.getBookingreference());
			
	}

	@Test
	public void testBooking() throws InvalidBookingReference, InvalidFlightCode {

		thrown.expect(InvalidBookingReference.class);
		thrown.expectMessage("Invalid Booking Reference : Must be 5 characters long.");
		Booking b = new Booking("RF1222", new Name("Minadakis George"), "A1320", false);
		
	}
	
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
