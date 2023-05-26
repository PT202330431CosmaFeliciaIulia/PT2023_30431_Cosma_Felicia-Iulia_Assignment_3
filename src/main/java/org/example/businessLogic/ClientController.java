package org.example.businessLogic;

import org.example.dataAccess.ClientDAO;
import org.example.model.Client;

import java.util.List;

public class ClientController {
    private ClientDAO clientDAO = new ClientDAO();

    /**
     * Constructs an instance of the ClientController class.
     */
    public ClientController() {
    }

    /**
     * Fetches all clients from the data source.
     *
     * @return a list of all clients
     */
    public List<Client> fetch() {
        return clientDAO.doFetchAll();
    }

    /**
     * Adds a new client to the data source.
     *
     * @param newClient the client to be added
     */
    public void addClient(Client newClient) {
        clientDAO.createClient(newClient);
    }

    /**
     * Deletes a client from the data source by its ID.
     *
     * @param clientId the ID of the client to be deleted
     */
    public void deleteClientById(int clientId) {
        clientDAO.deleteClientById(clientId);
    }

    /**
     * Fetches a client from the data source by its ID.
     *
     * @param clientId the ID of the client to be fetched
     * @return the client with the specified ID, or null if not found
     */
    public Client fetchClientById(int clientId) {
        List<Client> clientList = fetch();
        for (Client client : clientList) {
            if (client.getId() == clientId) {
                return client;
            }
        }
        return null;
    }

    /**
     * Updates a client in the data source.
     *
     * @param client the client to be updated
     */
    public void updateClient(Client client) {
        clientDAO.updateClient(client);
    }
}
