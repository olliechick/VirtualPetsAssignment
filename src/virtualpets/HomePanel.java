package virtualpets;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

/**
 * Home JPanel for VirtualPets Assignment. Acts as a controller
 * for the panels beneath it.
 * @author Samuel Pell
 * @author Ollie Chick
 */
@SuppressWarnings("serial")
public class HomePanel extends JPanel implements Observable {

    /**
     * Internal tabbed pane to hold tabs.
     */
    private JTabbedPane tabbedPane;
    /**
     * Internal panel to show status screen.
     */
    private StatusPanel statusTab;
    /**
     * Total number of days game will run for.
     */
    private String totalDays;
    /**
     * Element to display current (in-game) day.
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
     * Element to display the number of action the player has remaining.
     */
    private JLabel lblNumActions;
    /**
     * Internal panel to handle store stuff.
     */
    private StorePanel storeTab;
    /**
     * Internal panel to handle player playing with their pet.
     */
    private PlayPanel playTab;
    /**
     * Internal panel to handle player feeding with their pet.
     */
    private FeedPanel feedTab;
    /**
     * Internal panel to handle player putting their pet to sleep.
     */
    private SleepPanel sleepTab;
    /**
     * Internal panel to handle player toileting their pet.
     */
    private ToiletPanel toiletTab;
    /**
     * List of observers of this object.
     */
    private ArrayList<Observer> observers = new ArrayList<Observer>();
    /**
     * Button to advance onwards in the game.
     */
    private JButton btnNext;

    /**
     * Create the panel.
     * @param days Number of days the game will run for.
     * @param mainGame Reference to the game environment.
     */
    public HomePanel(int days, GameEnvironment mainGame) {
        totalDays = ((Integer) days).toString();
        setLayout(null);

        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(10, 36, 780, 419);
        add(tabbedPane);

        statusTab = new StatusPanel();
        tabbedPane.addTab("Status", null, statusTab, null);

        storeTab = new StorePanel(mainGame.getToyPrototypes(),
                                  mainGame.getFoodPrototypes());
        tabbedPane.addTab("Store", null, storeTab, null);

        feedTab = new FeedPanel();
        tabbedPane.addTab("Feed", null, feedTab, null);

        playTab = new PlayPanel();
        tabbedPane.addTab("Play", null, playTab, null);

        sleepTab = new SleepPanel();
        tabbedPane.addTab("Sleep", null, sleepTab, null);

        toiletTab = new ToiletPanel();
        tabbedPane.addTab("Toilet", null, toiletTab, null);

        JLabel lblPlayer = new JLabel("Player:");
        lblPlayer.setBounds(10, 11, 46, 14);
        add(lblPlayer);

        lblCurrentPlayer = new JLabel("currentPlayer");
        lblCurrentPlayer.setBounds(51, 11, 159, 14);
        add(lblCurrentPlayer);

        JLabel lblBalance = new JLabel("Balance:");
        lblBalance.setBounds(211, 11, 59, 14);
        add(lblBalance);

        lblPlayerBalance = new JLabel("playerBalance");
        lblPlayerBalance.setBounds(260, 11, 75, 14);
        add(lblPlayerBalance);

        JLabel lblPet = new JLabel("Pet:");
        lblPet.setBounds(320, 11, 46, 14);
        add(lblPet);

        lblCurrentPet = new JLabel("currentPet");
        lblCurrentPet.setBounds(345, 11, 176, 14);
        add(lblCurrentPet);

        JLabel lblActionsRemaining = new JLabel("Actions remaining:");
        lblActionsRemaining.setBounds(591, 7, 122, 23);
        add(lblActionsRemaining);

        lblNumActions = new JLabel("numActions");
        lblNumActions.setBounds(685, 11, 66, 14);
        add(lblNumActions);

        lblDayMarker = new JLabel("dayMarker");
        lblDayMarker.setBounds(731, 11, 59, 14);
        add(lblDayMarker);

        JLabel lblDay = new JLabel("Day:");
        lblDay.setBounds(701, 11, 25, 14);
        add(lblDay);

        btnNext = new JButton("Next");
        btnNext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                notifyObservers();
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
    public void refreshPlayerStats(Player currentPlayer, int numActions) {
        Integer balance = currentPlayer.getBalance();
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
     * @param currenlySleeping Is the current pet sleeping?
     * @param currentlyOnToilet Is the current pet on the toilet?
     */
    public void refreshTabs(Player currentPlayer, Pet currentPet,
                            int currentDay, int numActions,
                            Boolean currentlySleeping,
                            Boolean currentlyOnToilet) {
        statusTab.setPetStatus(currentPet);
        storeTab.updatePlayerInventory(currentPlayer);
        playTab.listPlayerToys(currentPlayer.getToyList());
        feedTab.listPlayerFood(currentPlayer.getFoodStock());
        refreshPlayerStats(currentPlayer, numActions);
        lblCurrentPet.setText(currentPet.getName());
        lblDayMarker.setText(currentDay + "/" + totalDays);
        if (currentlySleeping) {
            sleepTab.setAsleepImage(currentPet);
        } else {
            sleepTab.setAwakeImage(currentPet);
        }

        if (currentlyOnToilet) {
            toiletTab.setToiletingImage(currentPet);
        } else {
            toiletTab.setNormalImage(currentPet);
        }
    }

    /**
     * Get the store tab to add observer to it.
     * @return store tab
     */
    public StorePanel getStoreTab() {
        return storeTab;
    }

    /**
     * Get the feeding tab.
     * @return feeding tab
     */
    public FeedPanel getFeedingTab() {
        return feedTab;
    }

    /**
     * Get the toilet tab.
     * @return toilet tab
     */
    public ToiletPanel getToiletTab() {
        return toiletTab;
    }

    /**
     * Get the play tab.
     * @return play tab
     */
    public PlayPanel getPlayTab() {
        return playTab;
    }

    /**
     * Get the sleep tab to add observer to it.
     * @return storeTab
     */
    public SleepPanel getSleepTab() {
        return sleepTab;
    }

    /**
     * {@inheritDoc}
     */
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * Notifies observers with identifier "next".
     */
    public void notifyObservers() {
        for (Observer o: observers) {
            o.getValues("next", new String[0]);
        }
    }

    /**
     * Set the text on the button which moves player onto the next step.
     * @param text New button text
     */
    public void setNextButtonText(String text){
        btnNext.setText(text);
    }

    /**
     * Return to status screen.
     */
    public void returnToStatus() {
        tabbedPane.setSelectedComponent(statusTab);
    }

    /**
     * Main method for testing.
     * @param args Arguments passed in
     */
    public static void main(String[] args) {
        try {
            String systemLook = UIManager.getSystemLookAndFeelClassName();
            UIManager.setLookAndFeel(systemLook);
        } catch (Exception e) {
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
        Pet cat = new Pet("cat");
        cat.setGender("male");
        cat.setName("Snowy");
        testPlayer.getPetList().add(cat);
        myPanel.refreshTabs(testPlayer, cat, 5, 1, true, true);
    }
}
