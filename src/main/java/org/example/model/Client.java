package org.example.model;

/**
 * The Client class represents a client entity with their information.
 */
public class Client {
    private int id;
    private String name;
    private String address;
    private String email;

    /**
     * Constructs a Client object with the specified id, name, address, and email.
     *
     * @param id
     * @param name
     * @param address
     * @param email
     */
    public Client(int id, String name, String address, String email) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
    }

    /**
     * Constructs a default Client object with default values.
     */
    public Client() {
        this.id = 0;
        this.name = null;
        this.address = null;
        this.email = null;
    }

    // Getters and setters for all attributes

    /**
     * Retrieves the id of the client.
     *
     * @return the id of the client
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id of the client.
     *
     * @param id the id to set for the client
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the client.
     *
     * @return the name of the client
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the client.
     *
     * @param name the name to set for the client
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the address of the client.
     *
     * @return the address of the client
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the client.
     *
     * @param address the address to set for the client
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Retrieves the email of the client.
     *
     * @return the email of the client
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the client.
     *
     * @param email the email to set for the client
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns a string representation of the Client object.
     *
     * @return a string representation of the Client object
     */
    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
