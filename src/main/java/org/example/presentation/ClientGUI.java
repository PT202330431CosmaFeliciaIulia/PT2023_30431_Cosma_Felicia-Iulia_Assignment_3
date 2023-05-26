package org.example.presentation;

import org.example.businessLogic.ClientController;
import org.example.model.Client;

import java.lang.ref.Cleaner;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;

public class ClientGUI extends JFrame {
    private ImagePanel panel3;
    private JLabel lid, lname, laddress, lemail, lage;
    private JTextField id, name, address, email, age;
    private JButton add, edit, delete, viewAll;
    private JTable clientsTable;
    private ClientController clientController;

    /**
     * Constructs an instance of the ClientGUI class.
     * Initializes the JFrame with a title, size, and components.
     */
    public ClientGUI() {
        super("Client operations");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(730, 440);
        this.panel3 = new ImagePanel(null, "gray.png");

        this.add = new JButton("ADD Client");
        this.add.addActionListener(e -> {
            this.addClient();
        });
        this.add.setForeground(Color.DARK_GRAY);
        this.add.setBounds(275, 75, 190, 40);
        this.panel3.add(add);

        this.edit = new JButton("EDIT Client");
        this.edit.addActionListener(e -> {
            this.editClient();
        });
        this.edit.setForeground(Color.DARK_GRAY);
        this.edit.setBounds(275, 150, 190, 40);
        this.panel3.add(edit);

        this.delete = new JButton("DELETE Client");
        this.delete.addActionListener(e -> {
            this.deleteClient();
        });
        this.delete.setForeground(Color.DARK_GRAY);
        this.delete.setBounds(275, 225, 190, 40);
        this.panel3.add(delete);

        this.viewAll = new JButton("VIEW Client List");
        this.viewAll.addActionListener(e -> {
            this.viewAll();
        });
        this.viewAll.setForeground(Color.DARK_GRAY);
        this.viewAll.setBounds(275, 300, 190, 40);
        this.panel3.add(viewAll);

        this.clientController = new ClientController();
        this.getContentPane().add(panel3);
        this.setVisible(true);
    }

    /**
     * Adds a new client.
     * Displays a dialog to enter client details and creates a new client object.
     * Adds the new client using the client controller.
     * Displays an error message if the client ID already exists.
     */
    public void addClient() {
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField addressField = new JTextField();
        JTextField emailField = new JTextField();

        Object[] fields = {
                "Client ID:", idField,
                "Client Name:", nameField,
                "Client Address:", addressField,
                "Client Email:", emailField
        };
        int option = JOptionPane.showConfirmDialog(this, fields, "Add Client", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            int clientId = Integer.parseInt(idField.getText());
            String clientName = nameField.getText();
            String clientAddress = addressField.getText();
            String clientEmail = emailField.getText();

            Client existingClient = clientController.fetchClientById(clientId);
            if (existingClient != null) {
                JOptionPane.showMessageDialog(this, "Client ID already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Client newClient = new Client(clientId, clientName, clientAddress, clientEmail);
            clientController.addClient(newClient);
        }
    }

    /**
     * Edits an existing client.
     * Displays a dialog to enter client details for editing.
     * Retrieves the existing client object and updates its properties with the new values.
     * Updates the client using the client controller.
     */
    public void editClient() {
        String input = JOptionPane.showInputDialog(this, "Enter Client ID to Edit:");
        if (input != null && !input.isEmpty()) {
            int clientId = Integer.parseInt(input);
            Client existingClient = clientController.fetchClientById(clientId);
            if (existingClient == null) {
                JOptionPane.showMessageDialog(this, "Client not found!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            JTextField idField = new JTextField(String.valueOf(existingClient.getId()));
            JTextField nameField = new JTextField(existingClient.getName());
            JTextField addressField = new JTextField(existingClient.getAddress());
            JTextField emailField = new JTextField(existingClient.getEmail());

            Object[] fields = {"ID:", idField, "Name:", nameField, "Address:", addressField, "Email:", emailField};
            int result = JOptionPane.showConfirmDialog(this, fields, "Edit Client", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                int editedId = Integer.parseInt(idField.getText());
                String editedName = nameField.getText();
                String editedAddress = addressField.getText();
                String editedEmail = emailField.getText();

                Client editedClient = new Client(editedId, editedName, editedAddress, editedEmail);
                clientController.updateClient(editedClient);
            }
        }
    }

    /**
     * Deletes a client.
     * Displays a dialog to enter the client ID to be deleted.
     * Deletes the client using the client controller.
     */
    public void deleteClient() {
        String input = JOptionPane.showInputDialog(this, "Enter the Client ID to delete:");
        if (input != null && !input.isEmpty()) {
            try {
                int clientId = Integer.parseInt(input);
                clientController.deleteClientById(clientId);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid Client ID! Please enter a valid integer value.");
            }
        }
    }

    /**
     * Displays a table with all the clients.
     * Fetches the client list from the client controller.
     * Converts the client data into a table format and displays it using the Table class.
     */
    public void viewAll() {
        List<Client> clientList = clientController.fetch();
        String[] columns = {"id", "name", "address", "email"};

        List<String[]> rows = new ArrayList<>();

        for (Client client : clientList) {
            System.out.println(client.getName());
            String[] row = {
                    String.valueOf(client.getId()),
                    client.getName(),
                    client.getAddress(),
                    client.getEmail()
            };
            rows.add(row);
        }
        Table table = new Table(columns, rows);
    }
}
