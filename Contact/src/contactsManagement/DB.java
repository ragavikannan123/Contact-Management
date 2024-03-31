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
    boolean checkUser(String name,String password) throws SQLException {
		 String query = "SELECT * FROM Users WHERE user_name = ? AND user_password = ?";
	       PreparedStatement preparedStatement = connection.prepareStatement(query);
	        preparedStatement.setString(1, name);
	        preparedStatement.setString(2, password);
	        
	        ResultSet resultSet = preparedStatement.executeQuery();
	        return resultSet.next();
    }
    int getId(String name, String password) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT user_id FROM Users WHERE user_name = ? AND user_password = ?");
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.getInt("user_id");
    }

}
