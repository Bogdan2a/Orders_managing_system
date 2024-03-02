package presentation;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * The Menu class represents the main menu of the application's graphical user interface (GUI).
 * It provides buttons for navigating to different sections of the application, such as clients, products, and orders.
 * The menu is displayed as a window and allows users to interact with the application by clicking on the respective buttons.
 */
public class Menu extends JFrame {

    private JButton clientsButton;
    private JButton productsButton;
    private JButton ordersButton;
    private JPanel basePanel;

    /**
     * Constructs a new Menu object.
     * Sets the initial dimensions of the menu window.
     */
    public Menu() {
        setDimension(400, 300);
    }

    /**
     * Sets the dimensions of the menu window.
     * Adds the basePanel containing the menu components to the frame.
     * Sets the position of the menu window on the screen.
     * Configures the default close operation of the menu window.
     *
     * @param width  The width of the menu window in pixels.
     * @param height The height of the menu window in pixels.
     */
    public void setDimension(int width, int height) {
        add(basePanel);
        setBounds(300, 200, width, height);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * Adds an ActionListener to the clientsButton.
     * The listener will be triggered when the clientsButton is clicked.
     *
     * @param listener The ActionListener to be added to the clientsButton.
     */
    public void addClientsButtonListener(ActionListener listener) {
        this.clientsButton.addActionListener(listener);
    }

    /**
     * Adds an ActionListener to the productsButton.
     * The listener will be triggered when the productsButton is clicked.
     *
     * @param listener The ActionListener to be added to the productsButton.
     */
    public void addProductsButtonListener(ActionListener listener) {
        this.productsButton.addActionListener(listener);
    }

    /**
     * Adds an ActionListener to the ordersButton.
     * The listener will be triggered when the ordersButton is clicked.
     *
     * @param listener The ActionListener to be added to the ordersButton.
     */
    public void addOrdersButtonListener(ActionListener listener) {
        this.ordersButton.addActionListener(listener);
    }
}
