import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.AbstractListModel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ImageIcon;

public class StatusPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public StatusPanel() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Happiness");
		lblNewLabel.setBounds(32, 53, 77, 14);
		add(lblNewLabel);
		
		JLabel lblHunger = new JLabel("Hunger");
		lblHunger.setBounds(32, 78, 46, 14);
		add(lblHunger);
		
		JLabel lblPlayfulness = new JLabel("Fatigue");
		lblPlayfulness.setBounds(32, 103, 46, 14);
		add(lblPlayfulness);
		
		JLabel lblNewLabel_1 = new JLabel("Health");
		lblNewLabel_1.setBounds(32, 126, 46, 14);
		add(lblNewLabel_1);
		
		JLabel lblBladderFullness = new JLabel("Bladder Full");
		lblBladderFullness.setBounds(32, 150, 77, 14);
		add(lblBladderFullness);
		
		JLabel lblWeight = new JLabel("Weight");
		lblWeight.setBounds(32, 175, 46, 14);
		add(lblWeight);
		
		JLabel lblMisbehaving = new JLabel("Misbehaving");
		lblMisbehaving.setBounds(32, 200, 59, 14);
		add(lblMisbehaving);
		
		JLabel lblSick = new JLabel("Sick");
		lblSick.setBounds(32, 225, 46, 14);
		add(lblSick);
		
		JLabel lblRevivable = new JLabel("Revivable");
		lblRevivable.setBounds(32, 250, 59, 14);
		add(lblRevivable);
		
		JLabel lblHapinessscore = new JLabel("hapinessScore");
		lblHapinessscore.setBounds(119, 53, 69, 14);
		add(lblHapinessscore);
		
		JLabel lblHunger_1 = new JLabel("hunger");
		lblHunger_1.setBounds(119, 78, 46, 14);
		add(lblHunger_1);
		
		JLabel lblFatiguescore = new JLabel("fatigueScore");
		lblFatiguescore.setBounds(119, 103, 46, 14);
		add(lblFatiguescore);
		
		JLabel lblHealthscore = new JLabel("healthScore");
		lblHealthscore.setBounds(119, 126, 46, 14);
		add(lblHealthscore);
		
		JLabel lblBladderpercentage = new JLabel("bladderPercentage");
		lblBladderpercentage.setBounds(119, 150, 46, 14);
		add(lblBladderpercentage);
		
		JLabel lblWeightscore = new JLabel("weightScore");
		lblWeightscore.setBounds(119, 175, 46, 14);
		add(lblWeightscore);
		
		JLabel lblIsmisbehaving = new JLabel("isMisbehaving");
		lblIsmisbehaving.setBounds(119, 200, 46, 14);
		add(lblIsmisbehaving);
		
		JLabel lblIssick = new JLabel("isSick");
		lblIssick.setBounds(119, 225, 46, 14);
		add(lblIssick);
		
		JLabel lblIsrevivable = new JLabel("isRevivable");
		lblIsrevivable.setBounds(119, 250, 46, 14);
		add(lblIsrevivable);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(new Color(0, 0, 0));
		separator.setBounds(198, 53, 39, 218);
		add(separator);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(212, 53, 46, 14);
		add(lblName);
		
		JLabel lblPetname = new JLabel("petName");
		lblPetname.setBounds(268, 53, 46, 14);
		add(lblPetname);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setBounds(212, 78, 46, 14);
		add(lblGender);
		
		JLabel lblPetgender = new JLabel("petGender");
		lblPetgender.setBounds(268, 78, 46, 14);
		add(lblPetgender);
		
		JLabel lblSpecies = new JLabel("Species");
		lblSpecies.setBounds(212, 103, 46, 14);
		add(lblSpecies);
		
		JLabel lblPetspecies = new JLabel("petSpecies");
		lblPetspecies.setBounds(268, 103, 46, 14);
		add(lblPetspecies);
		
		JPanel panel = new JPanel();
		panel.setBounds(349, 53, 365, 275);
		add(panel);
		
	}
	
	public static void main(String[] args){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch(Exception e){} //ignore all exceptions
		JFrame myFrame = new JFrame();
		
		myFrame.setBounds(0, 0, 815, 540);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.getContentPane().setLayout(null);
		
		JPanel myPanel = new StatusPanel();
		
		myFrame.getContentPane().add(myPanel);
		myPanel.setVisible(true);
		
		myPanel.setSize(800, 500);
		
		myFrame.setVisible(true);
	}
}
