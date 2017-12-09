package Server;


import java.io.IOException;
import java.net.InetSocketAddress;
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
		String url = "plugin."+dbType+"Factory";
		try {
			c = Class.forName(url);
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			return;
		}
		try {
			factory = (IDAOFactory) c.newInstance();
		}
		catch (InstantiationException e) {
			e.printStackTrace();
			return;
		}
		catch (IllegalAccessException e) {
			e.printStackTrace();
			return;
		}
		ServerFacade.getSingleton().setUserDAO(factory.createUserDAO());
		ServerFacade.getSingleton().setGameDAO(factory.createGameDAO());
	}

	public static void main(String[] args) {
		if(args.length > 0) {
			createFactory(args[0]);
			if (args.length>1) {
				ServerFacade.getSingleton().setMaxNumCommands(Integer.parseInt(args[1]));
			}
		}
		new ServerCommunicator().run();
	}

}
