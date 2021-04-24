package app;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/*
 * @author Rehmanali
 */
public class CustomerDAO {

	private PreparedStatement insertCustomerStatement;
	private PreparedStatement deleteCustomerStatement;
	private PreparedStatement getAllCustomerStatement;
	private PreparedStatement updateCustomerStatement;
	private DBConnection dBConnection;
	private Connection connection;

	public CustomerDAO()
	{
		dBConnection = new DBConnection();
		connection = dBConnection.getConnection();

		try
		{
			insertCustomerStatement = connection.prepareStatement("INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, "
					+ "Create_Date, Created_By, Last_Update_By, Division_ID) VALUES(?,?,?,?,?,?,?,?)");
			deleteCustomerStatement = connection.prepareStatement("DELETE FROM customers WHERE Customer_ID = ?");
			getAllCustomerStatement = connection.prepareStatement("SELECT * FROM customers");
			updateCustomerStatement = connection.prepareStatement("UPDATE customers SET Customer_Name=?, "
					+ "Address=?, Postal_Code=?, Phone=?, Create_Date=?, Created_By=?, "
					+ "Last_Update_By=?, Division_ID=? WHERE Customer_ID=?");
		} catch (SQLException e)
		{
			e.printStackTrace();
		}

	}

	public int insertCustomer(Customer c)
	{
		try
		{
			
			insertCustomerStatement.setString(1, c.getName());
			insertCustomerStatement.setString(2,  c.getCountry() + " Address: " 
					+ c.getAddress() + ", " + c.getState());
			insertCustomerStatement.setString(3, c.getPostalCode());
			insertCustomerStatement.setString(4, c.getPhone());
			insertCustomerStatement.setDate(5, Date.valueOf(c.getCreateDate()));
			insertCustomerStatement.setString(6, c.getCreatedBy());
			insertCustomerStatement.setString(7, c.getLastUpdateBy());
			insertCustomerStatement.setInt(8, c.getDivisionId());

			return insertCustomerStatement.executeUpdate();
		} catch (SQLException e)
		{
			System.err.println("Cannot insert customer " + e.getMessage());
		}
		return 0;
	}

	public int updateCustomer(int id,Customer c)
	{
		try
		{
			updateCustomerStatement.setString(1, c.getName());
			updateCustomerStatement.setString(2, c.getCountry() + " Address: " 
			+ c.getAddress() + ", " + c.getState());
			updateCustomerStatement.setString(3, c.getPostalCode());
			updateCustomerStatement.setString(4, c.getPhone());
			updateCustomerStatement.setDate(5, Date.valueOf(c.getCreateDate()));
			updateCustomerStatement.setString(6, c.getCreatedBy());
			updateCustomerStatement.setString(7, c.getLastUpdateBy());
			updateCustomerStatement.setInt(8, c.getDivisionId());
			
			updateCustomerStatement.setInt(9, id);

			return updateCustomerStatement.executeUpdate();
		} catch (SQLException e)
		{
			System.err.println("Cannot update customer " + e.getMessage());
		}
		return 0;
	}
	
	public int deleteCustomerByID(int id)
	{
		try
		{
			deleteCustomerStatement.setInt(1, id);
			return deleteCustomerStatement.executeUpdate();
		} catch (SQLException e)
		{
			System.err.println("Cannot delete from customers " + e.getMessage());

		}
		return 0;
	}

	public ObservableList<Customer> getAllCustomer()
	{
		ObservableList<Customer> allCustomer = FXCollections.observableArrayList();

		try
		{
			ResultSet result = getAllCustomerStatement.executeQuery();
			while (result.next())
			{
				Customer c = new Customer();

				c.setId(result.getInt(1));
				c.setName(result.getString(2));
				String[] arr = getCountryFromAddress(result.getString(3));
				c.setCountry(arr[0]);
				c.setState(arr[2]);
				c.setAddress(result.getString(3));
				c.setPostalCode(result.getString(4));
				c.setPhone(result.getString(5));
				c.setCreateDate(result.getDate(6).toLocalDate());
				c.setCreatedBy(result.getString(7));
				c.setLastUpdate(result.getTime(8).toLocalTime());
				c.setLastUpdateBy(result.getString(9));
				c.setDivisionId(result.getInt(10));
				
				allCustomer.add(c);

			}

		} 
		catch (SQLException e)
		{
			System.err.println("cannot retrieve customers list " + e.getMessage());
		}
		
		return allCustomer;
	}
	
	private String[] getCountryFromAddress(String address){
		
		String[] country = address.split("Address:");
		String[] state = country[1].split(",");
		String s = country[0];
		String[] arr = new String[3];
		arr[0] = s.substring(0,s.length()-1).trim();
		arr[1] = state[state.length-1].trim();
		arr[2] = state[1].trim();
		
		return arr;
	}
	

}
