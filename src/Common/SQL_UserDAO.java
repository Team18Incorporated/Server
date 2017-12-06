package Common;

import Model.AuthToken;
import Model.User;
import java.sql.*;

public class SQL_UserDAO implements IUserDAO {

	public void createAuthTokens()
	{
		Connection c = null;
        Statement statement = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.sqlite");
            statement = c.createStatement();
            String createStatement = "CREATE TABLE if not exists AuthTokens " +
                    "(" +
                    "token varchar(255) NOT NULL," +
                    "username varchar(255) NOT NULL," +
                    "PRIMARY KEY (token)," +
                    "FOREIGN KEY (username) REFERENCES Users(username)" +
                    ");";
            statement.executeUpdate(createStatement);
            statement.close();
            c.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException se){
            se.printStackTrace();
        }
	}
	
	public void createUsers()
	{
		Connection c = null;
        Statement statement = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.sqlite");
            statement = c.createStatement();
            String createStatement = "CREATE TABLE if not exists Users " +
                    "(" +
                    "username varchar(255) NOT NULL," +
                    "password varchar(255) NOT NULL," +
                    "user BLOB NOT NULL" +
                    "PRIMARY KEY (username)" + 
                    ");";
            statement.executeUpdate(createStatement);
            statement.close();
            c.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException se){
            se.printStackTrace();
        }
	}
	
	@Override
	public void register(User user, AuthToken authToken) {
		// Check if tables exist; if so, register user.
		
		//Calls login so that the data is added to the AuthToken table as well.
		login(user, authToken);
		
		Connection connection = null;
		Statement statement = null;
		try {
			Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:test.sqlite");
            statement = connection.createStatement();
            String CreateStatement = "INSERT INTO Users (username, password, user) " +
            		"VALUES (\"" + user.getUsername() + "\",\"" + user.getPassword() + "\", " +
            		user + ");";
            statement.executeUpdate(CreateStatement);
            statement.close();
            connection.close();
		}catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException se){
            se.printStackTrace();
        }
		
	}

	@Override
	public void login(User user, AuthToken authToken) {
		// TODO Auto-generated method stub
		Connection connection = null;
		Statement statement = null;
		try {
			Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:test.sqlite");
            statement = connection.createStatement();
    		String s = "INSERT INTO AuthTokens (token,username) " +
                    "VALUES (\"" + authToken.getToken() + "\",\"" + user.getUsername() + "\");";
            statement.executeUpdate(s);
            statement.close();
            connection.close();
		}catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException se){
            se.printStackTrace();
        }
	}
	
}
