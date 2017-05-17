package virtualpets;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

/**
 * Panel to allow the player to put their pet to sleep.
 * @author Samuel Pell
 */
@SuppressWarnings("serial")
public class SleepPanel extends JPanel {
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
        add(btnSleep);

    }
}
