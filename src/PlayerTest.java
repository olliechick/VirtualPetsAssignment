import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Test;

/**
 * Tests for Player class implementation
 * @author Ollie Chick
 * @author Samuel Pell
 */
public class PlayerTest {
	String name1 = "Harry";
	String name2 = "Rex";
	Player player1 = new Player();
	Player player2 = new Player();
	Player player3 = new Player();
	
	@Test
	public void testPlayer() {
		assert(player1.getBalance()>0);
		assertTrue(player1.getPetList().isEmpty());
		assertTrue(player1.getFoodStock().isEmpty());
		assertTrue(player1.getToyList().isEmpty());

	}

	@Test
	public void testSetName() {
		player1.setName(name1);
		try{
			player2.setName(null);
			fail("Allows null names.");
		}catch(IllegalArgumentException e){}
	}

	@Test
	public void testSpend() {
		int currentBalance = player1.getBalance();
		player1.spend(currentBalance-1);
		assertEquals(player1.getBalance(), 1);
		
		try{
			player1.spend(-1);
			fail("Allows negative spending");
		}catch(IllegalArgumentException e){}
		assertEquals(player1.getBalance(), 1);
		
		try{
			player1.spend(currentBalance+1);
			fail("Allows overspending");
		}catch(IllegalArgumentException e){}
		assertEquals(player1.getBalance(), 1);
		
	}

	@Test
	public void testEarn() {
		int currentBalance = player1.getBalance();
		player1.earn(10);
		assertEquals(player1.getBalance(), currentBalance+10);
		
		try{
			player1.earn(-1);
			fail("Allows negative earning");
		}catch(IllegalArgumentException e){}
		assertEquals(player1.getBalance(), currentBalance+10);
	}

	@Test
	public void testCalculateAndGetScore() throws IOException {
		player1.getPetList().add(new Cat());
		
		
		player1.calculateScore();
		double score = player1.getScore();
		assertEquals(700.0, score, 0.0001);
		
		Alpaca testPet = new Alpaca();
		player2.getPetList().add(testPet);
		player2.getPetList().add(new Cat());
		
		testPet.increaseFatigue(50);
		testPet.increaseHappiness(-50);
		testPet.beSick();
		testPet.increaseHunger(100);
		testPet.misbehave();
		
		player2.calculateScore();
		score = player2.getScore();
		assertEquals(490, score, 0.0001);
		
		player1.getPetList().get(0).die();
		player1.calculateScore();
		score = player1.getScore();
		assertEquals(700.0, score, 0.0001);
	}

}
