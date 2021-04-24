package app;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author Rehmanali
 * Appointment class
 */
public class Appointment  {

    private int id;                         //ID of the Appointment
    private String title;
    private String description;
    private String location;
	private String type;
    private String startDateTime;
    private String EndDateTime;
    private LocalDate createDate;
    private String createdBy;
    private LocalTime lastUpdate;           //At what time the Appointment was Updated
    private String lastUpdateBy;
    private int customerID;
    private int userID;
    private int contactID;
    private String contact;

    /**
     * Default Constructor of the Appointment
     */
    public Appointment() {

    }//default-constructor

    /**
     *
     * @param title Title of the Appointment
     * @param description Description of the Appointment
     * @param location Location of the Appointment
     * @param type Type of the Appointment
     * @param startDateTime Start Date of the Appointment
     * @param endDateTime End Date of the Appointment
     * @param createDate Create Date of the Appointment
     * @param createdBy Which user created the Appointment
     * @param lastUpdateBy Which user Last Updated The Appointment
     * @param customerID Customer ID in the Appointment
     * @param userID User ID in the Appointment
     * @param contactID Contact ID in the Appointment
     */
    public Appointment(String title, String description,String location, String type, String startDateTime,
                       String endDateTime, LocalDate createDate,
                       String createdBy, String lastUpdateBy, int customerID, int userID,
                       int contactID) {

        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.startDateTime = startDateTime;
        this.EndDateTime = endDateTime;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdateBy = lastUpdateBy;
        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;
        this.contact = "";
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
     * @param id of the Appointment
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return location
     */
    public String getLocation() {
		return location;
	}

    /**
     *
     * @param location
     */
	public void setLocation(String location) {
		this.location = location;
	}

    /**
     *
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return startDateTime
     */
    public String getStartDateTime() {
        return startDateTime;
    }

    /**
     *
     * @param startDate
     */
    public void setStartDateTime(String startDate) {
        this.startDateTime = startDate;
    }

    /**
     *
     * @return endDateTime
     */
    public String getEndDateTime() {
        return EndDateTime;
    }

    /**
     *
     * @param endDate
     */
    public void setEndDateTime(String endDate) {
        EndDateTime = endDate;
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
     * @return customerID
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     *
     * @param customerID
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**
     *
     * @return userID
     */
    public int getUserID() {
        return userID;
    }

    /**
     *
     * @param userID
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     *
     * @return contactID
     */
    public int getContactID() {
        return contactID;
    }

    /**
     *
     * @param contactID
     */
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    /**
     *
     * @return contact
     */
    public String getContact() {
        return contact;
    }

    /**
     *
     * @param contact
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

}//end-of-Appointment-class
