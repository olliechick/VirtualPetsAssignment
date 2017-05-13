import javax.swing.JFrame;

/**
 * Main GUI class for VirtualPets
 * @author Samuel Pell
 *
 */
public class GUIMain implements Observer {
	
	private JFrame mainFrame;
	private GameEnvironment mainGame;
	int playerNumber;
	
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
				break;
			default:
				System.out.println("Unknown GUI Element Identifier\nDropping data");
		}
	}
	
	public void initialise(){
		mainGame = new GameEnvironment();
		mainFrame = new JFrame();
		
		mainFrame.setBounds(0, 0, 400, 226);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(null);
	}
	
	public void showSetup(){
		SetupPanel myPanel = new SetupPanel();
		myPanel.registerObserver(this);
		
		mainFrame.getContentPane().add(myPanel);
		myPanel.setVisible(true);
		
		myPanel.setSize(400, 226);
		
		mainFrame.setVisible(true);
	}
	
	public void createPlayers(){
		//TODO: Add me
	}
	
	public static void main(String[] args){
		GUIMain main = new GUIMain();
		main.initialise();
		main.showSetup();
		main.createPlayers();
	}

}
