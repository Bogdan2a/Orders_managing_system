package businessLayer.Validators;

import java.util.regex.Pattern;

import model.Client;

/**
 * The NameValidator class is responsible for validating the name of a Client.
 */
public class NameValidator implements Validator<Client> {
    private static final String NAME_PATTERN = "^[A-Za-z\\s]+$";

    /**
     * Validates the name of a Client.
     *
     * @param client the Client object to validate
     * @throws IllegalArgumentException if the name is not valid
     */
    public void validate(Client client) {
        Pattern pattern = Pattern.compile(NAME_PATTERN);
        if (!pattern.matcher(client.getName()).matches()) {
            throw new IllegalArgumentException("Name is not a valid name!");
        }
    }
}
