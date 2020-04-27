package com.ss.lms.tests.service.admin;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.List;

import com.ss.lms.models.Author;
import com.ss.lms.service.admin.AdminAuthorService;

import org.junit.Test;

public class TestAdminAuthorService {

    @Test
    public void addAuthorTest() throws SQLException {
        AdminAuthorService aBorServ = new AdminAuthorService();
        Author author = new Author();
        author.setAuthorName("Test Author");
        Integer primaryKey = aBorServ.addAuthor(author);
        System.out.println(primaryKey);

        assertEquals(aBorServ.readAnAuthor(primaryKey).getAuthorName().equals("Test Author"), true);
    }

    @Test
    public void updateAuthorTest() throws SQLException {
        AdminAuthorService aBorServ = new AdminAuthorService();
        Author author = new Author();
        author.setAuthorName("Test Author");
        Integer primaryKey = aBorServ.addAuthor(author);

        Author updateBor = new Author(primaryKey, "Updated Test Author");
        aBorServ.updateAAuthor(updateBor);

        assertEquals(aBorServ.readAnAuthor(primaryKey).getAuthorName().equals("Updated Test Author"), true);
    }

    @Test
    public void deleteAuthorTest() throws SQLException {
        AdminAuthorService aBorServ = new AdminAuthorService();
        Author author = new Author();
        author.setAuthorName("Test Author");
        Integer primaryKey = aBorServ.addAuthor(author);
        author.setAuthorId(primaryKey);
        assertEquals(aBorServ.readAnAuthor(primaryKey).getAuthorName().equals("Test Author"), true);

        aBorServ.deleteAuthor(author);
        Author deletedAuthor = aBorServ.readAnAuthor(primaryKey);

        assertEquals(deletedAuthor == null, true);
    }

    @Test
    public void readAuthorTest() throws SQLException {
        AdminAuthorService aBorServ = new AdminAuthorService();
        Author author = aBorServ.readAnAuthor(1);

        assertEquals(author.getAuthorName().equals("Jack London"), true);
    }

    @Test
    public void readAllAuthorsTest() throws SQLException {
        AdminAuthorService aBorServ = new AdminAuthorService();
        List<Author> authors = aBorServ.readAllAuthors();

        assertEquals(authors.get(0) instanceof Author, true);
    }
}