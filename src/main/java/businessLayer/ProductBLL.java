package businessLayer;

import businessLayer.Validators.PriceValidator;
import businessLayer.Validators.StockValidator;
import businessLayer.Validators.Validator;
import dataAccessLayer.ProductDAO;
import model.Product;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * The ProductBLL class represents the business logic for handling Product operations.
 */
public class ProductBLL {

    private List<Validator<Product>> validators;
    private ProductDAO productDAO;

    /**
     * Constructs a new ProductBLL object.
     */
    public ProductBLL() {
        validators = new ArrayList<>();
        validators.add(new PriceValidator());
        validators.add(new StockValidator());

        productDAO = new ProductDAO();
    }

    /**
     * Finds a product by ID.
     *
     * @param id the ID of the product to find
     * @return the found product
     * @throws SQLException           if an SQL exception occurs
     * @throws NoSuchElementException if the product was not found
     */
    public Product findProductById(int id) throws SQLException {
        ProductDAO product = new ProductDAO();
        Product st;
        st = product.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The product with id = " + id + " was not found!");
        }
        return st;
    }

    /**
     * Inserts a new product.
     *
     * @param newProduct the product to insert
     * @return the inserted product
     * @throws NoSuchElementException if the product was not created
     */
    public Product insertProduct(Product newProduct) {
        ProductDAO product = new ProductDAO();
        Product st = null;
        int ok = 0;
        for (Validator<Product> v : validators) {
            v.validate(newProduct);
            if (ok == 0) {
                st = product.insert(newProduct);
                ok++;
                if (st == null) {
                    throw new NoSuchElementException("The product with id = was not created!");
                }
            }
        }
        return st;
    }

    /**
     * Updates a product.
     *
     * @param newProduct the product to update
     * @return the updated product
     * @throws NoSuchElementException if the product was not updated
     */
    public Product updateProduct(Product newProduct) {
        ProductDAO product = new ProductDAO();
        Product st = null;
        for (Validator<Product> v : validators) {
            v.validate(newProduct);
            st = product.update(newProduct);
            if (st == null) {
                throw new NoSuchElementException("The product with id = was not updated!");
            }
        }
        return st;
    }

    /**
     * Deletes a product.
     *
     * @param newProduct the product to delete
     * @return the deleted product
     * @throws NoSuchElementException if the product was not deleted
     */
    public Product deleteProduct(Product newProduct) {
        ProductDAO product = new ProductDAO();
        Product st;
        st = product.delete(newProduct);
        if (st == null) {
            throw new NoSuchElementException("The product with id = was not deleted!");
        }
        return st;
    }

    /**
     * Finds the maximum product ID.
     *
     * @return the maximum product ID
     */
    public int findMaxProductID() {
        ProductDAO product = new ProductDAO();
        int aux;
        aux = product.findMaxId();
        return aux;
    }

    /**
     * Prints the product table to a JTable.
     *
     * @param table1 the JTable to display the product table
     */
    public void printProductTable(JTable table1) {
        ProductDAO product = new ProductDAO();
        product.setTable("assignment3.product", table1);
    }
}
