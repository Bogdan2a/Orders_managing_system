package businessLayer.Validators;

import model.Order;

/**
 * The QuantityValidator class is responsible for validating the quantity of an Order.
 */
public class QuantityValidator implements Validator<Order> {
    private static final int MIN_QUANTITY = 0;
    private static final int MAX_QUANTITY = 100;

    /**
     * Validates the quantity of an Order.
     *
     * @param order the Order object to validate
     * @throws IllegalArgumentException if the quantity is not within the valid range
     */
    public void validate(Order order) {
        if (order.getQuantity() < MIN_QUANTITY || order.getQuantity() > MAX_QUANTITY) {
            throw new IllegalArgumentException("The quantity capacity is not respected!");
        }
    }
}
