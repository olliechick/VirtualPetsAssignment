package virtualpets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;


/**
 * Launching off point for Virtual Pets game.
 *
 * @author Samuel Pell
 * @author Ollie Chick
 *
 */
public class GameEnvironment {
    /**
     * The current player.
     */
    private Player currentPlayer;
    /**
     * The list of players.
     */
    private ArrayList<Player> playerList = new ArrayList<Player>();
    /**
     * The list of names used so far. This is used to avoid duplicate names.
     */
    private ArrayList<String> nameList = new ArrayList<String>();
    /**
     * HashMap of all foods. It maps the name of the food to an instance of the
     * food.
     */
    private HashMap<String, Food> foodPrototypes;
    /**
     * HashMap of all toys.
     * It maps the name of the toy to an instance of the toy.
     */
    private HashMap<String, Toy> toyPrototypes;
    /**
     * The current day number.
     */
    private int dayNumber;
    /**
     * The total number of days the game will run for.
     */
    private int numberOfDays;
    /**
     * How much each player gets per pet per day, in dollars.
     */
    private int dailyPetAllowance;
    /**
     * The random number generated, used for random events. Random events
     * include misbehaving, being sick, and dying.
     */
    private Random randomNumGen;

    /**
     * Parses a line into food or toy info to create prototype items
     *
     * Takes a line read from a data file, splits it, and then iterates along
     * the columns. Maps the columns to data fields and then returns an object
     * array of the information
     *
     * @param line
     *            String taken from either foodData or toyData files
     * @param mapping
     *            Mapping generated from the first line of columns to fields
     * @return Object array in format {String name, String description, Integer
     *         price, Integer size, String[] speciesOrder, Integer[] increase}
     */
    private String[][] parseLine(String line, HashMap<Integer, String> mapping) {
        String[] splitLine = line.split(",");

        // Create returnable fields
        String[] name = new String[1];
        String[] description = new String[1];
        String[] price = new String[1];
        String[] size = new String[1];
        String[] increase = new String[mapping.keySet().size() - 4];
        // number of columns - number of columns common to each animal.
        String[] speciesOrder = new String[mapping.keySet().size() - 4];

        int i = 0;
        for (int col = 0; col < splitLine.length; col++) {
            // for each column look at the field header and decide
            // where the data lives
            switch (mapping.get(col)) {
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
                if (mapping.get(col).substring(0, 17).equals("increaseHappiness")) {
                    speciesOrder[i] = formatSpeciesName(mapping.get(col).substring(17));
                    increase[i] = splitLine[col];
                    i++;
                } else if (mapping.get(col).substring(0, 14).equals("increaseHealth")) {
                    speciesOrder[i] = formatSpeciesName(mapping.get(col).substring(14));
                    increase[i] = splitLine[col];
                    i++;
                }
                break;
            }
        }

        // This isn't very Javalike but it reduces code duplication
        return new String[][] {name, description, price, size, speciesOrder, increase};
    }

    /**
     * Format species name in a uniform way.
     *
     * In the item data csv files the headings aren't formatted the same way as
     * the pet species are used in code. This function converts between them. It
     * does this by making the species header lower case, comparing this against
     * a list of special cases, applying any necessary changes and returning
     * that formatted string.
     *
     * @param unformatted Unformatted species name.
     * @return Formatted species name.
     */
    private String formatSpeciesName(String unformatted) {
        String formatted;
        unformatted = unformatted.toLowerCase();
        switch (unformatted) {
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
     * Generates all food prototypes for game from the file foodData.csv.
     */
    public void generateFoodPrototypes() {
        foodPrototypes = new HashMap<String, Food>();
        ArrayList<String> data = getDataFromFile("foodData.csv");
        //Create map of column number to fields
        HashMap<Integer, String> mapping = new HashMap<Integer, String>();

        String[] firstLine = data.get(0).split(",");
        data.remove(0); // remove the first line so later iteration is easier

        for (int i = 0; i < firstLine.length; i++) {
            mapping.put(i, firstLine[i]); // create mapping of columns to fields
        }

        for (String line : data) {
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
     * Generates all toy prototypes for game from the file toyData.csv.
     */
    public void generateToyPrototypes() {
        toyPrototypes = new HashMap<String, Toy>();
        ArrayList<String> data = getDataFromFile("toyData.csv");
        //Create map of column number to fields
        HashMap<Integer, String> mapping = new HashMap<Integer, String>();

        String[] firstLine = data.get(0).split(",");
        data.remove(0); // remove the first line so later iteration is easier

        for (int i = 0; i < firstLine.length; i++) {
            mapping.put(i, firstLine[i]); // create mapping of columns to fields
        }

        for (String line : data) {
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
     * Sets the name for a new player.
     *
     * @param newPlayer
     *            Player object to set name for.
     */
    private void setPlayerName(Player newPlayer) {
        try {
            newPlayer.setName(CommandLineInterface.getName("Player name: ", nameList));
        } catch (IllegalArgumentException exception) {
            System.out.println("Unknown error. Please try again.");
        }
    }

    /**
     * Sets up random number generator for testing.
     *
     * @param args Only argument is a seed of type long for the generator.
     */
    public void initialiseNumGenerator(String[] args) {
        if (args.length == 1) {
            randomNumGen = new Random(Long.parseLong(args[0]));
        } else {
            randomNumGen = new Random();
        }
    }

    /**
     * Method to return a list of food prototypes - for testing purposes.
     * @return HashMap of food prototypes mapping from food name to food prototype.
     */
    public HashMap<String, Food> getFoodPrototypes() {
        return foodPrototypes;
    }

    /**
     * Method to return a list of toy prototypes - for testing purposes.
     * @return HashMap of toy prototypes mapping from toy name to toy prototype.
     */
    public HashMap<String, Toy> getToyPrototypes() {
        return toyPrototypes;
    }

    /**
     * After the game, tells the user the scores, etc.
     * @throws Exception if error in code
     */
    private void postGame() throws Exception {
        for (int i = 0; i < playerList.size(); i++) {
            playerList.get(i).calculateScore();
        }
        Player[] rankedPlayers = rankPlayers();
        CommandLineInterface.postGame(rankedPlayers);
    }

    /**
     * This runs just before a user interacts with their pet. It does two
     * things:
     * 1. Increases the pet's fatigue, decreases its happiness,
     * increases its hunger, and increases its mischievousness.
     * 2. If the pet is very tired, it decreases its health.
     *
     * @param pet The pet the player is about to interact with.
     * @return
     */
    public void newDayPetActions(Pet pet) {


        pet.increaseFatigue(30);
        pet.increaseHappiness(-10);
        pet.increaseHunger(30);
        pet.increaseMischievousness(5);

        // If fatigue is especially high, reduce health.
        int fatigue = pet.getFatigue();
        if (fatigue > 80) {
            pet.increaseHealth(-10);
        } else if (fatigue > 90) {
            pet.increaseHealth(-25);
        } else if (fatigue == 100) {
            pet.increaseHealth(-50);
        }

    }

    /**
     * Checks if the pet is misbehaving.
     * The pet will misbehave based on its wellness, which is a weighted
     * combination of the pet's happiness, health, mischievousness, and hunger.
     * @param pet The pet who might be misbehaving.
     * @return Whether the pet is misbehaving.
     */
    public Boolean checkIfMisbehaving(Pet pet) {
        // create random number between 0 and 99
        int randomNumber = randomNumGen.nextInt(100);
        int wellness = (pet.getHappiness() * 3
                + pet.getHealth()
                + (100 - pet.getMischievousness()) * 5
                + (100 - pet.getHunger())) / 10;
        if (wellness < 25 && randomNumber < 75
                || wellness < 50 && randomNumber < 50
                || wellness < 75 && randomNumber < 25) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if the pet is sick.
     * The pet will be sick if:
     * the pet was already sick
     * the pet has health below 5%.
     * It could also become sick randomly, if:
     * the pet has health below 25%, it has a 75% chance of becoming sick
     * the pet has health below 50%, it has a 50% chance of becoming sick
     * the pet has health below 75%, it has a 25% chance of becoming sick
     * @param pet The pet who might be sick.
     * @return Whether the pet is sick.
     */
    public Boolean checkIfSick(Pet pet) {
        // create random number between 0 and 99
        int randomNumber = randomNumGen.nextInt(100);
        int health = pet.getHealth();
        if (pet.getIsSick()
                || health < 5
                || health < 25 && randomNumber < 75
                || health < 50 && randomNumber < 50
                || health < 75 && randomNumber < 25) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks it the pet has died.
     * The pet will die if:
     * the pet is sick and has happiness less than 50%
     * its health is less than 5%
     * 2% chance of random death
     * @param pet The pet who might be dead.
     * @return Whether the pet is dead.
     */
    public Boolean checkIfDead(Pet pet) {

        /*
         * Check if dead
         */
        // create random number between 0 and 99
        int randomNumber = randomNumGen.nextInt(100);
        if (pet.getIsSick() && pet.getHappiness() < 50 || pet.getHealth() < 5 || randomNumber < 2) {
            return true;
        }

        return false;

    }

    /**
     * Ranks players based on score in descending order of score. Assumes all
     * scores have been calculated beforehand.
     *
     * @return ranked list of players.
     */
    public Player[] rankPlayers() {
        // Add players to an ArrayList
        ArrayList<Player> rankedList = new ArrayList<Player>();
        for (int i = 0; i < playerList.size(); i++) {
            rankedList.add(playerList.get(i));
        }

        // Sort the players
        rankedList.sort(null);

        // Convert ArrayList to array and return array
        Player[] rankedArray = new Player[rankedList.size()];
        rankedArray = rankedList.toArray(rankedArray);

        return rankedArray;
    }

    /**
     * Method purely for testing purposes. Overwrites existing playerList with
     * its own array of players.
     *
     * @param playerArray Fully setup list of players.
     */
    public void addPlayers(ArrayList<Player> playerArray) {
        playerList = playerArray;
    }

    /**
     * Get the number of days the game will go on for.
     * @return number of days game will run for.
     */
    public int getNumDays(){
        return numberOfDays;
    }

    /**
     * Get the player who's turn it currently is.
     * @return current player.
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Gets lines 2 onwards from a data file specified.
     * @param fileName File to get data from.
     * @return ArrayList of each line as a string.
     */
    private ArrayList<String> getDataFromFile(String fileName) {
        ArrayList<String> data = new ArrayList<String>();
        String line;
        try {
            Reader inputFile;
            try { //Runs if running class directly
                String topDir = System.getProperty("user.dir");
                if (topDir.endsWith("bin")) { //from cmdln
                    fileName = "../config/" + fileName;
                } else { //from eclipse
                    fileName = "config/" + fileName;
                }
                inputFile = new FileReader(fileName);
            } catch (FileNotFoundException e) { //if running from jar file.
                InputStream stream = this.getClass().getResourceAsStream(fileName);
                inputFile = new InputStreamReader(stream);
            }

            BufferedReader bufferReader = new BufferedReader(inputFile);
            bufferReader.readLine(); //ignore first line

            while ((line = bufferReader.readLine()) != null) {
                data.add(line);
            }

            bufferReader.close(); //tidy up after reading file
            inputFile.close();
        } catch (IOException e) {
            //If there is an IO error here just give up.
            System.err.println("Error while reading file line by line: " + e.getMessage());
            System.exit(0);
        }

        return data;
    }

    /**
     * Creates a new player and their pets.
     * @param values String array of data to convert into pet objects. Array should be of form
     * {"player name", "pet one name\npet one species", "pet two name\npet two species",...}
     * @throws IOException Pet creation side effect.
     * @throws Exception For an unknown pet type.
     */
    public void createPlayer(String[] values) throws IOException, Exception{
        Player newPlayer = new Player();
        newPlayer.setName(values[0]);
        String[] petData;
        for(int i = 1; i < values.length; i++){
            petData = values[i].split("\n");
            Pet newPet;
            switch (petData[1]){
                case "alpaca":
                    newPet = new Alpaca();
                    break;
                case "cat":
                    newPet = new Cat();
                    break;
                case "dog":
                    newPet = new Dog();
                    break;
                case "goat":
                    newPet = new Goat();
                    break;
                case "horse":
                    newPet = new Horse();
                    break;
                case "polar bear":
                    newPet = new PolarBear();
                    break;
                default:
                    throw new Exception("Error: Unknown Pet" + petData[1]);
            }
            newPet.setName(petData[0]);
            newPlayer.getPetList().add(newPet);
            Boolean genderDecider = randomNumGen.nextBoolean();
            if (genderDecider) { //gender decided by randomNumGen
                newPet.setGender("female");
            } else {
                newPet.setGender("male");
            }
            System.out.println(newPet.getGender());
        }
        playerList.add(newPlayer);
    }

    /**
     * Returns the list of players.
     * @return list of players
     */
    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    /**
     * Sets the current player.
     * @param p Current player.
     */
    public void setCurrentPlayer(Player p) {
        currentPlayer = p;
    }

    /**
     * Sets the number of days.
     * @param days Number of days.
     */
    public void setNumDays(int days) {
        numberOfDays = days;
    }
}
