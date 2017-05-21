package virtualpets;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * A class for pets.
 * @author Ollie Chick
 * @author Samuel Pell
 *
 */
public class Pet {
    /**
     * Name of the pet.
     */
    private String name;
    /**
     * The gender of the pet. Either male or female.
     */
    private String gender;
    /**
     * The species of the pet.
     * Either cat, dog, goat, horse, alpaca, or polar bear.
     */
    private String species;
    /**
     * The pet's favourite food.
     * This food will give the pet the most benefits.
     */
    private String favouriteFood;
    /**
     * The pet's favourite toy.
     * This toy will give the pet the most benefits.
     */
    private String favouriteToy;

    /**
     * The pet's health, on a scale of 0-100.
     */
    private int health;
    /**
     * The pet's mischievousness, on a scale of 0-100.
     */
    private int mischievousness;
    /**
     * The pet's happiness, on a scale of 0-100.
     */
    private int happiness;
    /**
     * The pet's hunger, on a scale of 0-100.
     */
    private int hunger;
    /**
     * How full the pet's bladder is, on a scale of 0-100.
     */
    private int percentBladderFull;
    /**
     * The pet's fatigue, on a scale of 0-100.
     */
    private int fatigue;
    /**
     * The pet's weight, in kilograms.
     */
    private double weight;

    /**
     * Whether or not the pet is sick.
     */
    private Boolean isSick;
    /**
     * Whether or not the pet is revivable.
     */
    private Boolean isRevivable;
    /**
     * Whether or not the pet is sick.
     */
    private Boolean isMisbehaving;
    /**
     * Whether or not the pet is dead.
     */
    private Boolean isDead;

    /**
     * The pet's default weight, in kilograms.
     * The pet will be initialised to this weight,
     * and return to this weight when it goes toilet.
     */
    private double defaultWeight;
    /**
     * The pet's bladder size.
     */
    private int bladderSize;
    /**
     * How tired the pet gets when it plays with a toy
     * (this is scaled depending on the toy).
     */
    private int fatigueIncrease;
    /**
     * How harsh the pet is with toys.
     * The harsher they are, the quicker toys they play with break.
     */
    private int harshness;

    /**
     * Constructs pet and sets initial values.
     * @param species The name of the species being created.
     */
    public Pet(String species) {
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
            weight = Double.parseDouble(
                    getDatumFromFile("petData.csv", "defaultWeight", species));
            bladderSize = Integer.parseInt(
                    getDatumFromFile("petData.csv", "bladderSize", species));
            fatigueIncrease = Integer.parseInt(
                    getDatumFromFile("petData.csv", "fatigueIncrease", species));
            harshness = Integer.parseInt(
                    getDatumFromFile("petData.csv", "harshness", species));
            favouriteFood = getDatumFromFile("petData.csv", "favouriteFood", species);
            favouriteToy = getDatumFromFile("petData.csv", "favouriteToy", species);
            defaultWeight = weight;
    }

    // Getters
    /**
     * Gets the name of the pet.
     * @return Pet name.
     */
    public String getName() {
        return name;
        }
    /**
     * Gets the gender of the pet.
     * @return Pet gender.
     */
    public String getGender() {
        return gender;
        }
    /**
     * Gets the species of the pet.
     * @return Pet species.
     */
    public String getSpecies() {
        return species;
        }
    /**
     * Gets the favourite food of the pet.
     * @return Pet's favourite food.
     */
    public String getFavouriteFood() {
        return favouriteFood;
        }
    /**
     * Gets the favourite toy of the pet.
     * @return Pet's favourite toy.
     */
    public String getFavouriteToy() {
        return favouriteToy;
    }
    /**
     * Gets the health of the pet.
     * @return Pet health.
     */
    public int getHealth() {
        return health;
    }
    /**
     * Gets the mischievousness of the pet.
     * @return Pet mischievousness.
     */
    public int getMischievousness() {
        return mischievousness;
    }
    /**
     * Gets the happiness of the pet.
     * @return Pet happiness.
     */
    public int getHappiness() {
        return happiness;
}
    /**
     * Gets the hunger of the pet.
     * @return Pet hunger.
     */
    public int getHunger() {
        return hunger;
    }
    /**
     * Gets how full the pet's bladder is.
     * @return How full the pet's bladder is.
     */
    public int getPercentBladderFull() {
        return percentBladderFull;
    }
    /**
     * Gets the fatigue of the pet.
     * @return Pet fatigue.
     */
    public int getFatigue() {
        return fatigue;
    }
    /**
     * Gets the weight of the pet.
     * @return Pet weight.
     */
    public double getWeight() {
        return weight;
    }
    /**
     * Gets the default weight of the pet.
     * @return Pet's default weight.
     */
    public double getDefaultWeight() {
        return defaultWeight;
    }
    /**
     * Gets whether the pet is sick.
     * @return Whether the pet is sick.
     */
    public Boolean getIsSick() {
        return isSick;
    }
    /**
     * Gets whether the pet is revivable.
     * @return Whether the pet is revivable.
     */
    public Boolean getIsRevivable() {
        return isRevivable;
    }
    /**
     * Gets whether the pet is misbehaving.
     * @return Whether the pet is misbehaving.
     */
    public Boolean getIsMisbehaving() {
        return isMisbehaving;
    }
    /**
     * Gets whether the pet is dead.
     * @return Whether the pet is dead.
     */
    public Boolean getIsDead() {
        return isDead;
    }

    // Setters

    /**
     * Sets the name of an pet.
     * @param name the name of the pet
     * @throws IllegalArgumentException if the name is null
     */
    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Null name.");
        } else {
            this.name = name;
        }
    }

    /**
     * Sets the gender of an animal.
     * @param gender can be either "male" or "female"
     * @throws IllegalArgumentException if the gender is not "male" or "female"
     */
    public void setGender(String gender) {
        if (gender != "male" && gender != "female") {
            throw new IllegalArgumentException("Invalid gender.");
        } else {
            this.gender = gender;
        }
    }

    /**
     * Sets the pet's favourite food.
     * @param favouriteFood The pet's favourite food.
     */
    public void setFavouriteFood(String favouriteFood) {
        this.favouriteFood = favouriteFood;
        }

    /**
     * Sets the pet's favourite toy.
     * @param favouriteToy The pet's favourite toy.
     */
    public void setFavouriteToy(String favouriteToy) {
        this.favouriteToy = favouriteToy;
        }

    /**
     * Sets whether the pet is sick.
     * @param isSick Whether the pet is sick.
     */
    public void setIsSick(Boolean isSick) {
        this.isSick = isSick;
    }

    /**
     * Sets whether the pet is revivable.
     * @param isRevivable Whether the pet is revivable.
     */
    public void setIsRevivable(Boolean isRevivable) {
        this.isRevivable = isRevivable;
    }

    /**
     * Sets whether the pet is misbehaving.
     * @param isMisbehaving Whether the pet is misbehaving.
     */
    public void setIsMisbehaving(Boolean isMisbehaving) {
        this.isMisbehaving = isMisbehaving;
    }

    // Increasers - like setters, but increase value rather than setting it

    /**
     * Increase the pet's health.
     * @param increase How much the pet's health increases.
     */
    public void increaseHealth(int increase) {
        health = increaseValue(increase, health);
    }

    /**
     * Increase the pet's mischievousness.
     * @param increase How much the pet's mischievousness increases.
     */
    public void increaseMischievousness(int increase) {
        mischievousness = increaseValue(increase, mischievousness);
    }

    /**
     * Increase the pet's happiness.
     * @param increase How much the pet's happiness increases.
     */
    public void increaseHappiness(int increase) {
        happiness = increaseValue(increase, happiness);
    }

    /**
     * Increase the pet's hunger.
     * @param increase How much the pet's hunger increases.
     */
    public void increaseHunger(int increase) {
        hunger = increaseValue(increase, hunger);
    }

    /**
     * Increase how full the pet's bladder is.
     * @param increase How much fuller the pet's bladder gets.
     */
    public void increasePercentBladderFull(int increase) {
        percentBladderFull = increaseValue(increase, percentBladderFull);
    }

    /**
     * Increase the pet's fatigue.
     * @param increase How much the pet's fatigue increases.
     */
    public void increaseFatigue(int increase) {
        fatigue = increaseValue(increase, fatigue);
    }

    /**
     * Increase the pet's weight.
     * @param increase How much the pet's weight increases.
     */
    public void increaseWeight(double increase) {
        double newWeight = weight + increase;
        if (newWeight < 1e-6) {
            throw new IllegalArgumentException("negative or 0 weight");
        } else {
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
    public void play(Toy toy) {
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
    public void sleep() {
        increaseFatigue(-80);
    }

    /**
     * The pet goes toilet.
     * Their percent bladder full will decrease to 0.
     * Their weight will return to normal.
     */
    public void goToilet() {
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
    public void feed(Food food) {
        int portionSize = food.getPortionSize();
        int healthIncrease = food.getHealthIncrease(species);
        increaseHunger(-portionSize);
        increaseWeight(portionSize);
        increasePercentBladderFull(portionSize / bladderSize + 1);
        increaseHappiness(healthIncrease * portionSize);
        increaseHealth(healthIncrease);
        }

    /**
     * Their happiness will decrease.
     */
    public void misbehave() {
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
    public void beSick() {
        increaseHappiness(-10);
        isSick = true;
    }

    /**
     * The pet is treated.
     */
    public void treat() {
        isSick = false;
        health = 100;
        increaseHunger(-50);
        increaseFatigue(10);
        increaseMischievousness(-5);
        increaseHappiness(40);
    }

    /**
     * The pet is revived.
     * They can no longer be revived.
     */
    public void revive() {
        isRevivable = false;
    }

    /**
     * The pet dies.
     */
    public void die() {
        isDead = true;
    }



    /**
     * @return String representation of the pet
     */
    public String toString() {
        return "A " + gender + " " + species + " named " + name + " with " + health + "HP";
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
        String datum = null;
        int col = 0;
        Boolean found = false;
        String typeOfItem = null;

        try{
            Reader inputFile;
            try { //Runs if running class directly
                String topDir = System.getProperty("user.dir");
                if (topDir.endsWith("bin")){ //from cmdln
                    fileName = "../config/" + fileName;
                }else{ //from eclipse
                    fileName = "config/" + fileName;
                }
                inputFile = new FileReader(fileName);
            } catch (FileNotFoundException e) { //if running from jar file.
                fileName = "/" + fileName;
                InputStream stream = this.getClass().getResourceAsStream(fileName);
                inputFile = new InputStreamReader(stream);
            }

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

        } catch(Exception e) {
            System.err.println("Error while reading file line by line: " + e.getMessage());
        }

        if (datum == null){
            throw new IllegalArgumentException("Unknown " + typeOfItem + ": " + row);
        } else {
            return datum;
        }
    }

    /**
     * This is a private function called by the increasers to make sure they stay within 0-100.
     * @param increase How much to increase the value by.
     * @param valueToIncrease The initial value.
     * @return The new value.
     */
    private int increaseValue(int increase, int valueToIncrease) {
        int newValue = valueToIncrease + increase;
        if (newValue < 0) { //if this makes it negative
            newValue = 0;
        } else if (newValue > 100) { //if this makes it greater than 100
            newValue = 100;
        }
        return newValue;
    }
}