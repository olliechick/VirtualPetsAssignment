package virtualpets;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.JRadioButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;

import java.util.ArrayList;


/**
 * A JPanel to create setup dialogue.
 * @author Samuel Pell
 * @author Ollie Chick
 */
@SuppressWarnings("serial")
public class SetupPanel extends JPanel implements Observable {
    /**
     * Values class will output.
     */
    private String[] outputValues = new String[2];

    /**
     * List of objects observing this object.
     */
    private ArrayList<Observer> observers = new ArrayList<Observer>();

    /**
     * Width of the object on screen.
     */
    public final int width = 300; //TODO why are these public? TODO are these even used?

    /**
     * Height of the object on screen.
     */
    public final int height = 165;

    /**
     * Text that is displayed as the tutorial.
     */
    private String helpText = "Welcome to Virtual Pets!"
            + "\nThis is a game for 1-3 players. "
            + "\nEach player has 1-3 pets, which can be cats, dogs, goats, "
                + "alpacas, horses, or polar bears, or a mixture. "
            + "\nWhen you choose your pets, you can hover over their species "
                + "to see their characteristics. "
            + "\nYou begin with $100 to buy food and toys for your pets. "
                + "You will receive $15 per day per (live) pet. "
            + "\nEach day, each player can perform up to two actions per pet "
                + "(or click the next button to skip to the next pet). "
            + "\nFeeding, playing, going toilet, and sleeping all count as one action each. "
            + "\nIf you neglect to keep your pet happy and healthy, "
                + "they may begin to misbehave, get sick, and even die. "
            + "\nIf they misbehave, you can choose to discipline your pet, which "
                + "will decrease their happiness but also decrease their mischievousness. "
            + "\nIf they get sick, you can choose to treat them (if you can afford it). "
            + "\nIf you don't treat them, or you're very unlucky, they may die. "
                + "You can revive each pet once, "
                + "but if they die again they will be dead for good."
            + "\n<html><h3>Strategy</h3></html>"
            + "\nCheck your pet's status before you start interacting with each pet. "
            + "\nThen make an informed decision of what to use your money "
                + "and two daily actions for. "
            + "\nKeeping your pet well will decrease its chance of misbhaving and getting sick. "
            + "\nAttributes that are of concern will be highlighted in orange; "
                + "those highlighted in red are urgent.";

    /**
     * Create the panel.
     */
    public SetupPanel() {
        setLayout(null);

        ButtonGroup radioButtonGroup = new ButtonGroup();

        JLabel lblNumDays = new JLabel("<html><u>Number of days to play for:</u></html>");
        //This label is underlined to let the user know they can hover over it.
        lblNumDays.setBounds(25, 15, 158, 14);
        lblNumDays.setToolTipText("The number of days must be an integer "
                                  + "between 1 and 365. If you enter a number "
                                  + "outside this range, it will revert to the "
                                  + "last valid input.");
        add(lblNumDays);

        JSpinner numberOfDaysSpinner = new JSpinner();
        numberOfDaysSpinner.setModel(new SpinnerNumberModel(1, 1, 365, 1));
        numberOfDaysSpinner.setBounds(187, 11, 39, 20);
        add(numberOfDaysSpinner);

        JLabel lblNumberOfPlayers = new JLabel("Number of players:");
        lblNumberOfPlayers.setBounds(67, 36, 116, 14);
        add(lblNumberOfPlayers);

        JRadioButton onePlayer = new JRadioButton("1", true);
        onePlayer.setBounds(187, 37, 60, 23);
        add(onePlayer);

        JRadioButton twoPlayers = new JRadioButton("2");
        twoPlayers.setBounds(187, 66, 60, 23);
        add(twoPlayers);

        JRadioButton threePlayers = new JRadioButton("3");
        threePlayers.setBounds(187, 95, 60, 23);
        add(threePlayers);

        JButton btnHelp = new JButton("");
        btnHelp.setHorizontalTextPosition(JButton.CENTER);
        btnHelp.setVerticalTextPosition(JButton.CENTER);
        btnHelp.setContentAreaFilled(false);
        //Set the help button image.
        String fileName = "img/helpIcon.png";
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
        btnHelp.setIcon(icon);


        btnHelp.setBounds(10, 131, 23, 23);
        btnHelp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                JOptionPane.showMessageDialog(null, helpText);
            }
        });
        add(btnHelp);

        radioButtonGroup.add(onePlayer);
        radioButtonGroup.add(twoPlayers);
        radioButtonGroup.add(threePlayers);

        JButton btnNext = new JButton("Next");
        btnNext.setBounds(187, 131, 70, 23);
        btnNext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Integer numPlayers;
                Integer numDays;
                if (onePlayer.isSelected()) {
                    numPlayers = 1;
                } else if (twoPlayers.isSelected()) {
                    numPlayers = 2;
                } else { //default case three players selected.
                    numPlayers = 3;
                }
                numDays = (Integer) numberOfDaysSpinner.getValue();

                outputValues[0] = numPlayers.toString();
                outputValues[1] = numDays.toString();
                notifyObservers();
            }
        });
        add(btnNext);
    }

    /**
     * Notify all observers of a change in values.
     */
    public void notifyObservers() {
        for (Observer o: observers) {
            o.getValues("setup", outputValues);
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

        myFrame.setBounds(0, 0, 300, 200);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.getContentPane().setLayout(null);

        JPanel myPanel = new SetupPanel();

        myFrame.getContentPane().add(myPanel);
        myPanel.setVisible(true);

        myPanel.setSize(300, 165);

        myFrame.setVisible(true);
    }
}
