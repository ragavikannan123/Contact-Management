package contactsManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
    private static DB db;
    Connection connection;

    public DB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            String url = "jdbc:mysql://localhost:3306/Contacts_Management";
           
            connection = DriverManager.getConnection(url, "ragavi-zstk352", "Karagavi3/");
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    public static DB getDB() {
        if (db == null) {
        	db = new DB();
        }
        return db;
    }

    public Connection getConnection() {
        return connection;
    }
    boolean checkUser(String email,String password){
		 String query = "SELECT * FROM Users WHERE user_email = ? AND user_password = ?";
	       ResultSet resultSet = null;
	        try {
				PreparedStatement preparedStatement = connection.prepareStatement(query);
		        preparedStatement.setString(1, email);
		        preparedStatement.setString(2, password);
		        resultSet = preparedStatement.executeQuery();
		        return resultSet.next();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        return false;
    }
    int getId(String email, String password){
        PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("SELECT user_id FROM Users WHERE user_email = ? AND user_password = ?");
			 preparedStatement.setString(1, email);
		        preparedStatement.setString(2, password);
		        ResultSet resultSet = preparedStatement.executeQuery();
		        return resultSet.getInt("user_id");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       return 0;
    }

}
