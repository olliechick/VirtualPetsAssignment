/**
 * Item class for Virtual Pets Assignment
 * @author Samuel Pell
 *
 * Base class for Food and Toy
 */
public abstract class Item {
	
	private String name;
	private String description;
	private int price;
	
	/**
	 * Gets the name of the item
	 * @return item name
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Returns the items description
	 * @return item description
	 */
	public String getDescription(){
		return description;
	}
	
	/**
	 * Returns the price of the item
	 * @return item price
	 */
	public int getPrice(){
		return price;
	}
	
	/**
	 * Sets the name of the item. The items name cannot be null or the
	 * empty string 
	 * @param newName items new name
	 * @throws IllegalArgumentException newName is null or the empty string
	 */
	public void setName(String newName) throws IllegalArgumentException{
		if (newName != null && newName != ""){
			name = newName;
		} else {
			throw new IllegalArgumentException("Item name must not be null or the empty string");
		}
	}
	
	/**
	 * Sets the price of the item. Item price must be greater than 0
	 * @param newPrice item's new price
	 * @throws IllegalArgumentException newPrice is less than 0
	 */
	public void setPrice(int newPrice) throws IllegalArgumentException{
		if (newPrice > 0){
			price = newPrice;
		} else{
			throw new IllegalArgumentException("Item price must be > 0");
		}
	}
	
	/**
	 * Sets the description of the item. The items description cannot be null 
	 * @param newDescription items new description
	 * @throws IllegalArgumentException newDescription is null object
	 */
	public void setDescription(String newDescription) throws IllegalArgumentException{
		if (newDescription != null){
			description = newDescription;
		} else {
			throw new IllegalArgumentException("Item description must not be null");
		}
	}
}
