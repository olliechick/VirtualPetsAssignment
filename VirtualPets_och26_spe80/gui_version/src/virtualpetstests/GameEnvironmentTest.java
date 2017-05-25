package virtualpetstests;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import virtualpets.Alpaca;
import virtualpets.Cat;
import virtualpets.Dog;
import virtualpets.Food;
import virtualpets.GameEnvironment;
import virtualpets.Player;
import virtualpets.Toy;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Test for GameEnvironment implentation
 * @author Samuel Pell
 *
 */
public class GameEnvironmentTest {

	private GameEnvironment myGame;
	private Food referenceFood;
	private Toy referenceToy;
	private String[] species = {"cat", "dog", "goat", "horse", "alpaca", "polar bear"};
	Player player1;
	Player player2;
	Player player3;

	@Before
	public void setUp() throws Exception {
		myGame = new GameEnvironment();
		referenceFood = new Food("Cat biscuits", "dry biscuits any cat will love", 5, 2);
		referenceToy = new Toy("Old shoe", "1 previous owner", 1, 4);

		String[] healthIncrease = {"20", "2", "-2", "-2", "-2", "1"}; //in species order
		String[] happinessIncrease = {"1", "40", "5", "0", "0", "1"};

		referenceFood.setHealthIncrease(species, healthIncrease);
		referenceToy.setHappinessIncrease(species, happinessIncrease);

		player1 = new Player();
		player2 = new Player();
		player3 = new Player();

		player1.setName("Harry");
		player2.setName("Rex");
		player3.setName("Reginald Fitzallen");
	}

	@Test
	public void testGenerateFoodPrototypes() {
		myGame.generateFoodPrototypes();
		Food testFood = myGame.getFoodPrototypes().get("Cat biscuits");
		assertEquals(testFood.getName(), referenceFood.getName());
		assertEquals(testFood.getDescription(), referenceFood.getDescription());
		assertEquals(testFood.getPrice(), referenceFood.getPrice());
		assertEquals(testFood.getPortionSize(), referenceFood.getPortionSize());

		for (String currentSpecies: species) {
			if (testFood.getHealthIncrease(currentSpecies) != referenceFood.getHealthIncrease(currentSpecies)) {
				fail("healthIncrease not the same for :" + currentSpecies + " <" + testFood.getHealthIncrease(currentSpecies) + "> vs <" + referenceFood.getHealthIncrease(currentSpecies) + ">");
			}
		}
	}

	@Test
	public void testGenerateToyPrototypes() {
		myGame.generateToyPrototypes();
		Toy testToy = myGame.getToyPrototypes().get("Old shoe");
		assertEquals(testToy.getName(), referenceToy.getName());
		assertEquals(testToy.getDescription(), referenceToy.getDescription());
		assertEquals(testToy.getPrice(), referenceToy.getPrice());
		assertEquals(testToy.getDurability(), referenceToy.getDurability());

		for (String currentSpecies: species) {
			referenceToy.getHappinessIncrease(currentSpecies);
			testToy.getHappinessIncrease(currentSpecies);
			if (testToy.getHappinessIncrease(currentSpecies) != referenceToy.getHappinessIncrease(currentSpecies)) {
				fail("happinessIncrease not the same for :" + currentSpecies + " <" + testToy.getHappinessIncrease(currentSpecies) + "> vs <" + referenceToy.getHappinessIncrease(currentSpecies) + ">");
			}
		}
	}

	@Test
	public void testRankPlayers() throws IOException {

		player1.getPetList().add(new Cat());

		Alpaca testPet = new Alpaca();

		testPet.increaseFatigue(50);
		testPet.increaseHappiness(-50);
		testPet.beSick();
		testPet.increaseHunger(100);
		testPet.increaseFatigue(50);
		testPet.misbehave();

		player2.getPetList().add(testPet);

		Dog testPet2 = new Dog();
		testPet2.misbehave();

		player3.getPetList().add(testPet2);

		player1.calculateScore();
		player2.calculateScore();
		player3.calculateScore();

		ArrayList<Player> playerList = new ArrayList<Player>();
		playerList.add(player1);
		playerList.add(player2);
		playerList.add(player3);
		myGame.addPlayers(playerList);

		Player[] rankedArray = myGame.rankPlayers();
		Player[] expectedRanking = new Player[] {player1, player3, player2};

		for (int i = 0; i < rankedArray.length; i++) {
			assertEquals(rankedArray[i], expectedRanking[i]);
		}

		playerList.remove(0);
		myGame.addPlayers(playerList);

		rankedArray = myGame.rankPlayers();
		expectedRanking = new Player[] {player3, player2};

		for (int i = 0; i < rankedArray.length; i++) {
			assertEquals(rankedArray[i], expectedRanking[i]);
		}

		playerList.remove(0);
		myGame.addPlayers(playerList);

		rankedArray = myGame.rankPlayers();
		expectedRanking = new Player[] {player3};

		for (int i = 0; i < rankedArray.length; i++) {
			assertEquals(rankedArray[i], expectedRanking[i]);
		}
	}

}
