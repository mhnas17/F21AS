package booking;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import core.Booking;
import name_plane.Name;

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
	public void testBooking() {
		
		thrown.expect(IllegalStateException.class);
		thrown.expectMessage("Booking Reference should be of format AB123");
		Booking b  =new Booking("RFA12",new Name("Minadakis George"),"A1320", false);
	}
	
}
