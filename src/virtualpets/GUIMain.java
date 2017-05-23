package virtualpets;

import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.IntStream;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;


/**
 * Main GUI class for VirtualPets. Sort of a View-Controller.
 * @author Samuel Pell
 * @author Ollie Chick
 */
public class GUIMain implements Observer {
     /**
     * Frame in which all GUI elements appear.
     */
    private JFrame mainFrame;

    /**
     * Main model class.
     */
    private GameEnvironment mainGame;

    /**
     * Number of players playing.
     */
    private int numberOfPlayers;

    /**
     * List of names to check for duplicates against.
     */
    private ArrayList<String> nameList = new ArrayList<String>();

    /**
     * Number of players that have been created by the ViewController.
     */
    private int numPlayersCreated = 0;

    /**
     * Home panel to display all in game info.
     */
    private HomePanel homeScreen;

    /**
     * The current player.
     */
    private Player currentPlayer;

    /**
     * The current pet.
     */
    private Pet currentPet;

    /**
     * Index of the current pet.
     * This is the index of an imaginary array made by
     * concatenating the petLists of all the players, in order
     * given by mainGame.getPlayerList().
     */
    private int currentPetIndex;

    /**
     * Number of actions remaining for the current pet.
     */
    private int numActions = 2;

    /**
     * Number of pets each player has.
     * This is initialised to 0 so currentPetIndex is not less
     * than numOfPets[i] if Player i does not exist.
     */
    private int[] numOfPets = {0, 0, 0};

    /**
     * Combined list of all players' pets.
     */
    private ArrayList<Pet> combinedPetList = new ArrayList<>();

    /**
     * The total number of pets.
     */
    private int totalNumOfPets;

    /**
     * Is the current pet currently sleeping.
     */
    private Boolean currentlySleeping = false;

    /**
     * Is the current pet currently going to the toilet.
     */
    private Boolean currentlyOnToilet = false;

    /**
     * Part of the Observer pattern to get data from GUI to GameEnvironment.
     * @param identifier Identifier of the operation.
     * @param values Array of strings appropriate to the GUI call.
     */
    public void getValues(String identifier, String[] values) {
        switch (identifier) {
            case "setup":
                Integer numDays = Integer.parseInt(values[1]);
                mainGame.setNumDays(numDays);
                numberOfPlayers = Integer.parseInt(values[0]);
                clearFrame();
                createPlayer();
                break;

            case "player creation":
                numPlayersCreated++;
                try {
                    mainGame.createPlayer(values);
                } catch (IllegalArgumentException e) {
                    //Just give up something has gone really wrong
                    e.printStackTrace();
                    System.exit(0);
                } catch (IOException e) {
                    //Just give up something has gone really wrong
                    e.printStackTrace();
                    System.exit(0);
                }

                clearFrame();
                if (numPlayersCreated < numberOfPlayers) {
                    createPlayer();
                } else { //finished making players, display home
                    displayHome();

                }
                break;

            case "buy item":
                attemptBuyItem(values[0]);
                refreshScreen();
                break;

            case "sleep":
                currentPet.sleep();
                numActions--;
                currentlySleeping = true;
                refreshScreen();
                break;

            case "toilet":
                currentPet.goToilet();
                numActions--;
                currentlyOnToilet = true;
                refreshScreen();
                break;

            case "feed":
                Food food = mainGame.getFoodPrototypes().get(values[0]);
                currentPet.feed(food);
                currentPlayer.getFoodStock().remove(food);
                numActions--;
                refreshScreen();
                break;

            case "next":
                numActions = 0;
                break;

            default:
                System.err.println("Unknown GUI Element Identifier");
                System.err.println("Dropping data");
                System.err.println(identifier);
        }

        if (numActions == 0) {
            //finished interacting with this pet today
            nextPet();
            refreshScreen();
        }
    }

    /**
     * Overloaded part of the Observer pattern to get data from GUI to GameEnvironment.
     * Used to handle playing with toys.
     * @param identifier Identifier of the operation.
     * @param toy Toy appropriate to GUI call.
     */
    public void getValues(String identifier, Toy toy) {
        switch (identifier) {
        case "play":
            play(toy);
            numActions--;
            refreshScreen();
            break;

        default:
            System.err.println("Unknown GUI Element Identifier");
            System.err.println("Dropping data");
            System.err.println(identifier);
        }

        if (numActions == 0) {
            //finished interacting with this pet today
            nextPet();
            refreshScreen();
        }
    }

    /**
     * The pet plays with the toy. If if breaks it, the user will be notified,
     * and the toy will be removed from the player's inventory.
     * @param toy The toy the pet is playing with.
     */
    private void play(Toy toy) {
        try {
            currentPet.play(toy);
        } catch (IllegalArgumentException e) {
            if (e.getMessage().equals("durability is zero or negative")) {
                //they've used the toy to the point of destruction
                String message = currentPet.getName() + " broke the "
                                 + toy.getName();
                JOptionPane.showMessageDialog(mainFrame, message, null,
                                              JOptionPane.INFORMATION_MESSAGE);
                currentPlayer.getToyList().remove(toy);
            } else {
                //unknown error
                throw e;
            }
        }
    }

    /**
     * Player attempts to buy an item.
     * @param itemBought Item player is trying to purchase
     */
    private void attemptBuyItem(String itemBought) {
        HashMap<String, Food> foodItems = mainGame.getFoodPrototypes();
        HashMap<String, Toy> toyItems = mainGame.getToyPrototypes();

        try { //first try to see if item is a food
            Food purchasedFoodPrototype = foodItems.get(itemBought);
            if (purchasedFoodPrototype.getPrice() <= currentPlayer.getBalance()) {
                //spend the required amount
                currentPlayer.spend(purchasedFoodPrototype.getPrice());
                //add the food prototype to the current player's inventory
                //Note that if you wanted to
                currentPlayer.addFood(purchasedFoodPrototype);
            } else {
                String message = "You do not have enough money to purchase ";
                message += purchasedFoodPrototype.getName();
                JOptionPane.showMessageDialog(mainFrame, message, null,
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NullPointerException e) {
            //Ignore null pointer exceptions. Occurs when foodItems doesn't
            //contain the item.
        }

        try { //then see if item is a toy
            Toy purchasedToyPrototype = toyItems.get(itemBought);
            if (purchasedToyPrototype.getPrice() <= currentPlayer.getBalance()) {
                //spend the required amount
                currentPlayer.spend(purchasedToyPrototype.getPrice());
                //add a clone of the toy prototype to the current player's inventory
                currentPlayer.addToy(new Toy(purchasedToyPrototype));
            } else {
                String message = "You do not have enough money to purchase ";
                message += purchasedToyPrototype.getName();
                JOptionPane.showMessageDialog(mainFrame, message, null,
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NullPointerException e) {
            //Ignore null pointer exceptions. Occurs when toyItems doesn't
            //contain the item.
        }
    }

    /**
     * Refresh screen to display change in data.
     */
    private void refreshScreen() {
        int currentDay = mainGame.getCurrentDay();
        homeScreen.refreshTabs(currentPlayer, currentPet, currentDay,
                               numActions, currentlySleeping,
                               currentlyOnToilet);


        //Set the next button to say Next pet, Next day, or Finish as appropriate.
        if (currentPetIndex == totalNumOfPets) { //this is the last pet today
            if (mainGame.getCurrentDay() == mainGame.getNumDays()) {
                //this is the last day
                homeScreen.setNextButtonText("Finish");
            } else {
                homeScreen.setNextButtonText("Next day");
            }
        } else { //there are more pet(s) today
            int futurePetIndex = currentPetIndex + 1;
            //assume there are no alive pets to go today unless proved wrong
            Boolean alivePetToGo = false;
            while (!alivePetToGo && futurePetIndex < totalNumOfPets) {
                if (combinedPetList.get(futurePetIndex).getIsDead()) {
                    //the next pet is dead
                    futurePetIndex++;
                } else { //the next pet is alive
                    alivePetToGo = true;
                }
            }
            if (alivePetToGo) {
                homeScreen.setNextButtonText("Next pet");
            } else { // the rest of the pets today are dead
                if (mainGame.getCurrentDay() == mainGame.getNumDays()) {
                    //this is the last day
                    homeScreen.setNextButtonText("Finish");
                } else {
                    homeScreen.setNextButtonText("Next day");
                }
            }
        }
    }

    /**
     * Displays home screen to the user and moves onto the first day.
     */
    private void displayHome() {
        clearFrame();

        int numDays = mainGame.getNumDays();
        homeScreen = new HomePanel(numDays, mainGame);

        //register observers with view components
        homeScreen.getStoreTab().registerObserver(this);
        homeScreen.getFeedingTab().registerObserver(this);
        homeScreen.getSleepTab().registerObserver(this);
        homeScreen.getToiletTab().registerObserver(this);
        homeScreen.getPlayTab().registerObserver(this);
        homeScreen.registerObserver(this);

        mainFrame.getContentPane().add(homeScreen);
        homeScreen.setVisible(true);

        homeScreen.setSize(800, 500);
        mainFrame.setMinimumSize(new Dimension(815, 535));

        initialiseDays();
    }

    /**
     * Moves on to the next pet.
     * If all pets have been played with, it calls nextDay() to move on to the next day.
     * If the pet is alive, it sets the number of actions remaining to 2
     * and calls newDayPetActions() to initialise the pet for the day.
     * If the pet is dead, it moves on to the next pet.
     *
     */
    private void nextPet() {
        currentlySleeping = false; //reset toileting and sleeping to default
        currentlyOnToilet = false;
        homeScreen.returnToStatus(); //Returns player to status screen on new pet
        currentPetIndex++;
        Boolean initialiseThisPet = true; //Boolean to decide if currentPet should be initialised.
        Boolean everyPetIsDead = true; //Boolean to decide if we should just end it all.
        Boolean newPlayer = false; //Boolean to decide whether to initialise the player.
        // Message to display to the user in a popup if they kill all their pets
        everyPetIsDead = !hasAnAlivePet(combinedPetList);

        // If there are no alive pets left, popup a message and enter the postGame
        if (everyPetIsDead) {
            postGame();
            initialiseThisPet = false;

        } else { // If there are any alive pets, work out which is the new currentPet
            // If we've just finished with the last pet, then move on to the next day.
            int numOfPetsPlayers1and2 = numOfPets[0] + numOfPets[1];

            // Find out which is the next pet, and set that to currentPet
            if (currentPetIndex < numOfPets[0]) {
                // still on Player 1
                currentPet = currentPlayer.getPetList().get(currentPetIndex);

            } else if (numberOfPlayers >= 2 && currentPetIndex == numOfPets[0]) {
                // Player 2 exists, so we're moving on to Player 2
                newPlayer = true;
                currentPlayer = mainGame.getPlayerList().get(1);
                numOfPets[1] = mainGame.getPlayerList().get(1).getPetList().size();
                currentPet = currentPlayer.getPetList().get(0); //get the player's first pet

            } else if (currentPetIndex < numOfPetsPlayers1and2) {
                // still on Player 2
                currentPet = currentPlayer.getPetList().get(currentPetIndex - numOfPets[0]);

            } else if (numberOfPlayers >= 3 && currentPetIndex == numOfPetsPlayers1and2) {
                // Player 3 exists, so we're moving on to Player 3
                newPlayer = true;
                currentPlayer = mainGame.getPlayerList().get(2);
                numOfPets[2] = mainGame.getPlayerList().get(2).getPetList().size();
                currentPet = currentPlayer.getPetList().get(0); //get the player's first pet

            } else if (currentPetIndex < (numOfPetsPlayers1and2 + numOfPets[2])) {
                // still on Player 3
                currentPet = currentPlayer.getPetList().get(
                        currentPetIndex - numOfPetsPlayers1and2);

            } else {
                // All players finished
                // Time to move on to the next day.
                nextDay();
                if (mainGame.getCurrentDay() > mainGame.getNumDays()) {
                    initialiseThisPet = false;
                }
            }
        }

        //If this is a new player, initialise them if they have any alive pets
        if (newPlayer && hasAnAlivePet(currentPlayer.getPetList())) {
            initialisePlayer();
        }

        // If we haven't reached the end of the game, and the current pet is alive
        // initialise the pet
        if (initialiseThisPet && !currentPet.getIsDead()) {
            newDayPetActions();
        }

        refreshScreen();

        everyPetIsDead = !hasAnAlivePet(combinedPetList);

        // If the pet is dead
        if (currentPet.getIsDead()) {
            if (everyPetIsDead) { //just killed the last pet
                postGame();
            } else { //still some alive pets out there
                nextPet();
            }
        }
    }

    /**
     * Returns whether the petList provided contains any alive pets.
     * @param petList List of pets to check
     * @return If there are any alive pets
     */
    private Boolean hasAnAlivePet(ArrayList<Pet> petList) {
        Boolean alivePetExists = false;
        for (Pet pet : petList) { // for every pet in the pet list
            if (!pet.getIsDead()) { //if the pet isn't dead
                alivePetExists = true;
            }
        }
        return alivePetExists;
    }

    /**
     * Move to a new day.
     */
    private void nextDay() {
        mainGame.nextDay();

        // Calculate all the scores for today (well, technically yesterday now)
        for (Player player : mainGame.getPlayerList()) {
            player.calculateScore();
        }

        //init day if all days haven't finished
        if (mainGame.getCurrentDay() <= mainGame.getNumDays()) {
            currentPetIndex = 0;
            currentPlayer = mainGame.getPlayerList().get(0);
            currentPet = currentPlayer.getPetList().get(0);
            initialisePlayer();
            refreshScreen();
        } else { // game is over
            postGame();
        }
    }

    /**
     * Initialises a player's turn.
     * It calls a function that gives the player their daily allowance.
     * If there are multiple players, it pops up a window to say who's turn it is.
     */
    private void initialisePlayer() {
        mainGame.initialisePlayer(currentPlayer);

        if (numberOfPlayers > 1) { //if there are multiple players
            // Display a popup saying whose turn it is
            String message = "It is now " + currentPlayer.getName() + "'s turn.";
            JOptionPane.showMessageDialog(homeScreen, message, null,
                                          JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Ranks the players and show them their scores.
     */
    private void postGame() {

        //If they have killed all their pets, do a popup
        if (!hasAnAlivePet(combinedPetList)) {
            String murdererMessage = "Quite frankly you are horrible.\n"
                + "You managed to murder all your pets.\n"
                + "You should feel bad about what you've done.";
            JOptionPane.showMessageDialog(homeScreen, murdererMessage, null,
                                      JOptionPane.INFORMATION_MESSAGE);
        }

        clearFrame();
        ScoreboardPanel scorePanel = new ScoreboardPanel();

        mainFrame.getContentPane().add(scorePanel);
        scorePanel.setVisible(true);

        scorePanel.setSize(450, 200);
        mainFrame.setMinimumSize(new Dimension(465, 225));
        mainFrame.setSize(465, 225);

        //Rank the players
        Player[] players;
        players = mainGame.rankPlayers();

        scorePanel.showRanking(players);
    }

    /**
     * Initialises the days.
     */
    private void initialiseDays() {
        mainGame.nextDay(); //moves to Day 1
        currentPlayer = mainGame.getPlayerList().get(0);
        currentPet = currentPlayer.getPetList().get(0);
        newDayPetActions();
        refreshScreen();
        initialisePlayer();
        refreshScreen();

        totalNumOfPets = IntStream.of(numOfPets).sum();

        //Now create the combined list of pets and the numOfPets array
        switch (numPlayersCreated) {
        case(3):
            combinedPetList.addAll(mainGame.getPlayerList().get(2).getPetList());
            numOfPets[2] = mainGame.getPlayerList().get(2).getPetList().size();
        case(2):
            combinedPetList.addAll(mainGame.getPlayerList().get(1).getPetList());
            numOfPets[1] = mainGame.getPlayerList().get(1).getPetList().size();
        case(1):
            combinedPetList.addAll(mainGame.getPlayerList().get(0).getPetList());
            numOfPets[0] = mainGame.getPlayerList().get(0).getPetList().size();
            break;
        default:
            throw new IllegalArgumentException("numPlayersCreated is not 1, 2, or 3.");
        }
    }

    /**
     * Perform daily actions for a pet.
     * This includes increasing fatigue, hunger, etc.,
     * as well as prompting the user if the pet is misbehaving, sick, or dead.
     * If so, they have the option to discipline, treat, or revive,
     * depending on what happened.
     * @throws IllegalArgumentException if the pet is dead
     */
    private void newDayPetActions() throws IllegalArgumentException {
        Boolean misbehaving;
        Boolean sick;
        Boolean dead;

        Boolean disciplined;
        Boolean treated;
        Boolean revived;

        //throw an error if the pet is dead
        if (currentPet.getIsDead()) {
            String message = "Trying to do new day pet actions on a dead pet.";
            throw new IllegalArgumentException(message);
        }

        numActions = 2; //2 actions for this pet today

        mainGame.newDayPetActions(currentPet);
        refreshScreen();
        misbehaving = mainGame.checkIfMisbehaving(currentPet);
        if (misbehaving) {
            disciplined = askToDiscipline();
            if (disciplined) {
                currentPet.discipline();
            } else {
                currentPet.misbehave();
            }
            refreshScreen();
        }

        sick = mainGame.checkIfSick(currentPet);
        if (sick) {
            treated = askToTreat();
            if (treated) {
                currentPlayer.spend(50);
                currentPet.treat();
            } else {
                currentPet.beSick();
            }
            refreshScreen();
        }

        dead = mainGame.checkIfDead(currentPet);
        if (dead) {
            revived = askToRevive();
            if (revived) {
                currentPet.revive();
            } else {
                currentPet.die();
            }
            refreshScreen();
        }
    }

    /**
     * Asks the player to discipline their pet.
     * @return if the player disciplines their pet.
     */
    private boolean askToDiscipline() {
        String message = currentPet.getName() + " is misbehaving, would you "
                         + "like to discipline them?";
        int option = JOptionPane.showConfirmDialog(homeScreen, message, null,
                                                   JOptionPane.YES_NO_OPTION,
                                                   JOptionPane.QUESTION_MESSAGE);
        if (option == 1) { // if they select no
            return false; //TODO can these be simplified to return option == 0;?
        } else {
            return true;
        }
    }

    /**
     * Asks the player if they want to treat their sick pet.
     * @return if the player treats their pet.
     */
    private boolean askToTreat() {
        if (currentPlayer.getBalance() >= 50) {
            String message = currentPet.getName() + " is sick, would you "
                             + "like to treat them for $50?";
            int option = JOptionPane.showConfirmDialog(homeScreen, message, null,
                                                       JOptionPane.YES_NO_OPTION,
                                                       JOptionPane.QUESTION_MESSAGE);
            if (option == 1) { // if they select no
                return false;
            } else {
                return true;
            }
        } else { //if they can't afford treatment
            String message = currentPet.getName() + " is sick. You currently "
                             + "cannot afford treatment.";
            JOptionPane.showMessageDialog(homeScreen, message, null,
                                          JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

    /**
     * Asks the player if the want to revive their pet. If the pet is not
     * revivable it tells them they are a bad person.
     * @return if the player revives their pet.
     */
    private boolean askToRevive() {
        if (currentPet.getIsRevivable()) {
            String message = currentPet.getName() + " has died, would you "
                             + "like to revive them?";
            int option = JOptionPane.showConfirmDialog(homeScreen, message, null,
                                                       JOptionPane.YES_NO_OPTION,
                                                       JOptionPane.QUESTION_MESSAGE);
            if (option == 1) { // if they select no
                return false;
            } else {
                return true;
            }
        } else { //if the pet has already been revived once.
            String message = currentPet.getName() + " has died. You cannot "
                             + "revive them.\nYou're a bad person "
                             + "(or just unlucky).";
            JOptionPane.showMessageDialog(homeScreen, message, null,
                                          JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

    /**
     * Checks if a name is a duplicate.
     * @param name Name to be checked.
     * @param nameList List of names to check against
     * @return If the name is a duplicate.
     */
    public boolean nameTaken(String name, ArrayList<String> nameList) {
        boolean found = false;
        int i = 0;

        while (!found && (i < nameList.size())) {
            if (nameList.get(i).equals(name)) {
                found = true;
            }
            i++;
        }

        return found;
    }

    /**
     * Get a list of all registered names.
     * @return List of names.
     */
    public ArrayList<String> getRegisteredNames() {
        return nameList;
    }

    /**
     * Add a name to the list of names.
     * @param name Name to add to the list
     */
    public void registerName(String name) {
        nameList.add(name);
    }

    /**
     * Sets up the main window and game environment.
     */
    private void initialise() {
        mainGame = new GameEnvironment();
        mainGame.generateFoodPrototypes();
        mainGame.generateToyPrototypes();
        mainGame.initialiseNumGenerator(new String[0]);
        mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.getContentPane().setLayout(null);
    }

    /**
     * Shows the setup panel to the user.
     */
    private void showSetup() {
        mainFrame.setBounds(0, 0, 300, 200);
        mainFrame.setMinimumSize(new Dimension(300, 200));
        SetupPanel setupPanel = new SetupPanel();
        setupPanel.registerObserver(this);

        mainFrame.getContentPane().add(setupPanel);
        setupPanel.setVisible(true);

        setupPanel.setSize(300, 165);

        mainFrame.setVisible(true);
    }

    /**
     * Display a player creation pane.
     */
    private void createPlayer() {
        mainFrame.setBounds(0, 0, 435, 392);
        mainFrame.setMinimumSize(new Dimension(435, 392));
        PlayerCreationPanel playerCreation = new PlayerCreationPanel(this);
        playerCreation.registerObserver(this);

        mainFrame.getContentPane().add(playerCreation);
        playerCreation.setVisible(true);

        playerCreation.setSize(420, 355);

        mainFrame.setVisible(true);
    }

    /**
     * Clears the frames of all components.
     */
    private void clearFrame() {
        mainFrame.getContentPane().setVisible(false);
        mainFrame.getContentPane().removeAll();
        mainFrame.getContentPane().setVisible(true);
    }

    /**
     * Main method to kick everything off.
     * @param args Some arguments that probably won't get used.
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        GUIMain main = new GUIMain();
        main.initialise();
        main.showSetup();
    }

}
