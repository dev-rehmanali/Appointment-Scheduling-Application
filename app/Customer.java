package app;

import java.time.LocalDate;
import java.time.LocalTime;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Rehmanali
 * Customer class
 */
public class Customer {

	private int id;						//ID of the customer
	private String name;
	private String country;
	private String state;
	private String address;
	private String postalCode;
	private String phone;
	private LocalDate createDate;
	private String createdBy;
	private LocalTime lastUpdate;		//last update
	private String lastUpdateBy;
	private int divisionId;

	/**
	 * Default-constructor of the Customer class
	 */
	public Customer() {

	}

	/**
	 *
	 * @param name name of customer
	 * @param country country of customer
	 * @param state state of customer
	 * @param address address of customer
	 * @param postalCode postalCode of customer
	 * @param phone phone of customer
	 * @param createDate createDate of customer
	 * @param createdBy createdBy  user
	 * @param lastUpdateBy lastUpdatedBy user
	 * @param divisionId divisionID of the state
	 */
	public Customer(String name,String country,String state, String address, String postalCode, String phone,LocalDate createDate, String createdBy, String lastUpdateBy, int divisionId) {

		this.name = name;
		this.country = country;
		this.state = state;
		this.address = address;
		this.postalCode = postalCode;
		this.phone = phone;
		this.createDate = createDate;
		this.createdBy = createdBy;
		this.lastUpdateBy = lastUpdateBy;
		this.divisionId = divisionId;
	}

	/**
	 *
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 *
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 *
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 *
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 *
	 * @return country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 *
	 * @param country
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 *
	 * @return state
	 */
	public String getState() {
		return state;
	}

	/**
	 *
	 * @param state
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 *
	 * @return address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 *
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 *
	 * @return postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 *
	 * @param postalCode
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 *
	 * @return phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 *
	 * @param phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 *
	 * @return createDate
	 */
	public LocalDate getCreateDate() {
		return createDate;
	}

	/**
	 *
	 * @param createDate
	 */
	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}

	/**
	 *
	 * @return createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 *
	 * @param createdBy
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	/**
	 * 
	 * @return lastUpdate
	 */
	public LocalTime getLastUpdate() {
		return lastUpdate;
	}
	/**
	 * 
	 * @param lastUpdate
	 */
	public void setLastUpdate(LocalTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}


	/**
	 *
	 * @return lastUpdatedBy
	 */
	public String getLastUpdateBy() {
		return lastUpdateBy;
	}

	/**
	 *
	 * @param lastUpdateBy
	 */
	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	/**
	 *
	 * @return divisionID
	 */
	public int getDivisionId() {
		return divisionId;
	}

	/**
	 *
	 * @param divisionId
	 */
	public void setDivisionId(int divisionId) {
		this.divisionId = divisionId;
	}

}
