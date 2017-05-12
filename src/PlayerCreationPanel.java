import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JButton;

/**
 * Player creation panel
 * @author Samuel
 */
public class PlayerCreationPanel extends JPanel{
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public PlayerCreationPanel() {
		setLayout(null);
		
		JLabel lblPlayerName = new JLabel("Player Name:");
		lblPlayerName.setBounds(10, 14, 89, 14);
		add(lblPlayerName);
		
		textField = new JTextField();
		textField.setBounds(88, 11, 123, 20);
		add(textField);
		textField.setColumns(10);
		
		JCheckBox checkBox = new JCheckBox("1");
		checkBox.setBounds(56, 75, 43, 23);
		add(checkBox);
		
		JCheckBox checkBox_1 = new JCheckBox("2");
		checkBox_1.setBounds(194, 75, 43, 23);
		add(checkBox_1);
		
		JCheckBox checkBox_2 = new JCheckBox("3");
		checkBox_2.setBounds(339, 71, 43, 31);
		add(checkBox_2);
		
		JLabel lblNumberOfPets = new JLabel("Number of Pets:");
		lblNumberOfPets.setBounds(10, 46, 116, 14);
		add(lblNumberOfPets);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 105, 123, 202);
		add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(143, 105, 141, 202);
		add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(294, 105, 116, 202);
		add(panel_2);
		
		JButton btnNext = new JButton("Next");
		btnNext.setBounds(321, 316, 89, 23);
		add(btnNext);

	}
	
	/**
	 * Testing functionality.
	 * @param args Input arguments.
	 */
	public static void main(String[] args){
		JFrame myFrame = new JFrame();
		
		myFrame.setBounds(0, 0, 450, 400);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.getContentPane().setLayout(null);
		
		JPanel myPanel = new PlayerCreationPanel();
		
		myFrame.getContentPane().add(myPanel);
		myPanel.setVisible(true);
		
		myPanel.setSize(420, 350);
		
		myFrame.setVisible(true);
	}
}
