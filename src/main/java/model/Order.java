package model;

import java.time.LocalDateTime;

/**
 * The Order class represents an order in the system.
 * <p>
 * It contains information about the order, such as the order ID, client ID, product ID, quantity, and total price.
 */
public class Order {
    private int id;
    private int clientid;
    private int productid;
    private int quantity;
    private int total_price;

    /**
     * Constructs a new Order object with the specified attributes.
     *
     * @param id          The ID of the order.
     * @param clientid    The ID of the client associated with the order.
     * @param productid   The ID of the product in the order.
     * @param quantity    The quantity of the product in the order.
     * @param total_price The total price of the order.
     */
    public Order(int id, int clientid, int productid, int quantity, int total_price) {
        this.id = id;
        this.clientid = clientid;
        this.productid = productid;
        this.quantity = quantity;
        this.total_price = total_price;
    }

    /**
     * Default constructor for the Order class.
     */
    public Order() {
    }

    /**
     * Retrieves the ID of the order.
     *
     * @return The ID of the order.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the order.
     *
     * @param id The ID of the order.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the client ID associated with the order.
     *
     * @return The client ID.
     */
    public int getClientID() {
        return clientid;
    }

    /**
     * Sets the client ID associated with the order.
     *
     * @param clientid The client ID.
     */
    public void setClientID(int clientid) {
        this.clientid = clientid;
    }

    /**
     * Retrieves the product ID in the order.
     *
     * @return The product ID.
     */
    public int getProductID() {
        return productid;
    }

    /**
     * Sets the product ID in the order.
     *
     * @param productid The product ID.
     */
    public void setProductID(int productid) {
        this.productid = productid;
    }

    /**
     * Retrieves the quantity of the product in the order.
     *
     * @return The quantity.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the product in the order.
     *
     * @param quantity The quantity.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Retrieves the total price of the order.
     *
     * @return The total price.
     */
    public int getPrice() {
        return total_price;
    }

    /**
     * Sets the total price of the order.
     *
     * @param price The total price.
     */
    public void setPrice(int price) {
        this.total_price = price;
    }
}
