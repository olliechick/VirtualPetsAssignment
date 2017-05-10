import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests for Food class implementation
 * @author Samuel Pell
 *
 */
public class FoodTest {

	private String nullString;
	private Food myFood;
	private String[] species = {"Wildcat", "Human", "Vegan"};
	private String[] values = {"3", "5", "-10"};
	
	@Before
	public void setup() throws Exception{
		this.myFood = new Food("Meatloaf", "A delicious meaty loaf", new Integer(20), new Integer(2));
		myFood.setHealthIncrease(species, values);
	}

	@Test
	public void testGetHealthIncrease() {
		//Test that all health increases are set correctly
		for (int i = 0; i < species.length; i++){
			assertEquals(myFood.getHealthIncrease(species[i]), Integer.parseInt(values[i]));
		}
		//Test that an unknown species throws an error
		try {
			myFood.getHealthIncrease("Alien");
			fail("Found a species which doesn't exits");
		} catch (IllegalArgumentException exception){}
	}

	@Test
	public void testSetPortionSize() {
		//Check that portion size can be set to a normal value
		myFood.setPortionSize(3);
		assertEquals(myFood.getPortionSize(), 3);
		
		//Test that portion size cannot be negative
		try {
		    myFood.setPortionSize(-1);
		    fail("Allowed an illegal portion size to be entered" );
		} catch (IllegalArgumentException exception) {
			assertEquals(myFood.getPortionSize(), 3);
		}
		
		//Test that portion size cannot be 0
		try{
			myFood.setPortionSize(0);
			fail("Allowed an illegal portion size to be entered" );
		} catch (IllegalArgumentException exception) {
			assertEquals(myFood.getPortionSize(), 3);
		}
	}

	
	/*---------------------------------------------------*
	 * This section test Item base class implementation  *
	 *                  works correctly                  *
	 *---------------------------------------------------*/

	@Test
	public void testSetName() {
		//Test if name can be set to a normal value
		myFood.setName("Delicious Meatloaf");
		assertEquals(myFood.getName(), "Delicious Meatloaf");
		
		//Test if name can be set to the empty string
		try {
		    myFood.setName("");
		    fail("Allowed an illegal item name to be entered" );
		} catch (IllegalArgumentException exception) {
			assertEquals(myFood.getName(), "Delicious Meatloaf");
		}
		
		//Test if name can be set to null
		try{
			myFood.setName(nullString);
			fail("Allowed an illegal item name to be entered" );
		} catch (IllegalArgumentException exception) {
			assertEquals(myFood.getName(), "Delicious Meatloaf");
		}
	}

	@Test
	public void testSetPrice() {
		//Test if price can be set to a normal value
		myFood.setPrice(50);
		assertEquals(myFood.getPrice(), 50);
		
		//Test if price can be set to 0 (shouldn't be possible)
		try {
		    myFood.setPrice(0);
		    fail("Allowed an illegal item name to be entered" );
		} catch (IllegalArgumentException exception) {
			assertEquals(myFood.getPrice(), 50);
		}
		
		//Test if price can be set to -ve values (shouldn't be possible)
		try{
			myFood.setPrice(-10);
			fail("Allowed an illegal item name to be entered" );
		} catch (IllegalArgumentException exception) {
			assertEquals(myFood.getPrice(), 50);
		}
	}

	@Test
	public void testSetDescription() {
		//Test items description can be set to a sane string
		myFood.setDescription("Dry and stale meatloaf");
		assertEquals(myFood.getDescription(), "Dry and stale meatloaf");
		
		//Test if food description can be set to the empty string (should be possible)
		try {
		    myFood.setDescription("");
		    assertEquals(myFood.getDescription(), "");
		} catch (IllegalArgumentException exception) {
			fail("Prevented a legal item name to be entered" );
		}
		
		//Test if food desciption can be set to null
		try{
			myFood.setName(nullString);
			fail("Allowed an illegal item name to be entered" );
		} catch (IllegalArgumentException exception) {
			assertEquals(myFood.getDescription(), "");
		}
	}
	
	@Test
	public void testConvertStringsToInts(){
		String[] testArray = {"0", "-30", "5000", "-1", "-500000", "99999"};
		Integer[] results = myFood.convertStringsToInts(testArray);
		
		Integer expectedResult;
		
		for(int i = 0; i < testArray.length; i++){
			expectedResult = Integer.parseInt(testArray[i]);
			assertEquals(results[i], expectedResult);
		}
	}
}
