package com.ss.lms.tests.service.admin;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.List;

import com.ss.lms.models.Borrower;
import com.ss.lms.service.admin.AdminBorrowerService;

import org.junit.Test;

public class TestAdminBorrowerService {

    @Test
    public void addABorrowerTest() throws SQLException {
        AdminBorrowerService aBorServ = new AdminBorrowerService();
        Borrower borrower = new Borrower();
        borrower.setName("Test Borrower");
        borrower.setAddress("789 Test St.");
        borrower.setPhone("123-456-7890");
        Integer primaryKey = aBorServ.addABorrower(borrower);
        System.out.println(primaryKey);

        assertEquals(aBorServ.readABorrower(primaryKey).getName().equals("Test Borrower"), true);
    }

    @Test
    public void updateABorrowerTest() throws SQLException {
        AdminBorrowerService aBorServ = new AdminBorrowerService();
        Borrower borrower = new Borrower();
        borrower.setName("Test Borrower");
        borrower.setAddress("789 Test St.");
        borrower.setPhone("123-456-7890");
        Integer primaryKey = aBorServ.addABorrower(borrower);

        Borrower updateBor = new Borrower(primaryKey, "Updated Test Borrower", "789 Test St.", "123-456-7890");
        aBorServ.updateABorrower(updateBor);

        assertEquals(aBorServ.readABorrower(primaryKey).getName().equals("Updated Test Borrower"), true);
    }

    @Test
    public void deleteABorrowerTest() throws SQLException {
        AdminBorrowerService aBorServ = new AdminBorrowerService();
        Borrower borrower = new Borrower();
        borrower.setName("Test Borrower");
        borrower.setAddress("789 Test St.");
        borrower.setPhone("123-456-7890");
        Integer primaryKey = aBorServ.addABorrower(borrower);
        borrower.setCardNo(primaryKey);
        assertEquals(aBorServ.readABorrower(primaryKey).getName().equals("Test Borrower"), true);

        aBorServ.deleteABorrower(borrower);
        Borrower deletedBorrower = aBorServ.readABorrower(primaryKey);

        assertEquals(deletedBorrower == null, true);
    }

    @Test
    public void readABorrowerTest() throws SQLException {
        AdminBorrowerService aBorServ = new AdminBorrowerService();
        Borrower borrower = aBorServ.readABorrower(1);

        assertEquals(borrower.getName().equals("Rickie Caesman"), true);
    }

    @Test
    public void readAllBorrowersTest() throws SQLException {
        AdminBorrowerService aBorServ = new AdminBorrowerService();
        List<Borrower> borrowers = aBorServ.readAllBorrowers();

        assertEquals(borrowers.get(0) instanceof Borrower, true);
    }
}