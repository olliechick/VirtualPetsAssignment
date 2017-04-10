/**
 * 
 * @author Ollie Chick
 * A class for pets
 *
 */
public class Pet {

	private String name;
	private String gender;
	private String species;
	private int mischievousness; //out of 100
	private int happiness; //out of 100
	private int hunger; //out of 100
	private int percentBladderFull; //out of 100
	private int fatigue; //out of 100
	private int playfulness; //out of 100
	private double weight; // in kg
	private Boolean isSick;
	private Boolean isRevivable;
	private Boolean isMisbehaving;

	public Pet(String species){
		this.species = species;
	}

	// Getters
	public String getName(){return name;}
	public String getGender(){return gender;}
	public String getSpecies(){return species;}
	public int getMischievousness(){return mischievousness;}
	public int getHappiness(){return happiness;}
	public int getHunger(){return hunger;}
	public int getPercentBladderFull(){return percentBladderFull;}
	public int getFatigue(){return fatigue;}
	public int getPlayfulness(){return playfulness;}
	public double getWeight(){return weight;}
	public Boolean getIsSick(){return isSick;}
	public Boolean getIsRevivable(){return isRevivable;}
	public Boolean getIsMisbehaving(){return isMisbehaving;}

	// Setters
	public void setName(String name){this.name=name;}
	public void setGender(String gender){this.gender=gender;}
	public void setIsSick(Boolean isSick){this.isSick = isSick;}
	public void setIsRevivable(Boolean isRevivable){this.isRevivable = isRevivable;}
	public void setIsMisbehaving(Boolean isMisbehaving){this.isMisbehaving = isMisbehaving;}

	/**
	 * 
	 * @param increase This is how much to increase the value by
	 * @param valueToIncrease This is the initial value
	 * @return the new value
	 */
	public int increaseValue(int increase, int valueToIncrease){
		int newValue = valueToIncrease + increase;
		if(newValue < 0){ //if this makes it negative
			newValue = 0;
		}else if(newValue > 100){ //if this makes it greater than 100
			newValue = 100;
		}
		return newValue;
	}

	// Increasers - like setters, but increase value rather than setting it
	public void increaseMischievousness(int increase){mischievousness=increaseValue(increase, mischievousness);}
	public void increaseHappiness(int increase){happiness=increaseValue(increase, happiness);}
	public void increaseHunger(int increase){hunger=increaseValue(increase, hunger);}
	public void increasePercentBladderFull(int increase){percentBladderFull=increaseValue(increase, percentBladderFull);}
	public void increaseFatigue(int increase){fatigue=increaseValue(increase, fatigue);}
	public void increasePlayfulness(int increase){playfulness=increaseValue(increase, playfulness);}
	
	public void increaseWeight(double increase){
		double newWeight = weight + increase;
		if (newWeight < 1e-6){
			throw new IllegalArgumentException("negative or 0 weight");
		}else{weight = newWeight;}
		}
}
