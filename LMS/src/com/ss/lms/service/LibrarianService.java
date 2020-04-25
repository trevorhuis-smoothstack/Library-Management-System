package com.ss.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ss.lms.dao.LibraryBranchDAO;
import com.ss.lms.models.BookCopies;
import com.ss.lms.models.LibraryBranch;

public class LibrarianService {
    private final TerminalService terminal = new TerminalService();
    public ConnectionUtil connUtil = new ConnectionUtil();

    public boolean updateBranchWithTerminal(List<LibraryBranch> branches) throws SQLException {
        LibraryBranch lb = terminal.LibrarianBranchMenu(branches);

        return updateBranch(lb);
    }

    public boolean updateCopies(BookCopies entry) {
        return true;
    }

    public boolean updateBranch(LibraryBranch branch) throws SQLException {
        Connection conn = null;
        try {
            conn = connUtil.getConnection();
            LibraryBranchDAO libDAO = new LibraryBranchDAO(conn);
            libDAO.updateBranch(branch);
            conn.commit();
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            conn.rollback();
            return false;
        } finally {
			if(conn!=null){
				conn.close();
			}
		}
    }
}