import java.util.HashMap;

/**
 * Toy class implementation for Virtual Pets
 * @author Samuel
 *
 */
public class Toy extends Item{
	
	private int durability;
	//private int happinessIncrease;
	private HashMap<String, Integer> happinessIncrease = new HashMap<String, Integer>();
	
	/**
	 * Constructor for Toy item
	 * @param name Toy Name
	 * @param description Toy description
	 * @param price Price of toy
	 * @param durability How durabil the toy is
	 */
	public Toy(String name, String description, int price, int durability){
		super();
		super.setName(name);
		super.setDescription(description);
		super.setPrice(price);
		this.setDurability(durability);
	}
	
	/**
	 * Gets the items remain durability
	 * @return items durability
	 */
	public int getDurability(){
		return this.durability;
	}
	
	/**
	 * Returns the amount happiness increases for the pet using the toy
	 * @param species Species of animal using toy
	 * @return Happiness increase
	 * @throws IllegalArgumentException Species not known
	 */
	public int getHappinessIncrease(String species) throws IllegalArgumentException{
		if (this.happinessIncrease.get(species) == null){
			throw new IllegalArgumentException("Specises " + species + " is not known to this toy " + this.getName());
		} else{
			return this.happinessIncrease.get(species);
		}
	}
	
	/**
	 * Set the objects durabilty. New durability must be greater than 0
	 * @param durability Items remaining durability
	 * @throws IllegalArgumentException Durability must be larger than 0
	 */
	public void setDurability(int durability) throws IllegalArgumentException{
		if (durability < 0){
			throw new IllegalArgumentException("durability has gone below 0");
		} else{
			this.durability = durability;
		}
	}
	
	/**
	 * Sets the objects happiness increase on a per species basis.
	 * @param species Species for which this increase applies
	 * @param increase Happiness increase
	 */
	public void setHappinessIncrease(String[] species, int[] increase){
		for (int i =0; i < species.length; i++){
			this.happinessIncrease.put(species[i], increase[i]);
		}
	}
}
