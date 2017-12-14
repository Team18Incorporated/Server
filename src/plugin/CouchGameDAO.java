package plugin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.couchbase.lite.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import Common.ICommand;
import Common.ICommandAdapter;
import Common.IGameDAO;
import Model.Game;
import Server.ByteUtil;

public class CouchGameDAO implements IGameDAO {
	
	Database database;
	Gson gson = new GsonBuilder().registerTypeAdapter(ICommand.class, new ICommandAdapter<ICommand>()).create();
	
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
					properties.put(game.getGameID(), gson.toJson(game));
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
		if(properties != null && properties.size() > 0) {
			Map<String, Object> newRevision = new HashMap<String, Object>(properties);
			newRevision.remove("_id");
			newRevision.remove("_rev");
			Map<String, Game> newMap;
			newMap = newRevision.entrySet().stream()
				.collect(Collectors.toMap(Map.Entry::getKey, e-> (Game)gson.fromJson((String) e.getValue(), Game.class)));

			List<Game> games = new ArrayList<Game>(newMap.values());
			return games;
		}
		return new ArrayList<Game>();
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
		List<ICommand> list = (List<ICommand>) gson.fromJson((String) database.getDocument("commands").getProperty(gameID),new TypeToken<List<ICommand>>(){}.getType());
		return list != null? list : new ArrayList<ICommand>();
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
					List<ICommand> commands = (List<ICommand>) gson.fromJson((String) properties.get(gameID),new TypeToken<List<ICommand>>(){}.getType());
					if (commands == null) commands = new ArrayList<ICommand>();
					commands.add(command);
					properties.put(gameID, gson.toJson(commands, new TypeToken<List<ICommand>>(){}.getType()));
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
