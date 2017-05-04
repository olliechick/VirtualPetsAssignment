import java.util.HashMap;

/**
 * Toy class implementation for Virtual Pets
 * @author Samuel Pell
 * @author Ollie Chick
 *
 */
public class Toy extends Item{
	
	private int durability;
	private HashMap<String, Integer> happinessIncrease = new HashMap<String, Integer>();
	
	/**
	 * Constructor for Toy item.
	 * @param name Toy name
	 * @param description Toy description
	 * @param price Price of toy
	 * @param durability How durable the toy is
	 * @throws IllegalArgumentException Toy's durability must be greater than 0
	 */
	public Toy(String name, String description, int price, int durability) throws IllegalArgumentException{
		super();
		super.setName(name);
		super.setDescription(description);
		super.setPrice(price);
		if (durability <= 0){
			throw new IllegalArgumentException("durability is zero or negative");
		} else {
			this.durability = durability;
		}
	}
	
	/**
	 * Gets the item's remaining durability.
	 * @return item's durability
	 */
	public int getDurability(){
		return this.durability;
	}
	
	/**
	 * Returns the amount happiness increases for the pet using the toy.
	 * @param species Species of animal using toy
	 * @return Happiness increase
	 * @throws IllegalArgumentException Species not known
	 */
	public int getHappinessIncrease(String species) throws IllegalArgumentException{
		if (this.happinessIncrease.get(species) == null){
			throw new IllegalArgumentException("Species " + species + " is not known to the toy " + this.getName());
		} else{
			return (int) this.happinessIncrease.get(species);
		}
	}
	
	/**
	 * Decrements the objects durability; the new durability must be greater than 0.
	 * 
	 * Durability is counted in number of uses remaining; at 0, the item has no uses left and needs to be removed from the player's toyList.
	 * @param decrement Amount to decrement durability by (must be larger than 0).
	 * @throws IllegalArgumentException decrement must be larger than 0
	 * @throws IllegalArgumentException Item's durability is negative or 0; it needs to be removed from the player's toyList
	 */
	public void decrementDurability(int decrement) throws IllegalArgumentException{
		if (decrement <= 0){
			throw new IllegalArgumentException("durability must decrease - currently, it is decrementing by "+decrement);
		}
		
		durability -= decrement;
		if (durability <= 0){
			throw new IllegalArgumentException("durability is zero or negative");
		}
	}
	
	/**
	 * Sets the object's happiness increase on a per species basis.
	 * @param species Species for which this increase applies
	 * @param increase Happiness increase
	 */
	public void setHappinessIncrease(String[] species, Integer[] increase){
		for (int i = 0; i < species.length; i++){
			this.happinessIncrease.put(species[i], increase[i]);
		}
	}
}
