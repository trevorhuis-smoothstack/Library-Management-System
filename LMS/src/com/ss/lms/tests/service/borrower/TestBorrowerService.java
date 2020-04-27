package com.ss.lms.tests.service.borrower;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.List;

import com.ss.lms.models.BookLoan;
import com.ss.lms.models.Borrower;
import com.ss.lms.service.BorrowerService;

import org.junit.Test;

public class TestBorrowerService {

    @Test
    public void checkIfBorrowerExists() throws SQLException {
        BorrowerService borService = new BorrowerService();
        Integer cardNo = 12;
        Borrower bor = borService.getBorrower(cardNo);

        assertEquals(bor.getCardNo() == 12, true);
    }

    @Test 
    public void getLoansForABorrowerTest() throws SQLException {
        BorrowerService borService = new BorrowerService();
        Integer cardNo = 26;
        List<BookLoan> loans = borService.getLoansFromABorrower(cardNo);

        assertEquals(loans.get(0).getCardNo() == cardNo, true);
    }

    @Test
    public void returnABookTest() throws SQLException {
        BorrowerService borService = new BorrowerService();
        Integer cardNo = 26;
        List<BookLoan> loans = borService.getLoansFromABorrower(cardNo);
        assertEquals(loans.get(0).getDateIn() == null, true);

        borService.returnABook(loans.get(0));

        assertEquals(loans.get(0).getDateIn() != null, true);
    }

    @Test
    public void checkOutABookTest() throws SQLException {
        BorrowerService borService = new BorrowerService();
        borService.checkOutABook(1, 1, 1);

        List<BookLoan> loans = borService.getLoansFromABorrower(1);
        assertEquals(loans.get(0).getBookId() == 1, true);
    }
}