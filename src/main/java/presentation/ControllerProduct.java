package presentation;

import businessLayer.ClientBLL;
import businessLayer.ProductBLL;
import model.Client;
import model.Product;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * The ControllerProduct class handles the interactions between the ProductView and the business layer components.
 * It listens to events triggered by buttons in the ProductView and performs the corresponding actions.
 * It allows adding, deleting, and updating products in the system through the ProductBLL class.
 * It also updates the table in the ProductView to reflect the changes made to the product data.
 */
public class ControllerProduct {
    private ProductView productView;

    /**
     * Constructs a ControllerProduct object with the specified ProductView.
     *
     * @param productView The ProductView associated with the controller.
     */
    public ControllerProduct(ProductView productView) {
        this.productView = productView;
        productView.addAddProductButtonListener(new AddProduct());
        productView.addDeleteProductButtonListener(new DeleteProduct());
        productView.addUpdateProductButtonListener(new UpdateProduct());
        productView.setTable1();
    }

    /**
     * ActionListener implementation for adding a product.
     * Retrieves the product details from the ProductView and inserts a new product into the database.
     * The table in the ProductView is updated to reflect the changes.
     */
    class AddProduct implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String productName = productView.getAddedNameProduct();
            int productStock = Integer.parseInt(productView.getAddedStockProduct());
            int productPrice = Integer.parseInt(productView.getAddedPriceProduct());
            ProductBLL product1 = new ProductBLL();
            Product aux = new Product(product1.findMaxProductID() + 1, productName, productPrice, productStock);
            product1.insertProduct(aux);
            System.out.println("Added product with id = " + aux.getId());
            productView.setTable1();
        }
    }

    /**
     * ActionListener implementation for deleting a product.
     * Retrieves the product ID from the ProductView and deletes the corresponding product from the database.
     * The table in the ProductView is updated to reflect the changes.
     */
    class DeleteProduct implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int productID = Integer.parseInt(productView.getDeletedIDProduct());
            ProductBLL product1 = new ProductBLL();
            Product aux = new Product(productID, "", 1, 1);
            product1.deleteProduct(aux);
            System.out.println("Deleted product with id = " + aux.getId());
            productView.setTable1();
        }
    }

    /**
     * ActionListener implementation for updating a product.
     * Retrieves the updated product details from the ProductView and updates the corresponding product in the database.
     * The table in the ProductView is updated to reflect the changes.
     */
    class UpdateProduct implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int productID = Integer.parseInt(productView.getUpdatedIDProduct());
            String productName = productView.getUpdatedNameProduct();
            String productStock = productView.getUpdatedStockProduct();
            String productPrice = productView.getUpdatedPriceProduct();
            int originalPrice;
            int originalStock;
            String originalName;
            ProductBLL product1 = new ProductBLL();
            try {
                originalPrice = product1.findProductById(productID).getPrice();
                originalStock = product1.findProductById(productID).getStock();
                originalName = product1.findProductById(productID).getName();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            if (productName.equals("")) {
                productName = originalName;
            }
            if (productPrice.equals("")) {
                productPrice = String.valueOf(originalPrice);
            }
            if (productStock.equals("")) {
                productStock = String.valueOf(originalStock);
            }
            Product aux = new Product(productID, productName, Integer.parseInt(productPrice), Integer.parseInt(productStock));
            product1.updateProduct(aux);
            System.out.println("Updated product with id = " + aux.getId());
            productView.setTable1();
        }
    }
}