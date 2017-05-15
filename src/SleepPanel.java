import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

public class SleepPanel extends JPanel {
    //TODO: Add functionality
    /**
     * Create the panel.
     */
    public SleepPanel() {
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("ImageLabel");
        lblNewLabel.setBounds(362, 110, 46, 14);
        add(lblNewLabel);
        
        JButton btnSleep = new JButton("Sleep");
        btnSleep.setBounds(340, 349, 89, 23);
        add(btnSleep);

    }

}
