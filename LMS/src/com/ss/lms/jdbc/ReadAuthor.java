package com.ss.lms.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReadAuthor {
    public static final String driver = "com.mysql.cj.jdbc.Driver";
	public static final String url = "jdbc:mysql://localhost:3306/library?useSSL=false";
	public static final String user = "root";
    public static final String password  = "texas";
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
		//1. Register driver.
		Class.forName(driver);
		//2. Create a connection
		Connection conn = DriverManager.getConnection(url, user, password);
		//3. Create a statement Object
		Statement stmt = conn.createStatement();
		//4. Execute
		String sql = "SELECT * FROM tbl_author";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next()){
			System.out.println("Author ID: "+rs.getInt("authorId"));
			System.out.println("Author Name: "+rs.getString("authorName"));
			System.out.println("----------------");
		}
	}

}