package org.example.presentation;

import org.example.businessLogic.OrderController;
import org.example.businessLogic.ProductController;
import org.example.businessLogic.ClientController;
import org.example.dataAccess.OrderDAO;
import org.example.dataAccess.ProductDAO;
import org.example.model.Order;
import org.example.model.Product;
import org.example.model.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class OrderGUI extends JFrame {
    private ImagePanel panel1;
    private JComboBox<Product> productComboBox;
    private JComboBox<Client> clientComboBox;
    private JTextField quantity;
    private JLabel lproduct, lclient, lquantity, stockMessage;
    private JButton createOrder;
    private ProductController productController;
    private ClientController clientController;
    private OrderController orderController;

    /**
     * Constructs an instance of the OrderGUI class.
     * Initializes the JFrame with a title, size, and components.
     * Sets up the event listeners and controllers.
     */
    public OrderGUI() {
        super("Create order");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(730, 440);

        this.panel1 = new ImagePanel(null, "gray.png");
        this.panel1.setLayout(null);

        this.lproduct = new JLabel("Product:");
        this.lproduct.setBounds(20, 20, 100, 25);
        this.panel1.add(lproduct);

        this.productComboBox = new JComboBox<>();
        this.productComboBox.setBounds(120, 20, 500, 25);
        this.panel1.add(productComboBox);

        this.lclient = new JLabel("Client:");
        this.lclient.setBounds(20, 50, 100, 25);
        this.panel1.add(lclient);

        this.clientComboBox = new JComboBox<>();
        this.clientComboBox.setBounds(120, 50, 500, 25);
        this.panel1.add(clientComboBox);

        this.lquantity = new JLabel("Quantity:");
        this.lquantity.setBounds(20, 80, 100, 25);
        this.panel1.add(lquantity);

        this.quantity = new JTextField();
        this.quantity.setBounds(120, 80, 100, 25);
        this.panel1.add(quantity);

        this.stockMessage = new JLabel();
        this.stockMessage.setForeground(Color.RED);
        this.stockMessage.setBounds(20, 150, 300, 25);
        this.panel1.add(stockMessage);

        this.createOrder = new JButton("Send order");
        this.createOrder.setBounds(500, 175, 160, 25);
        this.createOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createOrder();
            }
        });
        this.panel1.add(createOrder);

        this.productController = new ProductController();
        this.clientController = new ClientController();
        this.orderController = new OrderController();

        loadProducts();
        loadClients();

        this.getContentPane().add(panel1);
        this.setVisible(true);
    }

    /**
     * Loads the product list into the product combo box.
     * Fetches the product list from the product controller.
     * Creates a default combo box model with the product list and sets it as the model for the product combo box.
     */
    private void loadProducts() {
        List<Product> productList = productController.fetch();

        DefaultComboBoxModel<Product> model = new DefaultComboBoxModel<>(productList.toArray(new Product[0]));
        productComboBox.setModel(model);
    }

    /**
     * Loads the client list into the client combo box.
     * Fetches the client list from the client controller.
     * Creates a default combo box model with the client list and sets it as the model for the client combo box.
     */
    private void loadClients() {
        List<Client> clientList = clientController.fetch();

        DefaultComboBoxModel<Client> model = new DefaultComboBoxModel<>(clientList.toArray(new Client[0]));
        clientComboBox.setModel(model);
    }

    /**
     * Creates an order based on the selected product, client, and quantity.
     * Validates the order and updates the product stock if valid.
     * Saves the order and performs any necessary operations.
     * Displays a success message if the order is created successfully, or an error message if the order is invalid.
     */
    private void createOrder() {
        Product selectedProduct = (Product) productComboBox.getSelectedItem();
        Client selectedClient = (Client) clientComboBox.getSelectedItem();
        int selectedQuantity = Integer.parseInt(quantity.getText());

        if (selectedProduct.getStockQuantity() >= selectedQuantity && selectedQuantity > 0) {
            selectedProduct.decrementStock(selectedQuantity);

            productController.updateProduct(selectedProduct);
            OrderDAO orderDAO = new OrderDAO();
            int id = orderDAO.getLastId("ordert");
            Order newOrder = new Order(id, selectedClient.getId(), selectedProduct.getId(), selectedQuantity);
            orderController.addOrder(newOrder);
            JOptionPane.showMessageDialog(this, "Order created successfully!");
            clearForm();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid order! Please check the product stock and quantity.");
        }
    }

    /**
     * Clears the form by resetting the combo boxes and quantity field.
     */
    private void clearForm() {
        productComboBox.setSelectedIndex(0);
        clientComboBox.setSelectedIndex(0);
        quantity.setText("");
    }
}