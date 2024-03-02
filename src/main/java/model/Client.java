package model;

/**
 * The Client class represents a client object that contains information about a specific client in the system.
 * <p>
 * It includes details such as the client ID and name.
 */
public class Client {
    private int id;
    private String name;

    /**
     * Constructs a new Client object with the specified ID and name.
     *
     * @param id   The ID of the client.
     * @param name The name of the client.
     */
    public Client(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Default constructor for the Client class.
     */
    public Client() {
    }

    /**
     * Retrieves the ID of the client.
     *
     * @return The ID of the client.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the client.
     *
     * @param id The ID of the client.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the client.
     *
     * @return The name of the client.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the client.
     *
     * @param name The name of the client.
     */
    public void setName(String name) {
        this.name = name;
    }
}
