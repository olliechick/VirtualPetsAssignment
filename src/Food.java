import java.util.HashMap;

/**
 * Food class implementation for Virtual Pets
 * @author Samuel Pell
 *
 */
public class Food extends Item{
	
	private HashMap<String, Integer> healthIncrease = new HashMap<String, Integer>();
	private int portionSize;
	
	/**
	 * Constructor for Food item
	 * <h3><i>Warning: You must still call Food.setHealthIncrease before using that functionality</i></h3>
	 * @param name name of food
	 * @param description food description
	 * @param price price of food
	 * @param portionSize how large the portion is
	 */
	public Food(String name, String description, int price, int portionSize){
		super();
		super.setName(name);
		super.setDescription(description);
		super.setPrice(price);
		this.setPortionSize(portionSize);
	}
	
	/**
	 * Get how much eating each food changes the health
	 * of the animal
	 * @return change in HP for pet
	 * @param species relevant species
	 * @throws IllegalArgumentException Species not known
	 */
	public int getHealthIncrease(String species) throws IllegalArgumentException{
		if (this.healthIncrease.get(species) == null){
			throw new IllegalArgumentException("Species " + species + " is not known to this food " + this.getName());
		} else{
			return this.healthIncrease.get(species);
		}
	}
	
	/**
	 * Get portion size for the food
	 * @return portion size
	 */
	public int getPortionSize(){
		return this.portionSize;
	}
	
	/**
	 * Sets the foods health increase on a per species basis.
	 * @param species Species for which this increase applies
	 * @param increase Happiness increase
	 */
	public void setHealthIncrease(String[] species, int[] increase){
		for (int i =0; i < species.length; i++){
			this.healthIncrease.put(species[i], increase[i]);
		}
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
