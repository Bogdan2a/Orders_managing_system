package dataAccessLayer;

import model.Client;
import model.Order;
import model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * The AbstractDAO class provides a generic implementation for accessing and manipulating database tables.
 * It serves as a base class for specific Data Access Object (DAO) classes for different entities.
 *
 * @param <T> The type parameter representing the entity type for which the DAO is created.
 */
public abstract class AbstractDAO<T> {
    /**
     * The logger used for logging warnings and exceptions.
     */
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;
    private Connection connection;
    private PreparedStatement statement;

    /**
     * Constructs an AbstractDAO object.
     * It initializes the 'type' variable by obtaining the actual type argument from the generic superclass.
     */
    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }
    /**
     * Creates a SELECT query for retrieving an entity by a specific field value.
     *
     * @param field The name of the field used for filtering the query.
     * @return The generated SELECT query.
     */
    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM assignment3.");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }
    /**
     * Finds an entity by its ID.
     *
     * @param id The ID of the entity to find.
     * @return The found entity, or null if not found.
     * @throws SQLException If an SQL exception occurs.
     */
    public T findById(int id) throws SQLException {
        String query = createSelectQuery("id");

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "0711");
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            List<T> objects = createObjects(resultSet);
            if (!objects.isEmpty()) {
                return objects.get(0);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "Failed to close statement or connection: " + e.getMessage());
            }
        }
        return null;
    }
    /**
     * Creates a list of entity objects from a ResultSet.
     *
     * @param resultSet The ResultSet containing the data to create objects from.
     * @return The list of created entity objects.
     */
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T) ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }
    /**
     * Gets the ID of the specified entity.
     *
     * @param t The entity object.
     * @return The ID of the entity.
     */
    public abstract int getId(T t);
    /**
     * Inserts a new entity into the database.
     *
     * @param t The entity to insert.
     * @return The inserted entity.
     */
    public T insert(T t) {
        String tableName = type.getSimpleName();
        String sql;
        StringBuilder columns = new StringBuilder();
        StringBuilder values = new StringBuilder();
        Field[] fields = type.getDeclaredFields();
        int parameterIndex = 1;
        for (Field field : fields) {
            String fieldName = field.getName();
            columns.append(fieldName).append(", ");
            values.append("?, ");

        }
        columns.setLength(columns.length() - 2);
        values.setLength(values.length() - 2);

        sql = "INSERT INTO assignment3." + tableName + " (" + columns.toString() + ") VALUES (" + values.toString() + ")";
        System.out.println(sql);
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "0711");
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            for (Field field : fields) {
                String fieldName = field.getName();
                columns.append(fieldName).append(", ");
                values.append("?, ");

                try {
                    field.setAccessible(true);
                    Object value = field.get(t);
                    //    System.out.println("crap2");
                    statement.setObject(parameterIndex++, value);
                } catch (IllegalAccessException | SQLException e) {
                    e.printStackTrace();
                }
            }
            // Execute the statement
            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    Field idField = type.getDeclaredField("id");
                    idField.setAccessible(true);
                    idField.set(t, generatedId);
                }
            }



        } catch (SQLException | ClassNotFoundException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return t;
    }
    /**
     * Updates an existing entity in the database.
     *
     * @param t The entity to update.
     * @return The updated entity.
     */
    public T update(T t) {
        String tableName = type.getSimpleName();
        String sql;
        StringBuilder columns = new StringBuilder();
        Field[] fields = type.getDeclaredFields();
        int parameterIndex = 1;

        for (Field field : fields) {
            String fieldName = field.getName();
            columns.append(fieldName).append(" = ?, ");
        }

        columns.setLength(columns.length() - 2);

        sql = "UPDATE assignment3." + tableName + " SET " + columns.toString() + " WHERE id = ?";
        System.out.println(sql);

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "0711");
            statement = connection.prepareStatement(sql);

            for (Field field : fields) {
                String fieldName = field.getName();

                try {
                    field.setAccessible(true);
                    Object value = field.get(t);
                    statement.setObject(parameterIndex++, value);
                } catch (IllegalAccessException | SQLException e) {
                    e.printStackTrace();
                }
            }


            Field idField = type.getDeclaredField("id");
            idField.setAccessible(true);
            int id = (int) idField.get(t);
            statement.setObject(parameterIndex, id);


            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Entry updated successfully.");
            } else {
                System.out.println("Failed to update the entry.");
            }

        } catch (SQLException | ClassNotFoundException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return t;
    }
    /**
     * Deletes an entity from the database.
     *
     * @param t The entity to delete.
     * @return The deleted entity.
     */
    public T delete(T t) {
        String tableName = type.getSimpleName();
        String sql;
        sql = "DELETE FROM assignment3." + tableName + " WHERE id = ?";
        System.out.println(sql);

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "0711");
            statement = connection.prepareStatement(sql);



            Field idField = type.getDeclaredField("id");
            idField.setAccessible(true);
            int id = (int) idField.get(t);
            statement.setObject(1, id);


            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Entry deleted successfully.");
            } else {
                System.out.println("Failed to delete the entry.");
            }

        } catch (SQLException | ClassNotFoundException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return t;
    }
    /**
     * Finds the maximum ID value from the table associated with the entity type.
     *
     * @return The maximum ID value.
     */
    public int findMaxId() {
        String tableName = type.getSimpleName();
        String sql = "SELECT MAX(id) FROM assignment3." + tableName;
        System.out.println(sql);

        Statement statement1 = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "0711");
            statement1 = connection.createStatement();


            ResultSet resultSet = statement1.executeQuery(sql);

            if (resultSet.next()) {
                int maxId = resultSet.getInt(1);
                System.out.println("Max ID: " + maxId);
                return maxId;
            } else {
                System.out.println("Table is empty.");
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {

                if (statement1 != null) {
                    statement1.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return -1;
    }
    /**
     * Sets the data of a JTable component by retrieving all rows from a specific table in the database.
     *
     * @param tableName The name of the table.
     * @param table1    The JTable component to set the data for.
     */
    public void setTable(String tableName, JTable table1) {
        String url = "jdbc:postgresql://localhost/postgres";
        String username = "postgres";
        String password = "0711";


        String sql = "SELECT * FROM " + tableName;

        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();


            ResultSet resultSet = statement.executeQuery(sql);

            ResultSetMetaData metaData = resultSet.getMetaData();

            int columnCount = metaData.getColumnCount();

            DefaultTableModel tableModel = new DefaultTableModel();


            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                String columnName = metaData.getColumnName(columnIndex);
                tableModel.addColumn(columnName);
            }


            while (resultSet.next()) {
                Object[] rowData = new Object[columnCount];
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    rowData[columnIndex - 1] = resultSet.getObject(columnIndex);
                }
                tableModel.addRow(rowData);
            }


            table1.setModel(tableModel);

            resultSet.close();
            statement.close();
            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Populates two combo boxes with data from the 'client' and 'product' tables.
     *
     * @param orderClientcomboBox  The combo box for client data.
     * @param orderProductcomboBox The combo box for product data.
     */
    public void populateComboBoxes(JComboBox orderClientcomboBox,JComboBox orderProductcomboBox) {
        String url = "jdbc:postgresql://localhost/postgres";
        String username = "postgres";
        String password = "0711";

        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();


            ResultSet clientResultSet = statement.executeQuery("SELECT id FROM assignment3.client");
            while (clientResultSet.next()) {
                int clientId = clientResultSet.getInt("id");
                orderClientcomboBox.addItem(clientId);
            }
            clientResultSet.close();


            ResultSet productResultSet = statement.executeQuery("SELECT id FROM assignment3.product");
            while (productResultSet.next()) {
                int productId = productResultSet.getInt("id");
                orderProductcomboBox.addItem(productId);
            }
            productResultSet.close();

            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
