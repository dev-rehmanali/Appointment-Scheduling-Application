package app;

import java.io.IOException;

import controllers.AddAppointmentController;
import controllers.AddCustomerController;
import controllers.LoginController;
import controllers.MainDashboardController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Driver extends Application{

	private Stage primaryStage;
	private Parent rootPane;
	public static Users user;

	public static void main(String[] args) {
		
		launch(args);

	}//main

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		this.primaryStage = primaryStage;
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../controllers/login.fxml"));
		rootPane = loader.load();
		
		LoginController lc = loader.getController();
		lc.setDriver(this);
		
		Scene scene = new Scene(rootPane);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Login");
		primaryStage.show();
	}//start
	
		public void loadMainDashboard() throws IOException {
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../controllers/main12.fxml"));
		rootPane = loader.load();
		
		MainDashboardController mdc = loader.getController();
		mdc.setDriver(this);
		
		Scene scene = new Scene(rootPane);
		this.primaryStage.setScene(scene);
		this.primaryStage.setTitle("Dashboard");
		this.primaryStage.show();
		
	}
	
	public void loadAddCustomer(MainDashboardController mdc) throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../controllers/add-customer.fxml"));
		rootPane = loader.load();

		Scene scene = new Scene(rootPane);
		Stage stage = new Stage();

		stage.initOwner(primaryStage);
		stage.initModality(Modality.WINDOW_MODAL);

		AddCustomerController adc = loader.getController();
		adc.setMainDashboard(mdc);
		
		stage.setScene(scene);
		stage.setTitle("Add Customer");
		stage.show();
		
	}
	
	public void loadUpdateCustomer(Customer customer,MainDashboardController md) throws IOException {
		
//		rootPane = FXMLLoader.load(getClass().getResource("../view/add-customer.fxml"));
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../controllers/add-customer.fxml"));
		rootPane = loader.load();
		
		Scene scene = new Scene(rootPane);
		Stage stage = new Stage();
		
		stage.initOwner(primaryStage);
		stage.initModality(Modality.WINDOW_MODAL);

		AddCustomerController acc = loader.getController();
		acc.setCustomerToUpdate(customer,md);
		
		stage.setScene(scene);
		stage.setTitle("Update Customer");
		stage.show();
		
	}
	
	public void loadAddAppointment(MainDashboardController mdc) throws IOException {
		
//		rootPane = FXMLLoader.load(getClass().getResource("../controllers/add-appointment.fxml"));

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../controllers/add-appointment.fxml"));
		rootPane = loader.load();

		Stage stage = new Stage();
		
		stage.initOwner(primaryStage);
		stage.initModality(Modality.WINDOW_MODAL);

		AddAppointmentController aac = loader.getController();
		aac.setMainDashboardController(mdc);

		Scene scene = new Scene(rootPane);
		stage.setScene(scene);
		stage.setTitle("Add Appointment");
		stage.show();
		
	}
	
	public void loadUpdateAppointment(Appointment appointment,MainDashboardController md) throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../controllers/add-appointment.fxml"));
		rootPane = loader.load();

		Stage stage = new Stage();
		stage.initOwner(primaryStage);
		stage.initModality(Modality.WINDOW_MODAL);

		AddAppointmentController aac = loader.getController();
		aac.setAppointmentToUpdate(appointment,md);

		Scene scene = new Scene(rootPane);
		stage.setScene(scene);
		stage.setTitle("Update Appointment");
		stage.show();
		
	}
	
	public Stage getStage() {
		return primaryStage;
	}
	
}//Class
