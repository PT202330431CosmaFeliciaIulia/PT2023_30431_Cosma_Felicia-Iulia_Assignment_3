package org.example.dataAccess;

import org.example.model.Client;

public class ClientDAO extends AbstractDAO<Client> {
    /**
     * Constructs a new ClientDAO object.
     * Initializes the ClientDAO with the Client class type.
     */
    public ClientDAO() {
        super(Client.class);
    }

    /**
     * Creates a new client in the database.
     *
     * @param client the client object to be created
     */
    public void createClient(Client client) {
        String query = "INSERT INTO Client (id, name, address, email) VALUES (?, ?, ?, ?)";
        executeStatement(query, client.getId(), client.getName(), client.getAddress(), client.getEmail());
    }

    /**
     * Deletes a client from the database based on the specified client ID.
     *
     * @param clientId the ID of the client to be deleted
     */
    public void deleteClientById(int clientId) {
        String query = "DELETE FROM Client WHERE id = ?";
        executeStatement(query, clientId);
    }

    /**
     * Updates the information of an existing client in the database.
     *
     * @param client the client object with updated information
     */
    public void updateClient(Client client) {
        String query = "UPDATE Client SET name = ?, address = ?, email = ? WHERE id = ?";
        executeStatement(query, client.getName(), client.getAddress(), client.getEmail(), client.getId());
    }
}
