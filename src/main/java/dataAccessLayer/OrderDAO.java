package dataAccessLayer;

import model.Order;

/**
 * The OrderDAO class is responsible for accessing and manipulating the Order data in the database.
 * <p>
 * It extends the AbstractDAO class and specializes it for the Order model.
 */
public class OrderDAO extends AbstractDAO<Order> {

    /**
     * Retrieves the ID of the given Order object.
     *
     * @param order The Order object.
     * @return The ID of the Order.
     */
    @Override
    public int getId(Order order) {
        return order.getId();
    }
}
