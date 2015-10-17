
package hackathon.nestaway.com.nestawayhackathon;

/**
 * Value Object Base Class
 *
 * @author Chitransh.Srivastava
 */
public abstract class ValueObject extends SimpleObservable<ValueObject> {


    protected Boolean successful;

    protected String errorCode;

    protected String errorMessage;


    public Boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(Boolean successful) {
        this.successful = successful;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }


}
