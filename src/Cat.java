import java.io.IOException;

/**
 * Cat, a type of pet
 * @author Ollie Chick
 *
 */
public class Cat extends Pet {

	private static String species = "cat";
	double defaultWeight;
	int bladderSize;
	
	public Cat() throws IOException {
		super(species);
		defaultWeight = Double.parseDouble(getDatumFromFile("petdata.csv", "defaultWeight", species));
		bladderSize = Integer.parseInt(getDatumFromFile("petdata.csv", "bladderSize", species));
	}

	@Override
	public void play(Toy toy) {
		int happinessIncrease = toy.getHappinessIncrease(species);
		super.increaseHappiness(happinessIncrease);
		super.increaseFatigue(1);
		super.increaseMischievousness(-happinessIncrease);
		super.increaseHunger(1);
		toy.decrementDurability(1);
	}

	@Override
	public void sleep() {
		super.increaseFatigue(-80);
	}

	@Override
	public void goToilet() {
		super.increasePercentBladderFull(-100);
		super.increaseWeight(defaultWeight - super.getWeight());
	}

	@Override
	public void feed(Food food) {
		int portionSize = food.getPortionSize();
		int healthIncrease = food.getHealthIncrease(species);
		super.increaseHunger(-portionSize);
		super.increaseWeight(portionSize);
		super.increasePercentBladderFull(portionSize/bladderSize+1);
		super.increaseHappiness(healthIncrease*portionSize);
		super.increaseHealth(healthIncrease);
	}

	@Override
	public void beSick() {
		super.setIsSick(true);
	}

	@Override
	public void die() {
		super.setIsRevivable(false); //This may be a problem 
	}

}
