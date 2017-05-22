package virtualpets;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
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
        setImage(fileName);
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
        setImage(fileName);
    }

    /**
     * Sets the image for the based on the file name passed in.
     * Contains the logic to detect that the program is running in either
     * eclipse or in a jarred form. Then performs the correct actions to load
     * the image.
     * @param fileName Name of the image file to be loaded.
     */
    private void setImage(String fileName) {
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
        lblImageLabel.setIcon(icon);
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
