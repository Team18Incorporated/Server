package Common;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import Model.AuthToken;
import Model.Game;
import Model.User;

public class SQL_GameDAO implements IGameDAO {

	public void createGames()
	{
		Connection c = null;
        Statement statement = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.sqlite");
            statement = c.createStatement();
            String createStatement = "CREATE TABLE if not exists Users " +
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

	public List<Game> loadGames()
	{
		
	}

	public void deleteGame(String gameID)
	{
		
	}

	public List<ICommand> loadCommands(String gameID)
	{
		
	}

	public void clearCommands(String gameID)
	{
		
	}

}
