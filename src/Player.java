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
	
	/**
	 * Takes away the amount spent from the balance.
	 * @param amountSpent amount the user spends
	 * @throws IllegalArgumentException if you try to spend a negative amount
	 * @throws IllegalArgumentException if you try to spend more than you have
	 */
	public void spend(int amountSpent){
		if (amountSpent < 0){
			throw new IllegalArgumentException("Can't spend a negative amount.");
		}
		if (balance < amountSpent){
			throw new IllegalArgumentException("Can't spend more than you have.");
		}else{			
			balance -= amountSpent;
		}
	}
	
	/**
	 * Adds the amount earnt to the player's balance.
	 * @param amountEarnt amount the user earns
	 * @throws IllegalArgumentException if you try to earn a negative amount
	 */
	public void earn(int amountEarnt){
		if (amountEarnt < 0){
			throw new IllegalArgumentException("Can't earn a negative amount.");
		}
		balance += amountEarnt;
	}
	
	/**
	 * Calculate's the player's score
	 */
	public void calculateScore(){
		int score = 0;
		int petScore;
		
		int happiness;
		int fatigue;
		int health;
		int mischeviousness;
		int hunger;
		int percentBladderFull;
		double weight;
		boolean misbehaving;
		boolean sick;
		
		for(Pet currentPet: petList){
			petScore = 0;
			
			happiness = currentPet.getHappiness();
			fatigue = currentPet.getFatigue();
			health = currentPet.getHealth();
			mischeviousness = currentPet.getMischievousness();
			hunger = currentPet.getHunger();
			percentBladderFull = currentPet.getPercentBladderFull();
			weight = currentPet.getWeight();
			misbehaving = currentPet.getIsMisbehaving();
			sick = currentPet.getIsSick();
			
			//make bad attributes the inverse of what they were
			fatigue = 100 - fatigue;
			mischeviousness = 100 - mischeviousness;
			percentBladderFull = 100 - percentBladderFull;
			hunger = 100 - hunger;
			
			petScore += (happiness + fatigue + mischeviousness + hunger + percentBladderFull + weight + health) ;
			
			if (sick){
				petScore -= 150; 
			}
			
			if (misbehaving){
				petScore -= 50;
			}
			
			score += petScore;
		}
		
		this.score += score / petList.size();
	}
	
	/**
	 * Adds a toy to the player's inventory
	 * @param toy Toy to be added.
	 */
	public void addToy(Toy toy){
		toyList.add(toy);
	}
	
	/**
	 * Adds a food to the player's inventory
	 * @param food Food to be added.
	 */
	public void addFood(Food food){
		foodStock.add(food);
	}

}
