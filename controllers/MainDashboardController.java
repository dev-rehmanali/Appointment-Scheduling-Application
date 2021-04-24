package controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

import app.*;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainDashboardController {

	@FXML private Tab homeTab;

	@FXML private Label homeTypeValueFormal;
	@FXML private Label homeTypeValueInformal;
	@FXML private Label homeByMonthJanuary;
	@FXML private Label homeByMonthFebruary;
	@FXML private Label homeByMonthMarch;
	@FXML private Label homeByMonthApril;
	@FXML private Label homeByMonthMay;
	@FXML private Label homeByMonthJune;
	@FXML private Label homeByMonthJuly;
	@FXML private Label homeByMonthAugust;
	@FXML private Label homeByMonthSeptember;
	@FXML private Label homeByMonthOctober;
	@FXML private Label homeByMonthNovember;
	@FXML private Label homeByMonthDecember;
	@FXML private Label appointmentsByContact;

	@FXML TableView<Appointment> jhoneTableView;
	@FXML TableColumn<Appointment, Integer> jhoneTableViewColumnID;
	@FXML TableColumn<Appointment, String> jhoneTableViewColumnTitle;
	@FXML TableColumn<Appointment, String> jhoneTableViewColumnDescription;
	@FXML TableColumn<Appointment, String> jhoneTableViewColumnType;
	@FXML TableColumn<Appointment, String> jhoneTableViewColumnStartDateTime;
	@FXML TableColumn<Appointment, String> jhoneTableViewColumnEndDateTime;
	@FXML TableColumn<Appointment, Integer> jhoneTableViewColumnCustomerID;

	@FXML TableView<Appointment> smithTableView;
	@FXML TableColumn<Appointment, Integer> smithTaleColumnAppointmentID;
	@FXML TableColumn<Appointment, String> smithTaleCoulumnAppointmentTitle;
	@FXML TableColumn<Appointment, String> smithTaleCoulumnAppointmentDescription;
	@FXML TableColumn<Appointment, String> smithTaleCoulumnAppointmentType;
	@FXML TableColumn<Appointment, String> smithTaleCoulumnAppointmentStartDateTime;
	@FXML TableColumn<Appointment, String> smithTaleCoulumnAppointmentEndDateTime;
	@FXML TableColumn<Appointment, Integer> smithTaleCoulumnAppointmentCustomerID;

	@FXML TableView<Appointment> loraTableView;
	@FXML TableColumn<Appointment, Integer> loraTableColumnAppointmentID;
	@FXML TableColumn<Appointment, String> loraTableColumnAppointmentTitle;
	@FXML TableColumn<Appointment, String> loraTableColumnAppointmentDescription;
	@FXML TableColumn<Appointment, String> loraTableColumnAppointmentType;
	@FXML TableColumn<Appointment, String> loraTableColumnAppointmentStartDateTime;
	@FXML TableColumn<Appointment, String> loraTableColumnAppointmentEndDateTime;
	@FXML TableColumn<Appointment, Integer> loraTableColumnAppointmentCustomerID;
	
	@FXML TableView<Appointment> appointmentsFilterTableView;
	@FXML TableColumn<Appointment, Integer> appointmentsFilterID;
	@FXML TableColumn<Appointment, String> appointmentsFilterTitle;
	@FXML TableColumn<Appointment, String> appointmentsFilterDescription;
	@FXML TableColumn<Appointment, String> appointmentsFilterLocation;
	@FXML TableColumn<Appointment, String> appointmentsFilterContact;
	@FXML TableColumn<Appointment, String> appointmentsFilterType;
	@FXML TableColumn<Appointment, String> appointmentsFilterStartDateTime;
	@FXML TableColumn<Appointment, String> appointmentsFilterEndDateTime;
	@FXML TableColumn<Appointment, Integer> appointmentsFilterCustomerID;
	@FXML ToggleButton appointmentsFilterMonthToggleButton;
	@FXML ToggleButton appointmentsFilterWeekToggleButton;
	@FXML ToggleButton appointmentsFilterAllToggleButton;
	@FXML ToggleGroup filterAppointmentsToggleGroup;
	
	@FXML TableView<Customer> customersTableView;
	@FXML TableColumn<Customer, Integer> customerID;
	@FXML TableColumn<Customer, String> customersName;
	@FXML TableColumn<Customer, String> customerCountry;
	@FXML TableColumn<Customer, String> customerProvinceState;
	@FXML TableColumn<Customer, String> customersAddress;
	@FXML TableColumn<Customer, String> customersPostalCode;
	@FXML TableColumn<Customer, String> customersPhone;
	
	@FXML Button addAppointment;
	@FXML Button updateAppointment;
	@FXML Button deleteAppointment;
	
	@FXML Button addCustomer;
	@FXML Button updateCustomer;
	@FXML Button deleteCustomer;
	
	private Driver driver;
	private ObservableList<Appointment> appointmentsList;
	private AppointmentDAO appointmentDAO;
	private ContactDAO contactDAO;
	private CustomerDAO customerDAO;
	private ObservableList<Customer> customersList;
	private Appointment appointment;
	AddAppointmentController acc;


	@FXML public void initialize() {

		appointmentDAO = new AppointmentDAO();
		contactDAO  = new ContactDAO();
		customerDAO = new CustomerDAO();
		customersList = customerDAO.getAllCustomer();
		appointment = new Appointment();
		acc = new AddAppointmentController();

		loadHome();

		appointmentsList = appointmentDAO.getAllAppointments();

		smithTaleColumnAppointmentID.setCellValueFactory(new PropertyValueFactory<>("id"));
		smithTaleCoulumnAppointmentTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
		smithTaleCoulumnAppointmentDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
		smithTaleCoulumnAppointmentType.setCellValueFactory(new PropertyValueFactory<>("type"));
		smithTaleCoulumnAppointmentStartDateTime.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
		smithTaleCoulumnAppointmentEndDateTime.setCellValueFactory(new PropertyValueFactory<>("EndDateTime"));
		smithTaleCoulumnAppointmentCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));

		loadHome();

		jhoneTableViewColumnID.setCellValueFactory(new PropertyValueFactory<>("id"));
		jhoneTableViewColumnTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
		jhoneTableViewColumnDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
		jhoneTableViewColumnType.setCellValueFactory(new PropertyValueFactory<>("type"));
		jhoneTableViewColumnStartDateTime.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
		jhoneTableViewColumnEndDateTime.setCellValueFactory(new PropertyValueFactory<>("EndDateTime"));
		jhoneTableViewColumnCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));

		loadHome();

		loraTableColumnAppointmentID.setCellValueFactory(new PropertyValueFactory<>("id"));
		loraTableColumnAppointmentTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
		loraTableColumnAppointmentDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
		loraTableColumnAppointmentType.setCellValueFactory(new PropertyValueFactory<>("type"));
		loraTableColumnAppointmentStartDateTime.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
		loraTableColumnAppointmentEndDateTime.setCellValueFactory(new PropertyValueFactory<>("EndDateTime"));
		loraTableColumnAppointmentCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));

		loadHome();
		
		appointmentsFilterID.setCellValueFactory(new PropertyValueFactory<>("id"));
		appointmentsFilterTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
		appointmentsFilterDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
		appointmentsFilterLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
		appointmentsFilterContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
		appointmentsFilterType.setCellValueFactory(new PropertyValueFactory<>("type"));
		appointmentsFilterStartDateTime.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
		appointmentsFilterEndDateTime.setCellValueFactory(new PropertyValueFactory<>("EndDateTime"));
		appointmentsFilterCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));

		appointmentsFilterTableView.setItems(appointmentsList);

		customerID.setCellValueFactory(new PropertyValueFactory<>("id"));
		customersName.setCellValueFactory(new PropertyValueFactory<>("name"));
		customerCountry.setCellValueFactory(new PropertyValueFactory<>("country"));
		customerProvinceState.setCellValueFactory(new PropertyValueFactory<>("state"));
		customersAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
		customersPostalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
		customersPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));

		customersTableView.setItems(customersList);

		homeTab.setOnSelectionChanged(e ->{
			loadHome();
		});

//		appointmentsFilterMonthToggleButton.
		
		appointmentsFilterMonthToggleButton.setOnAction(e->{
			appointmentsFilterTableView.setItems(appointmentDAO.getAppointmentThisMonth());
		});// Filtering Based on The Month

		appointmentsFilterWeekToggleButton.setOnAction(e->{
			appointmentsFilterTableView.setItems(appointmentDAO.getAppointmentThisWeek());
		});// Filtering Based on The Week

		appointmentsFilterAllToggleButton.setOnAction(e->{
			reloadAppointmentData();
		});// Again Showing All Appointments


		addAppointment.setOnAction(e ->{
			try {
				driver.loadAddAppointment(this);
				reloadAppointmentData();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		updateAppointment.setOnAction(e ->{
			try {

				this.appointment = appointmentsFilterTableView.getSelectionModel().getSelectedItem();
				if(appointment != null){
					driver.loadUpdateAppointment(appointment,this);
					reloadAppointmentData();
				}
				else
				{
					Alert alert = new Alert(AlertType.ERROR);
					alert.setHeaderText("There is Nothing to Update");
					alert.setContentText("Please Select An Item To Be Updated");
					alert.showAndWait();
				}

				reloadAppointmentData();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		deleteAppointment.setOnAction(e ->{	
			int index = appointmentsFilterTableView.getSelectionModel().getSelectedIndex();
			if(index > 0) {
				
				Alert alert = new Alert(AlertType.CONFIRMATION);
			    alert.setTitle("Delete");
			    alert.setHeaderText("Do you want to delete the selected row");
			    alert.setContentText("Press OK to delete Cancel to Abort");

			    Optional<ButtonType> result = alert.showAndWait();
			    if (result.get() == ButtonType.OK){
			    	
			    	appointmentDAO.deleteAppointmentByID(appointmentsFilterTableView.getSelectionModel().getSelectedItem().getId());			
			    	reloadAppointmentData();			    
			    }else{
			    
			    }
				
			}else {

					Alert alert = new Alert(AlertType.ERROR);
					alert.setHeaderText("There is Nothing to Delete");
					alert.setContentText("Please Select An Item To Be Deleted");
					alert.showAndWait();
			}//if
			
		});

		
		addCustomer.setOnAction(e ->{
			try {
				driver.loadAddCustomer(this);
				reloadCustomerData();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		updateCustomer.setOnAction(e ->{
			try {
				Customer customer = customersTableView.getSelectionModel().getSelectedItem();
				if(customer != null){
				 driver.loadUpdateCustomer(customer,this);
				 reloadCustomerData();
				}
				else 
				{
					Alert alert = new Alert(AlertType.ERROR);
					alert.setHeaderText("There is Nothing to Update");
					alert.setContentText("Please Select An Item To Be Updated");
					alert.showAndWait();
				}
				
				reloadCustomerData();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		deleteCustomer.setOnAction(e ->{
			int index = customersTableView.getSelectionModel().getSelectedIndex();
			if(index > 0) {
				
				Alert alert = new Alert(AlertType.CONFIRMATION);
			    alert.setTitle("Delete Row");
			    alert.setHeaderText("Do you want to delete the selected row");
			    alert.setContentText("Press OK to delete Cancel to Abort");

			    Optional<ButtonType> result = alert.showAndWait();
			    if (result.get() == ButtonType.OK){
			    	
			    	customerDAO.deleteCustomerByID(customersTableView.getSelectionModel().getSelectedItem().getId());			
					reloadCustomerData();	
			    
			    }else{
			    
			    }
				
			}else {

					Alert alert = new Alert(AlertType.ERROR);
					alert.setHeaderText("Noting to Delete");
					alert.setContentText("Please Select An Item To Be Deleted");
					alert.showAndWait();
			}//if
			
		});
		
		
		
		
		
	}//end-of-initialize-method
	
	public void reloadCustomerData(){

		customersList = customerDAO.getAllCustomer();
		customersTableView.setItems(customersList);
		
	}
	
	public void reloadAppointmentData(){

//		System.out.println("Appointment Reload is Null: " + appointmentDAO.getAllAppointments());

		appointmentDAO = new AppointmentDAO();

		appointmentsList = appointmentDAO.getAllAppointments();
		appointmentsFilterTableView.setItems(appointmentsList);

	}

	public void loadHome(){
		smithTableView.setItems(appointmentDAO.getAppointmentsByContact("Smith"));
		jhoneTableView.setItems(appointmentDAO.getAppointmentsByContact("Jhone"));
		loraTableView.setItems(appointmentDAO.getAppointmentsByContact("Lora"));

		homeTypeValueFormal.setText("" + appointmentDAO.getCountAppointmentByType("Formal"));
		homeTypeValueInformal.setText("" + appointmentDAO.getCountAppointmentByType("Informal"));
		homeByMonthJanuary.setText(""+appointmentDAO.getCountAppointmentByMonth("01"));
		homeByMonthFebruary.setText(""+appointmentDAO.getCountAppointmentByMonth("02"));
		homeByMonthMarch.setText(""+appointmentDAO.getCountAppointmentByMonth("03"));
		homeByMonthApril.setText(""+appointmentDAO.getCountAppointmentByMonth("04"));
		homeByMonthMay.setText(""+appointmentDAO.getCountAppointmentByMonth("05"));
		homeByMonthJune.setText(""+appointmentDAO.getCountAppointmentByMonth("06"));
		homeByMonthJuly.setText(""+appointmentDAO.getCountAppointmentByMonth("07"));
		homeByMonthAugust.setText(""+appointmentDAO.getCountAppointmentByMonth("08"));
		homeByMonthSeptember.setText(""+appointmentDAO.getCountAppointmentByMonth("09"));
		homeByMonthOctober.setText(""+appointmentDAO.getCountAppointmentByMonth("10"));
		homeByMonthNovember.setText(""+appointmentDAO.getCountAppointmentByMonth("11"));
		homeByMonthDecember.setText(""+appointmentDAO.getCountAppointmentByMonth("12"));

	}

	public void filterAppointmentsByWeek(){
		appointmentsFilterTableView.setItems(appointmentDAO.getAppointmentThisMonth());
	}

	public void setDriver(Driver d) {
		this.driver = d;
	}

	public void setAppointmentToAdd(AddAppointmentController atac){
		this.acc = atac;
	}

}
