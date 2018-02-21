package Luggage;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import exceptions.NegativeNumbers;
import luggage.Luggage;



public class LuggageTest {
	
	
private Luggage c1;
	
	@Before
	public void setup() throws Exception {
		c1 = new Luggage(0, 0, 0, 0);
	}
	
	// test for the accumulative volume getter method
	@Test 
	public void testGetAccum_volume() {
		
		double expected1 = 0;
	     double actual1 = c1.getAccum_volume();
	     assertEquals(expected1, actual1,0.00000001);
		
	}
	// test for the accumulative setter volume method
	@Test
	public void testSetAccum_volume() throws NegativeNumbers {
		double expected1 = 10;
		double expected2 = 25.5;
	     c1.setAccum_volume(10);
		 assertEquals(expected1, c1.getAccum_volume(),0.00000001);
		 c1.setAccum_volume(15.5);
		 assertEquals(expected2, c1.getAccum_volume(),0.00000001);
	}
	// test for the accumulative weight getter method	
	@Test
	public void testGetAccum_weight() {
		double expected1 = 0;
	     double actual1 = c1.getAccum_weight();
	     assertEquals(expected1, actual1,0.00000001);
	}
	// test for the accumulative weight setter method
	@Test
	public void testSetAccum_weight() throws NegativeNumbers {
		double expected1 = 10;
		double expected2 = 22;
	     c1.setAccum_weight(10);
		 assertEquals(expected1, c1.getAccum_weight(),0.00000001);
		 c1.setAccum_weight(12);
		 assertEquals(expected2, c1.getAccum_weight(),0.00000001);
	}
	
	// test for the accumulative excess fees getter method
	@Test
	public void testGetAccum_excessfees() {
		double expected1 = 0;
	     double actual1 = c1.getAccum_excessfees();
	     assertEquals(expected1, actual1,0.00000001);
	}
	// test for the accumulative excess fees setter method
	@Test
	public void testSetAccum_excessfees() {
		double expected1 = 10;
		double expected2 = 22;
	     c1.setAccum_excessfees(10);
		 assertEquals(expected1, c1.getAccum_excessfees(),0.00000001);
		 c1.setAccum_excessfees(12);
		 assertEquals(expected2, c1.getAccum_excessfees(),0.00000001);
	}
	// test for the accumulative passengers getter method
	@Test
	public void testGetAccum_numberofpassengers() {
		double expected1 = 0;
	     double actual1 = c1.getAccum_numberofpassengers();
	     assertEquals(expected1, actual1,0.00000001);
	}
	// test for the accumulative passengers setter method
	@Test
	public void testSetAccum_numberofpassengers() {
		double expected1 = 10;
		double expected2 = 22;
	     c1.setAccum_numberofpassengers(10);
		 assertEquals(expected1, c1.getAccum_numberofpassengers(),0.00000001);
		 c1.setAccum_numberofpassengers(12);
		 assertEquals(expected2, c1.getAccum_numberofpassengers(),0.00000001);
	}
	// Test for the computation of the volume method
	@Test
	public void testComVolume() {
		double expected1 = 0.048125;
	    double actual1 = c1.computeVolume(25,35,55);
	    assertEquals(expected1, actual1,0.00000001);
	}
	// Test for the computation of the excess method for all the different cases
	@Test
	public void testcomputeExcessFees() {
		double expected1 = 0;
		double expected2 = 0;
		double expected3 = 25;
		double expected4 = 50;
		double expected5 = 60;
	    double actual1 = c1.compExceessFees(10);
	    double actual2 = c1.compExceessFees(20);
	    double actual3 = c1.compExceessFees(25);
	    double actual4 = c1.compExceessFees(30);
	    double actual5 = c1.compExceessFees(31);
	    assertEquals(expected1, actual1,0.00000001);
	    assertEquals(expected2, actual2,0.00000001);
	    assertEquals(expected3, actual3,0.00000001);
	    assertEquals(expected4, actual4,0.00000001);
	    assertEquals(expected5, actual5,0.00000001);
	}
}
