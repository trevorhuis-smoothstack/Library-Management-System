package com.ss.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.lms.models.BookGenre;

public class BookGenreDAO extends BaseDAO<BookGenre> {
    public BookGenreDAO(Connection conn) {
        super(conn);
    }

    public void addBookGenreEntry(BookGenre bookGenre) throws ClassNotFoundException, SQLException{
		save("INSERT INTO tbl_book_genres (genre_id, bookId) VALUES (?, ?)", new Object[] {bookGenre.getGenre_id(), bookGenre.getBookId()});
	}

	public void updateBookGenreByGenre(Integer oldId, Integer newId)  throws ClassNotFoundException, SQLException{
		save("UPDATE tbl_book_genres SET genre_id = ? WHERE genre_id = ?", new Object[] {oldId, newId});
    }
    
    public void updateBookGenreByBook(Integer oldId, Integer newId)  throws ClassNotFoundException, SQLException{
		save("UPDATE tbl_book_genres SET bookId = ? WHERE bookId = ?", new Object[] {oldId, newId});
	}

	public void deleteGenresReferenceByGenre(Integer genre_id)  throws ClassNotFoundException, SQLException{
		save("DELETE FROM tbl_book_genres WHERE genre_id = ?", new Object[]{genre_id});
	}

	public void deleteGenresReferenceByBook(Integer bookId) throws ClassNotFoundException, SQLException {
		save("DELETE FROM tbl_book_genres WHERE bookId = ?", new Object[] { bookId } );
	}
	
	public List<BookGenre> readAllGenreReferences() throws ClassNotFoundException, SQLException{
		return read("SELECT * FROM tbl_book_genres", null);
	}
	

    @Override
    public List<BookGenre> extractData(ResultSet rs) throws SQLException {
        List<BookGenre> entries = new ArrayList<>();
		while(rs.next()){
			BookGenre bookGenre = new BookGenre();
            bookGenre.setGenre_id(rs.getInt("bookGenre_id"));
            bookGenre.setBookId(rs.getInt("bookId"));
			entries.add(bookGenre);
		}
		return entries;
    }
}