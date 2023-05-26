package org.example.presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InitialGUI extends JFrame implements ActionListener {
    private ImagePanel panel;
    private JLabel label;
    JTextField text;
    JButton button;
    JLabel result_label;

    /**
     * Constructs an instance of the InitialGUI class.
     * Initializes the JFrame with a title, size, and components.
     */
    public InitialGUI() {
        super(" Start page");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(730, 440);
        this.panel = new ImagePanel(null, "blue c.png");

        this.label = new JLabel(" ORDERS MANAGEMENT ");
        this.label.setFont(new Font("Calibri", Font.BOLD, 20));
        this.label.setForeground(Color.white);

        JButton client = new JButton(" Client Operations ");
        client.addActionListener(x -> {
            this.openClient();
        });
        client.setForeground(Color.DARK_GRAY);
        client.setBounds(275, 225, 190, 40);
        this.panel.add(client);

        JButton productOperations = new JButton(" Product Operations ");
        productOperations.addActionListener(x -> {
            this.openProducts();
        });
        productOperations.setForeground(Color.DARK_GRAY);
        productOperations.setBounds(275, 300, 190, 40);
        this.panel.add(productOperations);

        JButton createOrder = new JButton(" Create Order ");
        createOrder.addActionListener(x -> {
            this.openOrders();
        });
        createOrder.setForeground(Color.DARK_GRAY);
        createOrder.setBounds(275, 150, 190, 40);
        this.panel.add(createOrder);

        this.label.setBounds(275, 70, 300, 40);
        this.panel.add(label);

        this.result_label = new JLabel("");
        this.result_label.setFont(new Font("Calibri", Font.BOLD, 20));
        this.result_label.setForeground(Color.white);
        this.result_label.setBounds(300, 150, 160, 25);
        panel.add(this.result_label);
        this.result_label.setVisible(false);

        this.getContentPane().add(panel);
        this.setVisible(true);
    }

    /**
     * Opens the client GUI window.
     */
    public void openClient() {
        ClientGUI clientGui = new ClientGUI();
    }

    /**
     * Opens the product GUI window.
     */
    public void openProducts() {
        ProductGUI productGUI = new ProductGUI();
    }

    /**
     * Opens the order GUI window.
     */
    public void openOrders() {
        OrderGUI orderGUI = new OrderGUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}