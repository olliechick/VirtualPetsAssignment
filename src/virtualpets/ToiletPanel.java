package virtualpets;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Panel to let the player take their pet to the toilet.
 * @author Samuel Pell
 */
@SuppressWarnings("serial")
public class ToiletPanel extends JPanel {
    //TODO: Add functionality
    /**
     * Create the panel.
     */
    public ToiletPanel() {
        setLayout(null);

        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setBounds(362, 184, 46, 14);
        add(lblNewLabel);

        JButton btnGoToilet = new JButton("Go Toilet");
        btnGoToilet.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            }
        });
        btnGoToilet.setBounds(340, 349, 89, 23);
        add(btnGoToilet);

    }
}
