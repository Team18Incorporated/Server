package plugin;

import Common.ICommand;
import Common.IGameDAO;
import Common.IUserDAO;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.AuthToken;
import Model.Game;
import Model.User;

public class SQL_GameDAO implements IGameDAO {

	public SQL_GameDAO()
	{
		Connection c = null;
        Statement statement = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.sqlite");
            statement = c.createStatement();
            String createStatement = "CREATE TABLE if not exists Games " +
                    "(" +
                    "gameID varchar(255) NOT NULL," +
                    "game BLOB NOT NULL," +
                    "PRIMARY KEY (gameID)" + 
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
  	public void storeGame(Game game)
  	{
  		Connection c = null;
        PreparedStatement statement = null;
        String s = "INSERT INTO Games (gameID, game) " +
                "VALUES (\"" + game.getGameID() + "\","  + "?" + ");";
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.sqlite");
            statement = c.prepareStatement(s);
            statement.setBlob(1, (Blob) game);
            statement.executeUpdate();
            statement.close();
            c.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException se){
            se.printStackTrace();
        }
  	}
	
	@Override
	public List<Game> loadGames()
	{
		List<Game> games = new ArrayList<Game>();
		Connection c = null;
		Statement statement = null;
		try {
			Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.sqlite");
            statement = c.createStatement();
            ResultSet rs = statement.executeQuery("SELECT game FROM Games;");
            if(rs.isClosed() == true)
            {
                return games;
            }
            while(rs.next())
            {
               games.add((Game) rs.getBlob("game"));
            }
            statement.close();
            c.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException se){
            se.printStackTrace();
        }		
		return games;
	}

	@Override
	public void deleteGame(String gameID)
	{
		Connection c = null;
        PreparedStatement statement = null;
        String s = "DELETE FROM Games WHERE gameID = " + gameID + ";";
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

	@Override
	public List<ICommand> loadCommands(String gameID)
	{
		return null;
	}

	@Override
	public void clearCommands(String gameID)
	{
		
	}

	@Override
	public void addCommand(ICommand command, String gameID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		Connection c = null;
        PreparedStatement statement = null;
        String s = "DELETE FROM Games;";
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
