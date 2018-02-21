package Luggage;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Exceptions.NegativeNumbers;
import core.Luggage;



public class LuggageTest {
	
	
private Luggage c1;
	
	@Before
	public void setup() throws Exception {
		c1 = new Luggage(0, 0, 0, 0);
	}
	

	@Test
	public void testGetAccum_volume() {
		
		double expected1 = 0;
	     double actual1 = c1.getAccum_volume();
	     assertEquals(expected1, actual1,0.00000001);
		
	}

	@Test
	public void testSetAccum_volume() throws NegativeNumbers {
		double expected1 = 10;
		double expected2 = 25.5;
	     c1.setAccum_volume(10);
		 assertEquals(expected1, c1.getAccum_volume(),0.00000001);
		 c1.setAccum_volume(15.5);
		 assertEquals(expected2, c1.getAccum_volume(),0.00000001);
	}

	@Test
	public void testGetAccum_weight() {
		double expected1 = 0;
	     double actual1 = c1.getAccum_weight();
	     assertEquals(expected1, actual1,0.00000001);
	}

	@Test
	public void testSetAccum_weight() throws NegativeNumbers {
		double expected1 = 10;
		double expected2 = 22;
	     c1.setAccum_weight(10);
		 assertEquals(expected1, c1.getAccum_weight(),0.00000001);
		 c1.setAccum_weight(12);
		 assertEquals(expected2, c1.getAccum_weight(),0.00000001);
	}
	
	
	@Test
	public void testGetAccum_excessfees() {
		double expected1 = 0;
	     double actual1 = c1.getAccum_excessfees();
	     assertEquals(expected1, actual1,0.00000001);
	}

	@Test
	public void testSetAccum_excessfees() {
		double expected1 = 10;
		double expected2 = 22;
	     c1.setAccum_excessfees(10);
		 assertEquals(expected1, c1.getAccum_excessfees(),0.00000001);
		 c1.setAccum_excessfees(12);
		 assertEquals(expected2, c1.getAccum_excessfees(),0.00000001);
	}
	
	@Test
	public void testGetAccum_numberofpassengers() {
		double expected1 = 0;
	     double actual1 = c1.getAccum_numberofpassengers();
	     assertEquals(expected1, actual1,0.00000001);
	}

	@Test
	public void testSetAccum_numberofpassengers() {
		double expected1 = 10;
		double expected2 = 22;
	     c1.setAccum_numberofpassengers(10);
		 assertEquals(expected1, c1.getAccum_numberofpassengers(),0.00000001);
		 c1.setAccum_numberofpassengers(12);
		 assertEquals(expected2, c1.getAccum_numberofpassengers(),0.00000001);
	}

}
