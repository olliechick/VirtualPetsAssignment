import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Player creation panel
 * @author Samuel
 */
public class PlayerCreationPanel extends JPanel implements Observable{
	/**
	 * Complained at me without it
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	private JTextField playerNameField;

	/**
	 * Create the panel.
	 */
	public PlayerCreationPanel() {
		setLayout(null); //Absoloute layout
		
		//Player name text box and label
		JLabel lblPlayerName = new JLabel("Player Name:");
		lblPlayerName.setBounds(10, 14, 89, 14);
		add(lblPlayerName);
		
		playerNameField = new JTextField();
		playerNameField.setBounds(88, 11, 123, 20);
		add(playerNameField);
		playerNameField.setColumns(10);
		
		//Pet number selection label
		JLabel lblNumberOfPets = new JLabel("Pet:");
		lblNumberOfPets.setBounds(10, 46, 116, 14);
		add(lblNumberOfPets);
		
		//Pet creation panel creation
		PetCreationPanel petOnePanel = new PetCreationPanel();
		petOnePanel.setBounds(10, 105, 123, 225);
		add(petOnePanel);
		petOnePanel.enable();
		
		PetCreationPanel petTwoPanel = new PetCreationPanel();
		petTwoPanel.setBounds(148, 105, 123, 225);
		add(petTwoPanel);
		
		PetCreationPanel petThreePanel = new PetCreationPanel();
		petThreePanel.setBounds(287, 105, 123, 225);
		add(petThreePanel);
		
		//Creating check boxes and adding enabling disabling behaivour
		JCheckBox petOneBox = new JCheckBox("1", true); //default to 1 pet
		petOneBox.setBounds(56, 75, 43, 23);
		petOneBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (petOneBox.isSelected()){
					petOnePanel.enable();
				} else{
					petOnePanel.disable();
				}
			}
		});
		add(petOneBox);
		
		
		JCheckBox petTwoBox = new JCheckBox("2");
		petTwoBox.setBounds(194, 75, 43, 23);
		petTwoBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (petTwoBox.isSelected()){
					petTwoPanel.enable();
				} else{
					petTwoPanel.disable();
				}
			}
		});
		add(petTwoBox);
		
		JCheckBox petThreeBox = new JCheckBox("3");
		petThreeBox.setBounds(339, 71, 43, 31);
		petThreeBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (petThreeBox.isSelected()){
					petThreePanel.enable();
				} else{
					petThreePanel.disable();
				}
			}
		});
		add(petThreeBox);
		
		//Add button to move to next dialog.
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				System.out.println("I was activated");
			}
		});
		btnNext.setBounds(321, 346, 89, 23);
		add(btnNext);

	}
	
	/**
	 * Notify all observers of a change in values.
	 */
	public void notifyObservers(){
		//TODO: Finish me
	}
	
	/**
	 * Add a new observer to the object.
	 * @param newObserver The observer to add.
	 */
	public void registerObserver(Observer newObserver){
		observers.add(newObserver);
	}
	
	/**
	 * Testing functionality.
	 * @param args Input arguments.
	 */
	public static void main(String[] args){
		JFrame myFrame = new JFrame();
		
		myFrame.setBounds(0, 0, 435, 425);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.getContentPane().setLayout(null);
		
		JPanel myPanel = new PlayerCreationPanel();
		
		myFrame.getContentPane().add(myPanel);
		myPanel.setVisible(true);
		
		myPanel.setSize(420, 370);
		
		myFrame.setVisible(true);
	}
}
