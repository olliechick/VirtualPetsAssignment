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
	private double delta;

	@Before
	public void setUp() throws Exception{		

	}

	@Test
	public void testCat() {
		assertEquals(myCat.getSpecies(), "cat");
		
		
		Pet[] petArray = new Pet[]{myCat, myMinCat};
		Pet pet = petArray[0];		

		assertEquals(myCat.getHealth(), 50);
		assertEquals(myCat.getMischievousness(), 50);
		assertEquals(myCat.getHappiness(), 50);
		assertEquals(myCat.getHunger(), 50);
		assertEquals(myCat.getPercentBladderFull(), 50);
		assertEquals(myCat.getFatigue(), 50);
		assertEquals(myCat.getWeight(), 4+1, delta);

		assertEquals(myMaxCat.getHealth(), 100);
		assertEquals(myMaxCat.getMischievousness(), 100);
		assertEquals(myMaxCat.getHappiness(), 100);
		assertEquals(myMaxCat.getHunger(), 100);
		assertEquals(myMaxCat.getPercentBladderFull(), 100);
		assertEquals(myMaxCat.getFatigue(), 100);

		assertEquals(myMinCat.getHealth(), 0);
		assertEquals(myMinCat.getMischievousness(), 0);
		assertEquals(myMinCat.getHappiness(), 0);
		assertEquals(myMinCat.getHunger(), 0);
		assertEquals(myMinCat.getPercentBladderFull(), 0);
		assertEquals(myMinCat.getFatigue(), 0);
		

		pet.misbehave();
		assertEquals(pet.getHappiness(), 40);
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
		try{
			myMinCat.play(myToy);
			fail("Does nothing if you play with a toy until it is destroyed.");
		} catch (IllegalArgumentException exception){
			assertEquals(exception.getMessage(), "durability is zero or negative");
		}
		
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
		try{
			myMinCat.play(myBadToy);
			fail("Does nothing if you play with a toy until it is destroyed.");
		} catch (IllegalArgumentException exception){
			assertEquals(exception.getMessage(), "durability is zero or negative");
		}
		
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

	@Test
	public void testSleep() {
		int initialFatigue = myMaxCat.getFatigue();

		myCat.sleep();
		myMaxCat.sleep();
		myMinCat.sleep();

		//check new fatigue is lower
		assertTrue(myCat.getFatigue() < initialFatigue);
		//check for no change in minimum fatigue case
		assertEquals(myMinCat.getFatigue(), 0);
	}

	@Test
	public void testGoToilet() {
		double initialWeight = myCat.getWeight();
		double initialMinWeight = myMinCat.getWeight();
		
		myCat.goToilet();
		myMaxCat.goToilet();
		myMinCat.goToilet();
		
		//check bladder is now empty
		assertEquals(myCat.getPercentBladderFull(), 0);
		//check for no change in already empty bladder
		assertEquals(myCat.getPercentBladderFull(), 0);
		
		//check new weight is lower
		assertTrue(myCat.getWeight() < initialWeight);
		//check no change for a normal weight cat
		assertEquals(myMinCat.getWeight(), initialMinWeight, delta);
	}

	@Test
	public void testFeed() {
		//get initial values
		int initialHunger = myCat.getHunger();
		double initialWeight = myCat.getWeight();
		int initialPercentBladderFull = myCat.getPercentBladderFull();
		int initialHappiness = myCat.getHappiness();
		int initialHealth = myCat.getHealth();

		//eat the food
		myCat.feed(myFood);
		myMaxCat.feed(myFood);
		myMinCat.feed(myFood);
		
		//check new hunger is lower
		assertTrue(myCat.getHunger() < initialHunger);
		//check for no change in minimum hunger case
		assertEquals(myMinCat.getHunger(), 0);
		
		//check new weight is higher
		assertTrue(myCat.getWeight() > initialWeight);

		//check new percent bladder full is higher
		assertTrue(myCat.getPercentBladderFull() > initialPercentBladderFull);
		//check for no change in max case
		assertEquals(myMaxCat.getPercentBladderFull(), 100);

		//check new happiness is higher
		assertTrue(myCat.getHappiness() > initialHappiness);
		//check for no change in max case
		assertEquals(myMaxCat.getHappiness(), 100);

		//check new health is higher
		assertTrue(myCat.getHealth() > initialHealth);
		//check for no change in max case
		assertEquals(myMaxCat.getHealth(), 100);
		
	}

	@Test
	public void testFeedBad() {
		//get initial values
		int initialHunger = myCat.getHunger();
		double initialWeight = myCat.getWeight();
		int initialPercentBladderFull = myCat.getPercentBladderFull();
		int initialHappiness = myCat.getHappiness();
		int initialHealth = myCat.getHealth();

		//eat the food
		myCat.feed(myBadFood);
		myMaxCat.feed(myBadFood);
		myMinCat.feed(myBadFood);
		
		//check new hunger is lower
		assertTrue(myCat.getHunger() < initialHunger);
		//check for no change in minimum hunger case
		assertEquals(myMinCat.getHunger(), 0);
		
		//check new weight is higher
		assertTrue(myCat.getWeight() > initialWeight);

		//check new percent bladder full is higher
		assertTrue(myCat.getPercentBladderFull() > initialPercentBladderFull);
		//check for no change in max case
		assertEquals(myMaxCat.getPercentBladderFull(), 100);

		//check new happiness is lower
		assertTrue(myCat.getHappiness() < initialHappiness);
		//check for no change in min case
		assertEquals(myMinCat.getHappiness(), 0);

		//check new health is lower
		assertTrue(myCat.getHealth() < initialHealth);
		//check for no change in min case
		assertEquals(myMinCat.getHealth(), 0);
		
	}

	@Test
	public void testMisbehave() {
		//get initial values
		int initialHappiness = myCat.getHappiness();
		
		myCat.misbehave();
		
		//check new happiness is lower
		assertTrue(myCat.getHappiness() < initialHappiness);
		//check for no change in min case
		assertEquals(myMinCat.getHappiness(), 0);
	}

	@Test
	public void testBeSick() {
		myCat.beSick();
		//nothing to test
	}

	@Test
	public void testDie() {
		myCat.die();
		assertFalse(myCat.getIsRevivable());
	}

}
