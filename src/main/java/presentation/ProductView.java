package presentation;

import businessLayer.ClientBLL;
import businessLayer.ProductBLL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 * The ProductView class represents the view for managing products in the application's graphical user interface (GUI).
 * It provides various components such as tables, text fields, and buttons to interact with products.
 * Users can add new products, delete existing products, update product information, and view product details.
 */
public class ProductView extends JFrame {
    private JTable table1;
    private JButton addProductButton;
    private JButton deleteProductButton;
    private JButton updateProductButton;
    private JTextField addNameProduct;
    private JTextField addPriceProduct;
    private JTextField addStockProduct;
    private JTextField deleteIDProduct;
    private JTextField updateIDProduct;
    private JTextField newNameProduct;
    private JTextField newPriceProduct;
    private JTextField newStockProduct;
    private JPanel basePanel;

    /**
     * Constructs a new ProductView object.
     * Sets the initial dimensions of the product view window.
     */
    public ProductView() {
        setDimension(900, 500);
    }

    /**
     * Sets the dimensions of the product view window.
     * Adds the basePanel containing the product view components to the frame.
     * Sets the position of the product view window on the screen.
     *
     * @param width  The width of the product view window in pixels.
     * @param height The height of the product view window in pixels.
     */
    public void setDimension(int width, int height) {
        add(basePanel);
        setBounds(300, 200, width, height);
    }

    /**
     * Sets the data for table1 displaying product information.
     * Populates the table with product records.
     */
    public void setTable1() {
        ProductBLL product1 = new ProductBLL();
        product1.printProductTable(this.table1);
    }

    /**
     * Retrieves the name of the product entered in the addNameProduct text field.
     *
     * @return The name of the product as a string.
     */
    public String getAddedNameProduct() {
        return this.addNameProduct.getText();
    }

    /**
     * Retrieves the price of the product entered in the addPriceProduct text field.
     *
     * @return The price of the product as a string.
     */
    public String getAddedPriceProduct() {
        return this.addPriceProduct.getText();
    }

    /**
     * Retrieves the stock quantity of the product entered in the addStockProduct text field.
     *
     * @return The stock quantity of the product as a string.
     */
    public String getAddedStockProduct() {
        return this.addStockProduct.getText();
    }

    /**
     * Retrieves the ID of the product entered in the deleteIDProduct text field.
     *
     * @return The ID of the product to be deleted as a string.
     */
    public String getDeletedIDProduct() {
        return this.deleteIDProduct.getText();
    }

    /**
     * Retrieves the ID of the product entered in the updateIDProduct text field.
     *
     * @return The ID of the product to be updated as a string.
     */
    public String getUpdatedIDProduct() {
        return this.updateIDProduct.getText();
    }

    /**
     * Retrieves the updated name of the product entered in the newNameProduct text field.
     *
     * @return The updated name of the product as a string.
     */
    public String getUpdatedNameProduct() {
        return this.newNameProduct.getText();
    }

    /**
     * Retrieves the updated price of the product entered in the newPriceProduct text field.
     *
     * @return The updated price of the product as a string.
     */
    public String getUpdatedPriceProduct() {
        return this.newPriceProduct.getText();
    }

    /**
     * Retrieves the updated stock quantity of the product entered in the newStockProduct text field.
     *
     * @return The updated stock quantity of the product as a string.
     */
    public String getUpdatedStockProduct() {
        return this.newStockProduct.getText();
    }

    /**
     * Adds an ActionListener to the addProductButton.
     * The listener will be triggered when the addProductButton is clicked.
     *
     * @param listener The ActionListener to be added to the addProductButton.
     */
    public void addAddProductButtonListener(ActionListener listener) {
        this.addProductButton.addActionListener(listener);
    }

    /**
     * Adds an ActionListener to the deleteProductButton.
     * The listener will be triggered when the deleteProductButton is clicked.
     *
     * @param listener The ActionListener to be added to the deleteProductButton.
     */
    public void addDeleteProductButtonListener(ActionListener listener) {
        this.deleteProductButton.addActionListener(listener);
    }

    /**
     * Adds an ActionListener to the updateProductButton.
     * The listener will be triggered when the updateProductButton is clicked.
     *
     * @param listener The ActionListener to be added to the updateProductButton.
     */
    public void addUpdateProductButtonListener(ActionListener listener) {
        this.updateProductButton.addActionListener(listener);
    }
}
