
package hackathon.nestaway.com.nestawayhackathon;

/**
 * Interface to bind contract for all model listeners
 * 
 * @author Chitransh.Srivastava
 */
public interface OnChangeListener<T> {
    void onChange(T model);
}
