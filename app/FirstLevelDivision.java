package app;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author Rehmanali
 * FirstLevelDivision class
 */
public class FirstLevelDivision {

	private int divisionID;
	private String division;
	private LocalDate createDate;
	private String createdBy;
	private LocalTime lastUpdate;
	private String lastUpdatedBy;
	private int countryID;

	/**
	 * Default-constructor of FirstLevelDivision
	 */
	public FirstLevelDivision() {

	}//default-constructor

	/**
	 *
	 * @param divisionID
	 * @param division
	 */
	public FirstLevelDivision(int divisionID, String division) {

		this.divisionID = divisionID;
		this.division = division;
	}//overloaded-constructor-1

	/**
	 *
	 * @param division
	 * @param createDate
	 * @param createdBy
	 * @param lastUpdate
	 * @param lastUpdatedBy
	 * @param countryID
	 */
	public FirstLevelDivision(String division, LocalDate createDate, String createdBy,
			LocalTime lastUpdate, String lastUpdatedBy, int countryID) {

		this.division = division;
		this.createDate = createDate;
		this.createdBy = createdBy;
		this.lastUpdate = lastUpdate;
		this.lastUpdatedBy = lastUpdatedBy;
		this.countryID = countryID;
	}//overloaded-constructor-2

	/**
	 *
	 * @return divisionID
	 */
	public int getDivisionID() {
		return divisionID;
	}

	/**
	 *
	 * @param divisionID
	 */
	public void setDivisionID(int divisionID) {
		this.divisionID = divisionID;
	}

	/**
	 *
	 * @return division
	 */
	public String getDivision() {
		return division;
	}

	/**
	 *
	 * @param division
	 */
	public void setDivision(String division) {
		this.division = division;
	}

	/**
	 *
	 * @return
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
	 * @return lastUpdatedBy
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

	/**
	 *
	 * @return countryID
	 */
	public int getCountryID() {
		return countryID;
	}

	/**
	 *
	 * @param countryID
	 */
	public void setCountryID(int countryID) {
		this.countryID = countryID;
	}
	
	


}
