import javax.swing.JFrame;

import java.util.ArrayList;

/**
 * Main GUI class for VirtualPets sort of a ViewController.
 * @author Samuel Pell
 *
 */
public class GUIMain implements Observer {
	
	private JFrame mainFrame;
	private GameEnvironment mainGame;
	private int playerNumber;
	private ArrayList<String> nameList = new ArrayList<String>();
	private int numPlayersCreated = 0;
	
	/**
	 * Part of the Observer pattern to get data from GUI to GameEnvironment.
	 * @param indentifier Identifier of the operation.
	 * @param values Array of objects appropriate to the GUI call.
	 */
	public void getValues(String identifier, String[] values){
		switch(identifier){
			case "setup":
				Integer numDays = Integer.parseInt(values[0]);
				mainGame.setNumDays(numDays);
				playerNumber = (int) Integer.parseInt(values[1]);
				
				//DEBUG
				System.out.println(numDays + " days to play for");
				System.out.println(playerNumber + " players set in GUI");
				//DEBUG
				
				clearFrame();
				createPlayer();
				break;
				
			case "player creation":
				
				numPlayersCreated++;
				
				//DEBUG
				for(String value: values){
					System.out.println(value);
				}
				//DEBUG
				
				clearFrame();
				if (numPlayersCreated < (playerNumber - 1)){
					createPlayer();
				}else{
					newDay();
				}
				break;
			default:
				System.out.println("Unknown GUI Element Identifier\nDropping data");
		}
	}
	
	private void newDay(){
		System.out.println("Its a brand new day!!");
	}
	
	/**
	 * Checks if a name is a duplicate.
	 * @param name Name to be checked.
	 * @param nameList List of names to check against
	 * @return If the name is a duplicate.
	 */
	public boolean nameTaken(String name, ArrayList<String> nameList){
		boolean found = false;
		int i = 0;

		while (!found && (i < nameList.size())){
			if (nameList.get(i).equals(name))
				found = true;
			i++;
		}

		return found;
	}
	
	public ArrayList<String> getRegisteredNames(){
		return nameList;
	}
	
	public void registerName(String name){
		nameList.add(name);
	}
	
	/**
	 * Sets up the main window and game environment.
	 */
	private void initialise(){
		mainGame = new GameEnvironment();
		mainFrame = new JFrame();
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(null);
	}
	
	/**
	 * Shows the setup panel to the user.
	 */
	private void showSetup(){
		mainFrame.setBounds(0, 0, 400, 226);
		SetupPanel setupPanel = new SetupPanel();
		setupPanel.registerObserver(this);
		
		mainFrame.getContentPane().add(setupPanel);
		setupPanel.setVisible(true);
		
		setupPanel.setSize(400, 226);
		
		mainFrame.setVisible(true);
	}
	
	
	
	
	private void createPlayer(){
		mainFrame.setBounds(0, 0, 435, 425);
		PlayerCreationPanel playerCreation = new PlayerCreationPanel(this);
		playerCreation.registerObserver(this);
			
		mainFrame.getContentPane().add(playerCreation);
		playerCreation.setVisible(true);
			
		playerCreation.setSize(420, 370);
			
		mainFrame.setVisible(true);
	}
	
	/**
	 * Clears the frames of all components
	 */
	private void clearFrame(){
		mainFrame.getContentPane().setVisible(false);
		mainFrame.getContentPane().removeAll();
		mainFrame.getContentPane().setVisible(true);
	}
	
	/**
	 * Main method to kick everything off.
	 * @param args Some arguments that probably won't get used.
	 */
	public static void main(String[] args){
		GUIMain main = new GUIMain();
		main.initialise();
		main.showSetup();
	}

}
