package controllers;

import java.io.IOException;
import java.util.Locale;

import app.Driver;
import app.Users;
import app.UsersDAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

public class LoginController{

	@FXML TextField userIdTextField;
	@FXML TextField passwordTextField;
	@FXML Button loginButton;
	@FXML Label locationLabel;
	private UsersDAO userDAO;
	private ObservableList<Users> userList;
	private Driver driver;
	private Stage stage;

	
	@FXML public void initialize() {

		userDAO = new UsersDAO();
		userList = userDAO.getAllUsers();
		driver = new Driver();
		stage = driver.getStage();		
		
		Locale locale = Locale.getDefault();
		System.out.println("Country: " + locale.getCountry());
		locationLabel.setText("Your Location: " + locale.getCountry());
		
		loginButton.setOnAction(e -> {
			validateLogin();
		});
			
	}
	
	private void validateLogin() {
		
		if((userIdTextField.getText().isBlank())) {
			showAlert("Please Enter Valid ID");
		}else if(passwordTextField.getText().isBlank()) {
			showAlert("Please Enter Valid Password");
		}else {
			
			int id = 0; 
			
			try {
				
				id = Integer.parseInt(userIdTextField.getText());
				if(isValid(id, passwordTextField.getText())) {
					try {
						Users u = new Users();
						Driver.user = userDAO.getSingleUser(id);
						System.out.println("User Name: " + Driver.user);
						driver.loadMainDashboard();
					} catch (IOException e) {
						
						System.err.println(e.getMessage());
						e.printStackTrace();
					
					}
				}else {
					showAlert("Enter Correct Value");
				}
			}catch (NumberFormatException e) {
				showAlert("Enter a valid ID");
			}
			
		}
		
	}//validateLogin
	
	private boolean isValid(int id,String password) {
		
		boolean isValid = false;
		
		for (Users users : userList) {
			if(users.getUserID() == id && users.getPassword().equals(password) ) {
				isValid = true;
			}//if
		}
		
		return isValid;
		
	}//isValid

	private void showAlert(String message) {

		Alert a = new Alert(AlertType.ERROR);
		a.setContentText(message);
		a.show();
		
	}
	
	public void setDriver(Driver d) {
		this.driver = d;
	}

}//LoginController
