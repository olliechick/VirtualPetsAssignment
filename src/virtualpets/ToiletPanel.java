package virtualpets;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Image;
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
    /**
     * Label to display image.
     */
    private JLabel lblImage;

    /**
     * Create the panel.
     */
    public ToiletPanel() {
        setLayout(null);

        lblImage = new JLabel("");
        lblImage.setBounds(235, 0, 300, 338);
        lblImage.setHorizontalTextPosition(JLabel.RIGHT);
        lblImage.setVerticalTextPosition(JLabel.BOTTOM);
        add(lblImage);

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
     * Set the default image associated with the pet.
     * @param pet Pet the image is associated with.
     */
    public void setNormalImage(Pet pet) {
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
     * Set the toileting image associated with the pet.
     * @param pet Pet the image is associated with.
     */
    public void setToiletingImage(Pet pet) {
        String fileName = "img/";
        switch (pet.getSpecies()) {
            case "alpaca":
                fileName += "AlpacaToilet.png";
                break;
            case "cat":
                fileName += "CatToilet.png";
                break;
            case "dog":
                fileName += "DogToilet.png";
                break;
            case "goat":
                fileName += "GoatToilet.png";
                break;
            case "horse":
                fileName += "HorseToilet.png";
                break;
            case "polar bear":
                fileName += "PolarBearToilet.png";
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
        lblImage.setIcon(icon);
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
