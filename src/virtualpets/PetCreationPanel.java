package virtualpets;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import java.awt.Component;

/**
 * Pet creation JPanel for VirtualPets Assignment.
 * @author Samuel Pell
 */
@SuppressWarnings("serial")
public class PetCreationPanel extends JPanel {
    /**
     * Text field into which user enters the name desired for the pet.
     */
    private JTextField petNameField;
    /**
     * Radiobutton to select cat species.
     */
    private JRadioButton rdbtnCat;
    /**
     * Radiobutton to select Dog species.
     */
    private JRadioButton rdbtnDog;
    /**
     * Radiobutton to select goat species.
     */
    private JRadioButton rdbtnGoat;
    /**
     * Radiobutton to select horse species.
     */
    private JRadioButton rdbtnHorse;
    /**
     * Radiobutton to select alpaca species.
     */
    private JRadioButton rdbtnAlpaca;
    /**
     * Radiobutton to select polar bear species.
     */
    private JRadioButton rdbtnPolarBear;

    /**
     * Create the panel.
     * Panel needs to be enabled before the user may use it.
     */
    public PetCreationPanel() {

        ButtonGroup radioButtonGroup = new ButtonGroup();
        setLayout(null);

        petNameField = new JTextField();
        petNameField.setBounds(10, 25, 103, 20);
        add(petNameField);
        petNameField.setColumns(10);

        JLabel lblPetName = new JLabel("Pet name:");
        lblPetName.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblPetName.setBounds(31, 11, 61, 14);
        add(lblPetName);

        JLabel lblPetSpecies = new JLabel("Pet species:");
        lblPetSpecies.setBounds(27, 48, 69, 14);
        add(lblPetSpecies);

        //Create the tooltips
        Cat cat = new Cat();
        Dog dog = new Dog();
        Horse horse = new Horse();
        Goat goat = new Goat();
        Alpaca alpaca = new Alpaca();
        PolarBear polarBear = new PolarBear();

        String attributes = "<html>Weight: %.0f kg"
        				  + "<br />Bladder size: %d"
        		          + "<br />Fatigue rate: %d"
        		          + "<br />Harshness (with toys): %d"
        		          + "<br />Favourite food: %s"
        		          + "<br />Favourite toy: %s</html>";

        rdbtnCat = new JRadioButton("<html><u>Cat</u></html>");
        rdbtnCat.setToolTipText(String.format(attributes,
        		                              cat.getDefaultWeight(),
        		                              cat.getBladderSize(),
        		                              cat.getFatigueIncrease(),
        		                              cat.getHarshness(),
        		                              cat.getFavouriteFood(),
        		                              cat.getFavouriteToy()));
        rdbtnCat.setBounds(0, 91, 109, 23);
        add(rdbtnCat);

        rdbtnDog = new JRadioButton("<html><u>Dog</u></html>");
        rdbtnDog.setToolTipText(String.format(attributes,
        		                              dog.getDefaultWeight(),
        		                              dog.getBladderSize(),
        		                              dog.getFatigueIncrease(),
        		                              dog.getHarshness(),
        		                              dog.getFavouriteFood(),
        		                              dog.getFavouriteToy()));
        rdbtnDog.setBounds(0, 117, 109, 23);
        add(rdbtnDog);

        rdbtnHorse = new JRadioButton("<html><u>Horse</u></html>");
        rdbtnHorse.setToolTipText(String.format(attributes,
        		                                horse.getDefaultWeight(),
        		                                horse.getBladderSize(),
        		                                horse.getFatigueIncrease(),
        		                                horse.getHarshness(),
        		                                horse.getFavouriteFood(),
        		                                horse.getFavouriteToy()));
        rdbtnHorse.setBounds(0, 169, 109, 23);
        add(rdbtnHorse);

        rdbtnGoat = new JRadioButton("<html><u>Goat</u></html>");
        rdbtnGoat.setToolTipText(String.format(attributes,
        		                               goat.getDefaultWeight(),
        		                               goat.getBladderSize(),
        		                               goat.getFatigueIncrease(),
        		                               goat.getHarshness(),
        		                               goat.getFavouriteFood(),
        		                               goat.getFavouriteToy()));
        rdbtnGoat.setBounds(0, 143, 109, 23);
        add(rdbtnGoat);

        rdbtnAlpaca = new JRadioButton("<html><u>Alpaca</u></html>", true); //default to alpaca
        rdbtnAlpaca.setToolTipText(String.format(attributes,
        		                                 alpaca.getDefaultWeight(),
        		                                 alpaca.getBladderSize(),
        		                                 alpaca.getFatigueIncrease(),
        		                                 alpaca.getHarshness(),
        		                                 alpaca.getFavouriteFood(),
        		                                 alpaca.getFavouriteToy()));
        rdbtnAlpaca.setBounds(0, 65, 109, 23);
        add(rdbtnAlpaca);

        rdbtnPolarBear = new JRadioButton("<html><u>Polar bear</u></html>");
        rdbtnPolarBear.setToolTipText(String.format(attributes,
        		                                    polarBear.getDefaultWeight(),
        		                                    polarBear.getBladderSize(),
        		                                    polarBear.getFatigueIncrease(),
        		                                    polarBear.getHarshness(),
        		                                    polarBear.getFavouriteFood(),
        		                                    polarBear.getFavouriteToy()));
        rdbtnPolarBear.setBounds(0, 195, 109, 23);
        add(rdbtnPolarBear);

        radioButtonGroup.add(rdbtnPolarBear);
        radioButtonGroup.add(rdbtnAlpaca);
        radioButtonGroup.add(rdbtnCat);
        radioButtonGroup.add(rdbtnDog);
        radioButtonGroup.add(rdbtnGoat);
        radioButtonGroup.add(rdbtnHorse);
        disable(); //Default to disabled state

    }


    /**
     * Enables all components in the panel. Wrapper function for setEnable.
     */
    public void enable() {
        setEnable(true);
    }


    /**
     * Disables all components in the panel. Wapper function for setEnable.
     */
    public void disable() {
        setEnable(false);
    }


    /**
     * Workhorse function to disable or enable all components in the panel.
     * @param enabled Boolean to enable or disable
     */
    private void setEnable(Boolean enabled) {
        rdbtnCat.setEnabled(enabled);
        rdbtnDog.setEnabled(enabled);
        rdbtnGoat.setEnabled(enabled);
        rdbtnHorse.setEnabled(enabled);
        rdbtnAlpaca.setEnabled(enabled);
        rdbtnPolarBear.setEnabled(enabled);
        petNameField.setEnabled(enabled);

    }


    /**
     * Get pet name from input box.
     * @return pet name
     */
    public String getPetName() {
        return petNameField.getText();
    }


    /**
     * Get species selected as a string.
     * @return speces selected
     */
    public String getPetSpecies() {
        String species;
        if (rdbtnAlpaca.isSelected()) {
            species = "alpaca";
        } else if (rdbtnCat.isSelected()) {
            species = "cat";
        } else if (rdbtnDog.isSelected()) {
            species = "dog";
        } else if (rdbtnGoat.isSelected()) {
            species = "goat";
        } else if (rdbtnHorse.isSelected()) {
            species = "horse";
        } else { //final case polar bear is selected
            species = "polar bear";
        }
        return species;
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

        myFrame.setBounds(0, 0, 450, 400);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.getContentPane().setLayout(null);

        PetCreationPanel myPanel = new PetCreationPanel();

        myFrame.getContentPane().add(myPanel);
        myPanel.setVisible(true);

        myPanel.setSize(420, 350);
        myPanel.enable();
        myFrame.setVisible(true);
    }
}
