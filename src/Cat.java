/**
 * Cat, a type of pet
 * @author Ollie Chick
 *
 */
public class Cat extends Pet implements Liveable {
	
	String species = "cat";

	@Override
	public void play(Toy toy) {
		toy.decrementDurability(1);
		super.increaseHappiness(1);
		super.increaseFatigue(1);
		super.increaseMischievousness(-1);
		super.increasePlayfulness(0); //what should go in here??
	}

	@Override
	public void sleep() {
		super.increaseFatigue(-80);
	}

	@Override
	public void goToilet() {
		super.increasePercentBladderFull(-100);
	}

	@Override
	public void feed(Food food) {
		int portionSize = food.getPortionSize();
		int healthIncrease = food.getHealthIncrease(species);
		super.increaseHunger(-portionSize);
		super.increaseWeight(portionSize);
		super.increasePercentBladderFull(portionSize);
		super.increaseHappiness(healthIncrease);
		super.increase
	}

	@Override
	public void misbehave() {
		// TODO Auto-generated method stub

	}

	@Override
	public void beSick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void die() {
		// TODO Auto-generated method stub

	}

}
