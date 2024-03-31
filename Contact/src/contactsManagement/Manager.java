package contactsManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface Manager {
	String add(Object object,int user_id);
	String delete(int user_id,int id);
	void update(int user_id,int id,int column,String newData);
	ResultSet search(int user_id,int column,String data);
	ResultSet get(int user_id);
}





class ContactManager implements Manager{
	
	@Override
	public String add(Object object, int user_id) {
		int rowsAffected = 0;
		try {
			Contact contact = (Contact) object;
		    DB db = DB.getDB();
		    Connection dbConnection = db.getConnection();
		    String query = "INSERT INTO Contacts (user_id, first_name, last_name, email, phone, address) VALUES (?, ?, ?, ?, ?, ?)";
		    PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
		    
		    preparedStatement.setInt(1, user_id);
		    preparedStatement.setString(2, contact.getFirstName());
		    preparedStatement.setString(3, contact.getLastName());
		    preparedStatement.setString(4, contact.getEmail());
		    preparedStatement.setString(5, contact.getPhone());
		    preparedStatement.setString(6, contact.getAddress());
		    
		    rowsAffected = preparedStatement.executeUpdate();
		    
		   
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		if(rowsAffected > 0) {
			return "Contact added";
		} 
	    return "Failed to add Contact";
	}

	
	@Override
	public String delete(int user_id, int id){
		int rowsAffected = 0;
		try {
			DB db = DB.getDB();
		    Connection dbConnection = db.getConnection();
		    PreparedStatement preparedStatement = null;
		    
		    String query = "DELETE FROM Contacts WHERE contact_id = ? AND user_id = ?";
		    preparedStatement = dbConnection.prepareStatement(query);
		    preparedStatement.setInt(1, id);
		    preparedStatement.setInt(2, user_id);
		        
		    rowsAffected = preparedStatement.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	  
	    if (rowsAffected > 0) {
	    	return "The contact was successfully deleted";
	    } 
	    else {
	        return "No contact found";
	    }
	 }

	@Override
	public void update(int user_id, int id, int column, String newData){
		try {
			DB db = DB.getDB();
		    Connection dbConnection = db.getConnection();
		    PreparedStatement preparedStatement = null;
		    String query = "";
	        switch (column) {
	            case 1:
	                query = "UPDATE Contacts SET first_name = ? WHERE contact_id = ? AND user_id = ?";
	                break;
	            case 2:
	                query = "UPDATE Contacts SET last_name = ? WHERE contact_id = ? AND user_id = ?";
	                break;
	            case 3:
	                query = "UPDATE Contacts SET email = ? WHERE contact_id = ? AND user_id = ?";
	                break;
	            case 4:
	                query = "UPDATE Contacts SET phone = ? WHERE contact_id = ? AND user_id = ?";
	                break;
	            case 5:
	                query = "UPDATE Contacts SET address = ? WHERE contact_id = ? AND user_id = ?";
	                break;
	            default:
	                throw new IllegalArgumentException("Invalid column number");
	        }
	        
	        preparedStatement = dbConnection.prepareStatement(query);
	        preparedStatement.setString(1, newData);
	        preparedStatement.setInt(2, id);
	        preparedStatement.setInt(3, user_id);

	        preparedStatement.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	    
	}

	
	@Override
	public ResultSet search(int user_id, int column, String data){
		ResultSet resultSet = null;
		try {
			DB db = DB.getDB();
		    Connection dbConnection = db.getConnection();
		    PreparedStatement preparedStatement = null;
		    String query = "";
	        switch (column) {
	            case 1:
	                query = "SELECT * FROM Contacts WHERE first_name LIKE ? AND user_id = ?";
	                break;
	            case 2:
	                query = "SELECT * FROM Contacts WHERE last_name LIKE ? AND user_id = ?";
	                break;
	            case 3:
	                query = "SELECT * FROM Contacts WHERE email LIKE ? AND user_id = ?";
	                break;
	            default:
	                throw new IllegalArgumentException("Invalid column number");
	        }
	        
	        preparedStatement = dbConnection.prepareStatement(query);
	        preparedStatement.setString(1, "%" + data + "%");
	        preparedStatement.setInt(2, user_id);
	        resultSet = preparedStatement.executeQuery();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	    return resultSet;
	}

	
	@Override
	public ResultSet get(int user_id){
		ResultSet resultSet = null;

		try {
			 DB db = DB.getDB();
			    Connection dbConnection = db.getConnection();
			    PreparedStatement preparedStatement = null;
			    
		        String query = "SELECT * FROM Contacts WHERE user_id = ?";
		        preparedStatement = dbConnection.prepareStatement(query);
		        preparedStatement.setInt(1, user_id);

		        resultSet = preparedStatement.executeQuery();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	   
        return resultSet;
	}


}




class CategoryManager implements Manager{
	
	@Override
	public String add(Object object, int user_id) {
		int rowsAffected = 0;
		try {
			Category category = (Category) object;
		    DB db = DB.getDB();
		    Connection dbConnection = db.getConnection();
		    String query = "insert into Categories (category_name,user_id) values (?, ?)";
		    PreparedStatement preparedStatement = null;

	        preparedStatement = dbConnection.prepareStatement(query);
	        preparedStatement.setString(1, category.getName());
	        preparedStatement.setInt(2, user_id);


	        rowsAffected = preparedStatement.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	    if (rowsAffected > 0) {
	        return "Category added";
	    } 
	    else {
	        return "Failed to add Category";
	    }
	}

	@Override
	public String delete(int user_id, int id){
		int rowsAffected = 0;
		try {
			DB db = DB.getDB();
			    Connection dbConnection = db.getConnection();
			    PreparedStatement preparedStatement = null;

			    String query = "DELETE FROM Categories WHERE category_id = ? AND user_id = ?";
		        preparedStatement = dbConnection.prepareStatement(query);
		        preparedStatement.setInt(1, id);
		        preparedStatement.setInt(2, user_id);

		        rowsAffected = preparedStatement.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
        if (rowsAffected > 0) {
            return "The category was successfully deleted";
        } 
        else {
            return "No category found";
        }
	}

	
	@Override
	public void update(int category_id, int contact_id, int addOrRemove, String newData){
		try {
			DB db = DB.getDB();
		    Connection dbConnection = db.getConnection();
		    PreparedStatement preparedStatement = null;
		    
		    if (addOrRemove == 1) {
	            String deleteQuery = "DELETE FROM Connect_Categories WHERE category_id = ? AND contact_id = ?";
	            preparedStatement = dbConnection.prepareStatement(deleteQuery);
	            preparedStatement.setInt(1, category_id);
	            preparedStatement.setInt(2, contact_id);
	        } 
		    else if (addOrRemove == 2) {
	            String insertQuery = "INSERT INTO Connect_Categories (contact_id, category_id) VALUES (?, ?)";
	            preparedStatement = dbConnection.prepareStatement(insertQuery);
	            preparedStatement.setInt(1, contact_id);
	            preparedStatement.setInt(2, category_id);
	        }
	        
	        preparedStatement.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public ResultSet search(int user_id, int column, String data){
		ResultSet resultSet = null;
		try {
			DB db = DB.getDB();
		    Connection dbConnection = db.getConnection();
		    String query = "SELECT * FROM Categories WHERE category_name LIKE ? AND user_id = ?";
	        PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
	        preparedStatement.setString(1, "%" + data + "%");
	        preparedStatement.setInt(2, user_id);
	        resultSet = preparedStatement.executeQuery();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	    
        
        return resultSet;
	    
	}


	@Override
	public ResultSet get(int user_id){
		ResultSet resultSet = null;

		try {
			DB db = DB.getDB();
		    Connection dbConnection = db.getConnection();
		    PreparedStatement preparedStatement = null;
		    
	        String query = "SELECT * FROM Categories WHERE user_id = ?";
	        preparedStatement = dbConnection.prepareStatement(query);
	        preparedStatement.setInt(1, user_id);

	        resultSet = preparedStatement.executeQuery();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	    
        return resultSet;
	}

	public ResultSet getContacts(int user_id, int category_id){
		ResultSet resultSet = null;
		try {
			DB db = DB.getDB();
		    Connection dbConnection = db.getConnection();
		    PreparedStatement preparedStatement = null;

	        String query = "SELECT * FROM Contact WHERE contact_id = (SELECT user_id FROM Connect_Categories WHERE user_id = ? AND category_id = ?)";
	        preparedStatement = dbConnection.prepareStatement(query);
	        preparedStatement.setInt(1, user_id);
	        preparedStatement.setInt(2, category_id);

	        resultSet = preparedStatement.executeQuery();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	    
        return resultSet;
	}

}