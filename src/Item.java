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
	 * @param newName
	 */
	public void setName(String newName){
		if (newName != null && newName != ""){
			name = newName;
		} else {
			throw new IllegalArgumentException("Item name must not be null or the empty string");
		}
	}
	
	/**
	 * Sets the price of the item. Item price must be greater than 0
	 * @param newPrice
	 * @throws IllegalArgumentException
	 */
	public void setPrice(int newPrice){
		if (newPrice > 0){
			price = newPrice;
		} else{
			throw new IllegalArgumentException("Item price must be > 0");
		}
	}
	
	/**
	 * Sets the decription of the item. The items description cannot be null 
	 * @param newDescription
	 * @throws IllegalArgumentException
	 */
	public void setDescription(String newDescription){
		if (newDescription != null){
			name = newDescription;
		} else {
			throw new IllegalArgumentException("Item description must not be null");
		}
	}
}
