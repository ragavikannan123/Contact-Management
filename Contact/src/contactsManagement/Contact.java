package contactsManagement;

import java.sql.SQLException;

public class Contact {
	static int id = 0;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String address;
	ContactManager contactManager =  new ContactManager();
	
	Contact(String firstName, String lastName, String email, String phone, String address) throws SQLException {
		id ++;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.address = address;
		contactManager.add(this,id);
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPhone (String phone) {
		this.phone = phone;
	}
	
	public void setAddress (String address) {
		this.address = address;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public String getAddress() {
		return address;
	}
}
