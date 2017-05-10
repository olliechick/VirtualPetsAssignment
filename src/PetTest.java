import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests for Pet class implementation
 * @author Ollie Chick
 * @author Samuel Pell
 */
public class PetTest {

	String name = "Fred";
	String name2 = "Harry";
	String gender = "male";
	String gender2 = "female";
	String gender3 = "table";
	String species = "cat";
	String species2 = "horse";
	String species3 = "dragon";
	String species4 = "Cat";
	double delta = 1e-6; //error for doubles, used in assertEquals() for methods involving weight
	
	Pet myPet;
	Pet myPet2;
	Cat myCat;
	Cat myMinCat;
	Cat myMaxCat;
	Toy myToy; 
	Toy myBadToy; 
	Food myFood;
	Food myBadFood;

	@Before
	public void setUp() throws IOException { 
		myPet = new Pet(species);
		myPet2 = new Pet(species2);
		myCat = new Cat();
		myMaxCat = new Cat();
		myMinCat = new Cat();
		
		
		String[] species = {myCat.getSpecies()};
		String[] values = {"20"};
		String[] badValues = {"-20"};
		
		delta = 1e-6; //error for doubles, used in assertEquals() for methods involving weight

		//initialise toys and foods
		myToy = new Toy("Ball", "", 1, 3); //name, description, price, durability
		myBadToy = new Toy("Roadspikes", "", 1, 3);
		myFood = new Food("General petfood", "", 1, 3); //name, description, price, portionSize
		myBadFood = new Food("Poison", "", 1, 3);

		//add values for happiness increase to toys
		myToy.setHappinessIncrease(species, values);
		myBadToy.setHappinessIncrease(species, badValues);
		//add values for health increase to foods
		myFood.setHealthIncrease(species, values);
		myBadFood.setHealthIncrease(species, badValues);
		
		//Set myCat's attributes to mid-values (50)
		myCat.increaseHealth(-50);
		myCat.increaseMischievousness(50);
		myCat.increaseHappiness(-50);
		myCat.increaseHunger(50);
		myCat.increasePercentBladderFull(50);
		myCat.increaseFatigue(50);
		myCat.increaseWeight(1); //so there is a change when it returns to defaultWeight

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
	public void testPet() throws IOException {
		assertEquals(myPet.getIsSick(), false);
		assertEquals(myPet.getIsRevivable(), true);
		assertEquals(myPet.getIsMisbehaving(), false);
		assertEquals(myPet.getHealth(), 100);
		assertEquals(myPet.getMischievousness(), 0);
		assertEquals(myPet.getHappiness(), 100);
		assertEquals(myPet.getHunger(), 0);
		assertEquals(myPet.getPercentBladderFull(), 0);
		assertEquals(myPet.getFatigue(), 0);
		assertEquals(myPet.getWeight(),4,delta);
		assertEquals(myPet2.getWeight(),500,delta);

		try {
			@SuppressWarnings("unused")
			Pet myPet3 = new Pet(species3);
			fail("Accepts bad species.");
		} catch (IllegalArgumentException e){
			assertEquals(e.getMessage(), "Unknown species: "+species3);
		}

		try {
			@SuppressWarnings("unused")
			Pet myPet3 = new Pet(species4);
			fail("Accepts bad species.");
		} catch (IllegalArgumentException e){}
		
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
		
	}

	@Test
	public void testGetName() {
		myPet.setName(name);
		assertEquals(myPet.getName(), name);
	}

	@Test
	public void testGetGender() {
		myPet.setGender(gender);
		assertEquals(myPet.getGender(), gender);
	}

	@Test
	public void testGetSpecies() {
		assertEquals(myPet.getSpecies(), species);
	}

	@Test
	public void testGetMischievousness() {
		assertEquals(myPet.getMischievousness(), 0);
	}

	@Test
	public void testGetHappiness() {
		assertEquals(myPet.getHappiness(), 100);
	}

	@Test
	public void testGetHunger() {
		assertEquals(myPet.getHunger(), 0);
	}

	@Test
	public void testGetPercentBladderFull() {
		assertEquals(myPet.getPercentBladderFull(), 0);
	}

	@Test
	public void testGetFatigue() {
		assertEquals(myPet.getFatigue(), 0);
	}

	@Test
	public void testGetWeight() {
		assertEquals(myPet.getWeight(), 4, delta);
	}

	@Test
	public void testGetIsSick() {
		assertEquals(myPet.getIsSick(), false);
	}

	@Test
	public void testGetIsRevivable() {
		assertEquals(myPet.getIsRevivable(), true);
	}

	@Test
	public void testGetIsMisbehaving() {
		assertEquals(myPet.getIsMisbehaving(), false);
	}

	@Test
	public void testSetName() {

		myPet.setName(name);
		assertEquals(myPet.getName(), name);

		try {
			myPet.setName(null);
			fail("Accepts null name.");
		} catch (IllegalArgumentException e){
			assertEquals(myPet.getName(), name);
		}
	}

	@Test
	public void testSetGender() {

		myPet.setGender(gender);
		assertEquals(myPet.getGender(), gender);

		myPet.setGender(gender2);
		assertEquals(myPet.getGender(), gender2);

		try {
			myPet.setGender(gender3);
			fail("Accepts invalid gender.");
		} catch (IllegalArgumentException e){
			assertEquals(myPet.getGender(), gender2);
		}
	}

	@Test
	public void testSetIsSick() {	

		myPet.setIsSick(true);
		assertEquals(myPet.getIsSick(), true);

		myPet.setIsSick(false);
		assertEquals(myPet.getIsSick(), false);
	}

	@Test
	public void testSetIsRevivable() {

		myPet.setIsRevivable(true);
		assertEquals(myPet.getIsRevivable(), true);

		myPet.setIsRevivable(false);
		assertEquals(myPet.getIsRevivable(), false);
	}

	@Test
	public void testSetIsMisbehaving() {

		myPet.setIsMisbehaving(true);
		assertEquals(myPet.getIsMisbehaving(), true);

		myPet.setIsMisbehaving(false);
		assertEquals(myPet.getIsMisbehaving(), false);	
	}

	@Test
	public void testIncreaseHealth() {
		myPet.increaseHealth(-500);
		assertEquals(myPet.getHealth(), 0);
		myPet.increaseHealth(5);
		assertEquals(myPet.getHealth(), 5);
		myPet.increaseHealth(-10);
		assertEquals(myPet.getHealth(), 0);
		myPet.increaseHealth(2);
		assertEquals(myPet.getHealth(), 2);
		myPet.increaseHealth(20000);
		assertEquals(myPet.getHealth(), 100);
		myPet.increaseHealth(250);
		assertEquals(myPet.getHealth(), 100);
		myPet.increaseHealth(-2);
		assertEquals(myPet.getHealth(), 98);
		myPet.increaseHealth(-500);
		assertEquals(myPet.getHealth(), 0);
		myPet.increaseHealth(-20);
		assertEquals(myPet.getHealth(), 0);
	}

	@Test
	public void testIncreaseMischievousness() {
		myPet.increaseMischievousness(5);
		assertEquals(myPet.getMischievousness(), 5);
		myPet.increaseMischievousness(-10);
		assertEquals(myPet.getMischievousness(), 0);
		myPet.increaseMischievousness(2);
		assertEquals(myPet.getMischievousness(), 2);
		myPet.increaseMischievousness(20000);
		assertEquals(myPet.getMischievousness(), 100);
		myPet.increaseMischievousness(250);
		assertEquals(myPet.getMischievousness(), 100);
		myPet.increaseMischievousness(-2);
		assertEquals(myPet.getMischievousness(), 98);
		myPet.increaseMischievousness(-500);
		assertEquals(myPet.getMischievousness(), 0);
		myPet.increaseMischievousness(-20);
		assertEquals(myPet.getMischievousness(), 0);
	}

	@Test
	public void testIncreaseHappiness() {
		myPet.increaseHappiness(-500);
		assertEquals(myPet.getHappiness(), 0);
		myPet.increaseHappiness(5);
		assertEquals(myPet.getHappiness(), 5);
		myPet.increaseHappiness(-10);
		assertEquals(myPet.getHappiness(), 0);
		myPet.increaseHappiness(2);
		assertEquals(myPet.getHappiness(), 2);
		myPet.increaseHappiness(20000);
		assertEquals(myPet.getHappiness(), 100);
		myPet.increaseHappiness(250);
		assertEquals(myPet.getHappiness(), 100);
		myPet.increaseHappiness(-2);
		assertEquals(myPet.getHappiness(), 98);
		myPet.increaseHappiness(-500);
		assertEquals(myPet.getHappiness(), 0);
		myPet.increaseHappiness(-20);
		assertEquals(myPet.getHappiness(), 0);
	}

	@Test
	public void testIncreaseHunger() {
		myPet.increaseHunger(5);
		assertEquals(myPet.getHunger(), 5);
		myPet.increaseHunger(-10);
		assertEquals(myPet.getHunger(), 0);
		myPet.increaseHunger(2);
		assertEquals(myPet.getHunger(), 2);
		myPet.increaseHunger(20000);
		assertEquals(myPet.getHunger(), 100);
		myPet.increaseHunger(250);
		assertEquals(myPet.getHunger(), 100);
		myPet.increaseHunger(-2);
		assertEquals(myPet.getHunger(), 98);
		myPet.increaseHunger(-500);
		assertEquals(myPet.getHunger(), 0);
		myPet.increaseHunger(-20);
		assertEquals(myPet.getHunger(), 0);
	}

	@Test
	public void testIncreasePercentBladderFull() {
		myPet.increasePercentBladderFull(5);
		assertEquals(myPet.getPercentBladderFull(), 5);
		myPet.increasePercentBladderFull(-10);
		assertEquals(myPet.getPercentBladderFull(), 0);
		myPet.increasePercentBladderFull(2);
		assertEquals(myPet.getPercentBladderFull(), 2);
		myPet.increasePercentBladderFull(20000);
		assertEquals(myPet.getPercentBladderFull(), 100);
		myPet.increasePercentBladderFull(250);
		assertEquals(myPet.getPercentBladderFull(), 100);
		myPet.increasePercentBladderFull(-2);
		assertEquals(myPet.getPercentBladderFull(), 98);
		myPet.increasePercentBladderFull(-500);
		assertEquals(myPet.getPercentBladderFull(), 0);
		myPet.increasePercentBladderFull(-20);
		assertEquals(myPet.getPercentBladderFull(), 0);
	}

	@Test
	public void testIncreaseFatigue() {
		myPet.increaseFatigue(5);
		assertEquals(myPet.getFatigue(), 5);
		myPet.increaseFatigue(-10);
		assertEquals(myPet.getFatigue(), 0);
		myPet.increaseFatigue(2);
		assertEquals(myPet.getFatigue(), 2);
		myPet.increaseFatigue(20000);
		assertEquals(myPet.getFatigue(), 100);
		myPet.increaseFatigue(250);
		assertEquals(myPet.getFatigue(), 100);
		myPet.increaseFatigue(-2);
		assertEquals(myPet.getFatigue(), 98);
		myPet.increaseFatigue(-500);
		assertEquals(myPet.getFatigue(), 0);
		myPet.increaseFatigue(-20);
		assertEquals(myPet.getFatigue(), 0);
	}

	@Test
	public void testIncreaseWeight() {
		myPet.increaseWeight(0);
		assertEquals(myPet.getWeight(), 4, delta);
		myPet.increaseWeight(0.5);
		assertEquals(myPet.getWeight(), 4.5, delta);
		myPet.increaseWeight(-1.5);
		assertEquals(myPet.getWeight(), 3, delta);
		myPet.increaseWeight(5.01);
		assertEquals(myPet.getWeight(), 8.01, delta);
		try {
			myPet.increaseWeight(-10);
			fail("Accepts negative weight.");
		} catch (IllegalArgumentException e){}
		myPet.increaseWeight(5000);
		assertEquals(myPet.getWeight(), 5008.01, delta);
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
		myMinCat.misbehave();
		
		//check new happiness is lower
		assertTrue(myCat.getHappiness() < initialHappiness);
		//check for no change in min case
		assertEquals(myMinCat.getHappiness(), 0);
		//check cat is now misbehaving
		assertTrue(myCat.getIsMisbehaving());
	}
	
	@Test
	public void testDiscipline() {
		//get initial values
		int initialHappiness = myCat.getHappiness();
		
		myCat.discipline();

		//check new happiness is lower
		assertTrue(myCat.getHappiness() < initialHappiness);
		//check for no change in min case
		assertEquals(myMinCat.getHappiness(), 0);
		//check new mischievousness is 0
		assertTrue(myMinCat.getMischievousness() == 0);
		//check cat is no longer misbehaving
		assertFalse(myCat.getIsMisbehaving());
	}

	@Test
	public void testBeSick() {
		//get initial values
		int initialHappiness = myCat.getHappiness();
		
		myCat.beSick();
		myMinCat.beSick();

		//check new happiness is lower
		assertTrue(myCat.getHappiness() < initialHappiness);
		//check for no change in min case
		assertEquals(myMinCat.getHappiness(), 0);
		//check cat is now sick
		assertTrue(myCat.getIsSick());
		
	}
	
	@Test
	public void testTreat(){
		//get initial values
		int initialHunger = myCat.getHunger();
		int initialFatigue = myMaxCat.getFatigue();
		int initialMischievousness = myCat.getMischievousness();
		
		myCat.treat();
		myMinCat.treat();
		myMaxCat.treat();

		//check new hunger is lower
		assertTrue(myCat.getHunger() < initialHunger);
		//check for no change in min case
		assertEquals(myMinCat.getHunger(), 0);
		//check new fatigue is higher
		assertTrue(myCat.getFatigue() > initialFatigue);
		//check for no change in max case
		assertEquals(myMaxCat.getFatigue(), 100);
		//check new mischievousness is lower
		assertTrue(myCat.getMischievousness() < initialMischievousness);
		//check for no change in min case
		assertEquals(myMinCat.getMischievousness(), 0);
	}
	
	@Test
	public void testRevive(){
		myCat.revive();
		assertFalse(myCat.getIsRevivable());
	}

	@Test
	public void testDie() {
		myCat.die();
		assertTrue(myCat.getIsDead());
	}

}
