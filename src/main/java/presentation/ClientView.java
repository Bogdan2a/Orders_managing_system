package presentation;

import businessLayer.ClientBLL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;

/**
 * The ClientView class represents the graphical user interface for managing clients.
 * It extends the JFrame class and provides methods to interact with the components
 * and retrieve user input related to clients.
 */
public class ClientView extends JFrame {
    private JTable table1;
    private JTextField addedNameClient;
    private JButton addClientButton;
    private JButton deleteClientButton;
    private JTextField deletedIDClient;
    private JTextField updatedIDClient;
    private JTextField newNameClient;
    private JButton updateClientButton;
    private JPanel basePanel;
    private JScrollPane clientsTable;

    /**
     * Constructs a new instance of the ClientView class.
     * Sets the default dimensions for the frame.
     */
    public ClientView() {
        setDimension(900, 500);
    }

    /**
     * Sets the dimensions for the frame.
     *
     * @param w the width of the frame
     * @param h the height of the frame
     */
    public void setDimension(int w, int h) {
        add(basePanel);
        setBounds(300, 200, w, h);
    }

    /**
     * Sets the data of the table with client information.
     * Retrieves the client data from the ClientBLL class and populates the table.
     */
    public void setTable1() {
        ClientBLL client1 = new ClientBLL();
        client1.printClientTable(this.table1);
    }

    /**
     * Retrieves the JTable component displaying client information.
     *
     * @return the JTable component for client information
     */
    public JTable getTable1() {
        return table1;
    }

    /**
     * Retrieves the added client name from the text field.
     *
     * @return the added client name
     */
    public String getAddedNameClient() {
        return this.addedNameClient.getText();
    }

    /**
     * Retrieves the deleted client ID from the text field.
     *
     * @return the deleted client ID
     */
    public String getDeletedIDClient() {
        return this.deletedIDClient.getText();
    }

    /**
     * Retrieves the updated client ID from the text field.
     *
     * @return the updated client ID
     */
    public String getUpdatedIDClient() {
        return this.updatedIDClient.getText();
    }

    /**
     * Retrieves the new name for the client from the text field.
     *
     * @return the new name for the client
     */
    public String getNewNameClient() {
        return this.newNameClient.getText();
    }

    /**
     * Adds an ActionListener to the add client button.
     *
     * @param listener the ActionListener to be added
     */
    public void addAddClientButtonListener(ActionListener listener) {
        this.addClientButton.addActionListener(listener);
    }

    /**
     * Adds an ActionListener to the delete client button.
     *
     * @param listener the ActionListener to be added
     */
    public void addDeleteClientButtonListener(ActionListener listener) {
        this.deleteClientButton.addActionListener(listener);
    }

    /**
     * Adds an ActionListener to the update client button.
     *
     * @param listener the ActionListener to be added
     */
    public void addUpdateClientButtonListener(ActionListener listener) {
        this.updateClientButton.addActionListener(listener);
    }
}