package com.ss.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.lms.models.Book;

public class BookDAO extends BaseDAO<Book> {
    public BookDAO(Connection conn) {
        super(conn);
    }

    public void addBook(Book book) throws ClassNotFoundException, SQLException {
        save("INSERT INTO tbl_book (authorName) VALUES (?)", new Object[] { book.getTitle() });
    }

    public void updateBook(Book book) throws ClassNotFoundException, SQLException {
        save("UPDATE tbl_book SET title = ? WHERE pubId = ?",
                new Object[] { book.getTitle(), book.getPublisherId() });
    }

    public void deleteBook(Book book) throws ClassNotFoundException, SQLException {
        save("DELETE FROM tbl_book WHERE authorId = ?", new Object[] { book.getBookId() });
    }

    public List<Book> readAllBooks() throws ClassNotFoundException, SQLException {
        return read("SELECT * FROM tbl_book", null);
    }

    public List<Book> readAllBooksWithSearch(String search) throws SQLException {
        return read("SELECT * FROM tbl_book WHERE title LIKE ?;", new Object[] {true, search });
    }

    public Book readABookById(Integer bookId) throws SQLException {
        List<Book> books = read("SELECT * FROM tbl_book WHERE bookId = ?;", new Object[] { bookId });
        return books.get(0);
    }

    @Override
    public List<Book> extractData(ResultSet rs) throws SQLException {
        List<Book> books = new ArrayList<>();
        while (rs.next()) {
            Book book = new Book();
            book.setBookId(rs.getInt("bookId"));
            book.setTitle(rs.getString("title"));
            book.setPublisherId(rs.getInt("pubId"));
            books.add(book);
        }
        return books;
    }
}