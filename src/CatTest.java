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
	private Toy myToy; 
	
	@Before
	public void setUp() throws Exception{
		myCat = new Cat();
		
		//initialise myToy
		myToy = new Toy("scratching post", "", 1, 2);
		//add values for happiness increase to myToy
		String[] species = {myCat.getSpecies()};
		int[] values = {25};
		myToy.setHappinessIncrease(species, values); 
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
		
		//play with the toy
		myCat.play(myToy);
		
		//check new values are as expected
		assertTrue(initialDurability  >myToy.getDurability());
		
		//check if initial happiness is lower than new happines or they are both maxed out (100)
		assertTrue(initialHappiness < myCat.getHappiness() || (initialHappiness == 100 && myCat.getHappiness() == 100));
		
		//check if initial fatigue is lower than new fatigue or they are both 100
		assertTrue(initialFatigue<myCat.getFatigue() || (initialFatigue == 100 && myCat.getFatigue() == 100));
		
		//check if initial mischeviousness is lower than new mischeviousness or they are both 0
		assertTrue(initialMischievousness>myCat.getMischievousness() || (initialMischievousness == 0 && myCat.getMischievousness() == 0));
		
		//check if animal is hungrier than before or that hunger was initially maxed at 100 
		assertTrue(initialHunger<myCat.getHunger() || (initialHunger == 100 && myCat.getHunger() == 100) );
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
