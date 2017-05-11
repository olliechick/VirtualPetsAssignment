import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * A class for pets 
 * @author Ollie Chick
 * @author Samuel Pell
 *
 */
public class Pet {

	private String name; 
	private String gender; //either male or female
	private String species; //either cat, dog, goat, horse, alpaca, or polar bear
	
	private int health; //out of 100
	private int mischievousness; //out of 100
	private int happiness; //out of 100
	private int hunger; //out of 100
	private int percentBladderFull; //out of 100
	private int fatigue; //out of 100
	private double weight; // in kg
	
	private Boolean isSick;
	private Boolean isRevivable;
	private Boolean isMisbehaving;
	private Boolean isDead;
	
	private double defaultWeight;
	private int bladderSize;
	private int fatigueIncrease;
	private int harshness;
	

	public Pet(String species) throws IOException{
		this.species = species;
		health = 100;
		mischievousness = 0;
		happiness = 100;
		hunger = 0;
		percentBladderFull = 0;
		fatigue = 0;
		isSick = false;
		isRevivable = true;
		isMisbehaving = false;
		isDead = false;
		weight = Double.parseDouble(getDatumFromFile("petData.csv", "defaultWeight", species));
		defaultWeight = weight;
		bladderSize = Integer.parseInt(getDatumFromFile("petData.csv", "bladderSize", species));
		fatigueIncrease = Integer.parseInt(getDatumFromFile("petData.csv", "fatigueIncrease", species));
		harshness = Integer.parseInt(getDatumFromFile("petData.csv", "harshness", species));
	}

	// Getters
	public String getName(){return name;}
	public String getGender(){return gender;}
	public String getSpecies(){return species;}
	public int getHealth(){return health;}
	public int getMischievousness(){return mischievousness;}
	public int getHappiness(){return happiness;}
	public int getHunger(){return hunger;}
	public int getPercentBladderFull(){return percentBladderFull;}
	public int getFatigue(){return fatigue;}
	public double getWeight(){return weight;}
	public double getDefaultWeight(){return defaultWeight;}
	public Boolean getIsSick(){return isSick;}
	public Boolean getIsRevivable(){return isRevivable;}
	public Boolean getIsMisbehaving(){return isMisbehaving;}
	public Boolean getIsDead(){return isDead;}

	// Setters

	/**
	 * Sets the name of an pet.
	 * @param name the name of the pet
	 * @throws IllegalArgumentException if the name is null
	 */
	public void setName(String name){
		if(name == null){
			throw new IllegalArgumentException("Null name.");
		}else{
			this.name=name;
		}
	}

	/**
	 * Sets the gender of an animal.
	 * @param gender can be either "male" or "female"
	 * @throws IllegalArgumentException if the gender is not "male" or "female"
	 */
	public void setGender(String gender){
		if(gender != "male" && gender != "female"){
			throw new IllegalArgumentException("Invalid gender.");
		}else{
			this.gender=gender;
		}
	}

	public void setIsSick(Boolean isSick){this.isSick = isSick;}
	public void setIsRevivable(Boolean isRevivable){this.isRevivable = isRevivable;}
	public void setIsMisbehaving(Boolean isMisbehaving){this.isMisbehaving = isMisbehaving;}

	// Increasers - like setters, but increase value rather than setting it
	public void increaseHealth(int increase){health=increaseValue(increase, health);}
	public void increaseMischievousness(int increase){mischievousness=increaseValue(increase, mischievousness);}
	public void increaseHappiness(int increase){happiness=increaseValue(increase, happiness);}
	public void increaseHunger(int increase){hunger=increaseValue(increase, hunger);}
	public void increasePercentBladderFull(int increase){percentBladderFull=increaseValue(increase, percentBladderFull);}
	public void increaseFatigue(int increase){fatigue=increaseValue(increase, fatigue);}

	public void increaseWeight(double increase){
		double newWeight = weight + increase;
		if (newWeight < 1e-6){
			throw new IllegalArgumentException("negative or 0 weight");
		}else{
			weight = newWeight;
		}
	}
	
	//doing functions
	
	/**
	 * The pet plays with the toy.
	 * Their happiness will increase or decrease.
	 * Their fatigue will increase.
	 * Their mischievousness will decrease or increase.
	 * Their hunger will increase.
	 * Also, the toy's durability will decrease.
	 * @param toy the toy the pet plays with
	 */
	public void play(Toy toy){
		int happinessIncrease = toy.getHappinessIncrease(species);
		increaseHappiness(happinessIncrease);
		increaseFatigue(fatigueIncrease);
		increaseMischievousness(-happinessIncrease);
		increaseHunger(fatigueIncrease);
		toy.decrementDurability(harshness);
	}
	
	/**
	 * The pet sleeps.
	 * Their fatigue will decrease.
	 */
	public void sleep(){
		increaseFatigue(-80);
	}
	
	/**
	 * The pet goes toilet.
	 * Their percent bladder full will decrease to 0.
	 * Their weight will return to normal.
	 */
	public void goToilet(){
		increasePercentBladderFull(-100);
		increaseWeight(defaultWeight - getWeight());
		}
	
	/**
	 * The pet eats food.
	 * Their hunger will decrease.
	 * Their weight will increase.
	 * Their percent bladder full will increase.
	 * Their happiness will increase or decrease.
	 * Their health will increase or decrease.
	 * @param food the food the pet eats
	 */
	public void feed(Food food){
		int portionSize = food.getPortionSize();
		int healthIncrease = food.getHealthIncrease(species);
		increaseHunger(-portionSize);
		increaseWeight(portionSize);
		increasePercentBladderFull(portionSize/bladderSize+1);
		increaseHappiness(healthIncrease*portionSize);
		increaseHealth(healthIncrease);
		}
	
	/**
	 * Their happiness will decrease.
	 */
	public void misbehave(){
		increaseHappiness(-10);
		setIsMisbehaving(true);
	}

	/**
	 * The pet is disciplined.
	 */
	public void discipline() {
		isMisbehaving = false;
		increaseHappiness(-5);
		mischievousness = 0;
	}
	
	/**
	 * The pet is sick.
	 */
	public void beSick(){
		increaseHappiness(-10);
		isSick = true;
	}
	
	/**
	 * The pet is treated.
	 */
	public void treat(){
		isSick = false;
		health = 100;
		increaseHunger(-50);
		increaseFatigue(10);
		increaseMischievousness(-5);
	}
	
	/**
	 * The pet is revived.
	 * They can no longer be revived.
	 */
	public void revive(){
		isRevivable = false;
	}
	
	/**
	 * The pet dies.
	 */
	public void die(){
		isDead = true;
	}
	
	
	
	/**
	 * @return String representation of the pet
	 */
	public String toString(){
		return "A " + gender + " " + species + " named " + name + " with " + health + "HP.";
	}

	//other methods
	/**
	 * Read the file from the path inputed and selects the data necessary.
	 * @param fileName File to read from
	 * @param heading Name of the column in the data file
	 * @param row The name of the item
	 * @return datum wanted
	 */
	private String getDatumFromFile(String fileName, String heading, String row){
		String topDir = System.getProperty("user.dir");
		if (topDir.endsWith("bin")){
			fileName = "../src/" + fileName;
		}else{
			fileName = System.getProperty("user.dir")  + "/src/" + fileName;
		}
		String datum = null;
		int col = 0;
		Boolean found = false;
		String typeOfItem = null;
		
		try{
			FileReader inputFile = new FileReader(fileName);
			BufferedReader bufferReader = new BufferedReader(inputFile);

			String line;
			int i = 0;
			
			while ((line = bufferReader.readLine()) != null){
				switch(i){
					case 0: break; //description line
					case 1: 
						int j = 0;
						typeOfItem = line.split(",")[0];
						for(String piece : line.split(",")){
							if (piece.equals(heading)){
								col = j;
								found = true;
							}
							j++;
						}
						if (!found){
							throw new IllegalArgumentException("No such heading");
						}
						break;
					default:
						if(line.split(",")[0].equals(row)){ //if the right row
							datum = line.split(",")[col];
						}
				}
				i++;
			}
			
			//when done close both reader and file
			bufferReader.close();
			inputFile.close();
			
		}catch(Exception e){
			System.err.println("Error while reading file line by line: " + e.getMessage());
		}
		
		if (datum == null)
			throw new IllegalArgumentException("Unknown " + typeOfItem + ": " + row);
		else
			return datum;
	}
	
	/**
	 * This is a private function called by the increasers to make sure they stay within 0-100
	 * @param increase This is how much to increase the value by
	 * @param valueToIncrease This is the initial value
	 * @return the new value
	 */
	private int increaseValue(int increase, int valueToIncrease){
		int newValue = valueToIncrease + increase;
		if(newValue < 0){ //if this makes it negative
			newValue = 0;
		}else if(newValue > 100){ //if this makes it greater than 100
			newValue = 100;
		}
		return newValue;
	}
}
