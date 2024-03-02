package dataAccessLayer;

import model.Product;

/**
 * The ProductDAO class is responsible for accessing and manipulating the Product data in the database.
 * <p>
 * It extends the AbstractDAO class and specializes it for the Product model.
 */
public class ProductDAO extends AbstractDAO<Product> {

    /**
     * Retrieves the ID of the given Product object.
     *
     * @param product The Product object.
     * @return The ID of the Product.
     */
    @Override
    public int getId(Product product) {
        return product.getId();
    }
}
