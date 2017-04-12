import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class PetTest {

	String name = "Fred";
	String name2 = "Harry";
	String gender = "male";
	String gender2 = "female";
	String gender3 = "table";
	String species = "cat";
	String species2 = "dog";
	String species3 = "dragon";
	String species4 = "Cat";
	
	Pet myPet;
	Pet myPet2;
	
	double delta = 1e-6; //error for doubles, used in assertEquals() for methods involving weight

	@Before
	public void setUp() throws IOException { 
		myPet = new Pet(species);
		myPet2 = new Pet(species2);
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

		try {
			@SuppressWarnings("unused")
			Pet myPet3 = new Pet(species3);
			fail("Accepts bad species.");
		} catch (IllegalArgumentException e){}

		try {
			@SuppressWarnings("unused")
			Pet myPet3 = new Pet(species4);
			fail("Accepts bad species.");
		} catch (IllegalArgumentException e){}
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
	}

}
