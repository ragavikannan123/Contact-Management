package contactsManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class User {
	
	static int id = 0;
	private String name;
	private String email;
	private String password;
	
	public User(String name, String email, String password) {
		
		this.name = name;
		this.email = email;
		this.password = password;
		
		DB db = DB.getDB();
	    Connection dbConnection = db.getConnection();
	    String query = "INSERT INTO Users (user_name, user_email, user_password) VALUES (?, ?, ?)";
	    PreparedStatement preparedStatement;
		try {
			preparedStatement = dbConnection.prepareStatement(query);
			preparedStatement.setString(1, name);
		    preparedStatement.setString(2, email);
		    preparedStatement.setString(3, password);
		    preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
	
	public static int getId() {
		return id;
	}
	
	public static void setId(int id) {
		User.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
}
