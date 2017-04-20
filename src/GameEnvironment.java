import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.io.BufferedReader;
import java.io.FileReader;
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
	private HashMap<String, Food> foodPrototypes;
	private HashMap<String, Toy> toyPrototypes;
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
	 * Gets lines 2 onwards from a data file specified.
	 * @param fileName File to get data from.
	 * @return ArrayList of each line as a string.
	 */
	private ArrayList<String> getDataFromFile(String fileName){
		fileName = System.getProperty("user.dir") + "/src/" + fileName;
		String line;
		ArrayList<String> data = new ArrayList<String>();
		
		try{
			FileReader inputFile = new FileReader(fileName);
			BufferedReader bufferReader = new BufferedReader(inputFile);
			
			bufferReader.readLine(); //ignore first line
			
			while ((line = bufferReader.readLine()) != null){
				data.add(line);
			}
			
			bufferReader.close(); //tidy up after reading file
			inputFile.close();
		}catch (IOException e){ 
			//If there is an IO error here just give up.
			System.err.println("Error while reading file line by line:" + e.getMessage());
			System.exit(0);
		}
		
		return data;
	}
	
	
	/**
	 * Parses a line into food or toy info to create prototype items
	 * 
	 * Takes a line read from a data file, splits it, and then iterates along the columns.
	 * Maps the columns to data fields and then returns an object array of the information
	 * @param line String taken from either foodData or toyData files
	 * @param mapping Mapping generated from the first line of columns to fields
	 * @return Object array in format {String name, String description, Integer price, Integer size, String[] speciesOrder, Intger[] increase}
	 */
	private Object[] parseLine(String line, HashMap<Integer, String> mapping){
		String[] splitLine = line.split(",");
		 
		//Create returnable fields
		String name = new String();
		String description = new String();
		Integer price = new Integer(0);
		Integer size = new Integer(0);
		Integer[] increase = new Integer[mapping.keySet().size() - 4]; //number of columns - number of columns common to each animal
		String[] speciesOrder = new String[mapping.keySet().size() - 4];
		
		int i = 0;
		for(int col = 0; col < splitLine.length; col++){ //for each column look at the field header and decide where the data lives
			switch(mapping.get(col)){
				case "name":
					name = splitLine[col];
					break;
				case "description":
					description = splitLine[col];
					break;
				case "price":
					price = Integer.parseInt(splitLine[col]);
					break;
				case "durability":
				case "portionSize":
					size = Integer.parseInt(splitLine[col]);
					break;
				default:
					if(mapping.get(col).substring(0, 16).equals("increaseHappiness")){
						speciesOrder[i] = mapping.get(col).substring(17);
						increase[i] = Integer.parseInt(splitLine[col]);
						i++;
					}else if(mapping.get(col).substring(0, 14).equals("increaseHealth")){
						speciesOrder[i] = mapping.get(col).substring(14);
						increase[i] = Integer.parseInt(splitLine[col]);
						i++;
					}
					break;
			}
		}
		
		//TODO: This isn't very Java like but it reduces code duplication
		return new Object[] {name, description, price, size, speciesOrder, increase};
	}
	
	/**
	 * Generates all food prototypes for game
	 */
	private void generateFoodPrototypes(){
		foodPrototypes = new HashMap<String, Food>();
		ArrayList<String> data = getDataFromFile("foodData.csv");
		HashMap<Integer, String> mapping = new HashMap<Integer, String>(); //mapping of columns number to fields
		
		String[] firstLine = data.get(0).split(",");
		data.remove(0); //remove the first line so later iteration is easier
		
		for(int i = 0; i < firstLine.length; i++){
			mapping.put(i, firstLine[i]); //create mapping of columns to fields
		}
		
		for(String line: data){
			//TODO: any recomendations on tidying this code?
			Object[] information = parseLine(line, mapping);
			Food newFood = new Food((String) information[0], (String) information[1], (Integer) information[2], (Integer) information[3]);
			newFood.setHealthIncrease((String[]) information[4], (Integer[]) information[5]);
			
			foodPrototypes.put((String) information[0], newFood);
		}
	}
	
	/**
	 * 
	 */
	private void generateToyPrototypes(){
		toyPrototypes = new HashMap<String, Toy>();
		ArrayList<String> data = getDataFromFile("toyData.csv");
		HashMap<Integer, String> mapping = new HashMap<Integer, String>(); //mapping of columns number to fields
		
		String[] firstLine = data.get(0).split(",");
		data.remove(0); //remove the first line so later iteration is easier
		
		for(int i = 0; i < firstLine.length; i++){
			mapping.put(i, firstLine[i]); //create mapping of columns to fields
		}
		
		for(String line: data){
			//TODO: any recomendations on tidying this code?
			Object[] information = parseLine(line, mapping);
			Toy newToy = new Toy((String) information[0], (String) information[1], (Integer) information[2], (Integer) information[3]);
			newToy.setHappinessIncrease((String[]) information[4], (Integer[]) information[5]);
			
			toyPrototypes.put((String) information[0], newToy);
		}
	}
	
	/**
	 * Creates a pet for a player
	 * @return Pet player has designed
	 * @throws IOException Pet creation side effect
	 */
	private Pet createPet() throws IOException{
		Pet newPet = createPetSpecies();
		
		try{
			newPet.setName(getName("Pet name: "));	
		}catch (IllegalArgumentException exception){
			System.out.println("Unknown error. Please try again.");
		}
		
		Boolean genderDecider = randomNumGen.nextBoolean();
		if (genderDecider){ //gender decided by randomNumGen
			newPet.setGender("female");
		} else{
			newPet.setGender("male");
		}
		
		return newPet;
	}
	
	
	/**
	 * Creates a new player
	 * @return A fully created player
	 * @throws IOException Pet creation side effect
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
	 * @throws IOException Pet creation side effect
	 */
	private void setup() throws IOException{
		numberOfDays = getNumberOfDays();
		dayNumber = 1;
		int numPlayers = getNumberRequired("How many players?    ");
		playerList = new Player[numPlayers];
	
		for (int i = 0; i < numPlayers; i++){
			playerList[i] = createPlayer();
			
			//DEBUG
			for (Pet pet: playerList[i].getPetList()){
				System.out.println(pet);
			}
			//DEBUG
		}
		
		generateToyPrototypes();
		generateFoodPrototypes();
	}
	
	
	/**
	 * Sets up random number generator for testing.
	 * @param args Only argument is a seed of type long for the generator.
	 */
	private void initialiseNumGenerator(String[] args){
		if (args.length == 1){
			randomNumGen = new Random(Long.parseLong(args[0]));
		}else {
			randomNumGen = new Random();
		}
	}
	
	
	/**
	 * Main entry point.
	 * @param args Arguments - don't really have many of them
	 * @throws IOException So it doesn't complain at me
	 */
	public static void main(String[] args) throws IOException{
		//Testing if setup works
		GameEnvironment mainGame = new GameEnvironment();
		mainGame.initialiseNumGenerator(args);
		mainGame.setup();
		mainGame.tearDown();
	}
	

}
