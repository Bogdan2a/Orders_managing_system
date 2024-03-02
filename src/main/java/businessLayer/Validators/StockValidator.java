package businessLayer.Validators;

import model.Product;

/**
 * The StockValidator class is responsible for validating the stock of a Product.
 */
public class StockValidator implements Validator<Product> {
    private static final int MIN_STOCK = 0;
    private static final int MAX_STOCK = 100;

    /**
     * Validates the stock of a Product.
     *
     * @param product the Product object to validate
     * @throws IllegalArgumentException if the stock is not within the valid range
     */
    public void validate(Product product) {
        if (product.getStock() < MIN_STOCK || product.getStock() > MAX_STOCK) {
            throw new IllegalArgumentException("The stock capacity is not respected!");
        }
    }
}
