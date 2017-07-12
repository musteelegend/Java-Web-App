package com.demo.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Database_Connectivity
{
	Connection conn;
	Statement statement;
	
	String url = "jdbc:mysql://localhost:3306/";
	String db_name = "spring";
	String username = "root";
	String password = "";
	
	public Statement do_db_connection()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url+db_name , username , password);
			statement = conn.createStatement();
			return statement;
		}
		catch(Exception e)
		{
			return null;
		}
	}
		
}
