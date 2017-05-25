package virtualpets;
/**
 * Observer class for Observer pattern.
 * @author Samuel Pell
 */
public interface Observer {
    /**
     * Method to get values from the object being observed.
     * @param identifier String to tell the object what the information is.
     * @param values Values being passed in.
     */
    void getValues(String identifier, String[] values);

    /**
     * Method to get values from the object being observed.
     * @param identifier String to tell the object what the information is.
     * @param selected Toy selected.
     */
    void getValues(String identifier, Toy selected);
}
