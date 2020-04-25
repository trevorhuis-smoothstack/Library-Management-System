package com.ss.lms.service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
	
	public Connection getConnection(){
		Connection conn = null;
		Properties prop = new Properties();
		try(InputStream input = new FileInputStream("LMS/resources/config/lms.properties")){
			prop.load(input);
			System.out.println("Loaded property values."+prop.get("user"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Class.forName((String) prop.get("driver"));
			conn = DriverManager.getConnection((String)prop.get("url"), (String)prop.get("user"), (String)prop.get("password"));
			conn.setAutoCommit(Boolean.FALSE);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Error connecting to the database. We can not fulfill your request at this time.");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
}
