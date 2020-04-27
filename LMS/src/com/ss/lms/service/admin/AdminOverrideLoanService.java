package com.ss.lms.service.admin;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.ss.lms.dao.BookLoanDAO;
import com.ss.lms.models.BookLoan;
import com.ss.lms.service.ConnectionUtil;

public class AdminOverrideLoanService {
    public ConnectionUtil connUtil = new ConnectionUtil();

    public void addAWeekToALoan(BookLoan loan) throws SQLException{
        Connection conn = null;
        try {
            conn = connUtil.getConnection();
            BookLoanDAO loanDAO = new BookLoanDAO(conn);
            LocalDateTime timeTochange = loan.getDueDate().toLocalDateTime();
            timeTochange = timeTochange.plusDays(7);
            Timestamp newTime = Timestamp.valueOf(timeTochange);
            loan.setDueDate(newTime);
            loanDAO.updateBookLoan(loan);
            conn.commit();
        } catch ( ClassNotFoundException | SQLException e) {
            System.out.println("We could not update that loan.");
            conn.rollback();
        } finally {
			if(conn!=null){
				conn.close();
			}
		}
    }

}