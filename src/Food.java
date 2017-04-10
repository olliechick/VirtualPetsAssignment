/**
 * Food class implementation for Virtual Pets
 * @author Samuel Pell
 *
 */
public class Food extends Item{
	
	private int healthIncrease;
	private int portionSize;
	
	/**
	 * Constructor for Food item
	 * @param name
	 * @param description
	 * @param price
	 * @param healthChange
	 * @param portionSizing
	 */
	public Food(String name, String description, int price, int healthChange, int portionSizing){
		super();
		super.setName(name);
		super.setDescription(description);
		super.setPrice(price);
		setHealthIncrease(healthChange);
		setPortionSize(portionSizing);
	}
	
	/**
	 * Get how much eating each food changes the health
	 * of the animal
	 * @return change in HP for pet
	 */
	public int getHealthIncrease(){
		return healthIncrease;
	}
	
	/**
	 * Get portion size for the food
	 * @return portion size
	 */
	public int getPortionSize(){
		return portionSize;
	}
	
	/**
	 * Change the health increase of the food
	 * @param increase
	 */
	public void setHealthIncrease(int increase){
		healthIncrease = increase;
	}
	
	/**
	 * Set the portion size, portion size must be greater than 0.
	 * @param size
	 */
	public void setPortionSize(int size){
		if (size > 0){
			portionSize = size;
		} else{
			throw new IllegalArgumentException("Portion size must be > 0");
		}
	}
}
