package org.example.dataAccess;

import org.example.connection.ConnectionFactory;
import org.example.model.Order;
import org.example.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;

public class OrderDAO extends AbstractDAO<Order> {
    /**
     * Constructs a new OrderDAO object.
     * Initializes the OrderDAO with the Order class type.
     */
    public OrderDAO() {
        super(Order.class);
    }

    /**
     * Creates a new order in the database.
     *
     * @param order the order object to be created
     */
    public void createOrder(Order order) {
        String query = "INSERT INTO ordert (id, clientid, productid, quantity) VALUES (?, ?, ?, ?)";
        executeStatement(query, order.getId(), order.getClientId(), order.getProductId(), order.getQuantity());
    }

    /**
     * Retrieves a new ID for the order by counting the existing orders in the database.
     *
     * @return the new ID for the order
     */
    public int getNewId() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        String query = "SELECT * FROM ordert;";

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            result = statement.executeQuery();
            List<Order> ordList = createObjects(result);
            int count = 0;
            for (Order o : ordList) {
                count++;
            }
            return count;
        } catch (
                SQLException e) {
            LOGGER.log(Level.WARNING, " order DAO: updateEntity " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return 0;
    }
}
