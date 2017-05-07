import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PlayerTest {
	String name1;
	String name2;
	Player player1;

	@Before
	public void setUp() throws Exception {
		name1 = "Harry";
		name2 = "Rex";
	}

	@Test
	public void testPlayer() {
		player1 = new Player();
		assert(player1.getBalance()>0);
		assertTrue(player1.getPetList().isEmpty());
		//TODO here down

	}

	@Test
	public void testSetName() {
		fail("Not yet implemented");
	}

	@Test
	public void testSpend() {
		fail("Not yet implemented");
	}

	@Test
	public void testEarn() {
		fail("Not yet implemented");
	}

	@Test
	public void testCalculateAndGetScore() {
		fail("Not yet implemented");
	}

}
