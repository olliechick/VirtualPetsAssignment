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
	private Toy myBadToy; 
	private Food myFood;
	private Food myBadFood;

	@Before
	public void setUp() throws Exception{		
		myCat = new Cat();
		myMaxCat = new Cat();
		myMinCat = new Cat();

		String[] species = {myCat.getSpecies()};
		int[] values = {20};
		int[] badValues = {-20};

		//initialise toys and foods
		myToy = new Toy("Ball", "", 1, 3); //name, description, price, durability
		myBadToy = new Toy("Roadspikes", "", 1, 3);
		myFood = new Food("General petfood", "", 1, 3); //name, description, price, portionSize
		myBadFood = new Food("Poison", "", 1, 3);

		//add values for happiness increase to toys
		myToy.setHappinessIncrease(species, values);
		myBadToy.setHappinessIncrease(species, badValues);
		//add values for health increase to foods
		myFood.setHealthIncrease(species, badValues);
		myBadFood.setHealthIncrease(species, badValues);
		
		//Set myCat's attributes to middle
		myCat.increaseHealth(-50);
		myCat.increaseMischievousness(50);
		myCat.increaseHappiness(-50);
		myCat.increaseHunger(50);
		myCat.increasePercentBladderFull(50);
		myCat.increaseFatigue(50);
		

		//Set myMaxCat's attributes to maximum extremes
		myMaxCat.increaseMischievousness(100);
		myMaxCat.increaseHappiness(100);
		myMaxCat.increaseFatigue(100);
		myMaxCat.increaseHunger(100);
		myMaxCat.increasePercentBladderFull(100);
		myMaxCat.increaseHealth(100);

		//Set myMinCat's attributes to minimum extremes
		myMinCat.increaseMischievousness(-100);
		myMinCat.increaseHappiness(-100);
		myMinCat.increaseFatigue(-100);
		myMinCat.increaseHunger(-100);
		myMinCat.increasePercentBladderFull(-100);
		myMinCat.increaseHealth(-100);
	}

	@Test
	public void testCat() {
		assertEquals(myCat.getSpecies(), "cat");
		
		//TODO add assertTrue(myMaxCat.getHappiness()==100); etc.
	}

	@Test
	public void testPlay() { 
		//get initial values
		int initialDurability = myToy.getDurability();
		int initialHappiness = myCat.getHappiness();
		int initialFatigue = myCat.getFatigue();
		int initialMischievousness = myCat.getMischievousness();
		int initialHunger = myCat.getHunger();

		//play with the toy
		myCat.play(myToy);
		myMaxCat.play(myToy);
		myMinCat.play(myToy);
		
		/*
		 * Their happiness will increase or decrease.
		 * Their fatigue will increase.
		 * Their mischievousness will decrease or increase.
		 * Their hunger will increase.
		 * Also, the toy's durability will decrease.
		 */

		//check new values are as expected

		//check new happiness is higher
		assertTrue(myCat.getHappiness() > initialHappiness);
		//check for no change to maxed out happiness
		assertTrue(myMaxCat.getHappiness() == 100);

		//check new fatigue is higher
		assertTrue(myCat.getFatigue() > initialFatigue);
		//check for no change to maxed out fatigue
		assertTrue(myMaxCat.getFatigue() == 100);

		//check new mischievousness is lower
		assertTrue(myCat.getMischievousness() < initialMischievousness);
		//check for no change in minimum case
		assertTrue(myMinCat.getMischievousness() == 0);

		//check new hunger is higher
		assertTrue(myCat.getHunger() > initialHunger);
		//Check for no change in maxed out case
		assertTrue(myMaxCat.getHunger() == 100);
		
		//check new durability is lower
		assertTrue(myToy.getDurability() < initialDurability);
		
		//test worn out toy
		try {
			myCat.play(myToy);
			fail("Lets pets play with worn out toys.");
		} catch (IllegalArgumentException e){}
		
		
	}

	@Test
	public void testPlayBad() { 
		//get initial values
		int initialDurability = myBadToy.getDurability();
		int initialHappiness = myCat.getHappiness();
		int initialFatigue = myCat.getFatigue();
		int initialMischievousness = myCat.getMischievousness();
		int initialHunger = myCat.getHunger();

		//play with the bad toy
		myCat.play(myBadToy);
		myMaxCat.play(myBadToy);
		myMinCat.play(myBadToy);
		
		/*
		 * Their happiness will increase or decrease.
		 * Their fatigue will increase.
		 * Their mischievousness will decrease or increase.
		 * Their hunger will increase.
		 * Also, the toy's durability will decrease.
		 */

		//check new values are as expected

		//check new happiness is lower
		assertTrue(myCat.getHappiness() < initialHappiness);
		//check for no change to minimum happiness
		assertTrue(myMinCat.getHappiness() == 0);

		//check new fatigue is higher
		assertTrue(myCat.getFatigue() > initialFatigue);
		//check for no change to maxed out fatigue
		assertTrue(myMaxCat.getFatigue() == 100);

		//check new mischievousness is higher
		assertTrue(myCat.getMischievousness() > initialMischievousness);
		//check for no change to maxed out mischievousness
		assertTrue(myMaxCat.getMischievousness() == 100);

		//check new hunger is higher
		assertTrue(myCat.getHunger() > initialHunger);
		//Check for no change in maxed out case
		assertTrue(myMaxCat.getHunger() == 100);
		
		//check new durability is lower
		assertTrue(myBadToy.getDurability() < initialDurability);
	}
	
	//TODO finish all tests below here

	@Test
	public void testSleep() {
		int initialFatigue = myMaxCat.getFatigue();
		int maxInitialFatigue = myMinCat.getFatigue();

		myMaxCat.sleep();
		myMinCat.sleep();

		//check if initial fatigue is higher than new fatigue 
		assertTrue(initialFatigue > myMaxCat.getFatigue());
		//check for no change in minimum fatigue case
		assertTrue(maxInitialFatigue == 0 && myMinCat.getFatigue() == 0);
	}

	@Test
	public void testGoToilet() {
		int initialPercentBladderFull = myCat.getPercentBladderFull();
		int maxInitialPercentBladderFull = myMinCat.getPercentBladderFull();
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
