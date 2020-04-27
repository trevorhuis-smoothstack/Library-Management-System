package com.ss.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.ss.lms.dao.BookCopiesDAO;
import com.ss.lms.dao.BookDAO;
import com.ss.lms.dao.BookLoanDAO;
import com.ss.lms.dao.BorrowerDAO;
import com.ss.lms.models.Book;
import com.ss.lms.models.BookCopies;
import com.ss.lms.models.BookLoan;
import com.ss.lms.models.Borrower;

public class BorrowerService {
    public ConnectionUtil connUtil = new ConnectionUtil();

    public Borrower getBorrower(Integer cardNo) throws SQLException {
        Connection conn = null;
        try {
            conn = connUtil.getConnection();
            BorrowerDAO borDAO = new BorrowerDAO(conn);
            List<Borrower> borrowerList = borDAO.readABorrower(cardNo);
            if(borrowerList.size() == 0) {
                return null;
            }
            return borrowerList.get(0);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
			if(conn!=null){
				conn.close();
			}
		}
    }

    public List<BookLoan> getLoansFromABorrower(Integer cardNo) throws SQLException {
        Connection conn = null;
        try {
            conn = connUtil.getConnection();
            BookLoanDAO loansDAO = new BookLoanDAO(conn);
            List<BookLoan> loans = loansDAO.readAllLoansFromABorrower(cardNo);
            if(loans.size() == 0) {
                return null;
            }
            return loans;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
			if(conn!=null){
				conn.close();
			}
		}
    }

    public boolean returnABook(BookLoan loan) throws SQLException {
        Connection conn = null;
        try {
            conn = connUtil.getConnection();
            BookLoanDAO loansDAO = new BookLoanDAO(conn);
            BookCopiesDAO entriesDAO = new BookCopiesDAO(conn);

            // Add the book to the copies table
            List<BookCopies> entries = entriesDAO.readAnEntry(loan.getBranchId(), loan.getBookId());
            if (entries.size() == 0) {
                BookCopies entry = new BookCopies(loan.getBookId(), loan.getBranchId(), 1);
                entriesDAO.addBookCopiesEntry(entry);
            } else if (entries.size() > 0){
                BookCopies entry = new BookCopies(loan.getBookId(), loan.getBranchId(), (entries.get(0).getNoOfCopies() + 1));
                entriesDAO.updateBookCopiesEntry(entry);
            }

            Timestamp now = Timestamp.from(Instant.now());
            loan.setDateIn(now);
            
            loansDAO.updateBookLoan(loan);
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

    public List<Book> getBookNamesFromLoans(List<BookLoan> loans) throws SQLException {
        Connection conn = null;
        try {
            conn = connUtil.getConnection();
            BookDAO bookDAO = new BookDAO(conn);
            List<Book> books = new ArrayList<>();
            for(BookLoan loan: loans) {
                books.add(bookDAO.readABookById(loan.getBookId()).get(0));
            }
            return books;
        } catch ( SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
			if(conn!=null){
				conn.close();
			}
		}
    }

    public boolean checkOutABook(Integer bookId, Integer branchId, Integer cardNo) throws SQLException {
        Connection conn = null;
        try {
            conn = connUtil.getConnection();
            BookLoanDAO loansDAO = new BookLoanDAO(conn);
            BookCopiesDAO entriesDAO = new BookCopiesDAO(conn);

            // Add the book to the copies table
            List<BookCopies> entries = entriesDAO.readAnEntry(branchId, bookId);
            BookCopies entry = new BookCopies(bookId, branchId, (entries.get(0).getNoOfCopies() - 1));
            entriesDAO.updateBookCopiesEntry(entry);
            
            LocalDateTime weekFromNow = LocalDateTime.now().plusDays(7);
            Timestamp weekFromNowTS = Timestamp.valueOf(weekFromNow);
            Timestamp now = Timestamp.from(Instant.now());

            BookLoan loan = new BookLoan(bookId, branchId, cardNo, now, weekFromNowTS, null);
            
            loansDAO.addBookLoan(loan);
            conn.commit();
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("You have already checked that book out.");
            conn.rollback();
            return false;
        } finally {
			if(conn!=null){
				conn.close();
			}
		}
    }
}