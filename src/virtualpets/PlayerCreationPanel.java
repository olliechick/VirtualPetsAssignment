package virtualpets;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Player creation panel.
 * @author Samuel Pell
 */
@SuppressWarnings("serial")
public class PlayerCreationPanel extends JPanel implements Observable {

    /**
     * List of observers the object has.
     */
    private ArrayList<Observer> observers = new ArrayList<Observer>();
    /**
     * Text box to let player enter their name.
     */
    private JTextField playerNameField;
    /**
     * Values class will output to parent controller.
     */
    private String[] outputValues;
    /**
     * Parent controller.
     */
    private GUIMain mainGUI;
    /**
     * Panel where pet one is created.
     */
    private PetCreationPanel petOnePanel;
    /**
     * Panel where pet two is created.
     */
    private PetCreationPanel petTwoPanel;
    /**
     * Panel where pet three is created.
     */
    private PetCreationPanel petThreePanel;
    /**
     * Check box to select pet slot one.
     */
    private JCheckBox petOneBox;
    /**
     * Check box to select pet slot two.
     */
    private JCheckBox petTwoBox;
    /**
     * Check box to select pet slot three.
     */
    private JCheckBox petThreeBox;

    /**
     * Create the panel.
     * @param parent Parent controller.
     */
    public PlayerCreationPanel(GUIMain parent) {
        mainGUI = parent;
        setLayout(null); //Absolute layout

        //Player name text box and label
        JLabel lblPlayerName = new JLabel("Player name:");
        lblPlayerName.setBounds(10, 14, 89, 14);
        add(lblPlayerName);

        playerNameField = new JTextField();
        playerNameField.setBounds(88, 8, 123, 26);
        add(playerNameField);
        playerNameField.setColumns(10);

        //Pet number selection label
        JLabel lblNumberOfPets = new JLabel("Pets:");
        lblNumberOfPets.setBounds(10, 39, 116, 14);
        add(lblNumberOfPets);

        //Pet creation panel creation
        petOnePanel = new PetCreationPanel();
        petOnePanel.setBounds(10, 87, 123, 225);
        add(petOnePanel);
        petOnePanel.enable();

        petTwoPanel = new PetCreationPanel();
        petTwoPanel.setBounds(148, 87, 123, 225);
        add(petTwoPanel);

        petThreePanel = new PetCreationPanel();
        petThreePanel.setBounds(287, 87, 123, 225);
        add(petThreePanel);

        //Creating check boxes and adding enabling disabling behavior
        petOneBox = new JCheckBox("1", true); //default to 1 pet
        petOneBox.setBounds(56, 57, 43, 23);
        petOneBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (petOneBox.isSelected()) {
                    petOnePanel.enable();
                } else {
                    petOnePanel.disable();
                }
            }
        });
        add(petOneBox);

        petTwoBox = new JCheckBox("2");
        petTwoBox.setBounds(194, 57, 43, 23);
        petTwoBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (petTwoBox.isSelected()) {
                    petTwoPanel.enable();
                } else {
                    petTwoPanel.disable();
                }
            }
        });
        add(petTwoBox);

        petThreeBox = new JCheckBox("3");
        petThreeBox.setBounds(339, 53, 43, 31);
        petThreeBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (petThreeBox.isSelected()) {
                    petThreePanel.enable();
                } else {
                    petThreePanel.disable();
                }
            }
        });
        add(petThreeBox);

        //Add button to move to next dialog.
        JButton btnNext = new JButton("Next");
        btnNext.addActionListener(new ActionListener() {
            /**
             * Listener that checks input, and if it is valid prepares it for
             * output and notifies the object's observers.
             * @param event Action event passed in when event triggered.
             */
            public void actionPerformed(ActionEvent event) {
                if (playerInputValid()) {
                    formatOutput();
                    notifyObservers();
                }
            }
        });
        btnNext.setBounds(321, 323, 89, 23);
        add(btnNext);
    }


    /**
     * Checks a players input into PlayerCreationPanel to see if it is
     * valid. If not this method displays an alert to let the player
     * know and to reattempt input.
     * @return If the players input is valid.
     */
    private boolean playerInputValid() {
        ArrayList<String> nameList = mainGUI.getRegisteredNames();
        //Clone name list so that the names entered in the form aren't
        //duplicates and so that any unconfirmed names aren't registered.
        //If not cloned then any entered names are effectively registered and
        //means they can't be used.
        @SuppressWarnings("unchecked") // Suppress warning that cast could fail.
        ArrayList<String> nameListClone = (ArrayList<String>) nameList.clone();

        //Always check player name strings
        String playerName = playerNameField.getText();
        if (playerName.equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter a player name and try again.");
            return false;
        } else if (mainGUI.nameTaken(playerName, nameListClone)) {
            JOptionPane.showMessageDialog(null, "Duplicate names are not allowed. (Player)");
            return false;
        }
        //add name to list so that duplicate names cannot occur within the form
        //not added to the full list as the names aren't confirmed yet.
        nameListClone.add(playerName);

        String petName;
        //Check pet one if it is selected
        if (petOneBox.isSelected()) {
            petName = petOnePanel.getPetName();
            if (petName.equals("")) {
                JOptionPane.showMessageDialog(null,
                        "Please enter a name for pet 1 and try again.");
                return false;
            } else if (mainGUI.nameTaken(petName, nameListClone)) {
                JOptionPane.showMessageDialog(null, "Duplicate names are not allowed. (Pet 1)");
                return false;
            }
            nameListClone.add(petName);
        }

        //Check pet two if it is selected
        if (petTwoBox.isSelected()) {
            petName = petTwoPanel.getPetName();
            if (petName.equals("")) {
                JOptionPane.showMessageDialog(null,
                        "Please enter a name for pet 2 and try again.");
                return false;
            } else if (mainGUI.nameTaken(petName, nameListClone)) {
                JOptionPane.showMessageDialog(null, "Duplicate names are not allowed. (Pet 2)");
                return false;
            }
            nameListClone.add(petName);
        }

        //Check pet three if it is selected
        if (petThreeBox.isSelected()) {
            petName = petThreePanel.getPetName();
            if (petName.equals("")) {
                JOptionPane.showMessageDialog(null,
                        "Please enter a name for pet 3 and try again.");
                return false;
            } else if (mainGUI.nameTaken(petName, nameListClone)) {
                JOptionPane.showMessageDialog(null, "Duplicate names are not allowed. (Pet 3)");
                return false;
            }
            nameListClone.add(petName);
        }

        if (!
                (petOneBox.isSelected()
                || petTwoBox.isSelected()
                || petThreeBox.isSelected())) { //if no pets are selected
            JOptionPane.showMessageDialog(null, "Each player must have at least one pet.");
            return false;
        }

        //if all input is valid (only option left - that or everything has gone horrendously wrong)
        return true;
    }

    /**
     * Formats output for new function.
     */
    private void formatOutput() {
        ArrayList<String> outputList = new ArrayList<String>();
        outputList.add(playerNameField.getText());
        mainGUI.registerName(playerNameField.getText());

        String petName;
        String petSpecies;
        if (petOneBox.isSelected()) {
            petName = petOnePanel.getPetName();
            petSpecies = petOnePanel.getPetSpecies();
            outputList.add(petName + "\n" + petSpecies);
            mainGUI.registerName(petName);
        }

        //Add pet two if it is selected
        if (petTwoBox.isSelected()) {
            petName = petTwoPanel.getPetName();
            petSpecies = petTwoPanel.getPetSpecies();
            outputList.add(petName + "\n" + petSpecies);
            mainGUI.registerName(petName);
        }

        //Add pet three if it is selected
        if (petThreeBox.isSelected()) {
            petName = petThreePanel.getPetName();
            petSpecies = petThreePanel.getPetSpecies();
            outputList.add(petName + "\n" + petSpecies);
            mainGUI.registerName(petName);
        }
        outputValues = outputList.toArray(new String[0]);
    }

    /**
     * Notify all observers of a change in values.
     */
    public void notifyObservers() {
        for (Observer o: observers) {
            o.getValues("player creation", outputValues);
        }
    }

    /**
     * Add a new observer to the object.
     * @param newObserver The observer to add.
     */
    public void registerObserver(Observer newObserver) {
        observers.add(newObserver);
    }

    /**
     * Testing functionality.
     * @param args Input arguments.
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
          //ignore all exceptions
        }
        JFrame myFrame = new JFrame();

        myFrame.setBounds(0, 0, 435, 395);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.getContentPane().setLayout(null);

        GUIMain mainGUI = new GUIMain();
        mainGUI.registerName("Alan");

        JPanel myPanel = new PlayerCreationPanel(mainGUI);

        myFrame.getContentPane().add(myPanel);
        myPanel.setVisible(true);

        myPanel.setSize(420, 355);

        myFrame.setVisible(true);
    }
}
