package com.ss.lms.tests.dao;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ss.lms.dao.BookCopiesDAO;
import com.ss.lms.models.BookCopies;
import com.ss.lms.service.ConnectionUtil;

import org.junit.Test;

public class TestBookCopiesDAO {

    @Test
    public void testSelectingABook() throws ClassNotFoundException, SQLException {
        ConnectionUtil cUtil = new ConnectionUtil();
        Connection conn = cUtil.getConnection();
        BookCopiesDAO entriesDAO = new BookCopiesDAO(conn);

        List<BookCopies> entries = entriesDAO.readAllEntries();

        assertEquals(entries.get(0) instanceof BookCopies, true);

    }
}