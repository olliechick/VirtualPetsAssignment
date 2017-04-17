import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.IOException;

/**
 * Launching off point for Virtual Pets game.
 * @author Samuel Pell
 *
 */
public class GameEnvironment {
	
	private Player[] playerList;
	private ArrayList<String> nameList = new ArrayList<String>();
	private int dayNumber;
	private int numberOfDays;
	private Random randomNumGen;
	private Scanner inputReader = new Scanner(System.in);
	
	
	/**
	 * Gets the number of days the game is to run for.
	 * @return Number of days to run game for.
	 */
	private int getNumberOfDays(){
		Integer numDays = null;
		
		do{
			System.out.print("How many days?    ");
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
	private int getNumberRequired(String query){
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
	 * @return Whether or not the name is a duplicate.
	 */
	private boolean nameTaken(String name){
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
	 * @return name of player or pet.
	 */
	private String getName(String query){
		String name = null;
		
		do{
			System.out.print(query);
			System.out.flush();
			name = inputReader.next();
			if (nameTaken(name)){ //if name is already taken
				name = null;
				System.out.println("Duplicate names are not allowed.");
			}
		} while (name == null);
		
		nameList.add(name);
		return name;
	}
	
	/**
	 * Sets the name for a new player.
	 * @param newPlayer Player object to set name for.
	 */
	private void setPlayerName(Player newPlayer){
		try{
			newPlayer.setName(getName("Player name: "));	
		}catch (IllegalArgumentException exception){
			System.out.println("Unknown error. Please try again.");
		}
	}
	
	//TODO: Ask Ollie how to handle IOException from Pet class
	/**
	 * Creates a pet object based on user input
	 * @return pet of species desired by player
	 * @throws IOException because it isn't handled at lower levels
	 */
	private Pet createPetSpecies() throws IOException{
		Pet newPet = new Cat();
		String choice;
		//Get pet species
		do{
			System.out.println("1- Alpaca\n2- Cat\n3- Dog\n4- Goat\n5- Horse\n6- Polar Bear");
			System.out.print("What Pet would you like?    ");
			System.out.flush();

			choice = inputReader.next();

			switch(choice.toLowerCase()){ //TODO: When all animals implemented add appropriate calls
			case "1":
			case "alpaca":
				System.out.println("Creating " + choice);
				break;

			case "2":
			case "cat": 
				System.out.println("Creating " + choice);
				newPet = new Cat();
				break;

			case "3":
			case "dog":
				System.out.println("Creating " + choice);
				newPet = new Dog();
				break;

			case "4":
			case "goat":

			case "5":
			case "horse":

			case  "6":
			case "polar bear":
				System.out.println("Creating " + choice);
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
	 * Creates a pet for a player
	 * @return Pet player has designed
	 * @throws IOException
	 */
	private Pet createPet() throws IOException{
		Pet newPet = createPetSpecies();
		
		try{
			newPet.setName(getName("Pet name: "));	
		}catch (IllegalArgumentException exception){
			System.out.println("Unknown error. Please try again.");
		}
		//TODO: Finish implementing createPet
		return newPet;
	}
	
	/**
	 * Creates a new player
	 * @return A fully created player
	 */
	private Player createPlayer() throws IOException{
		Player newPlayer = new Player();
		setPlayerName(newPlayer);
		int numPets = getNumberRequired("How many pets for player " + newPlayer.getName() + "?    ");
		
		ArrayList<Pet> playerPetList = newPlayer.getPetList();
		Pet newPet;
		for (int i = 0; i < numPets; i++){
			newPet = createPet();
			playerPetList.add(newPet);
		}
		return newPlayer;
	}
	
	/**
	 * Tidy up to close gracefully
	 */
	private void tearDown(){
		inputReader.close();
	}
	
	
	/**
	 * Performs all setup for the game.
	 * 
	 * Creates players, their pets, and a prototype of each Toy and Food.
	 */
	private void setup() throws IOException{
		numberOfDays = getNumberOfDays();
		dayNumber = 1;
		int numPlayers = getNumberRequired("How many players?    ");
		playerList = new Player[numPlayers];
	
		for (int i = 0; i < numPlayers; i++){
			playerList[i] = createPlayer();
		}		
	}
	
	
	/**
	 * Main entry point.
	 * @param args Arguments - don't really have many of them
	 */
	public static void main(String[] args) throws IOException{
		//Testing if setup works
		GameEnvironment mainGame = new GameEnvironment();
		mainGame.setup();
		mainGame.tearDown();
	}
	

}
