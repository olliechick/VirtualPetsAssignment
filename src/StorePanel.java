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
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;

/**
 * Store tab to allow user to buy items.
 * @author Samuel Pell
 *
 */
@SuppressWarnings("serial")
public class StorePanel extends JPanel {
    
    private JList<Item> storeInventory;
    JLabel lblName;
    JLabel lblDescription;
    JLabel lblPriceField;
    
    /**
     * Create the panel.
     */
    public StorePanel(HashMap<String, Toy> toyPrototypes, 
                      HashMap<String, Food> foodPrototypes) {
        setLayout(null);
        
        ArrayList<Item> storeItems = generateStoreInventory(toyPrototypes, foodPrototypes);
        Item[] storeItemArray = storeItems.toArray(new Item[0]);
        
        storeInventory = new JList<Item>(storeItemArray); //create JList with store items at core
        storeInventory.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //Set it so that one item can be selected at a time
        storeInventory.addListSelectionListener(new ListSelectionListener(){
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
        
        JSeparator separator = new JSeparator();
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
        
        JSeparator separator_1 = new JSeparator();
        separator_1.setForeground(Color.BLACK);
        separator_1.setOrientation(SwingConstants.VERTICAL);
        separator_1.setBounds(523, 39, 14, 333);
        add(separator_1);
        
        JButton btnBuyItem = new JButton("Buy Item");
        btnBuyItem.setBounds(235, 349, 89, 23);
        add(btnBuyItem);
        
        storeInventory.setSelectedIndex(0);
        
        
        
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
                                                   HashMap<String, Food> foodPrototypes){
        ArrayList<Item> itemList = new ArrayList<Item>();
        for(String key: toyPrototypes.keySet().toArray(new String[0])){
            itemList.add((Item) toyPrototypes.get(key));
        }
        for(String key: foodPrototypes.keySet().toArray(new String[0])){
            itemList.add((Item) foodPrototypes.get(key));
        }
        return itemList;
    }
    
    public static void main(String[] args){
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(Exception e){} //ignore all exceptions
        
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
