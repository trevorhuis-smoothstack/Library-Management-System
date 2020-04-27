package com.ss.lms.tests.service.admin;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.List;

import com.ss.lms.models.Author;
import com.ss.lms.models.Book;
import com.ss.lms.models.Genre;
import com.ss.lms.models.Publisher;
import com.ss.lms.service.admin.AdminBookService;

import org.junit.Test;

public class TestAdminBookService {

    @Test
    public void addBookTest() throws SQLException {
        AdminBookService aBookServ = new AdminBookService();
        Book book = new Book("Test Book", 6);
        Author author = new Author(25, "Test Author");
        Publisher publisher = new Publisher(6,	"Puppy Publishers",	"218. Cuteness St.", "713-212-6123");
        Genre genre = new Genre(1, "Adventure");
        Integer primaryKey = aBookServ.addBook(book);
        aBookServ.addBookReferences(book, author, publisher, genre);

        assertEquals(aBookServ.readABook(primaryKey).getTitle().equals("Test Book"), true);
    }

    @Test
    public void updateBookTest() throws SQLException {
        AdminBookService aBookServ = new AdminBookService();
        Book book = new Book("Test Book", 6);
        Author author = new Author(25, "Test Author");
        Publisher publisher = new Publisher(6,	"Puppy Publishers",	"218. Cuteness St.", "713-212-6123");
        Genre genre = new Genre(3, "Non-fiction");
        Integer primaryKey = aBookServ.addBook(book);
        aBookServ.addBookReferences(book, author, publisher, genre);

        Book updateBook = new Book(primaryKey, "Updated Test Book", 6);
        aBookServ.updateABook(updateBook);

        assertEquals(aBookServ.readABook(primaryKey).getTitle().equals("Updated Test Book"), true);
    }

    @Test
    public void readBookTest() throws SQLException {
        AdminBookService aBookServ = new AdminBookService();
        Book book = aBookServ.readABook(1);

        assertEquals(book.getTitle().equals("The Adventures of Huckleberry Finn"), true);
    }

    @Test
    public void readAllBooksTest() throws SQLException {
        AdminBookService aBookServ = new AdminBookService();
        List<Book> books = aBookServ.readAllBooks();

        assertEquals(books.get(0) instanceof Book, true);
    }
}