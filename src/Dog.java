import java.io.IOException;

public class Dog extends Pet implements Liveable {

	public Dog() throws IOException {
		super("dog");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void play(Toy toy) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sleep() {
		// TODO Auto-generated method stub

	}

	@Override
	public void goToilet() {
		// TODO Auto-generated method stub

	}

	@Override
	public void feed(Food food) {
		// TODO Auto-generated method stub

	}

	@Override
	public void misbehave() {
		// TODO Auto-generated method stub
		System.out.println("Dog misbehaves");
		super.increaseHappiness(-12);
		super.setIsMisbehaving(true);

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
