package virtualpets;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
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
    /**
     * Label to display pet image.
     */
    JLabel lblImageLabel;

    /**
     * Create the panel.
     */
    public SleepPanel() {
        setLayout(null);

        lblImageLabel = new JLabel("");
        lblImageLabel.setBounds(235, 0, 300, 338);
        lblImageLabel.setHorizontalTextPosition(JLabel.CENTER);
        lblImageLabel.setVerticalTextPosition(JLabel.BOTTOM);
        add(lblImageLabel);

        JButton btnSleep = new JButton("Sleep");
        btnSleep.setBounds(340, 349, 89, 23);
        btnSleep.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                notifyObservers();
            }
        });
        add(btnSleep);

    }

    /**
     * Set the image associated with the pet.
     * @param pet Pet the image is associated with.
     */
    public void setAwakeImage(Pet pet) {
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
        lblImageLabel.setIcon(new ImageIcon(fileName));
    }

    /**
     * Set the sleeping image associated with the pet.
     * @param pet Pet the image is associated with.
     */
    public void setAsleepImage(Pet pet) {
        String fileName = "img/";
        switch (pet.getSpecies()) {
            case "alpaca":
                fileName += "AlpacaSleeping.png";
                break;
            case "cat":
                fileName += "CatSleeping.png";
                break;
            case "dog":
                fileName += "DogSleeping.png";
                break;
            case "goat":
                fileName += "GoatSleeping.png";
                break;
            case "horse":
                fileName += "HorseSleeping.png";
                break;
            case "polar bear":
                fileName += "PolarBearSleeping.png";
                break;
        }
        lblImageLabel.setIcon(new ImageIcon(fileName));
    }


    /**
     * {@inheritDoc}
     */
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * Notifies observers with identifier "sleep".
     */
    public void notifyObservers() {
        for (Observer observer: observers) {
            observer.getValues("sleep", new String[0]);
        }
    }
}
