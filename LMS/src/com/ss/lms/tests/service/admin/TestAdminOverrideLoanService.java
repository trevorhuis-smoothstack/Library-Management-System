package com.ss.lms.tests.service.admin;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import com.ss.lms.models.BookLoan;
import com.ss.lms.service.BorrowerService;
import com.ss.lms.service.admin.AdminOverrideLoanService;

import org.junit.Test;

public class TestAdminOverrideLoanService {
    @Test
    public void updateTheLoanOfABorrowerTest() throws SQLException {
        BorrowerService bService = new BorrowerService();
        List<BookLoan> loans = bService.getLoansFromABorrower(1);
        LocalDateTime oldLoanTime = loans.get(0).getDueDate().toLocalDateTime();
        AdminOverrideLoanService aService = new AdminOverrideLoanService();
        aService.addAWeekToALoan(loans.get(0));
        List<BookLoan> loansUpdated = bService.getLoansFromABorrower(1);
        LocalDateTime newLoanTime = loansUpdated.get(0).getDueDate().toLocalDateTime();

        assertEquals(oldLoanTime.plusDays(7).getDayOfMonth() == newLoanTime.getDayOfMonth(), true);

    }
}