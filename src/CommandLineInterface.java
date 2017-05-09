import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Command line interface for Virtual Pets game.
 * @author Samuel Pell
 * @author Ollie Chick
 *
 */
public class CommandLineInterface {

	private static String divider = "----------/----------";
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
		Pet newPet = new Cat(); //default to pet TODO is this meant to be Pet newPet = new Pet()? --Ollie
		String choice;
		//Get pet species
		System.out.println("1. Alpaca\n2. Cat\n3. Dog\n4. Goat\n5. Horse\n6. Polar bear");
		do{
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
				System.out.println("\n"+choice + " is not a valid option. Please enter one of the below choices.");
				System.out.println("1. Alpaca\n2. Cat\n3. Dog\n4. Goat\n5. Horse\n6. Polar bear");
				choice = null;
				break;
			}
		}while (choice == null);

		return newPet;
	}

	public static String[] listPrototypes(HashMap<String, Food> foodPrototypes, HashMap<String, Toy> toyPrototypes){
		String[] foodNames = foodPrototypes.keySet().toArray(new String[0]);
		String[] toyNames = toyPrototypes.keySet().toArray(new String[0]);
		String[] ordering = new String[foodNames.length + toyNames.length];
		Toy toy;
		Food food;

		System.out.println("----------- Food -----------");
		int currentOption = 1;
		for (int i = 0; i < foodNames.length; i++){
			food = foodPrototypes.get(foodNames[i]);
			System.out.println(currentOption + ". " + food + " Cost: $" + food.getPrice());
			ordering[i] = foodNames[i];
			currentOption++;
		}

		System.out.println("----------- Toys -----------");
		for (int i = 0; i< toyNames.length; i++){
			toy = toyPrototypes.get(toyNames[i]);
			System.out.println(currentOption + ". " + toy + " Cost: $" + toy.getPrice());
			ordering[i + foodNames.length] = toyNames[i];
			currentOption++;
		}

		return ordering;
	}

	/**
	 * Initialises a day.
	 */
	public static void newDay(int dayNumber){
		System.out.println("=== Day "+dayNumber+" ===");
	}

	/**
	 * Initialise a player's turn.
	 */
	public static void newPlayer(Player player){
		System.out.println("--- "+player.getName()+"'s turn ---");
	}

	/**
	 * Main game loop for one player to interact with one pet.
	 * @param toyPrototypes 
	 * @param foodPrototypes 
	 */
	public static void interact(Player player, Pet pet, HashMap<String, Food> foodPrototypes, HashMap<String, Toy> toyPrototypes){
		int numOfActions = 2;
		String choice;
		while (numOfActions > 0){
			System.out.print("Hi "+player.getName()+"! You have "+numOfActions+" turns remaining today with "+pet.getName()
			+". What would you like to do?\n1. View pet status\n2. Visit the store\n3. Feed your pet\n4. Play with your pet\n"
			+"5. Put your pet to bed to sleep\n6. Let the pet go toilet\n7. Move on\n>>> ");
			choice = inputReader.next();
			switch(choice){
			case("1"):
				viewPetStatus(pet);
			break;
			case("2"):
				visitStore(player, foodPrototypes, toyPrototypes);
			break;
			case("3"):
				feedPet(player, pet);
			numOfActions--;
			break;
			case("4"):
				try{
					playWithPet(player, pet);
					numOfActions--;
				}catch(Exception e){
					System.out.println("Sorry, you don't have any toys to play with.");
				}
			break;
			case("5"):
				sleep(pet);
			numOfActions--;
			break;
			case("6"):
				goToilet(pet);
			numOfActions--;
			break;
			case("7"):
				numOfActions = 0;
			break;
			default:
				System.out.println("I'm sorry. That's not a valid option. Please try again.");
				break;
			}
		}
	}

	private static void goToilet(Pet pet){
		pet.goToilet();		
	}

	private static void sleep(Pet pet){
		pet.sleep();		
	}

	private static void playWithPet(Player player, Pet pet) throws Exception{
		String choiceStr;
		int choice;
		Toy toy;
		if (player.getToyList().size() == 0){
			throw new Exception("no toys to play with");
		}
		do{

			System.out.println("Hi! What toy would you like your pet to play with?");
			int i=1;
			for(Toy playersToy : player.getToyList()){
				System.out.println(i+". "+playersToy);
				i++;
			}
			System.out.print(">>> ");
			System.out.flush();

			choiceStr = inputReader.next();
			choice = Integer.parseInt(choiceStr);
			if (choice<=0 || choice > i-1){
				choiceStr = null;
			}else{
				toy = player.getToyList().get(i-2);
				try{
				pet.play(toy);
				}catch(IllegalArgumentException e){
					if (e.getMessage().equals("durability is zero or negative")){
						//they've used the toy to the point of destruction
						player.getToyList().remove(i-2);
					}else{
						throw e;
					}
				}
				
			}

		} while (choiceStr == null);


	}

	private static void feedPet(Player player, Pet pet){
		// TODO Auto-generated method stub

	}

	/**
	 * Store loop for player to purchase items from.
	 * @param player Player entering the store.
	 * @param foodPrototypes Hash map of the food item prototypes.
	 * @param toyPrototypes Hash map of the toy item prototypes.
	 */
	private static void visitStore(Player player, HashMap<String, Food> foodPrototypes, HashMap<String, Toy> toyPrototypes){
		Boolean userWantsToStay = true;
		String choice;
		
		System.out.println("Hello " + player.getName() + ", welcome to the store. What do you want to do?");
		while(userWantsToStay){
			System.out.println("1. View objects for sale\n2. View your items"
					+"\n3. Exit the store");
			System.out.print(">>> ");
			choice = inputReader.next();
			switch(choice){
			case("1"):
				buyFromStore(player, foodPrototypes, toyPrototypes);
				break;
			case("2"):
				printItems(player);
				break;
			case("3"):
				userWantsToStay = false;
				break;
			default:
				System.out.println("Sorry, that's not a valid option. Please try again.");
			}
			
		}
	}

	private static void printItems(Player player) {
		for (Toy toy : player.getToyList()){
			System.out.println(toy);
		}
		for (Food food : player.getFoodStock()){
			System.out.println(food);
		}
		
	}

	private static void buyFromStore(Player player, HashMap<String, Food> foodPrototypes, HashMap<String, Toy> toyPrototypes){
		String choice;
		String type;
		String purchasedItemName;
		Toy purchasedToy;
		Food purchasedFood;

		do{
			System.out.println("Hello " + player.getName() + ", you have $"+player.getBalance()+". What would you like to buy today?");
			String[] ordering = listPrototypes(foodPrototypes, toyPrototypes);

			System.out.print(">>> ");
			System.out.flush();

			choice = inputReader.next();
			//TODO check that choice is between 1 and len(food) + len(toy). if not, choice = null
			purchasedItemName = ordering[Integer.parseInt(choice)-1];
			//TODO make a switch statement for if it's a toy or food
			type = "toy";
			purchasedToy = toyPrototypes.get(purchasedItemName);

		} while (choice == null);

		if(type=="toy"){
			try{
				player.spend(purchasedToy.getPrice());
				player.addToy(purchasedToy);
				System.out.println("You have bought: "+ purchasedItemName);
			}catch(IllegalArgumentException e){
				System.out.println("Sorry, you don't have enough money for that. You have $"+player.getBalance()+" and that item costs $"
						+purchasedToy.getPrice()+".");
			}
		}
	}

	private static void viewPetStatus(Pet pet){
		System.out.println(divider);
		System.out.println("Status of "+pet.getName()+":\nGender: "+pet.getGender()
		+"\nSpecies: "+pet.getSpecies()
		+"\nFatigue: "+pet.getFatigue()
		+"\nHappiness: "+pet.getHappiness()
		+"\nHealth: "+pet.getHealth()
		+"\nHunger: "+pet.getHunger()
		+"\nMischievousness: "+pet.getMischievousness()
		+"\nPercent bladder full: "+pet.getPercentBladderFull()
		+"\nWeight: "+pet.getWeight()
		+"\nIs misbehaving: "+pet.getIsMisbehaving()
		+"\nIs revivable: "+pet.getIsRevivable()
		+"\nIs sick: "+pet.getIsSick());
		System.out.println(divider);
	}

	/**
	 * Tidy up to close gracefully.
	 */
	public static void tearDown(){
		inputReader.close();
	}

	public static void postGame(Player[] playerList){
		System.out.println("That's the end of the game. And the results are in:");
		for (Player player : playerList){
			System.out.println(player.getName()+" has a score of "+player.calculateAndGetScore());
		}
	}

}

