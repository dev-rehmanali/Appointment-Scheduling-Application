package controllers;

import app.*;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class AddAppointmentController {

    @FXML
    private TextField appointmentIdTextField;
    @FXML
    private TextField appointmentTitleTextField;
    @FXML
    private TextField appointmentDescriptionTextField;
    @FXML
    private TextField appointmentLocationTextField;
    @FXML
    private ComboBox<String> appointmentContactComboBox;
    @FXML
    private ComboBox<String> appointmentTypeComboBox;
    @FXML
    private JFXDatePicker appointmentStartDateJFXDatePicker;
    @FXML
    private JFXTimePicker appointmentStartTimeJFXTimePicker;
    @FXML
    private JFXDatePicker appointmentEndDateJFXDatePicker;
    @FXML
    private JFXTimePicker appointmentEndTimeJFXTimePicker;
    @FXML
    private ComboBox<String> appointmentCustomerIDComboBox;
    @FXML
    private Button appointmentAddButton;
    @FXML
    private Button appointmentCancelButton;
    private AppointmentDAO appointmentDAO;
    private ContactDAO contactDAO;
    private CustomerDAO customerDAO;
    private ObservableList<Contacts> contactList;
    private ObservableList<String> typeList;
    private ObservableList<Customer> customerList;
    private boolean isUpdateButton = false;
	private MainDashboardController mdc;
	private Appointment appointment;
	private TimeAPI timeAPI;


    @FXML public void initialize(){

        appointmentDAO = new AppointmentDAO();
		contactDAO = new ContactDAO();
		customerDAO = new CustomerDAO();
		contactList = FXCollections.observableArrayList();
		contactList = contactDAO.getAll();
		typeList = FXCollections.observableArrayList();
		typeList.addAll("Formal","Informal");
		customerList = FXCollections.observableArrayList();
		customerList = customerDAO.getAllCustomer();
		appointmentContactComboBox.setItems(this.getContacNumbertList(contactList));
		appointmentTypeComboBox.setItems(typeList);
		appointmentCustomerIDComboBox.setItems(getCustomerIDList(customerList));
		mdc = new MainDashboardController();
		appointment = new Appointment();
		timeAPI = new TimeAPI();

        
        appointmentAddButton.setOnAction(e -> {

			if (this.isUpdateButton){

				int value = -1;
				if(isValidatedInputs()) {
					value = appointmentDAO.updateAppointment(this.appointment.getId(),new Appointment(appointmentTitleTextField.getText(),
							appointmentDescriptionTextField.getText(),appointmentLocationTextField.getText(),
							appointmentTypeComboBox.getSelectionModel().getSelectedItem(), getselectedStartDateTime(),getselectedEndDateTime(),
							LocalDate.now(),Driver.user.getUserName(),Driver.user.getUserName(),
							Integer.valueOf(appointmentCustomerIDComboBox.getSelectionModel().getSelectedItem()),
							Driver.user.getUserID(), getContactID(appointmentContactComboBox.getSelectionModel().getSelectedItem())));

					if(value <= 0) {

						Alert a = new Alert(AlertType.ERROR);
						a.setContentText("Could not Update Data");
						a.show();

					}else {
						Alert a = new Alert(AlertType.INFORMATION);
						a.setContentText("Data Updated Successfully");
						a.show();
						clear();
//						mdc.reloadAppointmentData();
						appointmentAddButton.setText("Add");
						closeStage();
					}
					
				}//validate inputs before entering

				mdc.reloadAppointmentData();
				
			}else {
				int value = -1;
				if(isValidatedInputs()) {
					
					value = appointmentDAO.insertAppointment(new Appointment(appointmentTitleTextField.getText(),
							appointmentDescriptionTextField.getText(),appointmentLocationTextField.getText(),
							appointmentTypeComboBox.getSelectionModel().getSelectedItem(), getselectedStartDateTime(),getselectedEndDateTime(),
							LocalDate.now(),Driver.user.getUserName(),Driver.user.getUserName(),
							Integer.valueOf(appointmentCustomerIDComboBox.getSelectionModel().getSelectedItem()),
							Driver.user.getUserID(), getContactID(appointmentContactComboBox.getSelectionModel().getSelectedItem())));
					
					if(value > 0) {
						Alert a = new Alert(AlertType.INFORMATION);
						a.setContentText("Appointment Added Successfully");
						a.show();
						clear();
						closeStage();

					}else {
						Alert a = new Alert(AlertType.ERROR);
						a.setContentText("Could not Add Appointment");
						a.show();
					}
					
				}//validate inputs before entering

				mdc.reloadAppointmentData();
			
			}//outer-if



        });//Add Appointment Button Action

		appointmentCancelButton.setOnAction(e ->{
			closeStage();
		});

    }// end-of-initialize-method

	private String getselectedStartDateTime(){

    	LocalTime time = appointmentStartTimeJFXTimePicker.getValue();
    	LocalDate date = appointmentStartDateJFXDatePicker.getValue();
    	String timeStr = time + ":00";
    	String dateStr = date.toString();
    	return dateStr + " " + timeStr;

	}
	private String getselectedEndDateTime(){

    	LocalTime time = appointmentEndTimeJFXTimePicker.getValue();
    	LocalDate date = appointmentEndDateJFXDatePicker.getValue();
    	String timeStr = time + ":00";
    	String dateStr = date.toString();
    	return dateStr + " " + timeStr;

	}

	private int getContactID(String name){
		for (Contacts c:contactList) {
			if(c.getContactName().equals(name)){
				return c.getContactID();
			}
		}
		return -1;
	}

	private void closeStage(){
		Stage stage = (Stage) appointmentCancelButton.getScene().getWindow();
		stage.close();
	}

    private boolean isValidatedInputs() {
    	
    	boolean isValidated = false;

    	if(appointmentTitleTextField.getText().isBlank()) {
    		showAlert("Please Enter Valid Title");
    	}else if(appointmentDescriptionTextField.getText().isBlank()) {
    		showAlert("Please Enter Valid Description");
    	}else if(appointmentLocationTextField.getText().isBlank()) {
    		showAlert("Please Enter Valid Location");
    	}else if(appointmentContactComboBox.getSelectionModel().isEmpty()) {
    		showAlert("Please Select Contact");
    	}else if(appointmentTypeComboBox.getSelectionModel().isEmpty()) {
    		showAlert("Please Select a Type");
    	}else if(appointmentStartDateJFXDatePicker.getValue() == null) {
    		showAlert("Please pick a start date");
    	}else if(appointmentStartTimeJFXTimePicker.getValue() == null) {
    		showAlert("Please pick a start time");
    	}else if(appointmentEndDateJFXDatePicker.getValue() == null) {
    		showAlert("Please pick a end date");
    	}else if(appointmentEndTimeJFXTimePicker.getValue() == null) {
    		showAlert("Please pick a end time");
    	}else if(appointmentCustomerIDComboBox.getSelectionModel().isEmpty()) {
    		showAlert("Please Select a Customer ID");
    	}else if(isMeetingCollapse()) {
    		showAlert("Meeting Time Collapse\n"
    				+ "Please select another time slot");
    	}else if(isStartEndEqual()) {
    		showAlert("Meeting start and end time are equal \n"
    				+ "Please select different times");
    	}else if(isEndBeforeStart()) {
    		showAlert("Your selected end time is before start\n"
    				+ "Please select right time slot");
    	}else {

    		
        	timeAPI.setLocalStr(getselectedStartDateTime());
        	java.util.Date date = timeAPI.getEstDate();
    		
    		if(timeAPI.isDateInBusinessHours(date)) {
        		showAlert("Your selected time is in EST bussines hours(08:00 to 10:00)\n"
        				+ "Please Select a time outside of bussines hours");
        	}else if(timeAPI.isWeekend(date, timeAPI.getEstFormatter().getTimeZone())) {
        		showAlert("Your selected date is a weekend\n"
        				+ "Please select another date");
        	}else{
				isValidated = true;
			}
    		
    	}
    	
    	return isValidated;
  
    }

    private boolean isMeetingCollapse() {

    	timeAPI.setLocalStr(getselectedStartDateTime());
    	java.util.Date start = timeAPI.getLocalDate();
    	timeAPI.setLocalStr(getselectedEndDateTime());
    	java.util.Date end = timeAPI.getLocalDate();
    	boolean isCollapse = false;
    	
    	ObservableList<Appointment> listAp = appointmentDAO.getAllAppointments();
    	for (Appointment appointment : listAp) {
    		
        	timeAPI.setLocalStr(appointment.getStartDateTime());
        	java.util.Date apStart = timeAPI.getLocalDate();
        	timeAPI.setLocalStr(appointment.getStartDateTime());
        	java.util.Date apEnd = timeAPI.getLocalDate();
        	
        	if ((start.after(apStart) && start.before(apEnd)) || (end.after(apStart) && end.before(apEnd))) {
				isCollapse = true;
				break;
			}//if
        	
		}//for
    	
    	return isCollapse;
    	
    }//isMeetingCollapse
    
    private boolean isEndBeforeStart() {
    	
    	timeAPI.setLocalStr(getselectedStartDateTime());
    	java.util.Date start = timeAPI.getLocalDate();
    	timeAPI.setLocalStr(getselectedEndDateTime());
    	java.util.Date end = timeAPI.getLocalDate();
    	
    	return end.before(start);
    	
    }

    private boolean isStartEndEqual() {
    	
    	timeAPI.setLocalStr(getselectedStartDateTime());
    	java.util.Date start = timeAPI.getLocalDate();
    	timeAPI.setLocalStr(getselectedEndDateTime());
    	java.util.Date end = timeAPI.getLocalDate();
    	
    	boolean isEqual = false;
    	
    	if (start.compareTo(end)==0) {
			isEqual = true;
		}
    	
    	return isEqual;
    	
    }
    
    private void showAlert(String message) {

		Alert a = new Alert(AlertType.ERROR);
		a.setContentText(message);
		a.show();
		
	}
    
    private ObservableList<String> getContacNumbertList(ObservableList<Contacts> contactList) {
    	
    	ObservableList<String> contactNameList = FXCollections.observableArrayList();
    	
    	for (Contacts contacts : contactList) {
			contactNameList.add(contacts.getContactName());
		}
    	
    	return contactNameList;
    }
    
    private ObservableList<String> getCustomerIDList(ObservableList<Customer> customerList) {
    	
    	ObservableList<String> customerIDList = FXCollections.observableArrayList();
    	
    	for (Customer customer: customerList) {
			customerIDList.add(String.valueOf(customer.getId()));
		}
    	
    	return customerIDList;
    }

	public void setAppointmentToUpdate(Appointment appointment,MainDashboardController md) {

		this.appointment = appointment;
		this.isUpdateButton = true;
		mdc = md;
		this.timeAPI.setLocalStr(appointment.getStartDateTime());
		
			appointmentAddButton.setText("Update");
			appointmentTitleTextField.setText(appointment.getTitle());
			appointmentDescriptionTextField.setText(appointment.getDescription());
			appointmentLocationTextField.setText(appointment.getLocation());
			appointmentContactComboBox.setValue(appointment.getContact());
			appointmentTypeComboBox.setValue(appointment.getType());
			appointmentStartDateJFXDatePicker.setValue(timeAPI.getPickerLocalDateOFLocalDate());
			appointmentStartTimeJFXTimePicker.setValue(timeAPI.getPickerLocalTimeOFLocalDate());
			this.timeAPI.setLocalStr(appointment.getEndDateTime());
			appointmentEndDateJFXDatePicker.setValue(timeAPI.getPickerLocalDateOFLocalDate());
			appointmentEndTimeJFXTimePicker.setValue(timeAPI.getPickerLocalTimeOFLocalDate());
			appointmentCustomerIDComboBox.setValue("" + appointment.getCustomerID());

	}

    private void clear() {
    	
    	appointmentIdTextField.clear();
    	appointmentTitleTextField.clear();
    	appointmentDescriptionTextField.clear();
    	appointmentLocationTextField.clear();
    	appointmentContactComboBox.getSelectionModel().clearSelection();
    	appointmentTypeComboBox.getSelectionModel().clearSelection();
    	appointmentStartDateJFXDatePicker.setValue(null);
    	appointmentStartTimeJFXTimePicker.setValue(null);
    	appointmentEndDateJFXDatePicker.setValue(null);
    	appointmentEndTimeJFXTimePicker.setValue(null);
    	appointmentCustomerIDComboBox.getSelectionModel().clearSelection();
    }

    public void setMainDashboardController(MainDashboardController mdc){
    	this.mdc = mdc;
	}
    
    
    
    
    
}