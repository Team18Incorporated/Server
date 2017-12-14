package plugin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javafx.util.Pair;

import com.couchbase.lite.*;
import com.google.gson.Gson;

import Common.IUserDAO;
import Model.AuthToken;
import Model.Game;
import Model.User;

public class CouchUserDAO implements IUserDAO{
	
	Database database;
	Gson gson = new Gson();

	public CouchUserDAO() {
		// TODO Auto-generated constructor stub
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
	public void register(User user, AuthToken authToken) {
		// TODO Auto-generated method stub
		String id = user.getID();
		Document doc = database.getDocument("users");
		try {
			doc.update(new Document.DocumentUpdater() {

				@Override
				public boolean update(UnsavedRevision newRevision) {
					Map<String, Object> properties = newRevision
							.getProperties();
					properties.put(id, gson.toJson(user));
					newRevision.setUserProperties(properties);
					return true;
				}
				
			});
		}
		catch (CouchbaseLiteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		doc = database.getDocument("tokens");
		try {
			doc.update(new Document.DocumentUpdater() {

				@Override
				public boolean update(UnsavedRevision newRevision) {
					Map<String, Object> properties = newRevision
							.getProperties();
					properties.put(authToken.getToken(), id);
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
	public void login(User user, AuthToken authToken) {
		// TODO Auto-generated method stub

		String id = user.getID();
		Document doc = database.getDocument("tokens");
		try {
			doc.update(new Document.DocumentUpdater() {

				@Override
				public boolean update(UnsavedRevision newRevision) {
					Map<String, Object> properties = newRevision
							.getProperties();
					properties.put(authToken.getToken(), id);
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
		
	}

	@Override
	public List<User> loadUsers() {
		Document doc = database.getDocument("games");
		Map<String, Object> properties = doc.getProperties();
		if(properties != null && properties.size() > 0) {
			Map<String, Game> newMap;
			newMap = properties.entrySet().stream()
				.collect(Collectors.toMap(Map.Entry::getKey, e-> (Game)e.getValue()));

			List<Game> games = new ArrayList<Game>(newMap.values());
			//return games;
		}
		
		return null;
	}

	@Override
	public List<Pair<String, String>> loadAuthTokens() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
	}

}
