package plugin;

import Model.AuthToken;
import Model.User;
import java.sql.*;

import Common.IUserDAO;

public class SQL_UserDAO implements IUserDAO {

	public SQL_UserDAO()
	{
		createAuthTokens();
		createUsers();
	}
	
	private void createAuthTokens()
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
	
	private void createUsers()
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
                    "user BLOB NOT NULL," +
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
		PreparedStatement statement = null;  
		String s = "INSERT INTO Users (username, password, user) " +
        		"VALUES (\"" + user.getUsername() + "\",\"" + user.getPassword() + "\", " +
        		"?" + ");";
		try {
			Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:test.sqlite");
            statement = connection.prepareStatement(s);
            statement.setBlob(1, (Blob) user);
            statement.executeUpdate();
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

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		clearUsers();
		clearAuthTokens();
	}
	
	private void clearUsers()
	{
		Connection c = null;
        PreparedStatement statement = null;
        String s = "DELETE FROM Users;";
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.sqlite");
            statement = c.prepareStatement(s);
            statement.executeUpdate();
            statement.close();
            c.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException se){
            se.printStackTrace();
        }
	}
	
	private void clearAuthTokens()
	{
		Connection c = null;
        PreparedStatement statement = null;
        String s = "DELETE FROM AuthTokens;";
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.sqlite");
            statement = c.prepareStatement(s);
            statement.executeUpdate();
            statement.close();
            c.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException se){
            se.printStackTrace();
        }
	}
	
}
