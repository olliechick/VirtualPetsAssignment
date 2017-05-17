package virtualpets;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

/**
 * Panel to allow the player to put their pet to sleep.
 * @author Samuel Pell
 * @author Ollie Chick
 */
@SuppressWarnings("serial")
public class SleepPanel extends JPanel implements Observable{

    /**
     * List of objects currently observing this element.
     */
    private ArrayList<Observer> observers = new ArrayList<>();

    //TODO: Add functionality
    /**
     * Create the panel.
     */
    public SleepPanel() {
        setLayout(null);

        JLabel lblNewLabel = new JLabel("ImageLabel");
        lblNewLabel.setBounds(362, 184, 46, 14);
        add(lblNewLabel);

        JButton btnSleep = new JButton("Sleep");
        btnSleep.setBounds(340, 349, 89, 23);
        btnSleep.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                notifyObservers();
            }
        });
        add(btnSleep);

    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer: observers) {
            observer.getValues("sleep", new String[0]);
        }
    }
}
