package app;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author Rehmanali
 * Users class
 */
public class Users {

	private int userID;							//Users ID
	private String userName;
	private String password;
	private LocalDate createDate;
	private String createdBy;
	private LocalTime lastUpdate;
	private String lastUpdatedBy;

	/**
	 * Default-constructor of the Users
	 */
	public Users() {
		
	}//default-constructor

	/**
	 *
	 * @param userID
	 * @param userName
	 * @param password
	 * @param createDate
	 * @param createdBy
	 * @param lastUpdate
	 * @param lastUpdatedBy
	 */
	public Users(int userID, String userName, String password, LocalDate createDate, String createdBy,
			LocalTime lastUpdate, String lastUpdatedBy) {
		super();
		this.userID = userID;
		this.userName = userName;
		this.password = password;
		this.createDate = createDate;
		this.createdBy = createdBy;
		this.lastUpdate = lastUpdate;
		this.lastUpdatedBy = lastUpdatedBy;
	}//overloaded-constructor

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
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 *
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 *
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 *
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
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
	 * @return
	 */
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	/**
	 *
	 * @param lastUpdatedBy
	 */
	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

}
