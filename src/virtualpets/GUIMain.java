package virtualpets;
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
     * Index of the current pet in the current players pet list.
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
     * Value of the stipend player earns daily per pet.
     */
    private int dailyPetAllowance = 10;
    //TODO: Could you not use an array? --Sam
	int numOfPetsP1;
	int numOfPetsP2 = -1; //init to -1 so currentPetIndex !< numOfPetsP2 if P2 does not exist
	int numOfPetsP3 = -1; //init to -1 so currentPetIndex !< numOfPetsP3 if P3 does not exist

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
                nextPet();
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
                JOptionPane.showMessageDialog(null, currentPet.getName()
                        + " broke the "
                        + toy.getName());
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
                JOptionPane.showMessageDialog(null, message);
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
                JOptionPane.showMessageDialog(null, message);
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
        mainFrame.setBounds(0, 0, 815, 540);

        int numDays = mainGame.getNumDays();
        homeScreen = new HomePanel(numDays, mainGame);

        homeScreen.getStoreTab().registerObserver(this);
        homeScreen.getFeedingTab().registerObserver(this);
        homeScreen.getSleepTab().registerObserver(this);
        homeScreen.getToiletTab().registerObserver(this);
        homeScreen.getPlayTab().registerObserver(this);
        homeScreen.registerObserver(this);

        mainFrame.getContentPane().add(homeScreen);
        homeScreen.setVisible(true);

        homeScreen.setSize(800, 500);

        gameLoop();
    }

    private void nextPet() {
    	System.out.println("currentPetIndex = "+currentPetIndex+" other stuff: "+numOfPetsP1+numOfPetsP2);
    	currentPetIndex++;

    	// First find out which is the next pet, and set that to currentPet
    	if (currentPetIndex == 0) {
        	System.out.println("a");
    	}
    	else if (currentPetIndex < numOfPetsP1) {
        	System.out.println("b");
    		// still on Player 1
    		currentPet = currentPlayer.getPetList().get(currentPetIndex);

    	} else if (numberOfPlayers >= 2 && currentPetIndex == numOfPetsP1) {
        	System.out.println("c");
    		currentPlayer.calculateScore(); //Player 1 has finished, calculate its score
    		// Player 2 exists, so we're moving on to Player 2

    		currentPlayer = mainGame.getPlayerList().get(1);
    		initialisePlayer();
    		numOfPetsP2 = mainGame.getPlayerList().get(1).getPetList().size();
    		currentPet = currentPlayer.getPetList().get(0); //get the player's first pet

    	} else if (currentPetIndex < numOfPetsP1 + numOfPetsP2) {
    		// still on Player 2
        	System.out.println("d");
    		currentPet = currentPlayer.getPetList().get(currentPetIndex - numOfPetsP1);

    	} else if (numberOfPlayers >= 3 && currentPetIndex == numOfPetsP2) {
        	System.out.println("e");
    		currentPlayer.calculateScore(); //Player 2 has finished, calculate its score
    		// Player 3 exists, so we're moving on to Player 3
    		currentPlayer = mainGame.getPlayerList().get(2);
    		initialisePlayer();
    		numOfPetsP3 = mainGame.getPlayerList().get(2).getPetList().size();
    		currentPet = currentPlayer.getPetList().get(0); //get the player's first pet

    	} else if (currentPetIndex < numOfPetsP1 + numOfPetsP2 + numOfPetsP3) {
        	System.out.println("f");
    		// still on Player 3
    		currentPet = currentPlayer.getPetList().get(currentPetIndex - numOfPetsP1 - numOfPetsP2);

    	} else {
        	System.out.println("g");
    		if (numberOfPlayers == 3) { //if there are 3 players, then:
    			currentPlayer.calculateScore(); //Player 3 has finished, calculate its score
    		}
    		// All players finished, time to move on to the next day.
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

    private void initialisePlayer() {
        int numOfAlivePets = 0;
        for (Pet pet : currentPlayer.getPetList()) { // count up all the alive pets
            if (!pet.getIsDead()) {
                numOfAlivePets++;
            }
        }
        //Give them an allowance per alive pet
        currentPlayer.earn(dailyPetAllowance * numOfAlivePets);
	}

	/**
     * Move to a new day.
     */
    private void nextDay() {
    	currentDay++;
    	currentPetIndex = 0;
    	currentPlayer = mainGame.getPlayerList().get(0);
    	currentPet = currentPlayer.getPetList().get(0);
		initialisePlayer();
        System.out.println("========== New day ==============");
        homeScreen.refreshTabs(currentPlayer, currentPet, 1, 2);
    }

    /**
     * This is where the magic happens.
     */
    private void gameLoop(){
    	numOfPetsP1 = mainGame.getPlayerList().get(0).getPetList().size();
        int dayNumber = 0; //TODO why is this not 1? //TODO: You asked for it to be set to 0? --Sam
        currentPlayer = mainGame.getPlayerList().get(0);
        currentPet = currentPlayer.getPetList().get(0);
		initialisePlayer();
        refreshScreen();
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
            //TODO popup to discipline or not
            treated = true; //for the meantime
            if (treated) {
                currentPet.treat();
                currentPlayer.spend(50);
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
            //ignore all exceptions
        	//TODO all these ignoring all exceptions seems like a bad idea?
        }
        GUIMain main = new GUIMain();
        main.initialise();
        main.showSetup();
    }

}
