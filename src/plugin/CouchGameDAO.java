package plugin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.couchbase.lite.*;

import Common.ICommand;
import Common.IGameDAO;
import Model.Game;

public class CouchGameDAO implements IGameDAO {
	
	Database database;
	
	public CouchGameDAO(){

		JavaContext context = new JavaContext();
		Manager manager = null;
		try {
			manager = new Manager(context, Manager.DEFAULT_OPTIONS);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Create or open the database named app
		try {
			database = manager.getDatabase("app");
		} catch (CouchbaseLiteException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void storeGame(Game game) {
		// TODO Auto-generated method stub
		Document doc = database.getDocument("games");
		try {
			doc.update(new Document.DocumentUpdater() {

				@Override
				public boolean update(UnsavedRevision newRevision) {
					Map<String, Object> properties = newRevision
							.getProperties();
					properties.put(game.getGameID(), game);
					newRevision.setUserProperties(properties);
					return true;
				}
				
			});
		}
		catch (CouchbaseLiteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Game> loadGames() {
		// TODO Auto-generated method stub
		Document doc = database.getDocument("games");
		Map<String, Object> properties = doc.getProperties();
		Map<String, Game> newMap = properties.entrySet().stream()
				.collect(Collectors.toMap(Map.Entry::getKey, e-> (Game)e.getValue()));
		List<Game> games = new ArrayList<Game>(newMap.values());
		return games;
	}

	@Override
	public void deleteGame(String gameID) {
		// TODO Auto-generated method stub
		Document doc = database.getDocument("games");
		try {
			doc.update(new Document.DocumentUpdater() {

				@Override
				public boolean update(UnsavedRevision newRevision) {
					Map<String, Object> properties = newRevision
							.getProperties();
					properties.remove(gameID);
					newRevision.setUserProperties(properties);
					return true;
				}
				
			});
		}
		catch (CouchbaseLiteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<ICommand> loadCommands(String gameID) {
		// TODO Auto-generated method stub
		return (List<ICommand>) database.getDocument("commands").getProperty(gameID);
	}

	@Override
	public void clearCommands(String gameID) {
		// TODO Auto-generated method stub
		Document doc = database.getDocument("commands");
		try {
			doc.update(new Document.DocumentUpdater() {

				@Override
				public boolean update(UnsavedRevision newRevision) {
					Map<String, Object> properties = newRevision
							.getProperties();
					properties.remove(gameID);
					newRevision.setUserProperties(properties);
					return true;
				}
				
			});
		}
		catch (CouchbaseLiteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void addCommand(ICommand command, String gameID) {
		// TODO Auto-generated method stub
		Document doc = database.getDocument("commands");
		try {
			doc.update(new Document.DocumentUpdater() {

				@Override
				public boolean update(UnsavedRevision newRevision) {
					Map<String, Object> properties = newRevision
							.getProperties();
					List<ICommand> commands = (List<ICommand>) properties.get(gameID);
					if (commands == null) commands = new ArrayList<ICommand>();
					commands.add(command);
					properties.put(gameID, commands);
					newRevision.setUserProperties(properties);
					return true;
				}
				
			});
		}
		catch (CouchbaseLiteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		try {
			database.getDocument("games").delete();
		} catch (CouchbaseLiteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			database.getDocument("commands").delete();
		} catch (CouchbaseLiteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
