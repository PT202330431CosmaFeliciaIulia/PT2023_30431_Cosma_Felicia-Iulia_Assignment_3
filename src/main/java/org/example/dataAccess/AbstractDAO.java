package org.example.dataAccess;

import org.example.connection.ConnectionFactory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The AbstractDAO class provides common functionality for data access objects.
 *
 * @param <T> the type of entity handled by the data access object
 */
public class AbstractDAO<T> {

    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;

    /**
     * Constructs an AbstractDAO object with the specified type of entity.
     *
     * @param type the type of entity handled by the data access object
     */
    @SuppressWarnings("unchecked")
    public AbstractDAO(Class<T> type) {
        this.type = type;
    }

    /**
     * Creates a list of objects from the given ResultSet.
     *
     * @param result the ResultSet containing the data
     * @return a list of objects created from the ResultSet
     */
    public List<T> createObjects(ResultSet result) {

        ArrayList<T> objectList = new ArrayList<>();

        try {
            while (result.next()) {
                T object = type.getDeclaredConstructor().newInstance();

                for (Field field : type.getDeclaredFields()) {
                    Object value = result.getObject(field.getName());
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
                    Method setterMethod = propertyDescriptor.getWriteMethod();
                    Class<?> parameterType = setterMethod.getParameterTypes()[0];
                    Object convertedValue = convertValue(value, parameterType);
                    setterMethod.invoke(object, convertedValue);
                }
                objectList.add(object);
            }
        } catch (SQLException | NoSuchMethodException | InvocationTargetException | IllegalAccessException
                 | InstantiationException | IntrospectionException e) {
            e.printStackTrace();
        }

        return objectList;
    }

    /**
     * Converts the given value to the specified target type.
     *
     * @param value      the value to be converted
     * @param targetType the target type to convert the value to
     * @return the converted value
     */
    private Object convertValue(Object value, Class<?> targetType) {
        if (targetType == String.class) {
            return String.valueOf(value);
        } else if (targetType == int.class || targetType == Integer.class) {
            return Integer.parseInt(String.valueOf(value));
        } else if (targetType == long.class || targetType == Long.class) {
            return Long.parseLong(String.valueOf(value));
        } else if (targetType == double.class || targetType == Double.class) {
            return Double.parseDouble(String.valueOf(value));
        } else if (targetType == boolean.class || targetType == Boolean.class) {
            return Boolean.parseBoolean(String.valueOf(value));
        }
        return value;
    }

    /**
     * Executes the provided statement string with the specified parameters.
     *
     * @param statementString the statement string to execute
     * @param params          the parameters to set for the statement
     */
    public void executeStatement(String statementString, Object... params) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(statementString);
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
            statement.execute();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO: updateEntity " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * Retrieves a list of all objects of the specified type from the database.
     *
     * @return a list of all objects from the database
     */
    public List<T> doFetchAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        String query = "SELECT * FROM " + type.getSimpleName() + ";";

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            result = statement.executeQuery();
            return createObjects(result);

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO: updateEntity " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Deletes objects of the specified type from the database.
     *
     * @return a list of objects that were deleted from the database
     */
    public List<T> doDelete() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        String query = "DELETE FROM " + type.getSimpleName() + "WHERE NAME LIKE" + type.getName() + ";";

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            result = statement.executeQuery();
            return createObjects(result);

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO: updateEntity " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Retrieves the last ID from the specified table in the database.
     *
     * @param table the given table
     * @return the last ID from the specified table in the database
     */
    public int getLastId(String table) {
        StringBuilder query = new StringBuilder();
        Field field = type.getDeclaredFields()[0];
        field.setAccessible(true);

        query.append("select MAX(");
        query.append(field.getName() + ") as id from \"" + table + "\"");

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(String.valueOf(query));

            while (resultSet.next()) {
                int index = resultSet.getInt("id");
                return index + 1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return 0;
    }
}
