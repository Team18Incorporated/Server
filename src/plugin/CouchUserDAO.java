package plugin;

import java.io.IOException;
import java.util.ArrayList;

import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javafx.util.Pair;

import com.couchbase.lite.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

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
		String id = user.getUsername();
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

		String id = user.getUsername();
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
		try {
			database.getDocument("tokens").delete();
		} catch (CouchbaseLiteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			database.getDocument("users").delete();
		} catch (CouchbaseLiteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<User> loadUsers() {
		// TODO Auto-generated method stub
		Document doc = database.getDocument("users");
		Map<String, Object> properties = doc.getProperties();
		if(properties != null && properties.size() > 0) {
			Map<String, Object> newRevision = new HashMap<String, Object>(properties);
			newRevision.remove("_id");
			newRevision.remove("_rev");
			
			Map<String, User> newMap;
			System.out.println(newRevision.values().toString());
			newMap = newRevision.entrySet().stream()
				.collect(Collectors.toMap(Map.Entry::getKey, e-> (User)gson.fromJson((String) e.getValue(),User.class)));

			List<User> games = new ArrayList<User>(newMap.values());
			return games;
		}
		return new ArrayList<User>();
	}

	@Override
	public List<Pair<String, String>> loadAuthTokens() {
		// TODO Auto-generated method stub
		Document doc = database.getDocument("tokens");
		Map<String, Object> properties = doc.getProperties();
		if(properties != null && properties.size() > 0) {
			HashMap<String, Object> newRevision = new HashMap<String, Object>(properties);
			newRevision.remove("_id");
			newRevision.remove("_rev");
			Map<String, String> newMap;
			newMap = newRevision.entrySet().stream()
				.collect(Collectors.toMap(Map.Entry::getKey, e-> (String)e.getValue()));
			List<Pair<String, String>> list = new ArrayList<Pair<String, String>>();
			for(String id: newMap.keySet()){
				list.add(new Pair<String,String>(id, newMap.get(id)));
			}
			return list;
		}
		return new ArrayList<Pair<String,String>>();
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
	}

}
