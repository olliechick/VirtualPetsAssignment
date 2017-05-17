package virtualpets;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JScrollPane;

/**
 * Store tab to allow user to buy items.
 * @author Samuel Pell
 *
 */
@SuppressWarnings("serial")
public class StorePanel extends JPanel implements Observable {
    /**
     * Component that displays store inventory.
     */
    private JList<Item> storeInventory;
    /**
     * Label which displays object's name.
     */
    private JLabel lblName;
    /**
     * Label which displays object's description.
     */
    private JLabel lblDescription;
    /**
     * Label which displays the object's price.
     */
    private JLabel lblPriceField;
    /**
     * Panel which displays the players inventory.
     */
    private InventoryPanel playerInventory;
    /**
     * Currently selected item.
     */
    private Item selectedItem;
    /**
     * List of objects currently observing this element.
     */
    private ArrayList<Observer> observers = new ArrayList<Observer>();

    /**
     * Create the panel.
     * @param toyPrototypes Hashmap of toy prototypes.
     * @param foodPrototypes Hashmap of food prototypes.
     */
    public StorePanel(HashMap<String, Toy> toyPrototypes,
                      HashMap<String, Food> foodPrototypes) {
        setLayout(null);

        ArrayList<Item> storeItems = generateStoreInventory(toyPrototypes, foodPrototypes);
        Item[] storeItemArray = storeItems.toArray(new Item[0]);

        storeInventory = new JList<Item>(storeItemArray); //create JList with store items at core
        storeInventory.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //Set it so that one item can be selected at a time
        storeInventory.addListSelectionListener(new ListSelectionListener() {
            /**
             * When selection changes display new items stats.
             */
            public void valueChanged(ListSelectionEvent arg0) {
               Item selected = storeInventory.getSelectedValue();
               showItemStats(selected);
            }
        });
        JScrollPane scrollBar = new JScrollPane(storeInventory); //Add a scroll bar to the JList so that the user can scroll
        scrollBar.setBounds(22, 39, 179, 333); //put scrollbar in the right place.
        add(scrollBar);


        lblName = new JLabel("name");
        lblName.setBounds(235, 40, 247, 14);
        add(lblName);

        JSeparator separator = new JSeparator(); //break up the screen to make it look better.
        separator.setForeground(Color.BLACK);
        separator.setOrientation(SwingConstants.VERTICAL);
        separator.setBounds(211, 39, 14, 333);
        add(separator);

        lblDescription = new JLabel("description");
        lblDescription.setBounds(235, 65, 247, 14);
        add(lblDescription);

        JLabel lblPrice = new JLabel("Price:");
        lblPrice.setBounds(235, 90, 46, 14);
        add(lblPrice);


        lblPriceField = new JLabel("priceLabel");
        lblPriceField.setBounds(303, 90, 70, 14);
        add(lblPriceField);

        JLabel lblStore = new JLabel("Store");
        lblStore.setBounds(22, 14, 46, 14);
        add(lblStore);

        JLabel lblInventory = new JLabel("Inventory");
        lblInventory.setBounds(550, 14, 70, 14);
        add(lblInventory);

        JSeparator separator2 = new JSeparator(); //break up the screen to make it look better.
        separator2.setForeground(Color.BLACK);
        separator2.setOrientation(SwingConstants.VERTICAL);
        separator2.setBounds(560, 39, 14, 333);
        add(separator2);

        JButton btnBuyItem = new JButton("Buy Item");
        btnBuyItem.setBounds(235, 349, 89, 23);
        btnBuyItem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                selectedItem = storeInventory.getSelectedValue();
                notifyObservers();
            }
        });
        add(btnBuyItem);

        storeInventory.setSelectedIndex(0);

        playerInventory = new InventoryPanel();
        playerInventory.setBounds(571, 39, 189, 333);
        add(playerInventory);
    }

    /**
     * Update the players inventory.
     * @param currentPlayer Player currently in store.
     */
    public void updatePlayerInventory(Player currentPlayer) {
        ArrayList<Toy> toyList = currentPlayer.getToyList();
        ArrayList<Food> foodStock = currentPlayer.getFoodStock();

        ArrayList<Item> itemList = new ArrayList<Item>();
        for (Toy toyItem: toyList) {
            itemList.add((Item) toyItem);
        }
        for (Food foodItem: foodStock) {
            itemList.add((Item) foodItem);
        }
        Item[] itemArray = itemList.toArray(new Item[0]);
        playerInventory.displayInventory(itemArray);
    }

    /**
     * Display the selected item's stats to the player.
     * @param selected Item current selected in store inventory
     */
    private void showItemStats(Item selected) {
        lblName.setText(selected.getName());
        //Format description string
        String description = selected.getDescription() + ".";
        Character firstChar = description.charAt(0);
        firstChar = Character.toUpperCase(firstChar);
        description = firstChar + description.substring(1);
        lblDescription.setText(description);
        Integer price = (Integer) selected.getPrice();
        lblPriceField.setText("$" + price.toString());
    }

    /**
     * Merges toy prototypes and food prototypes for display.
     * @param toyPrototypes Hashmap of toy prototypes.
     * @param foodPrototypes Hashmap of food prototypes.
     * @return Merged list of items.
     */
    private ArrayList<Item> generateStoreInventory(HashMap<String, Toy> toyPrototypes,
                                                   HashMap<String, Food> foodPrototypes) {
        ArrayList<Item> itemList = new ArrayList<Item>();
        for (String key: toyPrototypes.keySet().toArray(new String[0])) {
            itemList.add((Item) toyPrototypes.get(key));
        }
        for (String key: foodPrototypes.keySet().toArray(new String[0])) {
            itemList.add((Item) foodPrototypes.get(key));
        }
        return itemList;
    }

    /**
     * {@inheritDoc}
     */
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * {@inheritDoc}
     */
    public void notifyObservers() {
        for (Observer observer: observers) {
            String[] itemPurchased = new String[]{selectedItem.getName()};
            observer.getValues("buy item", itemPurchased);
        }
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

        JFrame myFrame = new JFrame();

        myFrame.setBounds(0, 0, 785, 423);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.getContentPane().setLayout(null);

        GameEnvironment testGame = new GameEnvironment();
        testGame.generateFoodPrototypes();
        testGame.generateToyPrototypes();
        StorePanel myPanel = new StorePanel(
                testGame.getToyPrototypes(),
                testGame.getFoodPrototypes());


        myFrame.getContentPane().add(myPanel);
        myPanel.setVisible(true);

        myPanel.setSize(770, 383);
        myFrame.setVisible(true);
    }
}
