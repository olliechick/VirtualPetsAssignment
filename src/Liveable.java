/**
 * An interface for living things
 * @author Ollie Chick
 *
 */
public interface Liveable {
	
	/**
	 * The pet plays with the toy.
	 * Their happiness will increase or decrease
	 * Their fatigue will increase
	 * Their mischievousness will increase or decrease
	 * Their hunger will increase
	 * Also, the toy's durability will decrease
	 * @param toy the toy the pet plays with
	 */
	void play(Toy toy);
	
	/**
	 * The pet sleeps.
	 * Their fatigue will decrease.
	 */
	void sleep();
	
	/**
	 * The pet goes toilet.
	 * Their percent bladder full will decrease to 0.
	 * Their weight will return to normal.
	 */
	void goToilet();
	
	/**
	 * The pet eats food.
	 * Their hunger will decrease
	 * Their weight will increase
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
