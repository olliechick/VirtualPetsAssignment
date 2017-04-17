import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

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
	 * Gets the number of days the game is to run for
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
	 * @return Number of players.
	 */
	private int getNumberOfPlayers(){
		Integer numPlayers = null;
		
		do{
			System.out.print("How many players? (1-3)    ");
			System.out.flush();
			String userInput = inputReader.next();
			try{
				numPlayers = Integer.parseInt(userInput);
				if (numPlayers < 1 || numPlayers > 3){ 
					//if number is out of bounds 1-3 throw exception
					numPlayers = null;
					throw new NumberFormatException();
				}
			}catch (NumberFormatException exception){
				System.out.println("Please enter an integer between 1 and 3 inclusive.");
			}
		}while (numPlayers == null);
		
		return numPlayers;
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
	 * Sets the name for a new player.
	 * @param newPlayer Player object to set name for.
	 */
	private void setPlayerName(Player newPlayer){
		String playerName = null;
		
		do{
			System.out.print("Player Name:    ");
			System.out.flush();
			playerName = inputReader.next();
			
			try{
				if (nameTaken(playerName)){ //if name is already taken
					playerName = null;
					throw new IllegalArgumentException("Reserved name");
				}else{ //otherwise just set the name
					newPlayer.setName(playerName);
					nameList.add(playerName);
				}
			}catch (IllegalArgumentException exception){
				
				if (exception.getMessage() == "Reserved name")
					System.out.println("Duplicate names are not allowed.");
				else
					System.out.println("Unknown error. Please try again.");
			}
		} while (playerName == null);
	}
	
	private int numberOfPets(){
		//TODO: Add method to get number of pets desired
		return numPets;
	}
	
	private Pet createPet(){
		//TODO: Add method to create a pet
		return newPet;
	}
	
	/**
	 * Creates a new player
	 * @return A fully created player
	 */
	private Player createPlayer(){
		Player newPlayer = new Player();
		setPlayerName(newPlayer);
		int numPets = numberOfPets();
		for (int i = 0; i < numPets; i++){
			newPlayer.getPetList().add(createPet());
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
	private void setup(){
		numberOfDays = getNumberOfDays();
		dayNumber = 1;
		int numPlayers = getNumberOfPlayers();
		playerList = new Player[numPlayers];
	
		for (int i = 0; i < numPlayers; i++){
			playerList[i] = createPlayer();
		}		
	}
	
	
	/**
	 * Main entry point.
	 * @param args Arguments - don't really have many of them
	 */
	public static void main(String[] args){
		//Testing if setup works
		GameEnvironment mainGame = new GameEnvironment();
		mainGame.setup();
		mainGame.tearDown();
	}
	

}
