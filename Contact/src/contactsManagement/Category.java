package contactsManagement;

import java.sql.SQLException;
import java.util.ArrayList;

public class Category {
	static int id = 0;
	private String name;
	ArrayList<Contact> contactsList = new ArrayList<Contact>();
	CategoryManager categoryManager = new CategoryManager();
	
	Category(String name) throws SQLException {
		id ++;
		this.name = name;
		categoryManager.add(this, id);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	void removeContact(String firstName,String lastName,String email) {
		int index = 0;
		for (int i=0;i<contactsList.size();i++) {
			index = i;
			if (contactsList.get(i).getFirstName().equals(firstName) && contactsList.get(i).getLastName().equals(lastName) && contactsList.get(i).getEmail().equals(email)) {
				break;
			}
		}
		contactsList.remove(index);
	}
	
	void addContact (Contact contact) {
		this.contactsList.add(contact);
	}
	
	ArrayList<Contact> getContacts(){
		return contactsList;
	}
}
