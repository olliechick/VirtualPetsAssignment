import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
/**
 * Tests for Toy class implementation
 * @author Samuel Pell
 *
 */
public class ToyTest {

	private Toy myToy;
	private Toy myOtherToy;
	private String[] species = {"Wildcat", "Adult", "Child"};
	private int[] myToyValues = {3, 2, 6};
	private int[] myOtherToyValues = {30, 1000, -1100};
	
	@Before
	public void setUp() throws Exception {
		this.myToy = new Toy("Expensive Spinning Top", "A brightly coloured spinning top", new Integer(20), new Integer(15));
		this.myOtherToy = new Toy("Cheap Spinning Top", "A dull metal coloured spinning top", new Integer(5), new Integer(2));
		this.myToy.setHappinessIncrease(species, myToyValues);
		this.myOtherToy.setHappinessIncrease(species, myOtherToyValues);
	}

	@Test
	public void testGetDurability() {
		assertEquals(myToy.getDurability(), 15);
		assertEquals(myOtherToy.getDurability(), 2);
	}

	@Test
	public void testGetHappinessIncrease() {
		for (int i = 0; i < species.length; i++){
			assertEquals(myToy.getHappinessIncrease(species[i]), myToyValues[i]);
			assertEquals(myOtherToy.getHappinessIncrease(species[i]), myOtherToyValues[i]);
		}
		try {
			myToy.getHappinessIncrease("Alien");
			fail("Found a species which doesn't exits");
		} catch (IllegalArgumentException exception){}
	}

	@Test
	public void testSetDurability() {
		assertEquals(myToy.getDurability(), 15);
		assertEquals(myOtherToy.getDurability(), 2);
	}

	@Test
	public void testSetHappinessIncrease() {
		for (int i = 0; i < species.length; i++){
			assertEquals(myToy.getHappinessIncrease(species[i]), myToyValues[i]);
			assertEquals(myOtherToy.getHappinessIncrease(species[i]), myOtherToyValues[i]);
		}
	}
}
