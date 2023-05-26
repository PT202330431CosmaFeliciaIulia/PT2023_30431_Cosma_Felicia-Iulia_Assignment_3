package org.example.dataAccess;

import org.example.model.Product;

public class ProductDAO extends AbstractDAO<Product> {
    /**
     * Constructs a new ProductDAO object.
     * Initializes the ProductDAO with the Product class type.
     */
    public ProductDAO() {
        super(Product.class);
    }

    /**
     * Creates a new product in the database.
     *
     * @param product the product object to be created
     */
    public void createProduct(Product product) {
        String query = "INSERT INTO Product (id, name, price, stockquantity) VALUES (?, ?, ?, ?)";
        executeStatement(query, product.getId(), product.getName(), product.getPrice(), product.getStockQuantity());
    }

    /**
     * Deletes a product from the database based on the specified product ID.
     *
     * @param productId the ID of the product to be deleted
     */
    public void deleteProductById(int productId) {
        String query = "DELETE FROM Product WHERE id = ?";
        executeStatement(query, productId);
    }

    /**
     * Updates the information of an existing product in the database.
     *
     * @param product the product object with updated information
     */
    public void updateProduct(Product product) {
        String query = "UPDATE Product SET name = ?, price = ?, stockQuantity = ? WHERE id = ?";
        executeStatement(query, product.getName(), product.getPrice(), product.getStockQuantity(), product.getId());
    }
}
