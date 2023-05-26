package org.example.model;

import java.util.Date;

/**
 * The Order class represents an order entity with its information.
 */
public class Order {
    private int id;
    private int clientid;
    private int productid;
    private int quantity;

    /**
     * Constructs an Order object with the specified id, client ID, product ID, and quantity.
     *
     * @param id        the unique identifier of the order
     * @param clientId  the ID of the client associated with the order
     * @param productId the ID of the product associated with the order
     * @param quantity  the quantity of the product in the order
     */
    public Order(int id, int clientid, int productid, int quantity) {
        this.id = id;
        this.clientid = clientid;
        this.productid = productid;
        this.quantity = quantity;
    }

    /**
     * Constructs a default Order object with default values.
     */
    public Order() {
        this.id = 0;
        this.clientid = 0;
        this.productid = 0;
        this.quantity = 0;
    }

    // Getters and setters for all attributes

    /**
     * Retrieves the id of the order.
     *
     * @return the id of the order
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id of the order.
     *
     * @param id the id to set for the order
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the client ID associated with the order.
     *
     * @return the client ID associated with the order
     */
    public int getClientId() {
        return clientid;
    }

    /**
     * Sets the client ID associated with the order.
     *
     * @param clientId the client ID to set for the order
     */
    public void setClientId(int clientid) {
        this.clientid = clientid;
    }

    /**
     * Retrieves the product ID associated with the order.
     *
     * @return the product ID associated with the order
     */
    public int getProductId() {
        return productid;
    }

    /**
     * Sets the product ID associated with the order.
     *
     * @param productId the product ID to set for the order
     */
    public void setProductId(int productid) {
        this.productid = productid;
    }

    /**
     * Retrieves the quantity of the product in the order.
     *
     * @return the quantity of the product in the order
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the product in the order.
     *
     * @param quantity the quantity to set for the product in the order
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Returns a string representation of the Order object.
     *
     * @return a string representation of the Order object
     */
    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", client=" + clientid +
                ", product=" + productid +
                ", quantity=" + quantity +
                '}';
    }
}
