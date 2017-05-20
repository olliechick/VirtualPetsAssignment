package virtualpets;
import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;


/**
 * Main GUI class for VirtualPets sort of a ViewController.
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
     * Current day in game.
     */
    private int currentDay;
    /**
     * Number of actions remaining for the current pet.
     */
    private int numActions = 2;
    /**
     * Value of the stipend each player earns daily per (alive) pet, in dollars ($).
     */
    private int dailyPetAllowance = 15;
    /**
     * Number of pets each player has.
     * This is initialised to 0 so currentPetIndex !< numOfPets[i] if Player i does not exist.
     */
    private int[] numOfPets = {0, 0, 0};


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
                System.out.println("Creating player 1");
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
                } else {
                    displayHome();
                }
                break;

            case "buy item":
                attemptBuyItem(values[0]);
                refreshScreen();
                break;

            case "sleep":
                currentPet.sleep();
                System.out.println("Sleeping");
                numActions--;
                refreshScreen();
                break;

            case "toilet":
                currentPet.goToilet();
                System.out.println("Going toilet");
                numActions--;
                refreshScreen();
                break;

            case "feed":
                Food food = mainGame.getFoodPrototypes().get(values[0]);
                System.out.println("Feeding "  + currentPet.getName() + " : "
                                    + food.getName());
                currentPet.feed(food);
                currentPlayer.getFoodStock().remove(food);
                numActions--;
                refreshScreen();
                break;

            case "play":
                Toy toy = mainGame.getToyPrototypes().get(values[0]);
                String debug = "Playing "  + currentPet.getName() + " : " + toy.getName();
                System.out.println(debug);
                play(toy);
                numActions--;
                refreshScreen();
                break;

            case "next":
                numActions = 0;
                break;

            default:
                System.out.println("Unknown GUI Element Identifier");
                System.out.println("Dropping data");
                System.out.println(identifier);
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

        try { //first try to see if item is a food.
            Food purchasedItem = foodItems.get(itemBought);
            if (purchasedItem.getPrice() <= currentPlayer.getBalance()) {
                currentPlayer.spend(purchasedItem.getPrice());
                currentPlayer.addFood(purchasedItem);
            } else {
                String message = "You do not have enough money to purchase ";
                message += purchasedItem.getName();
                JOptionPane.showMessageDialog(mainFrame, message, null,
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            //Ignore first type exception.
        }

        try { //then see if item is a toy
            Toy purchasedItem = toyItems.get(itemBought);
            if (purchasedItem.getPrice() <= currentPlayer.getBalance()) {
                currentPlayer.spend(purchasedItem.getPrice());
                currentPlayer.addToy(purchasedItem);
            } else {
                String message = "You do not have enough money to purchase ";
                message += purchasedItem.getName();
                JOptionPane.showMessageDialog(mainFrame, message, null,
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            //ignore second exception
        }
    }

    /**
     * Refresh screen to display change in data.
     */
    private void refreshScreen() {
        homeScreen.refreshTabs(currentPlayer, currentPet, currentDay, numActions);
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

    private void nextPet() {
        homeScreen.returnToStatus(); //Returns player to status screen on new pet
    	currentPetIndex++;

    	// First find out which is the next pet, and set that to currentPet
    	if (currentPetIndex < numOfPets[0]) {
    		// still on Player 1
    		currentPet = currentPlayer.getPetList().get(currentPetIndex);

    	} else if (numberOfPlayers >= 2 && currentPetIndex == numOfPets[0]) {
    		// Player 2 exists, so we're moving on to Player 2

    		currentPlayer = mainGame.getPlayerList().get(1);
    		initialisePlayer();
    		numOfPets[1] = mainGame.getPlayerList().get(1).getPetList().size();
    		currentPet = currentPlayer.getPetList().get(0); //get the player's first pet

    	} else if (currentPetIndex < numOfPets[0] + numOfPets[1]) {
    		// still on Player 2
    		currentPet = currentPlayer.getPetList().get(currentPetIndex - numOfPets[0]);

    	} else if (numberOfPlayers >= 3 && currentPetIndex == numOfPets[1]) {
    		// Player 3 exists, so we're moving on to Player 3
    		currentPlayer = mainGame.getPlayerList().get(2);
    		initialisePlayer();
    		numOfPets[2] = mainGame.getPlayerList().get(2).getPetList().size();
    		currentPet = currentPlayer.getPetList().get(0); //get the player's first pet

    	} else if (currentPetIndex < numOfPets[0] + numOfPets[1] + numOfPets[2]) {
    		// still on Player 3
    		currentPet = currentPlayer.getPetList().get(currentPetIndex - numOfPets[0] - numOfPets[1]);

    	} else {
    		// All players finished
    	    // Time to move on to the next day.
    		nextDay();
    	}

		System.out.println("Moving on to " + currentPet.getName());

		if (currentPet.getIsDead()) {
			System.out.println("Actually they're dead.");
			nextPet();
		} else {
			//they're alive!
	        numActions = 2;
	        newDayPetActions();
		}
        refreshScreen();
    }

	/**
     * Move to a new day.
     */
    private void nextDay() {
    	mainGame.nextDay();
    	currentDay = mainGame.getCurrentDay();

        // Calculate all the scores for today (well, technically yesterday now)
        for (Player player : mainGame.getPlayerList()){
            player.calculateScore();
        }

        //init day if all days haven't finished
    	if (currentDay <= mainGame.getNumDays()) {
    	    currentPetIndex = 0;
    	    currentPlayer = mainGame.getPlayerList().get(0);
    	    currentPet = currentPlayer.getPetList().get(0);
    	    initialisePlayer();
    	    System.out.println("========== New day ==============");
    	    homeScreen.refreshTabs(currentPlayer, currentPet, 1, 2);
    	} else { // game is over
    	    clearFrame();
    	    postGame();
    	}
    }

    /**
     * Initialises a player's turn.
     * It gives the player their daily allowance.
     * If there are multiple players, it pops up a window to say who's turn it is.
     */
    private void initialisePlayer() {
    	mainGame.initialisePlayer(currentPlayer);

        if (numberOfPlayers > 1) {
            String message = "It is now " + currentPlayer.getName() + "'s turn.";
            JOptionPane.showMessageDialog(mainFrame, message, null, JOptionPane.INFORMATION_MESSAGE);
        }
	}

    private void postGame() {
        // TODO format frame, provide score
        Player[] players;
        players = mainGame.rankPlayers();
        for (Player player : players){
            System.out.println(player.getName() + ": "+ player.getScore());
        }
    }

    /**
     * Initialises the days.
     */
    private void initialiseDays(){
    	numOfPets[0] = mainGame.getPlayerList().get(0).getPetList().size();
        currentDay = 1;
        currentPlayer = mainGame.getPlayerList().get(0);
        currentPet = currentPlayer.getPetList().get(0);
        refreshScreen();
		initialisePlayer();
    }

    /**
     * Perform daily actions for a pet.
     * This includes increasing fatigue, hunger, etc.,
     * as well as prompting the user if the pet is misbehaving, sick, or dead.
     * If so, they have the option to discipline, treat, or revive,
     * depending on what happened.
     */
    private void newDayPetActions() {
        Boolean misbehaving;
        Boolean sick;
        Boolean dead;

        Boolean disciplined;
        Boolean treated;
        Boolean revived;


        mainGame.newDayPetActions(currentPet);
        misbehaving = mainGame.checkIfMisbehaving(currentPet);
        if (misbehaving) {
            //TODO popup to discipline or not
            disciplined = true; //for the meantime
            if (disciplined) {
                currentPet.discipline();
            } else {
                currentPet.misbehave();
            }
            refreshScreen();
        }

        sick = mainGame.checkIfSick(currentPet);
        if (sick) {
            //TODO popup to treat or not - OR if you don't have enough money, no option to treat
            treated = true; //for the meantime
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
            //TODO popup to revive or not OR popup to say you're a bad person, based on currentPet.getRevivable()
            revived = true; //for the meantime

            if (revived) {
                currentPet.revive();
            } else {
                currentPet.die();
            }
            refreshScreen();
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
    public ArrayList<String> getRegisteredNames(){
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
