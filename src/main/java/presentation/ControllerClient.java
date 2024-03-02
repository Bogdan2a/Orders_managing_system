package presentation;

import businessLayer.ClientBLL;
import businessLayer.ProductBLL;
import model.Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The ControllerClient class handles the interactions between the ClientView and the ClientBLL.
 * <p>
 * It listens to events triggered by the buttons in the ClientView and performs corresponding actions.
 */
public class ControllerClient {

    private ClientView clientView;

    /**
     * Constructs a new ControllerClient object with the specified ClientView.
     *
     * @param clientView The client view to associate with the controller.
     */
    public ControllerClient(ClientView clientView) {
        this.clientView = clientView;
        clientView.addAddClientButtonListener(new AddClient());
        clientView.addDeleteClientButtonListener(new DeleteClient());
        clientView.addUpdateClientButtonListener(new UpdateClient());
        clientView.setTable1();
    }

    /**
     * ActionListener implementation for handling the Add Client button action.
     * Creates a new client based on the user input and adds it to the database through the ClientBLL.
     */
    class AddClient implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String clientName = clientView.getAddedNameClient();
            ClientBLL clientBLL = new ClientBLL();
            Client aux = new Client(clientBLL.findMaxClientID() + 1, clientName);
            clientBLL.insertClient(aux);
            System.out.println("Added client with id = " + aux.getId());
            clientView.setTable1();
        }
    }

    /**
     * ActionListener implementation for handling the Delete Client button action.
     * Deletes a client from the database based on the user input through the ClientBLL.
     */
    class DeleteClient implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int clientID = Integer.parseInt(clientView.getDeletedIDClient());
            ClientBLL clientBLL = new ClientBLL();
            Client aux = new Client(clientID, "");
            clientBLL.deleteClient(aux);
            System.out.println("Deleted client with id = " + aux.getId());
            clientView.setTable1();
        }
    }

    /**
     * ActionListener implementation for handling the Update Client button action.
     * Updates the name of a client in the database based on the user input through the ClientBLL.
     */
    class UpdateClient implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int clientID = Integer.parseInt(clientView.getUpdatedIDClient());
            String newName = clientView.getNewNameClient();
            ClientBLL clientBLL = new ClientBLL();
            Client aux = new Client(clientID, newName);
            clientBLL.updateClient(aux);
            System.out.println("Updated client with id = " + aux.getId());
            clientView.setTable1();
        }
    }
}