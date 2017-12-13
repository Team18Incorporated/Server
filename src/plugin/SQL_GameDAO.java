package plugin;

import Common.ICommand;
import Common.IGameDAO;
import Common.IUserDAO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
                    "commands BLOB NOT NULL,"+
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
        String s = "INSERT INTO Games (gameID, game, commands) " +
                "VALUES (\"" + game.getGameID() + "\","  + "?" + "," + "?);";
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.sqlite");
            statement = c.prepareStatement(s);
            ByteArrayOutputStream baos=null;           
            try {
            	baos = new ByteArrayOutputStream();                 
                ObjectOutputStream objOstream = new ObjectOutputStream(baos);
				objOstream.writeObject(game);
				objOstream.flush();                 
	            objOstream.close(); 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}                   
                              
            byte[] bArray = baos.toByteArray(); 
            statement.setBytes(1, bArray);
            baos=null;           
            try {
            	baos = new ByteArrayOutputStream();                 
                ObjectOutputStream objOstream = new ObjectOutputStream(baos);
				objOstream.writeObject(new ArrayList<ICommand>());
				objOstream.flush();                 
	            objOstream.close(); 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}                   
                              
            byte[] bArray2 = baos.toByteArray(); 
            statement.setBytes(2, bArray2);
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
           
            while(rs.next())
            {
            	ObjectInputStream ois=null;
				try {
					//ois = new ObjectInputStream(rs.getBlob(1).getBinaryStream());
					
					byte[] blob = rs.getBytes(1);
					ByteArrayInputStream in = new ByteArrayInputStream(blob);
					ois = new ObjectInputStream(in);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
               try {
				games.add((Game) ois.readObject());
				ois.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

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
		List<ICommand> commands = null;
		Connection c = null;
		Statement statement = null;
		String s = "SELECT commands FROM Games WHERE gameID = \"" + gameID +"\";";
		try {
			Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.sqlite");
            statement = c.createStatement();
            ResultSet rs = statement.executeQuery(s);
            ObjectInputStream ois=null;
			try {
				//ois = new ObjectInputStream(rs.getBlob(1).getBinaryStream());
				
				byte[] blob = rs.getBytes(1);
				ByteArrayInputStream in = new ByteArrayInputStream(blob);
				ois = new ObjectInputStream(in);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
           try {
        	   commands = (ArrayList<ICommand>)  ois.readObject();
			ois.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

            statement.close();
            c.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException se){
            se.printStackTrace();
        }
		return commands;
	}

	@Override
	public void clearCommands(String gameID)
	{
		Connection c = null;
		PreparedStatement statement = null;
		String s = "UPDATE Games SET commands=? WHERE gameID = \"" + gameID +"\";";
		try {
			Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.sqlite");
            statement = c.prepareStatement(s);
            List<ICommand> commands = loadCommands(gameID);
            commands.clear();
            ByteArrayOutputStream baos=null;           
            try {
            	baos = new ByteArrayOutputStream();                 
                ObjectOutputStream objOstream = new ObjectOutputStream(baos);
				objOstream.writeObject(commands);
				objOstream.flush();                 
	            objOstream.close(); 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}                   
                              
            byte[] bArray = baos.toByteArray(); 
            statement.setBytes(1, bArray);
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
	public void addCommand(ICommand command, String gameID) {
		// TODO Auto-generated method stub
		List<ICommand> commands = loadCommands(gameID);
		commands.add(command);
		Connection c = null;
		PreparedStatement statement = null;
		String s = "UPDATE Games SET commands=? WHERE gameID = \"" + gameID +"\";";
		try {
			Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.sqlite");
            statement = c.prepareStatement(s);
            ByteArrayOutputStream baos=null;           
            try {
            	baos = new ByteArrayOutputStream();                 
                ObjectOutputStream objOstream = new ObjectOutputStream(baos);
				objOstream.writeObject(commands);
				objOstream.flush();                 
	            objOstream.close(); 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}                   
                              
            byte[] bArray = baos.toByteArray(); 
            statement.setBytes(1, bArray);
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
