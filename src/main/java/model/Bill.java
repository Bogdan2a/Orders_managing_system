package model;

import java.time.LocalDateTime;

/**
 * The Bill class represents a bill object that contains information about a specific bill in the system.
 * <p>
 * It includes details such as the bill ID, associated order ID, creation date, and total price.
 */
public final class Bill {
    private final int id;
    private final int orderId;
    private final LocalDateTime creation_Date;
    private final int total_Price;

    /**
     * Constructs a new Bill object with the specified attributes.
     *
     * @param id           The ID of the bill.
     * @param orderId      The ID of the associated order.
     * @param creationDate The creation date of the bill.
     * @param totalPrice   The total price of the bill.
     */
    public Bill(int id, int orderId, LocalDateTime creationDate, int totalPrice) {
        this.id = id;
        this.orderId = orderId;
        this.creation_Date = creationDate;
        this.total_Price = totalPrice;
    }

    /**
     * Retrieves the ID of the bill.
     *
     * @return The ID of the bill.
     */
    public int getId() {
        return id;
    }

    /**
     * Retrieves the ID of the associated order.
     *
     * @return The ID of the associated order.
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * Retrieves the creation date of the bill.
     *
     * @return The creation date of the bill.
     */
    public LocalDateTime getCreationDate() {
        return creation_Date;
    }

    /**
     * Retrieves the total price of the bill.
     *
     * @return The total price of the bill.
     */
    public int getTotalPrice() {
        return total_Price;
    }
}
