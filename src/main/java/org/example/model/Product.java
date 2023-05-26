package org.example.model;

/**
 * The Product class represents a product entity with its information.
 */
public class Product {
    private int id;
    private String name;
    private double price;
    private int stockQuantity;

    /**
     * Constructs a Product object with the specified id, name, price, and stock quantity.
     *
     * @param id            the unique identifier of the product
     * @param name          the name of the product
     * @param price         the price of the product
     * @param stockQuantity the stock quantity of the product
     */
    public Product(int id, String name, double price, int stockQuantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    /**
     * Constructs a default Product object with default values.
     */
    public Product() {
        this.id = 0;
        this.name = null;
        this.price = 0;
        this.stockQuantity = 0;
    }

    // Getters and setters for all attributes

    /**
     * Retrieves the id of the product.
     *
     * @return the id of the product
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id of the product.
     *
     * @param id the id to set for the product
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the product.
     *
     * @return the name of the product
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the product.
     *
     * @param name the name to set for the product
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the price of the product.
     *
     * @return the price of the product
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the product.
     *
     * @param price the price to set for the product
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Retrieves the stock quantity of the product.
     *
     * @return the stock quantity of the product
     */
    public int getStockQuantity() {
        return stockQuantity;
    }

    /**
     * Sets the stock quantity of the product.
     *
     * @param stockQuantity the stock quantity to set for the product
     */
    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    /**
     * Decrements the stock quantity of the product by the specified value.
     *
     * @param newStockQuantity the value to decrement the stock quantity by
     */
    public void decrementStock(int newStockQuantity) {
        this.stockQuantity = stockQuantity - newStockQuantity;
    }

    /**
     * Returns a string representation of the Product object.
     *
     * @return a string representation of the Product object
     */
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stockQuantity=" + stockQuantity +
                '}';
    }
}
