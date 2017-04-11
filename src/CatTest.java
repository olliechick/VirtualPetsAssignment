import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

/**
 * Test for Cat class implementation
 * @author Ollie Chick and Samuel Pell
 *
 */

public class CatTest {
	
	private Cat myCat;
	private Cat myOtherCat;
	private Toy myToy; 
	private Food myFood;
	
	@Before
	public void setUp() throws Exception{		
		myCat = new Cat();
		myOtherCat = new Cat();
		
		String[] species = {myCat.getSpecies()};
		int[] values = {25};
		
		//initialise myToy and myFood
		myToy = new Toy("scratching post", "", 1, 3);
		myFood = new Food("Meatloaf", "", 1, 3);
		
		//add values for happiness increase to myToy
		myToy.setHappinessIncrease(species, values);
		//add values for health increase to myFood
		myFood.setHealthIncrease(species, values);
		
		//Set myOtherCat's attributes to extremes
		myOtherCat.increaseMischievousness(100);
		myOtherCat.increaseHappiness(-100);
		myOtherCat.increaseFatigue(100);
		myOtherCat.increaseHunger(100);
	}

	@Test
	public void testCat() {
		assertEquals(myCat.getSpecies(), "cat");
	}

	@Test
	public void testPlay() {
		//get initial values
		int initialDurability = myToy.getDurability();
		int initialHappiness = myCat.getHappiness();
		int initialFatigue = myCat.getFatigue();
		int initialMischievousness = myCat.getMischievousness();
		int initialHunger = myCat.getHunger();
	
		int otherInitialHappiness = myOtherCat.getHappiness();
		int otherInitialFatigue = myOtherCat.getFatigue();
		int otherInitialMischievousness = myOtherCat.getMischievousness();
		int otherInitialHunger = myOtherCat.getHunger();
		
		//play with the toy
		myCat.play(myToy);
		myOtherCat.play(myToy);
		
		//check new values are as expected
		assertTrue(initialDurability  > myToy.getDurability());
		
		//check for no changed to maxed out happiness
		assertTrue((initialHappiness == 100 && myCat.getHappiness() == 100));
		//check if initial happiness is lower than new happiness 
		assertTrue(otherInitialHappiness < myOtherCat.getHappiness());
		
		//check if initial fatigue is lower than new fatigue 
		assertTrue(initialFatigue < myCat.getFatigue());
		//check for no change in maximum fatigue case
		assertTrue(otherInitialFatigue == 100 && myOtherCat.getFatigue() == 100);
		
		//check for no change in minimum case
		assertTrue(initialMischievousness == 0 && myCat.getMischievousness() == 0);
		//check if initial mischeviousness is higher than new mischeviousness 
		assertTrue(otherInitialMischievousness > myOtherCat.getMischievousness());
		
		//check if animal is hungrier than before 
		assertTrue(initialHunger < myCat.getHunger());
		//Check for no change in maxed out case
		assertTrue(otherInitialHunger == 100 && myOtherCat.getHunger() == 100);
	}

	@Test
	public void testSleep() {
		fail("Not yet implemented");
	}

	@Test
	public void testGoToilet() {
		fail("Not yet implemented");
	}

	@Test
	public void testFeed() {
		fail("Not yet implemented");
	}

	@Test
	public void testMisbehave() {
		fail("Not yet implemented");
	}

	@Test
	public void testBeSick() {
		fail("Not yet implemented");
	}

	@Test
	public void testDie() {
		fail("Not yet implemented");
	}

}
