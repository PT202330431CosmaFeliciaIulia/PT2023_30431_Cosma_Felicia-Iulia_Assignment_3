package org.example.presentation;

import org.example.businessLogic.ProductController;
import org.example.model.Product;

import java.lang.ref.Cleaner;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;

public class ProductGUI extends JFrame {
    private ImagePanel panel2;
    private JButton add, edit, delete, viewAll;
    private JLabel lid, lname, linStock, lprice;
    private JTextField id, name, inStock, price;
    private JTable productsTable;
    private ProductController productController;

    /**
     * Constructs an instance of the ProductGUI class.
     * Initializes the JFrame with a title, size, and components.
     */
    public ProductGUI() {
        super("Product operations");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(730, 440);
        this.panel2 = new ImagePanel(null, "gray.png");

        this.add = new JButton("ADD Product");
        this.add.addActionListener(e -> {
            this.addProduct();
        });
        this.add.setForeground(Color.DARK_GRAY);
        this.add.setBounds(275, 75, 190, 40);
        this.panel2.add(add);

        this.edit = new JButton("EDIT Product");
        this.edit.addActionListener(e -> {
            this.editProduct();
        });
        this.edit.setForeground(Color.DARK_GRAY);
        this.edit.setBounds(275, 150, 190, 40);
        this.panel2.add(edit);

        this.delete = new JButton("DELETE Product");
        this.delete.addActionListener(e -> {
            this.deleteProduct();
        });
        this.delete.setForeground(Color.DARK_GRAY);
        this.delete.setBounds(275, 225, 190, 40);
        this.panel2.add(delete);

        this.viewAll = new JButton("VIEW Product List");
        this.viewAll.addActionListener(e -> {
            this.viewAll();
        });
        this.viewAll.setForeground(Color.DARK_GRAY);
        this.viewAll.setBounds(275, 300, 190, 40);
        this.panel2.add(viewAll);

        this.productController = new ProductController();
        this.getContentPane().add(panel2);
        this.setVisible(true);
    }

    /**
     * Adds a new product.
     * Displays a dialog to enter product details and creates a new product object.
     * Adds the new product using the product controller.
     * Displays an error message if the product ID already exists.
     */
    public void addProduct() {
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField inStockField = new JTextField();

        Object[] fields = {
                "Product ID:", idField,
                "Product Name:", nameField,
                "Product price:", priceField,
                "Product stock quantity:", inStockField
        };
        int option = JOptionPane.showConfirmDialog(this, fields, "Add Product", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            int productId = Integer.parseInt(idField.getText());
            String productName = nameField.getText();
            double productPrice = Double.parseDouble(priceField.getText());
            int productStock = Integer.parseInt(inStockField.getText());

            Product existingProduct = productController.fetchProductById(productId);
            if (existingProduct != null) {
                JOptionPane.showMessageDialog(this, "Product ID already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Product newProduct = new Product(productId, productName, productPrice, productStock);
            productController.addProduct(newProduct);
        }
    }

    /**
     * Edits an existing product.
     * Displays a dialog to enter product details for editing.
     * Retrieves the existing product object and updates its properties with the new values.
     * Updates the product using the product controller.
     */
    public void editProduct() {
        String input = JOptionPane.showInputDialog(this, "Enter Product ID to Edit:");
        if (input != null && !input.isEmpty()) {
            int productId = Integer.parseInt(input);
            Product existingProduct = productController.fetchProductById(productId);
            if (existingProduct == null) {
                JOptionPane.showMessageDialog(this, "Product not found!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            JTextField idField = new JTextField(String.valueOf(existingProduct.getId()));
            JTextField nameField = new JTextField(existingProduct.getName());
            JTextField priceField = new JTextField(String.valueOf(existingProduct.getPrice()));
            JTextField inStockField = new JTextField(String.valueOf(existingProduct.getStockQuantity()));

            Object[] fields = {"ID:", idField, "Name:", nameField, "Price:", priceField, "Stock Quantity:", inStockField};
            int result = JOptionPane.showConfirmDialog(this, fields, "Edit Product", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                int editedId = Integer.parseInt(idField.getText());
                String editedName = nameField.getText();
                double editedPrice = Double.parseDouble(priceField.getText());
                int editedStock = Integer.parseInt(inStockField.getText());

                Product editedProduct = new Product(editedId, editedName, editedPrice, editedStock);
                productController.updateProduct(editedProduct);
            }
        }
    }

    /**
     * Deletes a product.
     * Displays a dialog to enter the product ID to be deleted.
     * Deletes the product using the product controller.
     */
    public void deleteProduct() {
        String input = JOptionPane.showInputDialog(this, "Enter the Product ID to delete:");
        if (input != null && !input.isEmpty()) {
            try {
                int productId = Integer.parseInt(input);
                productController.deleteProductById(productId);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid Product ID! Please enter a valid integer value.");
            }
        }
    }

    /**
     * Displays a table with all the products.
     * Fetches the product list from the product controller.
     * Converts the product data into a table format and displays it using the Table class.
     */
    public void viewAll() {

        java.util.List<Product> productList = productController.fetch();
        String[] columns = {"id", "name", "stock", "price"};

        List<String[]> rows = new ArrayList<>();

        for (Product product : productList) {
            System.out.println(product.getName());
            String[] row = {
                    String.valueOf(product.getId()),
                    product.getName(),
                    String.valueOf(product.getStockQuantity()),
                    String.valueOf(product.getPrice())
            };
            rows.add(row);
        }
        Table table = new Table(columns, rows);
    }
}
