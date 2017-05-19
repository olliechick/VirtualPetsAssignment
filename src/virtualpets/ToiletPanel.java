package virtualpets;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

/**
 * Panel to let the player take their pet to the toilet.
 * @author Samuel Pell
 */
@SuppressWarnings("serial")
public class ToiletPanel extends JPanel implements Observable {
    /**
     * List of observers currently observing this object.
     */
    private ArrayList<Observer> observers = new ArrayList<Observer>();

    //TODO: start with normal image, switch to toilet image when they click go toilet
    /**
     * Create the panel.
     */
    public ToiletPanel() {
        setLayout(null);

        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setBounds(362, 184, 46, 14);
        add(lblNewLabel);

        JButton btnGoToilet = new JButton("Go toilet");
        btnGoToilet.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                notifyObservers();
            }
        });
        btnGoToilet.setBounds(340, 349, 89, 23);
        add(btnGoToilet);

    }

    /**
     * {@inheritDoc}
     */
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * Notifies observers with identifier "toilet".
     */
    public void notifyObservers() {
        for (Observer o: observers) {
            o.getValues("toilet", new String[0]);
        }
    }
}
