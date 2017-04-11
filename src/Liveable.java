/**
 * An interface for living things
 * @author Ollie Chick
 *
 */
public interface Liveable {
	
	/**
	 * The pet plays with the toy
	 * @param toy the toy the pet plays with
	 */
	void play(Toy toy);
	/**
	 * The pet sleeps
	 */
	void sleep();
	/**
	 * The pet goes toilet
	 */
	void goToilet();
	/**
	 * The pet eats food
	 * @param food the food the pet eats
	 */
	void feed(Food food);
	/**
	 * The pet misbehaves
	 */
	void misbehave();
	/**
	 * The pet is sick
	 */
	void beSick();
	/**
	 * The pet dies
	 */
	void die();

}
