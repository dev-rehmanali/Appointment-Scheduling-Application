package app;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author Rehmanali
 * Country class
 */
public class Country {

    private int countryID;						//ID of the country
    private String country;
    private LocalDate createDate;
    private String createdBy;
    private LocalTime lastUpdate;
    private String lastUpdatedBy;

	/**
	 * Default-constructor
	 */
	public Country() {
    	
	}//default-constructor

	/**
	 *
	 * @param countryID country ID
	 * @param country country Name
	 * @param createDate createDate
	 * @param createdBy createdBy
	 * @param lastUpdate lastUpdate
	 * @param lastUpdatedBy lastUpdatedBy
	 */
    public Country(int countryID, String country, LocalDate createDate, String createdBy, LocalTime lastUpdate,
			String lastUpdatedBy) {
	
		this.countryID = countryID;
		this.country = country;
		this.createDate = createDate;
		this.createdBy = createdBy;
		this.lastUpdate = lastUpdate;
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
	 * @return lastUpdtae
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
