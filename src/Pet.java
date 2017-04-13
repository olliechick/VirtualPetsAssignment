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

	private String name; //either cat, dog, goat, horse, alpaca, or polar bear
	private String gender; //either male or female
	private String species;
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

		
		String fileName = System.getProperty("user.dir") + "/src/petdata.csv";
		String weightString = getDatumFromFile(fileName, "defaultWeight", species);
		
		this.weight = Double.parseDouble(weightString);
		
		/*switch(species){
			case "cat": 
			case "dog": weight = 4; break;
			case "goat": weight = 50.0; break;
			case "horse": weight = 500.0; break;
			case "alpaca": weight = 60.0; break;
			case "polar bear": weight = 250.0; break;
			default: throw new IllegalArgumentException("Species not recognised.");
		}*/
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
	public Boolean getIsSick(){return isSick;}
	public Boolean getIsRevivable(){return isRevivable;}
	public Boolean getIsMisbehaving(){return isMisbehaving;}

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

	//other methods
	/**
	 * Read the file form the path inputed and selects the data neccessary.
	 * @param fileName File to read from
	 * @param heading Name of the column in the data file
	 * @param row The name of the species
	 * @return data wanted
	 */
	protected String getDatumFromFile(String fileName, String heading, String row){
		String datum = null;
		int col = 0;
		Boolean found = false;
		
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
			System.out.println("Error while reading file line by line:" + e.getMessage());
		}
		
		if (datum == null)
			throw new IllegalArgumentException("Unknown species: " + row);
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
