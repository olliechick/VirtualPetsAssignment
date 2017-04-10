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
	 * @param name name of food
	 * @param description food description
	 * @param price price of food
	 * @param healthIncrease how much the pets health increase by when it is eaten
	 * @param portionSize how large the portion is
	 */
	public Food(String name, String description, int price, int healthIncrease, int portionSize){
		super();
		super.setName(name);
		super.setDescription(description);
		super.setPrice(price);
		setHealthIncrease(healthIncrease);
		setPortionSize(portionSize);
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
	 * @param increase new health increase
	 */
	public void setHealthIncrease(int increase){
		this.healthIncrease = increase;
	}
	
	/**
	 * Set the portion size, portion size must be greater than 0.
	 * @param size new portion size
	 * @throws IllegalArgumentException size less than 0
	 */
	public void setPortionSize(int size) throws IllegalArgumentException{
		if (size > 0){
			this.portionSize = size;
		} else{
			throw new IllegalArgumentException("Portion size must be > 0");
		}
	}
}
