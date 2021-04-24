package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/*
 * @author Rehmanali
 */
public class DBConnection
{
	private Connection connection;
	private final static String  DATABASE_URL = "jdbc:mysql://localhost/fxdb2?serverTimezone=UTC";
	
	public DBConnection() 
	{
		try
		{
			connection = DriverManager.getConnection(DATABASE_URL, "root","");
			System.out.println("Successfully Connected To DB");
		} 
		catch (SQLException e)
		{
			System.err.println("Cannot connect to DB: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public Connection getConnection()
	{
		return connection;
	}

	@Override
	protected void finalize() throws Throwable
	{
		connection.close();
	}
	
	

}