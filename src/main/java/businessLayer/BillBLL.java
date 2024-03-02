package businessLayer;

import businessLayer.Validators.QuantityValidator;
import businessLayer.Validators.Validator;
import dataAccessLayer.BillDAO;
import model.Bill;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * The BillBLL class represents the business logic for handling Bill operations.
 */
public class BillBLL {

    private List<Validator<Bill>> validators;
    private BillDAO billDAO;

    /**
     * Constructs a new BillBLL object.
     */
    public BillBLL() {
        validators = new ArrayList<>();
        billDAO = new BillDAO();
    }

    /**
     * Inserts a new bill.
     *
     * @param newBill the bill to insert
     * @return the inserted bill
     * @throws NoSuchElementException if the bill was not created
     */
    public Bill insertBill(Bill newBill) {
        BillDAO bill = new BillDAO();
        Bill st;

        st = bill.insert(newBill);
        if (st == null) {
            throw new NoSuchElementException("The bill with id= was not created!");
        }

        return st;
    }

    /**
     * Prints the bill table to a JTable.
     *
     * @param table1 the JTable to display the bill table
     */
    public void printBillTable(JTable table1) {
        BillDAO bill = new BillDAO();
        bill.setTable("assignment3.bill", table1);
    }

    /**
     * Finds the maximum bill ID.
     *
     * @return the maximum bill ID
     */
    public int findMaxBillID() {
        BillDAO bill = new BillDAO();
        int aux;
        aux = bill.findMaxId();
        return aux;
    }
}
