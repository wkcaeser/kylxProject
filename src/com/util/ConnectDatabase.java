package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDatabase {
	static final String sqlurl = "jdbc:mysql://localhost:3306/KYLX_DATABASE";
	static final String sqluser = "root";
	static final String sqlpassword = "WKgui000!";
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Connection getConnect() throws SQLException{
		return DriverManager.getConnection(sqlurl, sqluser, sqlpassword);
	}
}