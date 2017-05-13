
/**
 * An Observerable interface for the Observer design pattern
 * @author Samuel Pell
 *
 */
public interface Observable {
    public void registerObserver(Observer observer);

    public void notifyObservers();
}
