import java.io.IOException;

/**
 * Cat, a type of pet
 * @author Ollie Chick
 *
 */
public class Cat extends Pet {

	private static String species = "cat";
	private double defaultWeight;
	private int bladderSize;
	
	public Cat() throws IOException {
		super(species);
		defaultWeight = Double.parseDouble(getDatumFromFile("petdata.csv", "defaultWeight", species));
		bladderSize = Integer.parseInt(getDatumFromFile("petdata.csv", "bladderSize", species));
	}

	public void play(Toy toy) {
		int fatigueIncrease = 1;
		int harshness = 1;
		super.play(toy, fatigueIncrease, harshness);
	}

	public void goToilet() {
		super.goToilet(defaultWeight);
	}

	public void feed(Food food) {
		super.feed(food, bladderSize);
	}

}