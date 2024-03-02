package businessLayer;

import businessLayer.Validators.QuantityValidator;
import businessLayer.Validators.Validator;
import dataAccessLayer.OrderDAO;
import model.Order;
import model.Bill;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * The OrderBLL class represents the business logic for handling Order operations.
 */
public class OrderBLL {

    private List<Validator<Order>> validators;
    private OrderDAO orderDAO;

    /**
     * Constructs a new OrderBLL object.
     */
    public OrderBLL() {
        validators = new ArrayList<>();
        validators.add(new QuantityValidator());
        orderDAO = new OrderDAO();
    }

    /**
     * Finds an order by ID.
     *
     * @param id the ID of the order to find
     * @return the found order
     * @throws SQLException           if an SQL exception occurs
     * @throws NoSuchElementException if the order was not found
     */
    public Order findOrderById(int id) throws SQLException {
        OrderDAO order = new OrderDAO();
        Order st;
        st = order.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The order with id = " + id + " was not found!");
        }
        return st;
    }

    /**
     * Inserts a new order.
     *
     * @param newOrder the order to insert
     * @return the inserted order
     * @throws NoSuchElementException if the order was not created
     */
    public Order insertOrder(Order newOrder) {
        OrderDAO order = new OrderDAO();
        Order st = null;
        Bill bill = null;

        for (Validator<Order> v : validators) {
            v.validate(newOrder);
            st = order.insert(newOrder);
            if (st == null) {
                throw new NoSuchElementException("The order with id = was not created!");
            }
        }
        return st;
    }

    /**
     * Prints the order table to a JTable.
     *
     * @param table1 the JTable to display the order table
     */
    public void printOrderTable(JTable table1) {
        OrderDAO order = new OrderDAO();
        order.setTable("assignment3.order", table1);
    }

    /**
     * Adds client and product data to JComboBoxes.
     *
     * @param clients  the JComboBox for clients
     * @param products the JComboBox for products
     */
    public void addToComboBoxes(JComboBox clients, JComboBox products) {
        OrderDAO order = new OrderDAO();
        order.populateComboBoxes(clients, products);
    }

    /**
     * Finds the maximum order ID.
     *
     * @return the maximum order ID
     */
    public int findMaxOrderID() {
        OrderDAO order = new OrderDAO();
        int aux;
        aux = order.findMaxId();
        return aux;
    }
}
