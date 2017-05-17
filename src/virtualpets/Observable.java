package virtualpets;
/**
 * An Observerable interface for the Observer design pattern.
 * @author Samuel Pell
 *
 */
public interface Observable {
    /**
     * Adds an observer to the object.
     * @param observer Observer to add to the object.
     */
    public void registerObserver(Observer observer);

    /**
     * Notifies observers of a change in object.
     */
    public void notifyObservers();
}
