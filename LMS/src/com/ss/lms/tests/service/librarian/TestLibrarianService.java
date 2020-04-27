package com.ss.lms.tests.service.librarian;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.List;

import com.ss.lms.models.Book;
import com.ss.lms.models.BookCopies;
import com.ss.lms.models.LibraryBranch;
import com.ss.lms.service.LibrarianService;

import org.junit.Test;

public class TestLibrarianService {

    @Test
    public void updateBranchTest() throws SQLException {
        LibrarianService libServ = new LibrarianService();
        LibraryBranch libBranch = new LibraryBranch(7, "East Hills", "1234 Burnett Rd.");
        
        boolean didUpdate = libServ.updateBranch(libBranch);

        assertEquals(didUpdate == true, true);
    }

    @Test
    public void getBranchesTest() throws SQLException {
        LibrarianService libServ = new LibrarianService();
        List<LibraryBranch> branches = libServ.getBranches();
        LibraryBranch libBranchTest = new LibraryBranch(7, "East Hills", "1234 Burnett Rd.");

        assertEquals(branches.get(6).equals(libBranchTest), true);
    }

    @Test
    public void getBranchesTestFail() throws SQLException {
        LibrarianService libServ = new LibrarianService();
        List<LibraryBranch> branches = libServ.getBranches();
        LibraryBranch libBranchTest = new LibraryBranch(7, "East Hills False", "124 Burnett Rd.");

        assertEquals(branches.get(6).equals(libBranchTest), false);
    }

    @Test
    public void getBooksWithSearchTest() throws SQLException {
        LibrarianService libServ = new LibrarianService();
        List<Book> books = libServ.getBooksWithSearch("adventure");

        assertEquals(books.get(0).getBookId()==1, true);
    }

    @Test
    public void getCopyAndUpdateTest() throws SQLException, ClassNotFoundException {
        LibrarianService libServ = new LibrarianService();
        BookCopies entryToCreate = new BookCopies(1, 1, 3);
        libServ.updateCopies(entryToCreate);
        // Just created the entry
        BookCopies entry = libServ.getAnEntryOfBookCopies(1, 1);
        assertEquals(entry.getNoOfCopies() == 3, true);

    }

    @Test
    public void getEntryOfBookCopiesTestNotThere() throws SQLException {
        LibrarianService libServ = new LibrarianService();
        // Data doesn't exist
        BookCopies entry = libServ.getAnEntryOfBookCopies(2, 2);
        assertEquals(entry == null, true);
    }

    @Test
    public void updateCopiesTest() throws SQLException {
        LibrarianService libServ = new LibrarianService();
        BookCopies entry = new BookCopies(1, 1, 2);
        libServ.updateCopies(entry);
        // Changed an existing entry
        BookCopies entryReturn = libServ.getAnEntryOfBookCopies(1, 1);
        assertEquals(entryReturn.getNoOfCopies() == 2, true);
    }
}