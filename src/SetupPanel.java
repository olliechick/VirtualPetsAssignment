import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JRadioButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.ButtonGroup;


/**
 * A JPanel to create setup dialogue.
 * @author Samuel
 *
 */
public class SetupPanel extends JPanel {
	
	private int[] outputValues = new int[2]; 
	
	/**
	 * Create the panel.
	 */
	public SetupPanel() {
		setLayout(null);
		
		ButtonGroup radioButtonGroup = new ButtonGroup();
		
		JLabel lblNumDays = new JLabel("Number of days to play for:");
		lblNumDays.setBounds(60, 34, 133, 14);
		add(lblNumDays);
		
		JSpinner numberOfDaysSpinner = new JSpinner();
		numberOfDaysSpinner.setModel(new SpinnerNumberModel(1, 1, 365, 1));
		numberOfDaysSpinner.setBounds(199, 31, 39, 20);
		add(numberOfDaysSpinner);
		
		JLabel lblNumberOfPlayers = new JLabel("Number of Players:");
		lblNumberOfPlayers.setBounds(101, 61, 92, 14);
		add(lblNumberOfPlayers);
		
		JRadioButton onePlayer = new JRadioButton("1", true);
		onePlayer.setBounds(199, 57, 60, 23);
		add(onePlayer);
		
		JRadioButton twoPlayers = new JRadioButton("2");
		twoPlayers.setBounds(199, 86, 60, 23);
		add(twoPlayers);
		
		JRadioButton threePlayers = new JRadioButton("3");
		threePlayers.setBounds(199, 115, 60, 23);
		add(threePlayers);
		
		radioButtonGroup.add(onePlayer);
		radioButtonGroup.add(twoPlayers);
		radioButtonGroup.add(threePlayers);
		
		JButton btnNext = new JButton("Next");
		btnNext.setBounds(199, 145, 70, 23);
		btnNext.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				int numPlayers;
				int numDays;
				if(onePlayer.isSelected()){
					System.out.println("One Player");
					numPlayers = 1;
				} else if(twoPlayers.isSelected()){
					System.out.println("Two Players");
					numPlayers = 2;
				}else{
					System.out.println("Three Players");
					numPlayers = 3;
				}
				numDays = (Integer) numberOfDaysSpinner.getValue(); 
				System.out.println("Play for " + numDays + " days");
				
				outputValues[0] = numPlayers;
				outputValues[1] = numDays;
			}
		});
		add(btnNext);

	}
	
	public static void main(String[] args){
		JFrame myFrame = new JFrame();
		
		JPanel myPanel = new SetupPanel();
		myFrame.getContentPane().add(myPanel);
		myPanel.setVisible(true);
		myFrame.setVisible(true);
	}
}
