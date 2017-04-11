/**
 * Cat, a type of pet
 * @author Ollie Chick
 *
 */
public class Cat extends Pet implements Liveable {

	static String species = "cat";
	
	public Cat() {
		super(species);
	}

	@Override
	public void play(Toy toy) {
		int happinessIncrease = toy.getHappinessIncrease(species);
		toy.decrementDurability(1);
		super.increaseHappiness(happinessIncrease);
		super.increaseFatigue(1);
		super.increaseMischievousness(-happinessIncrease);
		super.increaseHunger(1);
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
		super.increaseHappiness(healthIncrease*portionSize);
		super.increaseHealth(healthIncrease);
	}

	@Override
	public void misbehave() {
		super.increaseHappiness(-1);
		super.setIsMisbehaving(true);
	}

	@Override
	public void beSick() {
		super.setIsSick(true);
	}

	@Override
	public void die() {
		super.setIsRevivable(false);
	}

}
