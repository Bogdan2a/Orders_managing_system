package model;

/**
 * The Product class represents a product in the system.
 *
 * It contains information about the product, such as the product ID, name, price, and stock quantity.
 */
public class Product {
    private int id;
    private String name;
    private int price;
    private int stock;

    /**
     * Constructs a new Product object with the specified attributes.
     *
     * @param id    The ID of the product.
     * @param name  The name of the product.
     * @param price The price of the product.
     * @param stock The stock quantity of the product.
     */
    public Product(int id, String name, int price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    /**
     * Default constructor for the Product class.
     */
    public Product() {
    }

    /**
     * Retrieves the ID of the product.
     *
     * @return The ID of the product.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the product.
     *
     * @param id The ID of the product.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the product.
     *
     * @return The name of the product.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the product.
     *
     * @param name The name of the product.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the price of the product.
     *
     * @return The price of the product.
     */
    public int getPrice() {
        return price;
    }

    /**
     * Sets the price of the product.
     *
     * @param price The price of the product.
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Retrieves the stock quantity of the product.
     *
     * @return The stock quantity of the product.
     */
    public int getStock() {
        return stock;
    }

    /**
     * Sets the stock quantity of the product.
     *
     * @param stock The stock quantity of the product.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }
}