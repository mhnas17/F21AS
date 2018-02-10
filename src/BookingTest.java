import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.AfterClass;

import name_plane.Name;

public class BookingTest {

	
	Booking b = new Booking ("ggggg", new Name("George Mindadakis"), "ER1234", false);
	
	@Rule
    public ExpectedException thrown = ExpectedException.none();
	
	/*@BeforeClass
	public static void setupBeforeClass() throws Exception{}
	@AfterClass
	public static void AfterClass() throws Exception{}
	
	*/
	
	@Test
	public void testException(){
		
		thrown.expect(IllegalStateException.class);
		thrown.expectMessage("Wrong");
		b.setBookingreference("ggrr");
	}


	

}
