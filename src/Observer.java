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
	public void getValues(String identifier, String[] values);
}
