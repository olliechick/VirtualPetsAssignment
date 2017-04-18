import java.io.IOException;

public class Dog extends Pet {

	private static String species = "dog";
	private double defaultWeight;
	private int bladderSize;
	
	public Dog() throws IOException {
		super(species);
		defaultWeight = Double.parseDouble(getDatumFromFile("petdata.csv", "defaultWeight", species));
		bladderSize = Integer.parseInt(getDatumFromFile("petdata.csv", "bladderSize", species));
	}

	public void play(Toy toy) {
		int fatigueIncrease = 5;
		int harshness = 2;
		super.play(toy, fatigueIncrease, harshness);
	}

	public void goToilet() {
		super.goToilet(defaultWeight);
	}

	public void feed(Food food) {
		super.feed(food, bladderSize);
	}
}
