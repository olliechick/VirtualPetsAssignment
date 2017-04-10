import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests for Food class implmentation
 * @author Samuel Pell
 *
 */
public class FoodTest {

	private String nullString;
	private Food myFood;
	private String[] species = {"Wildcat", "Human", "Vegan"};
	private int[] values = {3, 5, -10};
	
	@Before
	public void setup() throws Exception{
		this.myFood = new Food("Meatloaf", "A delicious meaty loaf", new Integer(20), new Integer(2));
		myFood.setHealthIncrease(species, values);
	}

	@Test
	public void testGetHealthIncrease() {
		for (int i = 0; i < species.length; i++){
			assertEquals(myFood.getHealthIncrease(species[i]), values[i]);
		}
		try {
			myFood.getHealthIncrease("Alien");
			fail("Found a species which doesn't exits");
		} catch (IllegalArgumentException exception){}
	}

	@Test
	public void testGetPortionSize() {
		assertEquals(myFood.getPortionSize(), 2);
	}

	@Test
	public void testSetHealthIncrease() {
		for (int i = 0; i < species.length; i++){
			assertEquals(myFood.getHealthIncrease(species[i]), values[i]);
		}
	}

	@Test
	public void testSetPortionSize() {
		myFood.setPortionSize(3);
		assertEquals(myFood.getPortionSize(), 3);
		try {
		    myFood.setPortionSize(-1);
		    fail("Allowed an illegal portion size to be entered" );
		} catch (IllegalArgumentException exception) {
			assertEquals(myFood.getPortionSize(), 3);
		}
		
		try{
			myFood.setPortionSize(0);
			fail("Allowed an illegal portion size to be entered" );
		} catch (IllegalArgumentException exception) {
			assertEquals(myFood.getPortionSize(), 3);
		}
	}

	@Test
	public void testGetName() {
		assertEquals(myFood.getName(), "Meatloaf");
	}

	@Test
	public void testGetDescription() {
		assertEquals(myFood.getDescription(), "A delicious meaty loaf");
	}

	@Test
	public void testGetPrice() {
		assertEquals(myFood.getPrice(), 20);
	}

	@Test
	public void testSetName() {
		myFood.setName("Delicious Meatloaf");
		assertEquals(myFood.getName(), "Delicious Meatloaf");
		try {
		    myFood.setName("");
		    fail("Allowed an illegal item name to be entered" );
		} catch (IllegalArgumentException exception) {
			assertEquals(myFood.getName(), "Delicious Meatloaf");
		}
		
		try{
			myFood.setName(nullString);
			fail("Allowed an illegal item name to be entered" );
		} catch (IllegalArgumentException exception) {
			assertEquals(myFood.getName(), "Delicious Meatloaf");
		}
	}

	@Test
	public void testSetPrice() {
		myFood.setPrice(50);
		assertEquals(myFood.getPrice(), 50);
		try {
		    myFood.setPrice(0);
		    fail("Allowed an illegal item name to be entered" );
		} catch (IllegalArgumentException exception) {
			assertEquals(myFood.getPrice(), 50);
		}
		
		try{
			myFood.setPrice(-10);
			fail("Allowed an illegal item name to be entered" );
		} catch (IllegalArgumentException exception) {
			assertEquals(myFood.getPrice(), 50);
		}
	}

	@Test
	public void testSetDescription() {
		myFood.setDescription("Dry and stale meatloaf");
		assertEquals(myFood.getDescription(), "Dry and stale meatloaf");
		try {
		    myFood.setDescription("");
		    assertEquals(myFood.getDescription(), "");
		} catch (IllegalArgumentException exception) {
			fail("Prevented a legal item name to be entered" );
		}
		
		try{
			myFood.setName(nullString);
			fail("Allowed an illegal item name to be entered" );
		} catch (IllegalArgumentException exception) {
			assertEquals(myFood.getDescription(), "");
		}
	}
}
