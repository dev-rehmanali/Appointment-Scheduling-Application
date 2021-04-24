package controllers;

import java.time.LocalDate;
import java.util.List;

import app.Appointment;
import app.Country;
import app.Customer;
import app.CustomerDAO;
import app.Driver;
import db.CountryStates;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddCustomerController {

	@FXML private TextField customerIdTextField;
	@FXML private TextField customerNameTextField;
	@FXML private ComboBox<String> customerCountriesComboBox;
	@FXML private ComboBox<String> customerStatesComboBox;
	@FXML private TextField customerAddressTextField;
	@FXML private TextField customerPostalCodeTextField;
	@FXML private TextField customerPhoneTextField;
	@FXML private Button customerAddButton;
	@FXML private Button customerCancelButton;
	private CountryStates countryStates;
	private ObservableList<Country> countryList;
	private ObservableList<String> countryNameList;
	private CustomerDAO customerdao;
	private Customer customer;
	private boolean isUpdateButton = false;
	private MainDashboardController mdc;

	@FXML void initialize() {

		countryStates = new CountryStates();
		countryList = FXCollections.observableArrayList();
		countryNameList = FXCollections.observableArrayList();
		customerdao = new CustomerDAO();
		mdc = new MainDashboardController();

		countryNameList.addAll(this.getCountryNames(countryStates.getAllCountries()));
		
		customerCountriesComboBox.setItems(countryNameList);
		
//		Country ComboBox Event Handler
		customerCountriesComboBox.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {

				customerStatesComboBox.setItems(countryStates.getAllStates(countryNameList.indexOf(
						customerCountriesComboBox.getSelectionModel().getSelectedItem())+1));
				
			}
		});// End Country ComboBox Event Handler
		
//		Cancel Button EventHandler
		customerCancelButton.setOnAction(e ->{
			closeStage();
		});// End Cancel Button EventHandler
		
		
		customerAddButton.setOnAction(e -> {
			if (this.isUpdateButton){

				String country = customerCountriesComboBox.getSelectionModel().getSelectedItem();
				int index = countryNameList.indexOf(country);
				String division = customerStatesComboBox.getSelectionModel().getSelectedItem();

				if(validateInputs()) {
					
					int value = customerdao.updateCustomer(customer.getId(),new Customer(customerNameTextField.getText(),
							customerCountriesComboBox.getSelectionModel().getSelectedItem(),
							customerStatesComboBox.getSelectionModel().getSelectedItem(), customerAddressTextField.getText(),
							customerPostalCodeTextField.getText(), customerPhoneTextField.getText(),LocalDate.now(),
							Driver.user.getUserName(), Driver.user.getUserName(), countryStates.getStateID(index+1,division)));

					if(value != 0) {
						Alert a = new Alert(AlertType.INFORMATION);
						a.setContentText("Customer Updated Successfully");
						a.show();
						customerAddButton.setText("Add");
						closeStage();
					}else {
						Alert a = new Alert(AlertType.INFORMATION);
						a.setContentText("Could not Update Customer");
						a.show();
					}//else
					
				}//validate-if
				
				mdc.reloadCustomerData();
				
			}else{

				String country = customerCountriesComboBox.getSelectionModel().getSelectedItem();
				int index = countryNameList.indexOf(country);
				String division = customerStatesComboBox.getSelectionModel().getSelectedItem();

				if(validateInputs()) {
					
					int value = customerdao.insertCustomer(new Customer(customerNameTextField.getText(),country,
							customerStatesComboBox.getSelectionModel().getSelectedItem(), customerAddressTextField.getText(),
							customerPostalCodeTextField.getText(), customerPhoneTextField.getText(),LocalDate.now(),
							Driver.user.getUserName(), Driver.user.getUserName(), countryStates.getStateID(index+1,division)));
					
					if(value != 0) {
						closeStage();
						Alert a = new Alert(AlertType.INFORMATION);
						a.setContentText("Data Inserted Successfully");
						a.show();
					}else {
						Alert a = new Alert(AlertType.INFORMATION);
						a.setContentText("Could not insert data");
						a.show();
					}//else
					
				}//validate-if
				
				
				mdc.reloadCustomerData();

			}

		});
		
		
	}

	private boolean validateInputs() {

			boolean isValid = false;

			if(customerNameTextField.getText().isBlank()) {
				showAlert("Please Enter Valid Name");
			}else if(customerCountriesComboBox.getSelectionModel().isEmpty()) {
				showAlert("Please Select Country");
			}else if(customerStatesComboBox.getSelectionModel().isEmpty()) {
				showAlert("Please Select State");
			}else if(customerAddressTextField.getText().isBlank()) {
				showAlert("Please Enter Address");
			}else if(customerPostalCodeTextField.getText().isBlank()) {
				showAlert("Please Enter Correct Postal Code");
			}else if(customerPhoneTextField.getText().isBlank()) {
				showAlert("Please Enter Contact Number");
			}else {
				isValid = true;
			}
			return isValid;

	}//validateInputs
	
	private void showAlert(String message) {

		Alert a = new Alert(AlertType.ERROR);
		a.setContentText(message);
		a.show();
		
	}

	private ObservableList<String> getCountryNames(List<Country> countryList){

		ObservableList<String> listCountryName = FXCollections.observableArrayList();

		for (int i = 0; i < countryList.size(); i++) {
			listCountryName.add(countryList.get(i).getCountry());
		}
		return listCountryName;
	}

	private void closeStage(){
		Stage stage = (Stage) customerCancelButton.getScene().getWindow();
		stage.close();
	}

	public void setCustomer(Customer c) {
		this.customer = c;
	}


	public void setCustomerToUpdate(Customer customer,MainDashboardController md) {

		this.isUpdateButton = true;
		mdc = md;
		customerAddButton.setText("Update");

		this.customer = customer;
		String[] addressStr = getCountryFromAddress(customer.getAddress());
		customerNameTextField.setText(customer.getName());
		customerCountriesComboBox.setValue(addressStr[0]);
		customerStatesComboBox.setValue(addressStr[1]);
		customerAddressTextField.setText(addressStr[2]);
		customerPostalCodeTextField.setText(customer.getPostalCode());
		customerPhoneTextField.setText(customer.getPhone());

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
	
	public void setMainDashboard(MainDashboardController mdc) {
		this.mdc = mdc;
	}

}
