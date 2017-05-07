import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PlayerTest {
	String name1 = "Harry";
	String name2 = "Rex";
	Player player1 = new Player();
	Player player2 = new Player();

	@Before
	public void setUp() {
	}

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
	public void testCalculateAndGetScore() {
		double score = player1.calculateAndGetScore();
		//TODO add tests when score has actually been quantified
	}

}
