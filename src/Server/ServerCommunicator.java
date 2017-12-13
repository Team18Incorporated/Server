package Server;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.URL;
import java.net.URLClassLoader;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.net.httpserver.HttpServer;

import Common.IDAOFactory;
import Common.IGameDAO;
import Common.IUserDAO;
import Model.ServerModel;
//TODO: add functionality when set outside program
import plugin.*;
public class ServerCommunicator {

	public static  int SERVER_PORT_NUMBER = 8080;
	private static final int MAX_WAITING_CONNECTIONS = 10;
	
	public static final String HTTP_ROOT = "public_html";
	

	public static final String DEFAULT_DESIGNATOR = "/";
	public static final String toLowerCase_DESIGNATOR = "/toLowerCase";
	public static final String trim_DESIGNATOR = "/trim";
	public static final String parseInteger_DESIGNATOR = "/parseInteger";
	
	
	private HttpServer server;
	
	private void run() {
		setUpServer(SERVER_PORT_NUMBER, MAX_WAITING_CONNECTIONS);
		setupContext();
		server.start();
	}
	
	
	
	private void setUpServer(int portNumber, int maxWaitingConnections) {
		try {
			server = HttpServer.create(new InetSocketAddress(portNumber),
									   maxWaitingConnections);
		} 
		catch (IOException e) {
			System.out.println("Could not create HTTP server: " + e.getMessage());
			e.printStackTrace();
			return;
		}

		server.setExecutor(null); // use the default executor
	}

	public void Server() {
		// TODO Auto-generated constructor stub
	}
	
	public static int getServerPortNumber() {
		return SERVER_PORT_NUMBER;
	}
	
	private void setupContext() {
		server.createContext(DEFAULT_DESIGNATOR, new Handler());
	}
	
	/*
	 * uses input dbType to create factory of given type
	 * @pre: dbType must be the name of the factory type only, the factory name should be 
	 * in the format TypeFactory
	 * @post: loads the databases to the serverModel
	 */
	private static void createFactory(String dbType) {
		Class<?> c = null;
		IDAOFactory factory = null;
		JsonParser parser = new JsonParser();
		try {
			Object object = parser.parse(new FileReader("Config.json")); 
			JsonObject jsonObject = (JsonObject)object;
			String plugin = jsonObject.get("Plugin").getAsString();

			File pluginDir = new File (plugin);
			URL url = pluginDir.toURI().toURL();

		    Class[] parameters = new Class[]{URL.class};
	    	URLClassLoader sysLoader = (URLClassLoader)
	    			ClassLoader.getSystemClassLoader();
	    	Class sysClass = URLClassLoader.class;
	    	try
	        {
	            Method method = sysClass.getDeclaredMethod("addURL", parameters);
	            method.setAccessible(true);
	            method.invoke(sysLoader,new Object[]{ url });

	            Constructor cs = ClassLoader.getSystemClassLoader().loadClass("plugin."+dbType+"Factory").getConstructor(); 
	            factory = (IDAOFactory)cs.newInstance();
	        }
	        catch(Exception ex)
	        {
	            System.err.println(ex.getMessage());
	        }
			//String url = plugin+dbType+"Factory";
			//c = Class.forName(url);

		}
		catch(FileNotFoundException fe)
        {
            fe.printStackTrace();
            return;
        }
		catch(Exception e)
        {
            e.printStackTrace();
            return;
        }
		
		/*try {
			factory = (IDAOFactory) c.newInstance();
		}
		catch (InstantiationException e) {
			e.printStackTrace();
			return;
		}
		catch (IllegalAccessException e) {
			e.printStackTrace();
			return;
		}*/
		ServerFacade.getSingleton().setUserDAO(factory.createUserDAO());
		ServerFacade.getSingleton().setGameDAO(factory.createGameDAO());
	}

	public static void main(String[] args) {
		if(args.length > 0) {
			createFactory(args[0]);
			if (args.length>1) {
				try{
					ServerFacade.getSingleton().setMaxNumCommands(Integer.parseInt(args[1]));
				}catch(Exception e){
					//clear database since its not an int
				}

					
			}
			
			ServerFacade.getSingleton().loadGames();
		}
		new ServerCommunicator().run();
	}

}
