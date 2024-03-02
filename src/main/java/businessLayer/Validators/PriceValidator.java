package businessLayer.Validators;

import model.Product;

/**
 * The PriceValidator class is responsible for validating the price of a Product.
 */
public class PriceValidator implements Validator<Product> {
    private static final int MIN_PRICE = 0;
    private static final int MAX_PRICE = 100;

    /**
     * Validates the price of a Product.
     *
     * @param product the Product object to validate
     * @throws IllegalArgumentException if the price is not within the valid range
     */
    public void validate(Product product) {
        if (product.getPrice() < MIN_PRICE || product.getPrice() > MAX_PRICE) {
            throw new IllegalArgumentException("The price range is not respected!");
        }
    }
}
