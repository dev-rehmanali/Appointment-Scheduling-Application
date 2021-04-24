package app;

/**
 * @author Rehmanali
 * Contacts class
 */
public class Contacts {

	private int contactID;  				//Id of the Contact
	private String contactName;
	private String email;

	/**
	 * Default-constructor of the Contacts
	 */
	public Contacts() {

	}//default-constructor

	/**
	 *
	 * @return contact in string
	 */
	@Override
	public String toString() {
		return "Contacts{" +
				"contactID=" + contactID +
				", contactName='" + contactName + '\'' +
				", email='" + email + '\'' +
				'}';
	}

	/**
	 *
	 * @param contactName
	 * @param email
	 */
	public Contacts(String contactName, String email) {

		this.contactID = contactID;
		this.contactName = contactName;
		this.email = email;
	}//overloaded-constructor

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
	 * @return contactName
	 */
	public String getContactName() {
		return contactName;
	}

	/**
	 *
	 * @param contactName
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	/**
	 *
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 *
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	

}
