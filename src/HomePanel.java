import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

public class HomePanel extends JPanel {

	/**
	 * To stop IDE complaining at me
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public HomePanel() {
		setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 36, 780, 419);
		add(tabbedPane);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Status", null, tabbedPane_1, null);
		
		JTabbedPane tabbedPane_2 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Store", null, tabbedPane_2, null);
		
		JTabbedPane tabbedPane_3 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Feed", null, tabbedPane_3, null);
		
		JTabbedPane tabbedPane_4 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Play", null, tabbedPane_4, null);
		
		JTabbedPane tabbedPane_5 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Sleep", null, tabbedPane_5, null);
		
		JTabbedPane tabbedPane_6 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Toilet", null, tabbedPane_6, null);
		
		JLabel lblPlayer = new JLabel("Player:");
		lblPlayer.setBounds(10, 11, 46, 14);
		add(lblPlayer);
		
		JLabel lblCurrentplayer = new JLabel("currentPlayer");
		lblCurrentplayer.setBounds(51, 11, 91, 14);
		add(lblCurrentplayer);
		
		JLabel lblBalance = new JLabel("Balance:");
		lblBalance.setBounds(135, 11, 59, 14);
		add(lblBalance);
		
		JLabel lblPlayerbalance = new JLabel("playerBalance");
		lblPlayerbalance.setBounds(184, 11, 91, 14);
		add(lblPlayerbalance);
		
		JLabel lblPet = new JLabel("Pet:");
		lblPet.setBounds(285, 11, 46, 14);
		add(lblPet);
		
		JLabel lblCurrentpet = new JLabel("currentPet");
		lblCurrentpet.setBounds(310, 11, 66, 14);
		add(lblCurrentpet);
		
		JLabel lblActionsRemaining = new JLabel("Actions Remaining:");
		lblActionsRemaining.setBounds(386, 11, 112, 14);
		add(lblActionsRemaining);
		
		JLabel lblNumactions = new JLabel("numActions");
		lblNumactions.setBounds(481, 11, 66, 14);
		add(lblNumactions);
		
		JLabel lblDaymarker = new JLabel("dayMarker");
		lblDaymarker.setBounds(731, 11, 59, 14);
		add(lblDaymarker);
		
		JLabel lblDay = new JLabel("Day:");
		lblDay.setBounds(701, 11, 25, 14);
		add(lblDay);
		
		JButton btnNewButton = new JButton("Next");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(701, 466, 89, 23);
		add(btnNewButton);

	}
	
	public static void main(String[] args){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch(Exception e){} //ignore all exceptions
		JFrame myFrame = new JFrame();
		
		myFrame.setBounds(0, 0, 815, 540);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.getContentPane().setLayout(null);
		
		JPanel myPanel = new HomePanel();
		
		myFrame.getContentPane().add(myPanel);
		myPanel.setVisible(true);
		
		myPanel.setSize(800, 500);
		
		myFrame.setVisible(true);
	}
}
