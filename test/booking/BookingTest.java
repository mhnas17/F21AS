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
	public void testGetFlightcode() throws InvalidFlightCode, InvalidBookingReference {
	
		 String expected1 = "A1234";
		 Booking b1 = new Booking("AB234", new Name("Minadakis George"), "A1234", false);
	     String actual1 = b1.getFlightcode();
		 assertEquals(expected1, actual1);
	}

	
	@Test
	public void testSetFlightcode() throws InvalidFlightCode, InvalidBookingReference {
		 String expected1 = "A1235";
		 Booking b1 = new Booking("RF112", new Name("Minadakis George"), "A1320", false);
	     b1.setFlightcode("A1235");
		 assertEquals(expected1, b1.getFlightcode());
	}
	
	@Test(expected = InvalidFlightCode.class)
	public  void invalidflightcodesupplied() throws InvalidFlightCode, InvalidBookingReference {
		Booking b1 = new Booking("RF112", new Name("Minadakis George"), "Ab320", false);
	}

	@Test
	public void testGetpassengername() throws InvalidFlightCode, InvalidBookingReference {
		Name expected1= new Name("Minadakis George");
		Booking b1 = new Booking("RF112", new Name("Minadakis George"), "A1320", false);
		Name actual1 = b1.getPassengername();
		assertEquals(expected1.getFirstName(),actual1.getFirstName());
		assertEquals(expected1.getLastName(),actual1.getLastName());
		assertEquals(expected1.getFullName(),actual1.getFullName());
		assertEquals(expected1.getInitials(),actual1.getInitials());
	}
	
	@Test
	public void testSetpassengername() throws InvalidFlightCode, InvalidBookingReference {
		Name expected1= new Name("Stam Tiniakos");
		Booking b1 = new Booking("RF112", new Name("Minadakis George"), "A1320", false);
		b1.setPassengername(expected1);
		assertEquals(expected1, b1.getPassengername());
		
	}
}
