package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The Controller class handles the interactions between the user interface components and the application logic.
 *
 * It listens to events triggered by the menu buttons and creates the corresponding views and controllers.
 */
public class Controller {

    private Menu menu;

    /**
     * Constructs a new Controller object with the specified menu.
     *
     * @param menu The menu to associate with the controller.
     */
    public Controller(Menu menu) {
        this.menu = menu;
        menu.addClientsButtonListener(new Clients());
        menu.addOrdersButtonListener(new Orders());
        menu.addProductsButtonListener(new Products());
    }

    /**
     * ActionListener implementation for handling the Clients button action.
     * Creates a new ClientView and its corresponding ControllerClient.
     */
    class Clients implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ClientView clientView = new ClientView();
            clientView.setVisible(true);
            ControllerClient controllerClient = new ControllerClient(clientView);
        }
    }

    /**
     * ActionListener implementation for handling the Products button action.
     * Creates a new ProductView and its corresponding ControllerProduct.
     */
    class Products implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ProductView productView = new ProductView();
            productView.setVisible(true);
            ControllerProduct controllerProduct = new ControllerProduct(productView);
        }
    }

    /**
     * ActionListener implementation for handling the Orders button action.
     * Creates a new OrderView and its corresponding ControllerOrder.
     */
    class Orders implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            OrderView orderView = new OrderView();
            orderView.setVisible(true);
            ControllerOrder controllerOrder = new ControllerOrder(orderView);
        }
    }
}