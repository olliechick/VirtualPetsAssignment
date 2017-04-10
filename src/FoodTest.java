import static org.junit.Assert.*;

import org.junit.Test;

public class FoodTest {

	public String nullString;

	@Test
	public void testGetHealthIncrease() {
		Food myFood = new Food("Meatloaf", "A delicious meaty loaf", new Integer(20), new Integer(-3), new Integer(2));
		assertEquals(myFood.getHealthIncrease(), -3);
	}

	@Test
	public void testGetPortionSize() {
		Food myFood = new Food("Meatloaf", "A delicious meaty loaf", new Integer(20), new Integer(-3), new Integer(2));
		assertEquals(myFood.getPortionSize(), 2);
	}

	@Test
	public void testSetHealthIncrease() {
		Food myFood = new Food("Meatloaf", "A delicious meaty loaf", new Integer(20), new Integer(-3), new Integer(2));
		myFood.setHealthIncrease(-5);
		assertEquals(myFood.getHealthIncrease(), -5);		
	}

	@Test
	public void testSetPortionSize() {
		Food myFood = new Food("Meatloaf", "A delicious meaty loaf", new Integer(20), new Integer(-3), new Integer(2));
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
		Food myFood = new Food("Meatloaf", "A delicious meaty loaf", new Integer(20), new Integer(-3), new Integer(2));
		assertEquals(myFood.getName(), "Meatloaf");
	}

	@Test
	public void testGetDescription() {
		Food myFood = new Food("Meatloaf", "A delicious meaty loaf", new Integer(20), new Integer(-3), new Integer(2));
		assertEquals(myFood.getDescription(), "A delicious meaty loaf");
	}

	@Test
	public void testGetPrice() {
		Food myFood = new Food("Meatloaf", "A delicious meaty loaf", new Integer(20), new Integer(-3), new Integer(2));
		assertEquals(myFood.getPrice(), 20);
	}

	@Test
	public void testSetName() {
		Food myFood = new Food("Meatloaf", "A delicious meaty loaf", new Integer(20), new Integer(-3), new Integer(2));
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
		Food myFood = new Food("Meatloaf", "A delicious meaty loaf", new Integer(20), new Integer(-3), new Integer(2));
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
		Food myFood = new Food("Meatloaf", "A delicious meaty loaf", new Integer(20), new Integer(-3), new Integer(2));
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
