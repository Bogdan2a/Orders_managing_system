package businessLayer;

import businessLayer.Validators.NameValidator;
import businessLayer.Validators.Validator;
import dataAccessLayer.ClientDAO;
import model.Client;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * The ClientBLL class represents the business logic for handling Client operations.
 */
public class ClientBLL {

    private List<Validator<Client>> validators;
    private ClientDAO clientDAO;

    /**
     * Constructs a new ClientBLL object.
     */
    public ClientBLL() {
        validators = new ArrayList<>();
        validators.add(new NameValidator());
        clientDAO = new ClientDAO();
    }

    /**
     * Finds a client by their ID.
     *
     * @param id the ID of the client to find
     * @return the found client
     * @throws SQLException          if a database error occurs
     * @throws NoSuchElementException if the client with the given ID was not found
     */
    public Client findClientById(int id) throws SQLException {
        ClientDAO client = new ClientDAO();
        Client st;
        st = client.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The client with id=" + id + " was not found!");
        }
        return st;
    }

    /**
     * Inserts a new client.
     *
     * @param newClient the client to insert
     * @return the inserted client
     * @throws NoSuchElementException if the client was not created
     */
    public Client insertClient(Client newClient) {
        ClientDAO client = new ClientDAO();
        Client st = null;
        for (Validator<Client> v : validators) {
            v.validate(newClient);

            st = client.insert(newClient);
            if (st == null) {
                throw new NoSuchElementException("The client with id= was not created!");
            }
        }
        return st;
    }

    /**
     * Updates an existing client.
     *
     * @param newClient the client to update
     * @return the updated client
     * @throws NoSuchElementException if the client was not updated
     */
    public Client updateClient(Client newClient) {
        ClientDAO client = new ClientDAO();
        Client st = null;
        for (Validator<Client> v : validators) {
            v.validate(newClient);

            st = client.update(newClient);
            if (st == null) {
                throw new NoSuchElementException("The client with id= was not updated!");
            }
        }
        return st;
    }

    /**
     * Deletes a client.
     *
     * @param newClient the client to delete
     * @return the deleted client
     * @throws NoSuchElementException if the client was not deleted
     */
    public Client deleteClient(Client newClient) {
        ClientDAO client = new ClientDAO();
        Client st = null;

        st = client.delete(newClient);
        if (st == null) {
            throw new NoSuchElementException("The client with id= was not deleted!");
        }
        return st;
    }

    /**
     * Finds the maximum client ID.
     *
     * @return the maximum client ID
     */
    public int findMaxClientID() {
        ClientDAO client = new ClientDAO();
        int aux;
        aux = client.findMaxId();
        return aux;
    }

    /**
     * Prints the client table to a JTable.
     *
     * @param table1 the JTable to display the client table
     */
    public void printClientTable(JTable table1) {
        ClientDAO client = new ClientDAO();
        client.setTable("assignment3.client", table1);
    }
}
