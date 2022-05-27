package com.cts.fw.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public static Connection retrieveConnection() throws SQLException
	{
		new com.mysql.jdbc.Driver();
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/wipro3","root","");
	}
}
