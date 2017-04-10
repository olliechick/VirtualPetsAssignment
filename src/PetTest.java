import static org.junit.Assert.*;
import org.junit.Test;

public class PetTest {
	
	@Test
	public void testGetName() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetGender() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSpecies() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMischievousness() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetHappiness() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetHunger() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPercentBladderFull() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetFatigue() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPlayfulness() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetWeight() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetIsSick() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetIsRevivable() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetIsMisbehaving() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetName() {
		Pet myPet = new Pet("Dragon");
		Pet myPet2 = new Pet("Dog");
		
		String name = "Fred";
		String name2 = null;
		String name3 = "George";
		
		myPet.setName(name);
		assertEquals(myPet.getName(), name);
		
		myPet.setName(name2);
		assertEquals(myPet.getName(), name2);
		
		myPet2.setName(name3);
		assertEquals(myPet2.getName(), name3);
	}

	@Test
	public void testSetGender() {
		Pet myPet = new Pet("Dragon");

		String gender = "male";
		String gender2 = "female";
		String gender3 = "table";

		myPet.setGender(gender);
		assertEquals(myPet.getGender(), gender);
		myPet.setGender(gender2);
		assertEquals(myPet.getGender(), gender2);
		
		try {
			myPet.setGender(gender3);
		    fail( "Accepts invalid gender" );
		} catch (IllegalArgumentException e){}
		
		assertEquals(myPet.getGender(), gender2);
	}

	@Test
	public void testSetIsSick() {
		Pet myPet = new Pet("Dragon");
		Boolean isSick = false;
		myPet.setIsSick(isSick);
		assertEquals(myPet.getIsSick(), isSick);
	}

	@Test
	public void testSetIsRevivable() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetIsMisbehaving() {
		fail("Not yet implemented");
	}

	@Test
	public void testIncreaseValue() {
		fail("Not yet implemented");
	}

	@Test
	public void testIncreaseMischievousness() {
		fail("Not yet implemented");
	}

	@Test
	public void testIncreaseHappiness() {
		fail("Not yet implemented");
	}

	@Test
	public void testIncreaseHunger() {
		fail("Not yet implemented");
	}

	@Test
	public void testIncreasePercentBladderFull() {
		fail("Not yet implemented");
	}

	@Test
	public void testIncreaseFatigue() {
		fail("Not yet implemented");
	}

	@Test
	public void testIncreasePlayfulness() {
		fail("Not yet implemented");
	}

	@Test
	public void testIncreaseWeight() {
		fail("Not yet implemented");
	}

}
