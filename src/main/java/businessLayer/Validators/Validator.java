package businessLayer.Validators;

/**
 * The Validator interface defines a contract for validating objects.
 *
 * @param <T> the type of object to validate
 *
 */
public interface Validator<T> {

    public void validate(T t);
}
