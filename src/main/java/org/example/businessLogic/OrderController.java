package org.example.businessLogic;

import org.example.dataAccess.OrderDAO;
import org.example.model.Order;
import org.example.model.Product;

import java.util.List;

public class OrderController {
    private OrderDAO orderDAO = new OrderDAO();

    /**
     * Constructs an instance of the OrderController class.
     */
    public OrderController() {
    }

    /**
     * Fetches all orders from the data source.
     *
     * @return a list of all orders
     */
    public List<Order> fetch() {
        return orderDAO.doFetchAll();
    }

    /**
     * Adds a new order to the data source.
     *
     * @param order the order to be added
     */
    public void addOrder(Order order) {
        orderDAO.createOrder(order);
    }
}