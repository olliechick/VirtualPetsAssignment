import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

/**
 * Home JPanel for VirtualPets Assignment.
 * @author Samuel Pell
 *
 */
@SuppressWarnings("serial")
public class HomePanel extends JPanel {
    /**
     * Internal panel to show status screen.
     */
    private StatusPanel statusTab;
    /**
     * Total number of days game will run for.
     */
    private String totalDays;
    /**
     * Element to display current ingame day.
     */
    private JLabel lblDayMarker;
    /**
     * Element to display current player.
     */
    private JLabel lblCurrentPlayer;
    /**
     * Element to display current players balance.
     */
    private JLabel lblPlayerBalance;
    /**
     * Element to display the current pet.
     */
    private JLabel lblCurrentPet;
    /**
     * Element to display the number of action the player has remainging.
     */
    private JLabel lblNumActions;
    
    /**
     * Internal panel to handle store stuff.
     */
    private StorePanel storeTab;

    /**
     * Create the panel.
     */
    public HomePanel(int days, GameEnvironment mainGame) {
        totalDays = ((Integer) days).toString();
        setLayout(null);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(10, 36, 780, 419);
        add(tabbedPane);

        statusTab = new StatusPanel();
        tabbedPane.addTab("Status", null, statusTab, null);

        storeTab = new StorePanel(mainGame.getToyPrototypes(), 
                                  mainGame.getFoodPrototypes(),
                                  mainGame);
        tabbedPane.addTab("Store", null, storeTab, null);

        JTabbedPane feedTab = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.addTab("Feed", null, feedTab, null);

        JTabbedPane playTab = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.addTab("Play", null, playTab, null);

        JTabbedPane sleepTab = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.addTab("Sleep", null, sleepTab, null);

        JTabbedPane toiletTab = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.addTab("Toilet", null, toiletTab, null);

        JLabel lblPlayer = new JLabel("Player:");
        lblPlayer.setBounds(10, 11, 46, 14);
        add(lblPlayer);

        lblCurrentPlayer = new JLabel("currentPlayer");
        lblCurrentPlayer.setBounds(51, 11, 91, 14);
        add(lblCurrentPlayer);

        JLabel lblBalance = new JLabel("Balance:");
        lblBalance.setBounds(135, 11, 59, 14);
        add(lblBalance);

        lblPlayerBalance = new JLabel("playerBalance");
        lblPlayerBalance.setBounds(184, 11, 91, 14);
        add(lblPlayerBalance);

        JLabel lblPet = new JLabel("Pet:");
        lblPet.setBounds(285, 11, 46, 14);
        add(lblPet);

        lblCurrentPet = new JLabel("currentPet");
        lblCurrentPet.setBounds(310, 11, 66, 14);
        add(lblCurrentPet);

        JLabel lblActionsRemaining = new JLabel("Actions Remaining:");
        lblActionsRemaining.setBounds(386, 11, 112, 14);
        add(lblActionsRemaining);

        lblNumActions = new JLabel("numActions");
        lblNumActions.setBounds(481, 11, 66, 14);
        add(lblNumActions);

        lblDayMarker = new JLabel("dayMarker");
        lblDayMarker.setBounds(731, 11, 59, 14);
        add(lblDayMarker);

        JLabel lblDay = new JLabel("Day:");
        lblDay.setBounds(701, 11, 25, 14);
        add(lblDay);

        JButton btnNext = new JButton("Next");
        btnNext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            }
        });
        btnNext.setBounds(701, 466, 89, 23);
        add(btnNext);

    }

    /**
     * Refreshes player balance, number of actions remaining, and
     * the current player displayed to the user.
     * @param currentPlayer Player to display stats for
     * @param numActions Number of actions remaining
     */
    public void refreshPlayerStats(Player currentPlayer, int numActions){
        Integer balance = (Integer) currentPlayer.getBalance();
        lblPlayerBalance.setText("$" + balance.toString());
        lblCurrentPlayer.setText(currentPlayer.getName());
        lblNumActions.setText(((Integer) numActions).toString());
    }

    /**
     * Refreshes all statistics and tabs presented to user.
     * @param currentPlayer Player to display stats for
     * @param currentPet Pet to display stats for
     * @param currentDay Current day number
     * @param numActions number of actions remaining
     */
    public void refreshTabs(Player currentPlayer, Pet currentPet, int currentDay, int numActions){
        statusTab.setPetStatus(currentPet);
        refreshPlayerStats(currentPlayer, numActions);
        lblCurrentPet.setText(currentPet.getName());
        lblDayMarker.setText(currentDay + "/" + totalDays);
    }

    /**
     * Get the store tab to add observer to it
     * @return storeTab
     */
    public StorePanel getStoreTab(){
        return storeTab;
    }
    
    /**
     * Main method for testing.
     * @param args Arguments passed in
     */
    public static void main(String[] args){
        try{
            String systemLook = UIManager.getSystemLookAndFeelClassName();
            UIManager.setLookAndFeel(systemLook);
        }catch(Exception e){
          //ignore all exceptions
        }
        JFrame myFrame = new JFrame();

        myFrame.setBounds(0, 0, 815, 540);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.getContentPane().setLayout(null);

        
        GameEnvironment mainGame = new GameEnvironment();
        mainGame.generateFoodPrototypes();
        mainGame.generateToyPrototypes();
        HomePanel myPanel = new HomePanel(12, mainGame);
        myFrame.getContentPane().add(myPanel);
        myPanel.setVisible(true);

        myPanel.setSize(800, 500);

        myFrame.setVisible(true);

        Player testPlayer = new Player();
        testPlayer.setName("Stewart Little");
        Pet cat;
        try{
            cat = new Pet("cat");
            cat.setGender("male");
            cat.setName("Snowy");
            testPlayer.getPetList().add(cat);
            myPanel.refreshTabs(testPlayer, cat, 5, 1);
        }catch (IOException e){
            System.out.println("Problem in cat giving displaying default label values");
        }
    }
}
