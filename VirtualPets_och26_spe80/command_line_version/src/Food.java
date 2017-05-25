import java.util.HashMap;

/**
 * Food class implementation for Virtual Pets.
 * @author Samuel Pell
 * @author Ollie Chick
 *
 */
public class Food extends Item {

    /**
     * A HashMap of species name to the increase in health
     * that species gets from eating this food.
     */
    private HashMap<String, Integer> healthIncrease = new HashMap<String, Integer>();
    /**
     * The portion size of the food.
     */
    private int portionSize;

    /**
     * Constructor for Food item.
     * <b>Warning: You must still call Food.setHealthIncrease before using that functionality.</b>
     * @param name Name of food
     * @param description Food description
     * @param price Price of food
     * @param portionSize How large the food is
     */
    public Food(String name, String description, int price, int portionSize) {
        super();
        super.setName(name);
        super.setDescription(description);
        super.setPrice(price);
        this.setPortionSize(portionSize);
    }

    /**
     * Gets how much eating each food changes the health
     * of the animal.
     * @param species Relevant species
     * @return Change in health for pet
     * @throws IllegalArgumentException Species not known
     */
    public int getHealthIncrease(String species) throws IllegalArgumentException {
        if (this.healthIncrease.get(species) == null) {
            throw new IllegalArgumentException("Species " + species
                    + " is not known to this food " + this.getName());
        } else {
            return this.healthIncrease.get(species);
        }
    }

    /**
     * Gets portion size for the food.
     * @return Portion size.
     */
    public int getPortionSize() {
        return this.portionSize;
    }

    /**
     * Sets the food's health increase on a per species basis.
     * @param species Species for which this increase applies.
     * @param increases Happiness increase.
     */
    public void setHealthIncrease(String[] species, String[] increases) {
        //Convert strings to integers
        Integer[] convertedIncreases = super.convertStringsToInts(increases);
        for (int i = 0; i < species.length; i++) {
            this.healthIncrease.put(species[i], convertedIncreases[i]);
        }
    }

    /**
     * Sets the portion size; this must be greater than 0.
     * @param size New portion size.
     * @throws IllegalArgumentException Negative or 0 size.
     */
    public void setPortionSize(int size) throws IllegalArgumentException {
        if (size > 0) {
            this.portionSize = size;
        } else {
            throw new IllegalArgumentException("Portion size must be greater than zero.");
        }
    }
}
