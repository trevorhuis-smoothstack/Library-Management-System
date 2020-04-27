package com.ss.lms.tests.models;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ss.lms.dao.LibraryBranchDAO;
import com.ss.lms.models.LibraryBranch;
import com.ss.lms.service.ConnectionUtil;

import org.junit.Test;

public class LibraryBranchTest {

    @Test
    public void readBranches() throws ClassNotFoundException, SQLException {
        ConnectionUtil cUtil = new ConnectionUtil();
        Connection conn = cUtil.getConnection();
        LibraryBranchDAO libDAO = new LibraryBranchDAO(conn);

        List<LibraryBranch> branches = libDAO.readAllBranches();

        assertEquals(branches.get(0) instanceof LibraryBranch, true);
    }
}