package org.example.businessLogic;

import org.example.dataAccess.ProductDAO;
import org.example.model.Product;

import java.util.List;

public class ProductController {
    private ProductDAO productDAO = new ProductDAO();

    /**
     * Constructs an instance of the ProductController class.
     */
    public ProductController() {
    }

    /**
     * Fetches all products from the data source.
     *
     * @return a list of all products
     */
    public List<Product> fetch() {
        return productDAO.doFetchAll();
    }

    /**
     * Adds a new product to the data source.
     *
     * @param newProduct the product to be added
     */
    public void addProduct(Product newProduct) {
        productDAO.createProduct(newProduct);
    }

    /**
     * Deletes a product from the data source by its ID.
     *
     * @param productId the ID of the product to be deleted
     */
    public void deleteProductById(int productId) {
        productDAO.deleteProductById(productId);
    }

    /**
     * Fetches a product from the data source by its ID.
     *
     * @param productId the ID of the product to be fetched
     * @return the product with the specified ID, or null if not found
     */
    public Product fetchProductById(int productId) {
        List<Product> productList = fetch();
        for (Product product : productList) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }

    /**
     * Updates a product in the data source.
     *
     * @param product the product to be updated
     */
    public void updateProduct(Product product) {
        productDAO.updateProduct(product);
    }
}
