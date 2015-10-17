
package hackathon.nestaway.com.nestawayhackathon;

import java.util.ArrayList;

/**
 * This class implements basic addition/removal/notification of observers. This
 * class to be implemented by all models
 * 
 * @author Chitransh.Srivastava
 * @param <T>
 */
public class SimpleObservable<T> {

    private final ArrayList<OnChangeListener<T>> listeners = new ArrayList<OnChangeListener<T>>();

    public void addListener(OnChangeListener<T> listener) {
        synchronized (listeners) {
            listeners.add(listener);
        }
    }

    public void removeListener(OnChangeListener<T> listener) {
        synchronized (listeners) {
            listeners.remove(listener);
        }
    }

    protected void notifyObservers(final T model) {
        synchronized (listeners) {
            for (OnChangeListener<T> listener : listeners) {
                listener.onChange(model);
            }
        }
    }

}
