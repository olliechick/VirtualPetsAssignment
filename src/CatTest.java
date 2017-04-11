import static org.junit.Assert.*;

import org.junit.Test;

public class CatTest {
	Cat myCat = new Cat();
	
	Toy myToy = new Toy("scratching post", " ", 1, 2);

	@Test
	public void testCat() {
		assertEquals(myCat.getSpecies(), "cat");
	}

	@Test
	public void testPlay() {
		int initialDurability = myToy.getDurability();
		int initialHappiness = myCat.getHappiness();
		int initialFatigue = myCat.getFatigue();
		int initialMischievousness = myCat.getMischievousness();
		int initialHunger = myCat.getHunger();
		myCat.play(myToy);
		assertTrue(initialDurability>myToy.getDurability());
		assertTrue(initialHappiness<myCat.getHappiness() || (initialHappiness == 100 && myCat.getHappiness() == 100) );
		assertTrue(initialFatigue<myCat.getFatigue() || (initialFatigue == 100 && myCat.getFatigue() == 100) );
		assertTrue(initialMischievousness>myCat.getMischievousness() || (initialMischievousness == 0 && myCat.getMischievousness() == 0) );
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
