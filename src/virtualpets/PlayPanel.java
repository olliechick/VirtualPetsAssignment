package virtualpets;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;

/**
 * Panel to allow the user to play with their pet.
 * @author Samuel Pell
 */
@SuppressWarnings("serial")
public class PlayPanel extends JPanel {
    /**
     * Element that displays players toys.
     */
    private InventoryPanel itemListPanel;
    /**
     * Label to display name of the item selected.
     */
    private JLabel lblName;
    /**
     * Label to display the description of the item selected.
     */
    private JLabel lblDescription;
    /**
     * Label to show the durability of the toy.
     */
    private JLabel lblDurabilityScore;

    /**
     * Create the panel.
     */
    public PlayPanel() {
        setLayout(null);

        itemListPanel = new InventoryPanel();
        itemListPanel.addEventListener(new ListSelectionListener() {
            /**
             * When selection changes display new items stats.
             */
            public void valueChanged(ListSelectionEvent e) {
                Item selected = itemListPanel.getSelectedItem();
                showItemStats(selected);
            }
        });
        itemListPanel.setBounds(22, 39, 189, 333);
        add(itemListPanel);

        JLabel lblInventory = new JLabel("Inventory");
        lblInventory.setBounds(22, 14, 64, 14);
        add(lblInventory);

        JSeparator separator = new JSeparator();
        separator.setForeground(Color.BLACK);
        separator.setOrientation(SwingConstants.VERTICAL);
        separator.setBounds(221, 39, 14, 333);
        add(separator);

        lblName = new JLabel("");
        lblName.setBounds(235, 52, 189, 14);
        add(lblName);

        lblDescription = new JLabel("");
        lblDescription.setBounds(235, 76, 331, 14);
        add(lblDescription);

        JLabel lblDurability = new JLabel("Durability: ");
        lblDurability.setBounds(235, 101, 58, 14);
        add(lblDurability);

        JButton btnPlayWithToy = new JButton("Play with Toy");
        btnPlayWithToy.setBounds(656, 349, 104, 23);
        add(btnPlayWithToy);

        lblDurabilityScore = new JLabel("");
        lblDurabilityScore.setBounds(291, 101, 189, 14);
        add(lblDurabilityScore);
    }

    /**
     * Shows the player the currently selected item from their inventory.
     * @param selected Item to display information for.
     */
    private void showItemStats(Item selected) {
        if (selected != null) {
            Toy selectedToy = (Toy) selected;
            lblName.setText(selected.getName());
            //Format description string
            String description = selected.getDescription() + ".";
            Character firstChar = description.charAt(0);
            firstChar = Character.toUpperCase(firstChar);
            description = firstChar + description.substring(1);
            lblDescription.setText(description);
            Integer durability = (Integer) selectedToy.getDurability();
            lblDurabilityScore.setText(durability.toString());
        } else {
            lblName.setText("");
            lblDescription.setText("");
            lblDurabilityScore.setText("");
        }
    }

    /**
     * List the toys in the players inventory.
     * @param toyList List of toys the player owns
     */
    public void listPlayerToys(ArrayList<Toy> toyList) {
        Item[] itemList = toyList.toArray(new Item[0]);
        itemListPanel.displayInventory(itemList);
    }

    /**
     * Testing functionality of this panel.
     * @param args Arguments pased in from command line.
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
          //ignore all exceptions
        }

        ArrayList<Toy> toyList = new ArrayList<Toy>();
        for (int i = 0; i < 30; i++) {
            toyList.add(new Toy("Stick", "Brown", 5, 20));
        }
        JFrame myFrame = new JFrame();

        myFrame.setBounds(0, 0, 785, 423);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.getContentPane().setLayout(null);

        PlayPanel myPanel = new PlayPanel();
        myPanel.listPlayerToys(toyList);

        myFrame.getContentPane().add(myPanel);
        myPanel.setVisible(true);

        myPanel.setSize(770, 383);
        myFrame.setVisible(true);
    }
}
