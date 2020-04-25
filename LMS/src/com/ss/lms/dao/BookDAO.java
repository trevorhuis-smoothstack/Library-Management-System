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
        save("INSERT INTO tbl_author (authorName) VALUES (?)", new Object[] { book.getTitle() });
    }

    public void updateBook(Book book) throws ClassNotFoundException, SQLException {
        save("UPDATE tbl_author SET authorName = ? WHERE authorId = ?",
                new Object[] { book.getTitle(), book.getBookId() });
    }

    public void deleteBook(Book book) throws ClassNotFoundException, SQLException {
        save("DELETE FROM tbl_author WHERE authorId = ?", new Object[] { book.getBookId() });
    }

    public List<Book> readAllBooks() throws ClassNotFoundException, SQLException {
        return read("SELECT * FROM tbl_author", null);
    }

    @Override
    public List<Book> extractData(ResultSet rs) throws SQLException {
        List<Book> books = new ArrayList<>();
        while (rs.next()) {
            Book book = new Book();
            book.setBookId(rs.getInt("bookId"));
            book.setTitle(rs.getString("title"));
            books.add(book);
        }
        return books;
    }


}