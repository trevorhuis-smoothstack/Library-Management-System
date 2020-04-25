package com.ss.lms.tests.service.librarian;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import com.ss.lms.models.BookCopies;
import com.ss.lms.models.LibraryBranch;
import com.ss.lms.service.LibrarianService;

import org.junit.Test;

public class TestLibrarianAdmin {

    @Test
    public void updateCopiesTest() {
        LibrarianService libServ = new LibrarianService();
        BookCopies entry = new BookCopies(3, 12, 3);

        boolean didUpdate = libServ.updateCopies(entry);

        assertEquals(didUpdate == true, true);
    }

    @Test
    public void updateBranchDetails() throws SQLException {
        LibrarianService libServ = new LibrarianService();
        LibraryBranch libBranch = new LibraryBranch(7, "East Hills", "1234 Burnett Rd.");
        
        boolean didUpdate = libServ.updateBranch(libBranch);

        assertEquals(didUpdate == true, true);
    }
}