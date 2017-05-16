import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.JRadioButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import java.util.ArrayList;


/**
 * A JPanel to create setup dialogue.
 * @author Samuel Pell
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
    public final int width = 300;
    /**
     * Height of the object on screen.
     */
    public final int height = 165;

    /**
     * Create the panel.
     */
    public SetupPanel() {
        setLayout(null);

        ButtonGroup radioButtonGroup = new ButtonGroup();

        JLabel lblNumDays = new JLabel("Number of days to play for:");
        lblNumDays.setBounds(25, 11, 158, 14);
        add(lblNumDays);

        JSpinner numberOfDaysSpinner = new JSpinner();
        numberOfDaysSpinner.setModel(new SpinnerNumberModel(1, 1, 365, 1));
        numberOfDaysSpinner.setBounds(187, 11, 39, 20);
        add(numberOfDaysSpinner);

        JLabel lblNumberOfPlayers = new JLabel("Number of Players:");
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
