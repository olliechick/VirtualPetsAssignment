import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GameEnvironmentTest {
	
	private GameEnvironment myGame;
	private Food referenceFood;
	private Toy referenceToy;
	private String[] species = {"cat", "dog", "goat", "horse", "alpaca", "polar bear"};

	@Before
	public void setUp() throws Exception {
		myGame = new GameEnvironment();
		referenceFood = new Food("cat biscuits", "", 5, 2);
		referenceToy = new Toy("old shoe", "", 1, 4);
		
		String[] healthIncrease = {"20", "2", "-2", "-2", "-2", "1"}; //in species order
		Integer[] happinessIncrease = {1, 40, 5, 0, 0, 1};
		
		referenceFood.setHealthIncrease(species, healthIncrease);
		referenceToy.setHappinessIncrease(species, happinessIncrease);
	}

	@Test
	public void testGenerateFoodPrototypes() {
		myGame.generateFoodPrototypes();
		Food testFood = myGame.getFoodPrototypes().get("cat biscuits");
		assertEquals(testFood.getName(), referenceFood.getName());
		assertEquals(testFood.getDescription(), referenceFood.getDescription());
		assertEquals(testFood.getPrice(), referenceFood.getPrice());
		assertEquals(testFood.getPortionSize(), referenceFood.getPortionSize());
		
		for(String currentSpecies: species){
			if (testFood.getHealthIncrease(currentSpecies) != referenceFood.getHealthIncrease(currentSpecies)){
				fail("healthIncrease not the same for :" + currentSpecies + " <" + testFood.getHealthIncrease(currentSpecies) + "> vs <" + referenceFood.getHealthIncrease(currentSpecies) + ">");
			}
		}
	}

	@Test
	public void testGenerateToyPrototypes() {
		myGame.generateToyPrototypes();
		Toy testToy = myGame.getToyPrototypes().get("old shoe");
		assertEquals(testToy.getName(), referenceToy.getName());
		assertEquals(testToy.getDescription(), referenceToy.getDescription());
		assertEquals(testToy.getPrice(), referenceToy.getPrice());
		assertEquals(testToy.getDurability(), referenceToy.getDurability());
		
		for(String currentSpecies: species){
			referenceToy.getHappinessIncrease(currentSpecies);
			testToy.getHappinessIncrease(currentSpecies);
			if (testToy.getHappinessIncrease(currentSpecies) != referenceToy.getHappinessIncrease(currentSpecies)){
				fail("happinessIncrease not the same for :" + currentSpecies + " <" + testToy.getHappinessIncrease(currentSpecies) + "> vs <" + referenceToy.getHappinessIncrease(currentSpecies) + ">");
			}
		}
	}

}
