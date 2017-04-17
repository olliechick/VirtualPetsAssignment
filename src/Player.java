import java.util.ArrayList;

/**
 * Player class for VirtualPets
 * @author Ollie Chick
 * @author Samuel Pell
 *
 */
public class Player {
	
	String name;
	int balance; // in dollars ($)
	ArrayList<Pet> petList;
	ArrayList<Food> foodStock;
	ArrayList<Toy> toyList;
	double score;

	public Player() {
		balance = 100;
		petList = new ArrayList<Pet>();
		foodStock = new ArrayList<Food>();
		toyList = new ArrayList<Toy>();
	}

	//Getters
	public String getName(){return name;}
	public int getBalance(){return balance;}
	public ArrayList<Pet> getPetList(){return petList;}
	public ArrayList<Food> getFoodStock(){return foodStock;}
	public ArrayList<Toy> getToyList(){return toyList;}
	public double getScore(){return score;}
	
	/**
	 * Sets the name of an player.
	 * @param name the name of the player
	 * @throws IllegalArgumentException if the name is null
	 */
	public void setName(String name){
		if(name == null){
			throw new IllegalArgumentException("Null name.");
		}else{this.name=name;}
	}
	
	public void spend(int amountSpent){
		
	}

}
