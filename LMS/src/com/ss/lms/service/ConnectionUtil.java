/**
 * 
 */
package com.ss.lms.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author ppradhan
 *
 */
public class ConnectionUtil {
	public final String driver = "com.mysql.cj.jdbc.Driver";
	public final String url = "jdbc:mysql://localhost:3306/library?useSSL=false";
	public final String user = "root";
	public final String password = "texas";

	public Connection getConnection(){
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(Boolean.FALSE);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("xxx");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
}