import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

/**
 * Test for Cat class implementation
 * @author Ollie Chick
 * @author Samuel Pell
 *
 */

public class CatTest {
	
	private Cat myCat;
	private Cat myMaxCat;
	private Cat myMinCat;
	private Toy myToy; 
	private Food myFood;
	
	@Before
	public void setUp() throws Exception{		
		myCat = new Cat();
		myMaxCat = new Cat();
		myMinCat = new Cat();
		
		String[] species = {myCat.getSpecies()};
		int[] values = {25};
		
		//initialise myToy and myFood
		myToy = new Toy("scratching post", "", 1, 3);
		myFood = new Food("Meatloaf", "", 1, 3);
		
		//add values for happiness increase to myToy
		myToy.setHappinessIncrease(species, values);
		//add values for health increase to myFood
		myFood.setHealthIncrease(species, values);
		
		//Set myMaxCat's attributes to maximum extremes
		myMaxCat.increaseMischievousness(100);
		myMaxCat.increaseHappiness(100);
		myMaxCat.increaseFatigue(100);
		myMaxCat.increaseHunger(100);
		myMaxCat.increasePercentBladderFull(100);
		myMaxCat.increaseHealth(100);
		
		//Set myMinCat's attributes to minimum extremes
		myMinCat.increaseMischievousness(100);
		myMinCat.increaseHappiness(100);
		myMinCat.increaseFatigue(100);
		myMinCat.increaseHunger(100);
		myMinCat.increasePercentBladderFull(100);
		myMinCat.increaseHealth(100);
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
	
		int maxInitialHappiness = myMaxCat.getHappiness();
		int maxInitialFatigue = myMaxCat.getFatigue();
		int maxInitialMischievousness = myMaxCat.getMischievousness();
		int maxInitialHunger = myMaxCat.getHunger();
	
		int minInitialHappiness = myMinCat.getHappiness();
		int minInitialFatigue = myMinCat.getFatigue();
		int minInitialMischievousness = myMinCat.getMischievousness();
		int minInitialHunger = myMinCat.getHunger();
		
		//play with the toy
		myCat.play(myToy);
		myMaxCat.play(myToy);
		myMinCat.play(myToy);
		
		//check new values are as expected
		assertTrue(initialDurability  > myToy.getDurability());

		//check if initial happiness is lower than new happiness 
		assertTrue(maxInitialHappiness < myMaxCat.getHappiness());
		//check for no changed to maxed out happiness
		assertTrue((initialHappiness == 100 && myCat.getHappiness() == 100));
		
		//check if initial fatigue is lower than new fatigue 
		assertTrue(initialFatigue < myCat.getFatigue());
		//check for no change in maximum fatigue case
		assertTrue(maxInitialFatigue == 100 && myMaxCat.getFatigue() == 100);
		
		//check if initial mischeviousness is higher than new mischeviousness 
		assertTrue(maxInitialMischievousness > myMaxCat.getMischievousness());
		//check for no change in minimum case
		assertTrue(initialMischievousness == 0 && myCat.getMischievousness() == 0);
		
		//check if animal is hungrier than before 
		assertTrue(initialHunger < myCat.getHunger());
		//Check for no change in maxed out case
		assertTrue(maxInitialHunger == 100 && myMaxCat.getHunger() == 100);
	}

	@Test
	public void testSleep() {
		int initialFatigue = myCat.getFatigue();
		int maxInitialFatigue = myOtherCat.getFatigue();
		
		myCat.sleep();
		myOtherCat.sleep();
		
		//check if initial fatigue is higher than new fatigue 
		assertTrue(initialFatigue > myCat.getFatigue());
		//check for no change in minimum fatigue case
		assertTrue(maxInitialFatigue == 0 && myOtherCat.getFatigue() == 0);
	}

	@Test
	public void testGoToilet() {
		int initialPercentBladderFull = myCat.getPercentBladderFull();
		int maxInitialPercentBladderFull = myOtherCat.getPercentBladderFull();
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
		myCat.die();
		assertFalse(myCat.getIsRevivable());
	}

}
