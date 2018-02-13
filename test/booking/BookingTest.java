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
import core.Booking;
import name_plane.Name;

public class BookingTest {
	Booking b1 = new Booking("RF112", new Name("Minadakis George"), "A1320", false);

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
	// Testing the getters and setters for booking reference. These should pass
	public void testGetterAndSetter_shouldPass() {

		assertEquals("Booking should be RF112", "RF112", b1.getBookingreference());
		b1.setBookingreference("AB123");
		assertEquals("Booking should be AB123", "AB123", b1.getBookingreference());
	}

	@Test
	public void testBooking() {

		//thrown.expect(InvalidBookingReference.class);
		//thrown.expectMessage("Invalid Booking Reference : Must be 5 characters long.");
		Booking b = new Booking("RF12", new Name("Minadakis George"), "A1320", false);
	}

}
