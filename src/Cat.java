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
		super.goToilet(defaultWeight);
	}

	public void feed(Food food) {
		super.feed(food,  bladderSize);
	}

}