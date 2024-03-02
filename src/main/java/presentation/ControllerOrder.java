package presentation;

import businessLayer.BillBLL;
import businessLayer.ClientBLL;
import businessLayer.OrderBLL;
import businessLayer.ProductBLL;
import model.Bill;
import model.Client;
import model.Order;
import model.Product;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * The ControllerOrder class handles the interactions between the OrderView and the business layer classes.
 * <p>
 * It listens to events triggered by the buttons in the OrderView and performs corresponding actions.
 */
public class ControllerOrder {
    private OrderView orderView;

    /**
     * Constructs a new ControllerOrder object with the specified OrderView.
     *
     * @param orderView The order view to associate with the controller.
     */
    public ControllerOrder(OrderView orderView) {
        this.orderView = orderView;
        orderView.addAddOrderButtonListener(new AddOrder());
        orderView.setComboBoxes();
        orderView.setTable1();
        orderView.setTable2();
        orderView.setTable3();
    }

    /**
     * ActionListener implementation for handling the Add Order button action.
     *
     * Creates a new order based on the user input and adds it to the database through the OrderBLL.
     */
    class AddOrder implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int clientID = Integer.parseInt(orderView.getOrderClient());
            int productID = Integer.parseInt(orderView.getOrderProduct());
            int quantity = Integer.parseInt(orderView.getAddedOrderQuantity());
            OrderBLL order1 = new OrderBLL();
            BillBLL bill1 = new BillBLL();
            ProductBLL productaux = new ProductBLL();
            try {
                if (productaux.findProductById(productID).getStock() < quantity) {
                    orderView.showMessage("Not enough stock to place order!");
                } else {
                    int finalPrice = 0;
                    try {
                        finalPrice = quantity * (productaux.findProductById(productID).getPrice());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                    Order aux = new Order(order1.findMaxOrderID() + 1, clientID, productID, quantity, finalPrice);
                    order1.insertOrder(aux);
                    // add bill
                    Bill auxB = new Bill(bill1.findMaxBillID() + 1, aux.getId(), LocalDateTime.now(), aux.getPrice());
                    bill1.insertBill(auxB);

                    //
                    System.out.println("Added order with id = " + aux.getId());
                    ProductBLL orderedProduct = new ProductBLL();
                    Product auxiliar = new Product(productaux.findProductById(productID).getId(), productaux.findProductById(productID).getName(), productaux.findProductById(productID).getPrice(), productaux.findProductById(productID).getStock() - quantity);
                    System.out.println(productaux.findProductById(productID).getPrice());
                    //   ProductBLL orderedProduct= new ProductBLL(productaux.findProductById(productID).getId(),productaux.findProductById(productID).getName(),productaux.findProductById(productID).getPrice(),productaux.findProductById(productID).getPrice());
                    orderedProduct.updateProduct(auxiliar);
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            orderView.setTable1();
            orderView.setTable2();
            orderView.setTable3();
        }
    }
}
