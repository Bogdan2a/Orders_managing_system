package dataAccessLayer;

import model.Bill;

/**
 * The BillDAO class is responsible for accessing and manipulating the Bill data in the database.
 * <p>
 * It extends the AbstractDAO class and specializes it for the Bill model.
 */
public class BillDAO extends AbstractDAO<Bill> {

    /**
     * Retrieves the ID of the given Bill object.
     *
     * @param bill The Bill object.
     * @return The ID of the Bill.
     */
    @Override
    public int getId(Bill bill) {
        return bill.getId();
    }
}

