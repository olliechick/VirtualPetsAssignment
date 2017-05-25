import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

/**
 * Test for Cat class implementation.
 * @author Ollie Chick
 * @author Samuel Pell
 *
 */
public class CatTest {

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
