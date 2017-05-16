import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionListener;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;

/**
 * Inventory panel to display items in players inventory
 * @author Samuel Pell
 */
@SuppressWarnings("serial")
public class InventoryPanel extends JPanel {
    /**
     * GUI component items are listed in.
     */
    private JList<Item> itemList;
    /**
     * List of items {@link itemList} displays.
     */
    private DefaultListModel<Item> listModel;
    
    
    /**
     * Create the panel.
     */
    public InventoryPanel() {
        setLayout(null);
        listModel = new DefaultListModel<Item>();
        
        itemList = new JList<Item>(listModel);
        JScrollPane pane = new JScrollPane(itemList);
        pane.setBounds(0, 0, 189, 333);
        add(pane);
    }
    
    /**
     * Clear the inventory and display the list of items passed into it.
     * @param itemList List of item objects player has in their inventory.
     */
    public void displayInventory(Item[] itemList){
        listModel.clear();
        for(int i = 0; i < itemList.length; i++){
            listModel.add(i, itemList[i]); 
        }
    }
    
    public void addEventListener(ListSelectionListener newListener){
        itemList.addListSelectionListener(newListener);
    }
    
    /**
     * Gets the currently selected item.
     * @return currently selected item.
     */
    public Item getSelectedItem(){
        return itemList.getSelectedValue();
    }
    
    /**
     * Class to test functionality and development.
     * @param args Arguments passed in from command line.
     */
    public static void main(String[] args){
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(Exception e){} //ignore all exceptions
        
        JFrame myFrame = new JFrame();
        
        myFrame.setBounds(0, 0, 785, 423);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.getContentPane().setLayout(null);
        
        InventoryPanel myPanel = new InventoryPanel();
        myFrame.getContentPane().add(myPanel);
        myPanel.setVisible(true);
        
        myPanel.setSize(770, 383);
        myFrame.setVisible(true);
        
        myPanel.displayInventory(new Item[]{((Item) new Toy("Stick", "Brown", 5, 3))});
    }
}
