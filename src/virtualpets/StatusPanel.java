package virtualpets;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
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
     * Image to quickly communicate pet species.
     */
    private JLabel lblSpeciesIcon;
    /**
     * Label to display pet's mischeviousness.
     */
    JLabel lblMischievousnessScore;
    /**
     * Label to display pets favourite toy.
     */
    JLabel lblFavouriteToy;
    /**
     * Label to display pets favourite food.
     */
    JLabel lblFavouriteFood;

    /**
     * Create the panel.
     */
    public StatusPanel() {
        setLayout(null);

        JLabel lblHappiness = new JLabel("Happiness:");
        lblHappiness.setBounds(32, 53, 77, 14);
        add(lblHappiness);

        JLabel lblHunger = new JLabel("Hunger:");
        lblHunger.setBounds(32, 78, 46, 14);
        add(lblHunger);

        JLabel lblPlayfulness = new JLabel("Fatigue:");
        lblPlayfulness.setBounds(32, 103, 46, 14);
        add(lblPlayfulness);

        JLabel lblHealth = new JLabel("Health:");
        lblHealth.setBounds(32, 126, 46, 14);
        add(lblHealth);

        JLabel lblBladderFullness = new JLabel("Bladder full:");
        lblBladderFullness.setBounds(32, 150, 77, 14);
        add(lblBladderFullness);

        JLabel lblWeight = new JLabel("Weight:");
        lblWeight.setBounds(32, 204, 46, 14);
        add(lblWeight);

        JLabel lblMisbehaving = new JLabel("Misbehaving:");
        lblMisbehaving.setBounds(32, 229, 77, 14);
        add(lblMisbehaving);

        JLabel lblSick = new JLabel("Sick:");
        lblSick.setBounds(32, 254, 46, 14);
        add(lblSick);

        JLabel lblRevivable = new JLabel("Revivable:");
        lblRevivable.setBounds(32, 279, 59, 14);
        add(lblRevivable);

        lblHappinessScore = new JLabel("hapinessScore");
        lblHappinessScore.setBounds(119, 53, 46, 14);
        add(lblHappinessScore);

        lblHungerScore = new JLabel("hungerScore");
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
        lblWeightScore.setBounds(119, 204, 46, 14);
        add(lblWeightScore);

        lblIsMisbehaving = new JLabel("isMisbehaving");
        lblIsMisbehaving.setBounds(119, 229, 46, 14);
        add(lblIsMisbehaving);

        lblIsSick = new JLabel("isSick");
        lblIsSick.setBounds(119, 254, 46, 14);
        add(lblIsSick);

        lblIsRevivable = new JLabel("isRevivable");
        lblIsRevivable.setBounds(119, 279, 46, 14);
        add(lblIsRevivable);

        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(175, 53, 46, 14);
        add(lblName);

        lblPetName = new JLabel("petName");
        lblPetName.setBounds(221, 53, 164, 14);
        add(lblPetName);

        JLabel lblGender = new JLabel("Gender:");
        lblGender.setBounds(175, 78, 46, 14);
        add(lblGender);

        lblPetGender = new JLabel("petGender");
        lblPetGender.setBounds(221, 78, 164, 14);
        add(lblPetGender);

        JLabel lblSpecies = new JLabel("Species:");
        lblSpecies.setBounds(175, 103, 46, 14);
        add(lblSpecies);

        lblPetSpecies = new JLabel("petSpecies");
        lblPetSpecies.setBounds(221, 103, 164, 14);
        add(lblPetSpecies);

        JPanel panel = new JPanel();
        panel.setBounds(395, 53, 365, 275);
        add(panel);
        panel.setLayout(null);

        lblSpeciesIcon = new JLabel("");
        lblSpeciesIcon.setBounds(37, 0, 365, 275);
        panel.add(lblSpeciesIcon);

        JLabel lblFavourites = new JLabel("Favourites");
        lblFavourites.setBounds(175, 126, 69, 14);
        add(lblFavourites);

        JLabel lblFood = new JLabel("Food:");
        lblFood.setBounds(175, 150, 46, 14);
        add(lblFood);

        JLabel lblToy = new JLabel("Toy:");
        lblToy.setBounds(175, 175, 46, 14);
        add(lblToy);

        lblFavouriteFood = new JLabel("favouriteFood");
        lblFavouriteFood.setBounds(221, 150, 164, 14);
        add(lblFavouriteFood);

        lblFavouriteToy = new JLabel("favouriteToy");
        lblFavouriteToy.setBounds(221, 175, 164, 14);
        add(lblFavouriteToy);

        JLabel lblMischeviousness = new JLabel("Mischievousness:");
        lblMischeviousness.setBounds(32, 175, 93, 14);
        add(lblMischeviousness);

        lblMischievousnessScore = new JLabel("mischeivousnessScore");
        lblMischievousnessScore.setBounds(119, 175, 46, 14);
        add(lblMischievousnessScore);

    }

    /**
     * Set the image associated with the pet.
     * @param pet Pet the image is associated with.
     */
    private void setImage(Pet pet) {
        //String topDir = System.getProperty("user.dir");
        String fileName = "img/";
        switch (pet.getSpecies()) {
            case "alpaca":
                fileName += "AlpacaSmall.png";
                break;
            case "cat":
                fileName += "CatSmall.png";
                break;
            case "dog":
                fileName += "DogSmall.png";
                break;
            case "goat":
                fileName += "GoatSmall.png";
                break;
            case "horse":
                fileName += "HorseSmall.png";
                break;
            case "polar bear":
                fileName += "PolarBearSmall.png";
                break;
        }

        ImageIcon icon = new ImageIcon(fileName);
        if (icon.getImage().getHeight(null) == -1) {
            //if running from jar image height will be -1 as it cannot be found
            //in this case add a forward slash onto the front of the filename
            //so that the system searches from top of class path.
            icon = new ImageIcon();
            Image image;
            try {
                image = ImageIO.read(getClass().getResource("/" + fileName));
                icon.setImage(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        lblSpeciesIcon.setIcon(icon);
    }

    /**
     * Refresh the pet status.
     * @param pet Pet to display status for.
     */
    public void setPetStatus(Pet pet) {
        //Cast all ints to Integers then use the toString method to get string representation
        lblFatigueScore.setText(((Integer) pet.getFatigue()).toString() + "%");
        lblHappinessScore.setText(((Integer) pet.getHappiness()).toString() + "%");
        lblHealthScore.setText(((Integer) pet.getHealth()).toString() + "%");
        lblHungerScore.setText(((Integer) pet.getHunger()).toString() + "%");
        lblMischievousnessScore.setText(((Integer) pet.getMischievousness()).toString() + "%");

        Integer bladderLevelInt = pet.getPercentBladderFull();
        String bladderLevel = bladderLevelInt.toString();
        lblBladderPercentage.setText(bladderLevel + "%");

        String weight = ((Double) pet.getWeight()).toString();
        lblWeightScore.setText(weight + " kg");

        lblPetName.setText(pet.getName());
        lblPetGender.setText(sentenceCase(pet.getGender()));
        lblPetSpecies.setText(sentenceCase(pet.getSpecies()));
        lblFavouriteFood.setText(sentenceCase(pet.getFavouriteFood()));
        lblFavouriteToy.setText(sentenceCase(pet.getFavouriteToy()));

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

        setImage(pet);
        highlightWarnings(pet);
    }

    /**
     * Takes a strings and returns the sentence case version.
     * e.g. "this is my string" -> "This is my string"
     * e.g. "Already sentence case string" -> "Already sentence case string"
     * e.g. "c" -> "C"
     * @param str String to turn sentence case
     * @return String in sentence case form
     */
    private String sentenceCase(String str) {
    	if (str.length() == 0) {
    		return str;
    	} else if (str.length() == 1) {
    		return str.toUpperCase();
    	} else { //str.length() >= 2
    		return str.substring(0, 1).toUpperCase() + str.substring(1);
    	}
    }

	/**
     * Highlight labels to warn player. Also sets tool tips to alert user of
     * level of concern.
     * Highlighting rules: orange if in bottom 40%, red if in bottom 15%.
     * @param pet Pet currently being displayed.
     */
    private void highlightWarnings(Pet pet) {
        int fatigue = pet.getFatigue();
        int happiness = pet.getHappiness();
        int health = pet.getHealth();
        int hunger = pet.getHunger();
        int bladderLevel = pet.getPercentBladderFull();
        int mischievousness = pet.getMischievousness();

        if (fatigue >= 60 && fatigue < 85) {
            lblFatigueScore.setForeground(Color.ORANGE);
            lblFatigueScore.setToolTipText("Warning");
        } else if (fatigue >= 85) {
            lblFatigueScore.setForeground(Color.RED);
            lblFatigueScore.setToolTipText("Urgent");
        } else { //everything is normal
            lblFatigueScore.setForeground(Color.BLACK);
            lblFatigueScore.setToolTipText(null);
        }

        if (happiness <= 40 && happiness > 15) {
            lblHappinessScore.setForeground(Color.ORANGE);
            lblHappinessScore.setToolTipText("Warning");
        } else if (happiness <= 15) {
            lblHappinessScore.setForeground(Color.RED);
            lblHappinessScore.setToolTipText("Urgent");
        }  else { //everything is normal
            lblHappinessScore.setForeground(Color.BLACK);
            lblHappinessScore.setToolTipText(null);
        }


        if (health <= 40 && health > 15) {
            lblHealthScore.setForeground(Color.ORANGE);
            lblHealthScore.setToolTipText("Warning");
        } else if (health <= 15) {
            lblHealthScore.setForeground(Color.RED);
            lblHealthScore.setToolTipText("Urgent");
        } else { //everything is normal
            lblHealthScore.setForeground(Color.BLACK);
            lblHealthScore.setToolTipText(null);
        }


        if (hunger >= 60 && hunger < 85) {
            lblHungerScore.setForeground(Color.ORANGE);
            lblHungerScore.setToolTipText("Warning");
        } else if (hunger >= 85) {
            lblHungerScore.setForeground(Color.RED);
            lblHungerScore.setToolTipText("Urgent");
        } else { //everything is normal
            lblHungerScore.setForeground(Color.BLACK);
            lblHungerScore.setToolTipText(null);
        }


        if (bladderLevel >= 60 && bladderLevel < 85) {
            lblBladderPercentage.setForeground(Color.ORANGE);
            lblBladderPercentage.setToolTipText("Warning");
        } else if (bladderLevel >= 85) {
            lblBladderPercentage.setForeground(Color.RED);
            lblBladderPercentage.setToolTipText("Urgent");
        } else { //everything is normal
            lblBladderPercentage.setForeground(Color.BLACK);
            lblBladderPercentage.setToolTipText(null);
        }

        if (mischievousness >= 60 && mischievousness < 85) {
            lblMischievousnessScore.setForeground(Color.ORANGE);
            lblMischievousnessScore.setToolTipText("Warning");
        } else if (mischievousness >= 85) {
            lblMischievousnessScore.setForeground(Color.RED);
            lblMischievousnessScore.setToolTipText("Urgent");
        } else { //everything is normal
            lblMischievousnessScore.setForeground(Color.BLACK);
            lblMischievousnessScore.setToolTipText(null);
        }

        if (pet.getIsMisbehaving()) {
            lblIsMisbehaving.setForeground(Color.RED);
        } else {
            lblIsMisbehaving.setForeground(Color.BLACK);
        }


        if (pet.getIsSick()) {
            lblIsSick.setForeground(Color.RED);
        } else {
            lblIsSick.setForeground(Color.BLACK);
        }

        if (!pet.getIsRevivable()) {
            lblIsRevivable.setForeground(Color.RED);
        } else {
            lblIsRevivable.setForeground(Color.BLACK);
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

        cat = new Pet("cat");
        cat.setGender("male");
        cat.setName("Snowy");
        myPanel.setPetStatus(cat);

        myFrame.getContentPane().add(myPanel);
        myPanel.setVisible(true);

        myPanel.setSize(770, 383);
        myFrame.setVisible(true);
    }
}
