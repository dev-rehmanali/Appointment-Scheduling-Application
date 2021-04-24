package app;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UsersDAO {


	private DBConnection dBConnection;
	private Connection connection;
	private PreparedStatement getUsersStatement;
	private PreparedStatement addUsersStatement;
	private PreparedStatement getSingleUserStatement;
	
	public UsersDAO() {
		
		dBConnection = new DBConnection();
		connection = dBConnection.getConnection();
		
		try {
		
			getUsersStatement = connection.prepareStatement("SELECT * FROM users WHERE 1");
			addUsersStatement = connection.prepareStatement("INSERT INTO users (User_Name, Password, Create_Date, Created_By, "
					+ "Last_Updated_By) VALUES (?,?,?,?,?)");
			getSingleUserStatement = connection.prepareStatement("SELECT * FROM users WHERE User_ID=?");
			
		}catch (SQLException e) {
			
			System.err.println(e.getMessage());
		
		}
	}//Constructor
	
	public int addUsers(Users u) {
		try {
			
			addUsersStatement.setString(1, u.getUserName());
			addUsersStatement.setString(2, u.getPassword());
			addUsersStatement.setDate(3, Date.valueOf(u.getCreateDate()));
			addUsersStatement.setString(4, u.getCreatedBy());
			addUsersStatement.setString(5, u.getLastUpdatedBy());

			return addUsersStatement.executeUpdate();
			
		} catch (SQLException e) {
			
			System.err.println("Cannot insert customer " + e.getMessage());
		
		}
		
		return 0;
	}
	
	public ObservableList<Users> getAllUsers() {
		
		ObservableList<Users> userList = FXCollections.observableArrayList();
		
		Users u = new Users();
		
		try {
			
			ResultSet result = getUsersStatement.executeQuery();
			
			while (result.next()) {

				u.setUserID(result.getInt(1));
				u.setUserName(result.getString(2));
				u.setPassword(result.getString(3));
				u.setCreateDate(result.getDate(4).toLocalDate());
				u.setCreatedBy(result.getString(5));
				u.setLastUpdate(result.getTime(6).toLocalTime());				
				
				userList.add(u);
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return userList;
		
	}//getAllUsers
	
	public Users getSingleUser(int id) {
		
		Users u = new Users();
		
		try {
		
			getSingleUserStatement.setInt(1, id);
			
			ResultSet result = getUsersStatement.executeQuery();
			
			if (result.next()) {

				u.setUserID(result.getInt(1));
				u.setUserName(result.getString(2));
				u.setPassword(result.getString(3));
				u.setCreateDate(result.getDate(4).toLocalDate());
				u.setCreatedBy(result.getString(5));
				u.setLastUpdate(result.getTime(6).toLocalTime());				
				
			}//if
			
		} catch (Exception e) {
			System.err.println("Cannot get Single User " + e.getMessage());
		}
		
		return u;
		
	}//getSingleUser
	
	
	
	

}
