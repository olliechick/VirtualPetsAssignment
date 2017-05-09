import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


/**
 * Launching off point for Virtual Pets game.
 * @author Samuel Pell
 * @author Ollie Chick
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
	
	
	/**
	 * Sets the name for a new player.
	 * @param newPlayer Player object to set name for.
	 */
	private void setPlayerName(Player newPlayer){
		try{
			newPlayer.setName(CommandLineInterface.getName("Player name: ", nameList));	
		}catch (IllegalArgumentException exception){
			System.out.println("Unknown error. Please try again.");
		}
	}
	
	
	/**
	 * Gets lines 2 onwards from a data file specified.
	 * @param fileName File to get data from.
	 * @return ArrayList of each line as a string.
	 */
	private ArrayList<String> getDataFromFile(String fileName){
		String topDir = System.getProperty("user.dir");
		System.out.println(topDir);
		if (topDir.endsWith("bin")){
			fileName = "../src/" + fileName;
		}else{
			fileName = System.getProperty("user.dir")  + "/src/" + fileName;
		}
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
			System.err.println("Error while reading file line by line: " + e.getMessage());
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
	 * @return Object array in format {String name, String description, Integer price, Integer size, String[] speciesOrder, Integer[] increase}
	 */
	private String[][] parseLine(String line, HashMap<Integer, String> mapping){
		String[] splitLine = line.split(",");
		 
		//Create returnable fields
		String[] name = new String[1];
		String[] description = new String[1];
		String[] price = new String[1];
		String[] size = new String[1];
		String[] increase = new String[mapping.keySet().size() - 4]; //number of columns - number of columns common to each animal
		String[] speciesOrder = new String[mapping.keySet().size() - 4];
		
		int i = 0;
		for(int col = 0; col < splitLine.length; col++){ //for each column look at the field header and decide where the data lives
			switch(mapping.get(col)){
				case "name":
					name[0] = splitLine[col];
					break;
				case "description":
					description[0] = splitLine[col];
					break;
				case "price":
					price[0] = splitLine[col];
					break;
				case "durability":
				case "portionSize":
					size[0] = splitLine[col];
					break;
				default:
					if(mapping.get(col).substring(0, 17).equals("increaseHappiness")){
						speciesOrder[i] = formatSpeciesName(mapping.get(col).substring(17));
						increase[i] = splitLine[col];
						i++;
					}else if(mapping.get(col).substring(0, 14).equals("increaseHealth")){
						speciesOrder[i] = formatSpeciesName(mapping.get(col).substring(14));
						increase[i] = splitLine[col];
						i++;
					}
					break;
			}
		}
		
		//This isn't very Javalike but it reduces code duplication
		return new String[][] {name, description, price, size, speciesOrder, increase};
	}
	
	/**
	 * Format species name in a uniform way. 
	 * 
	 * In the item data csv files the headings aren't formatted the same way as the pet species
	 * are used in code. This function converts between them. It does this by making the
	 * species header lower case, comparing this against a list of special cases, applying any 
	 * necessary changes and returning that formatted string.
	 * @param unformatted unformatted species name
	 * @return formatted species name
	 */
	private String formatSpeciesName(String unformatted){
		String formatted;
		unformatted = unformatted.toLowerCase();
		switch(unformatted){
			case "polarbear":
				formatted = "polar bear";
				break;
			default:
				formatted = unformatted;
				break;
		}
		return formatted;
	}
	
	/**
	 * Generates all food prototypes for game from "foodData.csv"
	 */
	public void generateFoodPrototypes(){
		foodPrototypes = new HashMap<String, Food>();
		ArrayList<String> data = getDataFromFile("foodData.csv");
		HashMap<Integer, String> mapping = new HashMap<Integer, String>(); //mapping of columns number to fields
		
		String[] firstLine = data.get(0).split(",");
		data.remove(0); //remove the first line so later iteration is easier
		
		for(int i = 0; i < firstLine.length; i++){
			mapping.put(i, firstLine[i]); //create mapping of columns to fields
		}
		
		for(String line: data){
			String[][] information = parseLine(line, mapping);
			String name = information[0][0];
			String description = information[1][0];
			int price = Integer.parseInt(information[2][0]);
			int portionSize = Integer.parseInt(information[3][0]);
			Food newFood = new Food(name, description, price, portionSize);
			newFood.setHealthIncrease(information[4], information[5]);
			
			foodPrototypes.put(name, newFood);
		}
	}
	
	/**
	 * Generates all toy prototypes for game from "toyData.csv"
	 */
	public void generateToyPrototypes(){
		toyPrototypes = new HashMap<String, Toy>();
		ArrayList<String> data = getDataFromFile("toyData.csv");
		HashMap<Integer, String> mapping = new HashMap<Integer, String>(); //mapping of columns number to fields
		
		String[] firstLine = data.get(0).split(",");
		data.remove(0); //remove the first line so later iteration is easier
		
		for(int i = 0; i < firstLine.length; i++){
			mapping.put(i, firstLine[i]); //create mapping of columns to fields
		}
		
		for(String line: data){
			String[][] information = parseLine(line, mapping);
			
			String name = information[0][0];
			String description = information[1][0];
			int price = Integer.parseInt(information[2][0]);
			int durability = Integer.parseInt(information[3][0]);
			
			Toy newToy = new Toy(name, description, price, durability);
			newToy.setHappinessIncrease(information[4], information[5]);
			
			toyPrototypes.put(name, newToy);
		}
	}
	
	/**
	 * Creates a pet for a player
	 * @return Pet player has designed
	 * @throws IOException Pet creation side effect
	 */
	private Pet createPet() throws IOException{
		Pet newPet = CommandLineInterface.createPetSpecies();
		
		try{
			newPet.setName(CommandLineInterface.getName("Pet name: ", nameList));	
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
		int numPets = CommandLineInterface.getNumberRequired("Hi " + newPlayer.getName() + "! How many pets do you want? ");
		
		ArrayList<Pet> playerPetList = newPlayer.getPetList();
		Pet newPet;
		for (int i = 0; i < numPets; i++){
			newPet = createPet();
			playerPetList.add(newPet);
		}
		return newPlayer;
	}
	
	/**
	 * Performs all setup for the game.
	 * 
	 * Creates players, their pets, and a prototype of each Toy and Food.
	 * @throws IOException Pet creation side effect
	 */
	private void setup() throws IOException{
		numberOfDays = CommandLineInterface.getNumberOfDays();
		dayNumber = 1;
		int numPlayers = CommandLineInterface.getNumberRequired("How many players? ");
		playerList = new Player[numPlayers];
	
		for (int i = 0; i < numPlayers; i++){
//			System.out.println("----------- Player Creation ----------");
			playerList[i] = createPlayer();
		}
		
		generateToyPrototypes();
		generateFoodPrototypes();
	}
	
	/**
	 * Tears down the game
	 */
	private void tearDown(){
		CommandLineInterface.tearDown();
	}
	
	
	/**
	 * Sets up random number generator for testing.
	 * @param args Only argument is a seed of type long for the generator.
	 */
	public void initialiseNumGenerator(String[] args){
		if (args.length == 1){
			randomNumGen = new Random(Long.parseLong(args[0]));
		}else {
			randomNumGen = new Random();
		}
	}
	
	
	/**
	 * Method to return a list of food prototypes - for testing.
	 * @return Hash map of food prototypes mapping from food name to food prototype.
	 */
	public HashMap<String, Food> getFoodPrototypes(){
		return foodPrototypes;
	}
	
	/**
	 * Method to return a list of toy prototypes - for testing
	 * @return Hash map of toy prototypes mapping from food name to food prototype.
	 */
	public HashMap<String, Toy> getToyPrototypes(){
		return toyPrototypes;
	}
	
	/**
	 * After the game, tells the user the scores, etc.
	 */
	private void postGame(){
		
		CommandLineInterface.postGame(playerList);
	}
	
	/**
	 * Main game loop
	 */
	private void gameLoop(){
		while (dayNumber<=numberOfDays){
			CommandLineInterface.newDay(dayNumber);
			for (Player player : playerList){
				CommandLineInterface.newPlayer(player);
				for (Pet pet : player.getPetList()){
					CommandLineInterface.interact(player, pet, foodPrototypes, toyPrototypes);
				}
			}
			dayNumber++;
		}
	}
	
	/**
	 * Main entry point.
	 * @param args Arguments - don't really have many of them
	 * @throws IOException So it doesn't complain at me
	 */
	public static void main(String[] args) throws IOException{
		GameEnvironment mainGame = new GameEnvironment();
		
		//Testing if setup works
//		mainGame.testStore();
		
		
		mainGame.initialiseNumGenerator(args);
		mainGame.setup();
		mainGame.generateToyPrototypes();
		mainGame.generateFoodPrototypes();
		mainGame.gameLoop();
		mainGame.postGame();
		
		mainGame.tearDown();
		
	}
}
