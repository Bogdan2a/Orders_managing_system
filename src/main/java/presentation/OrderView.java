package presentation;

import businessLayer.BillBLL;
import businessLayer.ClientBLL;
import businessLayer.OrderBLL;
import businessLayer.ProductBLL;

import javax.swing.*;
import java.awt.event.ActionListener;
/**
 * The OrderView class represents the view for managing orders in the application's graphical user interface (GUI).
 * It provides various components such as tables, combo boxes, and buttons to interact with orders.
 * Users can add new orders, view order details, and perform related operations.
 */
public class OrderView extends JFrame {
    private JTable table1;
    private JButton addOrderButton;
    private JComboBox orderClientcomboBox;
    private JComboBox orderProductcomboBox;
    private JTextField addQuantityOrder;
    private JTable table2;
    private JTable table3;
    private JPanel basePanel;
    /**
     * Constructs a new OrderView object.
     * Sets the initial dimensions of the order view window.
     */
    public OrderView() {
        setDimension(900, 500);
    }
    /**
     * Sets the dimensions of the order view window.
     * Adds the basePanel containing the order view components to the frame.
     * Sets the position of the order view window on the screen.
     *
     * @param w  The width of the order view window in pixels.
     * @param h The height of the order view window in pixels.
     */
    public void setDimension(int w, int h) {
        add(basePanel);
        setBounds(300, 200, w, h);

    }
    /**
     * Sets the options for the client and product combo boxes.
     * Populates the combo boxes with available clients and products.
     */
    public void setComboBoxes(){
        OrderBLL order1= new OrderBLL();
        order1.addToComboBoxes(this.orderClientcomboBox,this.orderProductcomboBox);
    }
    /**
     * Sets the data for table1 displaying order information.
     * Populates the table with order records.
     */
    public void setTable1() {
        OrderBLL order1= new OrderBLL();
        order1.printOrderTable(this.table1);
    }
    /**
     * Sets the data for table2 displaying product information.
     * Populates the table with product records.
     */
    public void setTable2() {
        ProductBLL product1= new ProductBLL();
        product1.printProductTable(this.table2);
    }
    /**
     * Sets the data for table3 displaying bill information.
     * Populates the table with bill records.
     */
    public void setTable3() {
        BillBLL bill1= new BillBLL();
        bill1.printBillTable(this.table3);
    }
    /**
     * Retrieves the selected client from the orderClientComboBox.
     *
     * @return The selected client as a string.
     */
    public String getOrderClient() {
        return this.orderClientcomboBox.getSelectedItem().toString();
    }
    /**
     * Retrieves the selected product from the orderProductComboBox.
     *
     * @return The selected product as a string.
     */
    public String getOrderProduct() {
        return this.orderProductcomboBox.getSelectedItem().toString();
    }
    /**
     * Retrieves the quantity entered the addQuantityOrder text field.
     *
     * @return The entered quantity as a string.
     */
    public String getAddedOrderQuantity() {
        return this.addQuantityOrder.getText();
    }
    /**
     * Displays a message dialog with the provided message.
     *
     * @param message The message to be displayed.
     */
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(OrderView.this, message, "Messsage", JOptionPane.INFORMATION_MESSAGE);
    }
    public void addAddOrderButtonListener(ActionListener listener) {
        this.addOrderButton.addActionListener(listener);
    }
}