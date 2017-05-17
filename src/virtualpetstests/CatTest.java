package virtualpetstests;
import static org.junit.Assert.*;

import org.junit.Test;

import virtualpets.Cat;

import org.junit.Before;

/**
 * Test for Cat class implementation.
 * @author Ollie Chick
 * @author Samuel Pell
 *
 */
public class CatTest {
	/**
	 * Cat variable for testing if wrapper classes wok
	 */
	private Cat myCat;

	@Before
	public void setUp() throws Exception {
		myCat = new Cat();
	}

	@Test
	public void testCat() {
		assertEquals(myCat.getSpecies(), "cat");
	}

}
