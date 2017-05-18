package virtualpets;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 * Store tab to allow user to buy items.
 * @author Samuel Pell
 * @author Ollie Chick
 *
 */
@SuppressWarnings("serial")
public class StorePanel extends JPanel implements Observable {
    /**
     * Component that displays store inventory.
     */
    private JTable storeInventory;
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
     * Method to transpose a matrix of items.
     * @param matrix Matrix to transpose
     * @return A transposed matrix.
     */
    public static Item[][] transposeMatrix(Item[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        Item[][] trasposedMatrix = new Item[n][m];

        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                trasposedMatrix[x][y] = matrix[y][x];
            }
        }

        return trasposedMatrix;
    }

    /**
     * Create the panel.
     * @param toyPrototypes Hashmap of toy prototypes.
     * @param foodPrototypes Hashmap of food prototypes.
     */
    public StorePanel(HashMap<String, Toy> toyPrototypes,
                      HashMap<String, Food> foodPrototypes) {
        setLayout(null);

        ArrayList<Item> toyItems = generateToyInventory(toyPrototypes);
        Item[] toyItemArray = toyItems.toArray(new Item[0]);
        ArrayList<Item> foodItems = generateFoodInventory(foodPrototypes);
        Item[] foodItemArray = foodItems.toArray(new Item[0]);
        Item[][] storeArray = {toyItemArray, foodItemArray};
        //Transposing storeArray so there are two columns, toy and food
        storeArray = transposeMatrix(storeArray);
        String[] typesOfItem = {"Toys", "Food"};

        storeInventory = new JTable(storeArray, typesOfItem){
            public boolean isCellEditable(int row, int column) {
                    return false;
            };
        }; //create JTable with store items at core
        storeInventory.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //Set it so that one item can be selected at a time
        storeInventory.setCellSelectionEnabled(true);
        storeInventory.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            /**
             * When selection changes display new items stats.
             */
            public void valueChanged(ListSelectionEvent arg0) {
            	int row = storeInventory.getSelectedRow();
            	int col = storeInventory.getSelectedColumn();
            	Item selected = (Item) storeInventory.getValueAt(row, col);
                showItemStats(selected);
			}
        });
        storeInventory.setBounds(22, 39, 250, 333);
        add(storeInventory);


        lblName = new JLabel("");
        lblName.setBounds(295, 40, 247, 14);
        add(lblName);

        lblDescription = new JLabel("");
        lblDescription.setBounds(295, 65, 247, 14);
        add(lblDescription);

        lblPriceField = new JLabel("");
        lblPriceField.setBounds(295, 90, 70, 14);
        add(lblPriceField);

        JLabel lblStore = new JLabel("Store");
        lblStore.setBounds(22, 14, 46, 14);
        add(lblStore);

        JLabel lblInventory = new JLabel("Inventory");
        lblInventory.setBounds(550, 14, 70, 14);
        add(lblInventory);

        JButton btnBuyItem = new JButton("Buy Item");
        btnBuyItem.setBounds(295, 349, 89, 23);
        btnBuyItem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                int row = storeInventory.getSelectedRow();
                int col = storeInventory.getSelectedColumn();
                selectedItem = (Item) storeInventory.getValueAt(row, col) ;
                notifyObservers();
            }
        });
        add(btnBuyItem);

        playerInventory = new InventoryPanel();
        playerInventory.setBounds(550, 39, 189, 333);
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
            itemList.add(toyItem);
        }
        for (Food foodItem: foodStock) {
            itemList.add(foodItem);
        }
        Item[] itemArray = itemList.toArray(new Item[0]);
        playerInventory.displayInventory(itemArray);
    }

    /**
     * Display the selected item's stats to the player.
     * @param selected Item currently selected in store inventory
     */
    private void showItemStats(Item selected) {
        lblName.setText(selected.getName());
        //Format description string
        String description = selected.getDescription() + ".";
        Character firstChar = description.charAt(0);
        firstChar = Character.toUpperCase(firstChar);
        description = firstChar + description.substring(1);
        lblDescription.setText(description);
        Integer price = selected.getPrice();
        lblPriceField.setText("Price: $" + price.toString());
    }

    /**
     * Generates food prototypes for display.
     * @param foodPrototypes Hashmap of food prototypes.
     * @return food prototypes turned into a list of items.
     */
    private ArrayList<Item> generateFoodInventory(HashMap<String, Food> foodPrototypes) {
        ArrayList<Item> itemList = new ArrayList<Item>();
        for (String key: foodPrototypes.keySet().toArray(new String[0])) {
            itemList.add(foodPrototypes.get(key));
        }
        return itemList;
    }

    /**
     * Generates toy prototypes for display.
     * @param toyPrototypes Hashmap of toy prototypes.
     * @return toy prototypes turned into a list of items.
     */
    private ArrayList<Item> generateToyInventory(HashMap<String, Toy> toyPrototypes) {
        ArrayList<Item> itemList = new ArrayList<Item>();
        for (String key: toyPrototypes.keySet().toArray(new String[0])) {
            itemList.add(toyPrototypes.get(key));
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

        /*
         * TESTS
         */

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

        /*
         * END OF TESTS
         */
    }
}
