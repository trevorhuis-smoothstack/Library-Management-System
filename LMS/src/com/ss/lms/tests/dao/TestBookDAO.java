package com.ss.lms.tests.dao;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ss.lms.dao.BookDAO;
import com.ss.lms.models.Book;
import com.ss.lms.service.ConnectionUtil;

import org.junit.Test;

public class TestBookDAO {
    @Test
    public void testBookSearchAll() throws SQLException, ClassNotFoundException {
        ConnectionUtil cUtil = new ConnectionUtil();
        Connection conn = cUtil.getConnection();
        BookDAO bookDAO = new BookDAO(conn);
        List<Book> books = bookDAO.readAllBooks();

        assertEquals(books.get(0) instanceof Book, true);
    }

    @Test
    public void testBookSearchOneBook() throws SQLException, ClassNotFoundException {
        ConnectionUtil cUtil = new ConnectionUtil();
        Connection conn = cUtil.getConnection();
        BookDAO bookDAO = new BookDAO(conn);
        List<Book> books = bookDAO.readAllBooksWithSearch("adventure");

        assertEquals(books.get(0).getPublisherId() == 4, true);
    }


}