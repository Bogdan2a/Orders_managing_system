package dataAccessLayer;

import model.Client;

/**
 * The ClientDAO class is responsible for accessing and manipulating the Client data in the database.
 * <p>
 * It extends the AbstractDAO class and specializes it for the Client model.
 */
public class ClientDAO extends AbstractDAO<Client> {

    /**
     * Retrieves the ID of the given Client object.
     *
     * @param client The Client object.
     * @return The ID of the Client.
     */
    @Override
    public int getId(Client client) {
        return 0;
    }
}


