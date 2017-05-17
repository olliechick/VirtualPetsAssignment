package virtualpets;
import javax.swing.JPanel;
import java.awt.Color;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.ImageIcon;


/**
 * Shows a pets status to the user.
 * @author Samuel Pell
 * @author Ollie Chick
 */
@SuppressWarnings("serial")
public class StatusPanel extends JPanel {
    /**
     * Label to display pet's happiness.
     */
    private JLabel lblHappinessScore;
    /**
     * Label to display how hungry the pet is.
     */
    private JLabel lblHungerScore;
    /**
     * Label to display if the pet is revivable.
     */
    private JLabel lblIsRevivable;
    /**
     * Label to display pet's fatigue.
     */
    private JLabel lblFatigueScore;
    /**
     * Label to displays pet's health.
     */
    private JLabel lblHealthScore;
    /**
     * Label to display how full the pet's bladder is.
     */
    private JLabel lblBladderPercentage;
    /**
     * Label to display the pet's weight (in kg).
     */
    private JLabel lblWeightScore;
    /**
     * Label to display if the pet is misbehaving.
     */
    private JLabel lblIsMisbehaving;
    /**
     * Label to display if the pet is sick or not.
     */
    private JLabel lblIsSick;
    /**
     * Label to display the pet's name.
     */
    private JLabel lblPetName;
    /**
     * Label to display the pet's gender.
     */
    private JLabel lblPetGender;
    /**
     * Label to display the pet's species.
     */
    private JLabel lblPetSpecies;

    /**
     * Create the panel.
     */
    public StatusPanel() {
        setLayout(null);

        JLabel lblHappiness = new JLabel("Happiness");
        lblHappiness.setBounds(32, 53, 77, 14);
        add(lblHappiness);

        JLabel lblHunger = new JLabel("Hunger");
        lblHunger.setBounds(32, 78, 46, 14);
        add(lblHunger);

        JLabel lblPlayfulness = new JLabel("Fatigue");
        lblPlayfulness.setBounds(32, 103, 46, 14);
        add(lblPlayfulness);

        JLabel lblHealth = new JLabel("Health");
        lblHealth.setBounds(32, 126, 46, 14);
        add(lblHealth);

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

        lblHappinessScore = new JLabel("hapinessScore");
        lblHappinessScore.setBounds(119, 53, 69, 14);
        add(lblHappinessScore);

        lblHungerScore = new JLabel("hunger");
        lblHungerScore.setBounds(119, 78, 46, 14);
        add(lblHungerScore);

        lblFatigueScore = new JLabel("fatigueScore");
        lblFatigueScore.setBounds(119, 103, 46, 14);
        add(lblFatigueScore);

        lblHealthScore = new JLabel("healthScore");
        lblHealthScore.setBounds(119, 126, 46, 14);
        add(lblHealthScore);

        lblBladderPercentage = new JLabel("bladderPercentage");
        lblBladderPercentage.setBounds(119, 150, 46, 14);
        add(lblBladderPercentage);

        lblWeightScore = new JLabel("weightScore");
        lblWeightScore.setBounds(119, 175, 46, 14);
        add(lblWeightScore);

        lblIsMisbehaving = new JLabel("isMisbehaving");
        lblIsMisbehaving.setBounds(119, 200, 46, 14);
        add(lblIsMisbehaving);

        lblIsSick = new JLabel("isSick");
        lblIsSick.setBounds(119, 225, 46, 14);
        add(lblIsSick);

        lblIsRevivable = new JLabel("isRevivable");
        lblIsRevivable.setBounds(119, 250, 46, 14);
        add(lblIsRevivable);

        JSeparator separator = new JSeparator(); //break up the screen to make it look better.
        separator.setOrientation(SwingConstants.VERTICAL);
        separator.setForeground(new Color(0, 0, 0));
        separator.setBounds(198, 53, 39, 218);

        JLabel lblName = new JLabel("Name");
        lblName.setBounds(212, 53, 46, 14);
        add(lblName);

        lblPetName = new JLabel("petName");
        lblPetName.setBounds(268, 53, 46, 14);
        add(lblPetName);

        JLabel lblGender = new JLabel("Gender");
        lblGender.setBounds(212, 78, 46, 14);
        add(lblGender);

        lblPetGender = new JLabel("petGender");
        lblPetGender.setBounds(268, 78, 46, 14);
        add(lblPetGender);

        JLabel lblSpecies = new JLabel("Species");
        lblSpecies.setBounds(212, 103, 46, 14);
        add(lblSpecies);

        lblPetSpecies = new JLabel("petSpecies");
        lblPetSpecies.setBounds(268, 103, 71, 14);
        add(lblPetSpecies);

        JPanel panel = new JPanel();
        panel.setBounds(349, 53, 365, 275);
        add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("");
        //TODO: Make this work for different animals
        String topDir = System.getProperty("user.dir");
        String fileName = "AlpacaSmall.png";
        if (topDir.endsWith("bin")) {
            fileName = "../img/" + fileName;
        } else {
            fileName = System.getProperty("user.dir")  + "/img/" + fileName;
        }
        lblNewLabel.setIcon(new ImageIcon(fileName));
        lblNewLabel.setBounds(0, 0, 365, 275);
        panel.add(lblNewLabel);
    }
    /**
     * Refresh the pet status.
     * @param pet Pet to display status for.
     */
    public void setPetStatus(Pet pet) {
        //Cast all ints to integers then use the toString method to get string representation
        lblFatigueScore.setText(((Integer) pet.getFatigue()).toString());
        lblHappinessScore.setText(((Integer) pet.getHappiness()).toString());
        lblHealthScore.setText(((Integer) pet.getHealth()).toString());
        lblHungerScore.setText(((Integer) pet.getHunger()).toString());

        Integer bladderLevelInt = pet.getPercentBladderFull();
        String bladderLevel = bladderLevelInt.toString();
        lblBladderPercentage.setText(bladderLevel + " %");

        String weight = ((Double) pet.getWeight()).toString();
        lblWeightScore.setText(weight + " kg");

        lblPetGender.setText(pet.getGender());
        lblPetName.setText(pet.getName());
        lblPetSpecies.setText(pet.getSpecies());

        if (pet.getIsRevivable()) {
            lblIsRevivable.setText("Yes");
        } else {
            lblIsRevivable.setText("No");
        }

        if (pet.getIsMisbehaving()) {
            lblIsMisbehaving.setText("Yes");
        } else {
            lblIsMisbehaving.setText("No");
        }

        if (pet.getIsSick()) {
            lblIsSick.setText("Yes");
        } else {
            lblIsSick.setText("No");
        }
    }

    /**
     * Testing the panel.
     * @param args Arguments passed from the command line.
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
          //ignore all exceptions
        }

        JFrame myFrame = new JFrame();

        myFrame.setBounds(0, 0, 785, 423);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.getContentPane().setLayout(null);

        Pet cat;
        StatusPanel myPanel = new StatusPanel();

        //TODO is this more tests?
        try {
            cat = new Pet("cat");
            cat.setGender("male");
            cat.setName("Snowy");
            myPanel.setPetStatus(cat);
        } catch (IOException e) {
            System.out.println("Problem in cat giving displaying default label values");
        }

        myFrame.getContentPane().add(myPanel);
        myPanel.setVisible(true);

        myPanel.setSize(770, 383);
        myFrame.setVisible(true);
    }
}
