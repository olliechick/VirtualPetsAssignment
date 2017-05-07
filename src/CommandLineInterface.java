import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Command line interface for Virtual Pets game.
 * @author Samuel Pell
 * @author Ollie Chick
 *
 */
public class CommandLineInterface {
	
	private static Scanner inputReader = new Scanner(System.in);
	
	/**
	 * Gets the number of days the game is to run for.
	 * @return Number of days to run game for.
	 */
	public static int getNumberOfDays(){
		Integer numDays = null;
		
		do{
			System.out.print("How many days do you want to play for? ");
			System.out.flush();
			String userInput = inputReader.next();
			try{
				numDays = Integer.parseInt(userInput);
				if (numDays < 1){ 
					//if number is 0 or less throw exception
					numDays = null;
					throw new NumberFormatException();
				}
			}catch (NumberFormatException exception){
				System.out.println("Please enter an integer greater than 0.");
			}
		}while (numDays == null);
		
		return numDays;
	}
	
	/**
	 * Used to ask the user the number of pets/players desired (between 1 and 3 inclusive).
	 * @param query Query to pose to user.
	 * @return Number of required pets or players.
	 */
	public static int getNumberRequired(String query){
		Integer numReq = null;
		
		do{
			System.out.print(query);
			System.out.flush();
			String userInput = inputReader.next();
			try{
				numReq = Integer.parseInt(userInput);
				if (numReq< 1 || numReq > 3){ 
					//if number is out of bounds 1-3 throw exception
					numReq = null;
					throw new NumberFormatException();
				}
			}catch (NumberFormatException exception){
				System.out.println("Please enter an integer between 1 and 3 inclusive.");
			}
		}while (numReq == null);
		
		return numReq;
	}

	/**
	 * Checks if a name is a duplicate.
	 * @param name Name to check for duplication.
	 * @param nameList List of already taken names.
	 * @return Whether or not the name is a duplicate.
	 */
	private static boolean nameTaken(String name, ArrayList<String> nameList){
		boolean found = false;
		int i = 0;
		
		while (!found && (i < nameList.size())){
			if (nameList.get(i).equals(name))
					found = true;
			i++;
		}
		
		return found;
	}
	
	/**
	 * Get the name of a player or pet.
	 * @param query Query to pose to user.
	 * @param nameList ArrayList of taken names.
	 * @return name of player or pet.
	 */
	public static String getName(String query, ArrayList<String> nameList){
		String name = null;
		
		do{
			System.out.print(query);
			System.out.flush();
			name = inputReader.next();
			if (nameTaken(name, nameList)){ //if name is already taken
				name = null;
				System.out.println("Duplicate names are not allowed.");
			}
		} while (name == null);
		
		nameList.add(name);
		return name;
	}
	
	
	//TODO: Ask Ollie how to handle IOException from Pet class
	/**
	 * Creates a pet object based on user input.
	 * @return pet of species desired by player.
	 * @throws IOException because it isn't handled at lower levels.
	 */
	public static Pet createPetSpecies() throws IOException{
		Pet newPet = new Cat(); //default to pet
		String choice;
		//Get pet species
		do{
			System.out.println("1. Alpaca\n2. Cat\n3. Dog\n4. Goat\n5. Horse\n6. Polar bear");
			System.out.print("Which pet would you like? ");
			System.out.flush();

			choice = inputReader.next();

			switch(choice.toLowerCase()){
			case "1":
			case "alpaca":
			case "aplaca":
			case "apalca":
				newPet = new Alpaca();
				break;

			case "2":
			case "cat":
				newPet = new Cat();
				break;

			case "3":
			case "dog":
				newPet = new Dog();
				break;

			case "4":
			case "goat":
				newPet = new Goat();
				break;
				

			case "5":
			case "horse":
			case "hoarse":
				newPet = new Horse();
				break;

			case  "6":
			case "polar bear":
			case "poler bear":
			case "polarbear":
			case "polerbear":
			case "polar":
			case "poler":
			case "bear":
			case "beer":
			case "bare":
				newPet = new PolarBear();
				break;

			default:
				System.out.println(choice + " is not a valid option. Please enter one of the below choices.");
				choice = null;
				break;
			}
		}while (choice == null);
		
		return newPet;
	}
	
	
	/**
	 * Main game loop for one player for one day.
	 */
	public static void gameLoop(Player[] playerList, int dayNumber){
		System.out.println("Day "+dayNumber);
		for (Player player : playerList){
			System.out.println("Player "+player.getName()+"'s turn.");
		}
	}
	
	/**
	 * Tidy up to close gracefully.
	 */
	public static void tearDown(){
		inputReader.close();
	}
}

