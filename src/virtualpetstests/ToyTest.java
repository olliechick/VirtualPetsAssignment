package virtualpetstests;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import virtualpets.Toy;

/**
 * Tests for Toy class implementation
 * @author Samuel Pell
 *
 */
public class ToyTest {

	private Toy myToy;
	private Toy myOtherToy;
	private Toy myThirdToy;
	private String[] species = {"Wildcat", "Adult", "Child"};
	private String[] myToyValues = {"3", "2", "6"};
	private String[] myOtherToyValues = {"30", "1000", "-1100"};
	
	@Before
	public void setUp() throws Exception {
		this.myToy = new Toy("Expensive Spinning Top", "A brightly coloured spinning top", new Integer(20), new Integer(15));
		this.myOtherToy = new Toy("Cheap Spinning Top", "A dull metal coloured spinning top", new Integer(5), new Integer(2));
		this.myToy.setHappinessIncrease(species, myToyValues);
		this.myOtherToy.setHappinessIncrease(species, myOtherToyValues);
		this.myThirdToy = new Toy("String", "", 20, 15);
	}

	@Test
	public void testGetDurability() {
		assertEquals(myToy.getDurability(), 15);
		assertEquals(myOtherToy.getDurability(), 2);
	}

	@Test
	public void testGetHappinessIncrease() {
		for (int i = 0; i < species.length; i++){
			assertEquals(myToy.getHappinessIncrease(species[i]), Integer.parseInt(myToyValues[i]));
			assertEquals(myOtherToy.getHappinessIncrease(species[i]), Integer.parseInt(myOtherToyValues[i]));
		}
		try {
			myToy.getHappinessIncrease("Alien");
			fail("Found a species which doesn't exits");
		} catch (IllegalArgumentException exception){}
	}

	@Test
	public void testDecrementDurability() {
		//Standard use case - eventual decrement to 0
		//when durability gets to 0 (after decrement) raise exception
		myOtherToy.decrementDurability(1);
		try{
			myOtherToy.decrementDurability(1);
			fail("Let durability = 0");
		}catch(IllegalArgumentException e){
			assertEquals(myOtherToy.getDurability(), 0);
		}
		
		//Try decrementing by a negative number
		try {
			myToy.decrementDurability(-5);
			fail("Allows a negative durability change");
		} catch (IllegalArgumentException exception){
			assertEquals(exception.getMessage().substring(0, 24), "durability must decrease");	
		}
		
		//Then try decrementing by 0
		try {
			myToy.decrementDurability(0);
			fail("Allows a 0 durability change");
		} catch (IllegalArgumentException exception){
			assertEquals(exception.getMessage().substring(0, 24), "durability must decrease");	
		}
		
		//Try having a 0 durability toy (directly to 0
		try {
			myToy.decrementDurability(15);
			fail("Allows a 0 durability");
		} catch (IllegalArgumentException exception){
			assertEquals(exception.getMessage(), "durability is zero or negative");
		}
		
		//Try having a -ve durability toy
		try {
			myThirdToy.decrementDurability(16);
			fail("Allows a 0 durability");
		} catch (IllegalArgumentException exception){
			assertEquals(exception.getMessage(), "durability is zero or negative");
		}
	}

	@Test
	public void testSetHappinessIncrease() {
		for (int i = 0; i < species.length; i++){
			assertEquals(myToy.getHappinessIncrease(species[i]), Integer.parseInt(myToyValues[i]));
			assertEquals(myOtherToy.getHappinessIncrease(species[i]), Integer.parseInt(myOtherToyValues[i]));
		}
	}
}
