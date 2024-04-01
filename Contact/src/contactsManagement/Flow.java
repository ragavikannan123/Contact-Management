package contactsManagement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Flow {
	static Scanner input = new Scanner(System.in);
	static DB db = DB.getDB();
	
	public static void main(String[] args) throws SQLException {
		Flow mainFlow = new Flow();
		int user_id = mainFlow.authentication();
		mainFlow.mainMenu(user_id);
	}
	int authentication() throws SQLException {
	    System.out.print("\n1. Signup\n2. Login\nEnter the number: ");
	    int authenticationChoice;
	    do {
	        authenticationChoice = input.nextInt();
	        if (authenticationChoice != 1 && authenticationChoice != 2) {
	            System.out.println("\nInvalid input. Please enter 1 or 2.");
	        }
	    } 
	    while (authenticationChoice != 1 && authenticationChoice != 2);
	    
	    if (authenticationChoice == 1) {
	        signup();
	    } 
	    else {
	        return login();
	    }
	    return 0;
	}

	void signup() {
		System.out.println("\nName: ");
		String name = input.next();
		System.out.println("Email: ");
		String email = input.next();
		System.out.println("Password: ");
		String password = input.next();
		new User(name, email, password);
		System.out.println("\n New account was created\n");
	}
	int login() throws SQLException {
	    System.out.println("\nEmail: ");
	    String email = input.next();
	    
	    System.out.println("Password: ");
	    String password = input.next();
//	    System.out.println(email+" "+password);
	    
	    if (db.checkUser(email, password)) {
	        System.out.println("\nLogin successful");
	        return db.getId(email, password);
	    } else {
	        System.out.println("\nInvalid credentials. Please try again.");
	        return authentication();
	    }
	}

	void mainMenu(int user_id) throws SQLException {
		System.out.println("\n1. Contact\n2. Group\n3. Exit");
		int mainMenu = input.nextInt();
		if (mainMenu == 1) {
			contactMenu(user_id);
		}
		else if (mainMenu == 2) {
			categoryMenu(user_id);
		}
		else if (mainMenu == 3) {
			System.exit(0);
		}
		else {
			System.out.println("\nEnter the correct number");
			mainMenu(user_id);
		}
	}
	void contactMenu(int user_id) throws SQLException {
		ContactManager contacts = new ContactManager(); 
		ArrayList<Integer> idArrayList = null;
		System.out.println("\n1. Add contact\n2. View contacts\n3. Update contact\n4. Delete contact\n5. Back\n6. Exit");
		int menu = input.nextInt();
		if (menu == 1) {
			System.out.print("\nFirst name: ");
			String firstName = input.next();
			System.out.print("Last Name: ");
			String lastName = input.next();
			System.out.print("Email: ");
			String email = input.next();
			System.out.print("Phone: ");
			String phone = input.next();
			System.out.print("Address");
			String address = input.next();
			new Contact(firstName, lastName, email, phone, address);
		}
		else if (menu == 2) {
		    ResultSet resultSet = contacts.get(user_id);
		    if (!resultSet.next()) {
		        System.out.println("\nNo data found");
		    } else {
		        do {
		            System.out.println("\n\nFirst Name: " + resultSet.getString("first_name") +
		                               "\nLast Name: " + resultSet.getString("last_name") +
		                               "\nEmail: " + resultSet.getString("email") +
		                               "\nPhone: " + resultSet.getString("phone") +
		                               "\nAddress: " + resultSet.getString("address"));
		        } while (resultSet.next());
		    }
		}
		else if (menu == 3) {
			System.out.println("\n1. First name\n2. Last name\n3. Email\n4. Phone\n5. address");
			int column;
			do {
				column = input.nextInt();
				if (column < 0 && column > 6) {
					System.out.println("\nInvalid number");
				}
			}
			while(column > 0 && column < 6);
			ResultSet resultSet = contacts.get(user_id);
			idArrayList = new ArrayList<Integer>();
			int count = 1;
		    if (!resultSet.next()) {
		    	System.out.println("\nNo data found");	    
		    }
		    else {
				do {
					System.out.println("\n\n"+count+")  First Name : "+ resultSet.getString("first_name")+"\nLast Name : "+ resultSet.getString("last_name")+"\nEmail : "+ resultSet.getString("email")+"\nPhone : "+ resultSet.getString("phone")+"\nAddress : "+ resultSet.getString("address"));
			    	idArrayList.add(resultSet.getInt("contact_id"));
			    	count ++;	
				}
				while(resultSet.next());
				System.out.print("\nEnter the number of contact: ");
			    int numOfContact = input.nextInt();
			    System.out.println("\nEnter the new data: ");
			    String newData = input.nextLine();
			    contacts.update(user_id, idArrayList.get(numOfContact), column, newData);
			    System.out.println("\nThe contact was updated");
			}
		    
		}
		else if (menu == 4) {
			ResultSet resultSet = contacts.get(user_id);
			idArrayList = new ArrayList<Integer>();
			int count = 1;
			if (!resultSet.next()) {
		    	System.out.println("\nNo data found");	    
		    }
		    else {
				do {
					System.out.println("\n\n"+count+")  First Name : "+ resultSet.getString("first_name")+"\nLast Name : "+ resultSet.getString("last_name")+"\nEmail : "+ resultSet.getString("email")+"\nPhone : "+ resultSet.getString("phone")+"\nAddress : "+ resultSet.getString("address"));
			    	idArrayList.add(resultSet.getInt("contact_id"));
			    	count ++;	
				}
				while(resultSet.next());
				System.out.print("\nEnter the number of contact: ");
			    int numOfContact = input.nextInt();
			    contacts.delete(user_id, idArrayList.get(numOfContact-1));
			    System.out.println("\nThe contact was deleted");
			}
		    
		}
		else if (menu == 5) {
			mainMenu(user_id);
		}
		else if (menu == 6) {
			System.exit(0);
		}
		contactMenu(user_id);
	}
	void categoryMenu(int user_id) throws SQLException {
		CategoryManager categories = new CategoryManager(); 
		ContactManager contacts = new ContactManager();
		ArrayList<Integer> idArrayList = null;
		System.out.println("\n1. Add Category\n2. View Categories\n3. Update Category\n4. Delete Category\n5. Back/n6. Exit");
		int menu = input.nextInt();
		if (menu == 1) {
			System.out.print("Category name: ");
			String name = input.nextLine();
			new Category(name);
		}
		else if (menu == 2) {
			ResultSet resultSet = categories.get(user_id);
			
		    if (!resultSet.next()) {
		    	System.out.println("\nNo data found");
			}
		    else {
		    	do {
		    		System.out.println(resultSet.getString("category_name"));
		    	}
		    	while(resultSet.next());
		    }
		}
		else if (menu == 3) {
			System.out.println("\n1. Delete contact \n2. Add contact");
			int addOrRemove;
			do {
				addOrRemove = input.nextInt();
				if (addOrRemove < 0 && addOrRemove > 3) {
					System.out.println("\nInvalid number");
				}
			}
			while(addOrRemove > 0 && addOrRemove < 3);
			ResultSet resultSet = categories.get(user_id);
			idArrayList = new ArrayList<Integer>();
			int count = 1;
		    if (!resultSet.next()) {
		    	System.out.println("\nNo data found");	    
		    }
		    else {
		    	do {
		    		System.out.println("\n\n"+count+")  "+resultSet.getString("category_name"));		    	
			    	idArrayList.add(resultSet.getInt("category_id"));
			    	count ++;	
		    	}
		    	while(resultSet.next());
		    	
		    	System.out.print("\nEnter the number of category: ");
			    int numOfCategory = input.nextInt();
			    ResultSet resultSetContacts = null;
			    if (addOrRemove == 1) {
			    	resultSetContacts = categories.getContacts(user_id,idArrayList.get(numOfCategory-1));
			    }
			    else {
			    	resultSetContacts = contacts.get(user_id);
			    }
			    ArrayList<Integer> contactsId = new ArrayList<Integer>();
				count = 1;
			    if (!resultSetContacts.next()) {
			    	System.out.println("\nNo contact found");	    
			    }
			    else {
			    	do {
			    		System.out.println("\n\n"+count+")  First Name : "+ resultSet.getString("first_name")+"\nLast Name : "+ resultSet.getString("last_name")+"\nEmail : "+ resultSet.getString("email")+"\nPhone : "+ resultSet.getString("phone")+"\nAddress : "+ resultSet.getString("address"));
				    	contactsId.add(resultSet.getInt("contact_id"));
				    	count ++;	
			    	}
			    	while(resultSetContacts.next());
			    	System.out.print("\nEnter the number of contact: ");
				    int numOfContact = input.nextInt();
				    categories.update(idArrayList.get(numOfCategory-1),contactsId.get(numOfContact-1), addOrRemove, "");
				    System.out.println("\nThe category was updated");
			    }
			    
		    }
		    
		}
		else if (menu == 4) {
			ResultSet resultSet = categories.get(user_id);
			idArrayList = new ArrayList<Integer>();
			int count = 1;
			if (!resultSet.next()) {
		    	System.out.println("\nNo data found");	    
		    }
		    else {
		    	do {
		    		System.out.println("\n\n"+count+")  "+resultSet.getString("category_name"));		    	
			    	idArrayList.add(resultSet.getInt("category_id"));
			    	count ++;	
		    	}
		    	while(resultSet.next());
		    	 System.out.print("\nEnter the number of category: ");
				 int numOfContact = input.nextInt();
				 categories.delete(user_id, idArrayList.get(numOfContact-1));
				 System.out.println("\nThe category was deleted");
		    }
		   
		}
		else if (menu == 5) {
			mainMenu(user_id);
		}
		else if (menu == 6) {
			System.exit(0);
		}
		categoryMenu(user_id);
	}
}
