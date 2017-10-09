

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(args.length > 0) SERVER_PORT_NUMBER = Integer.parseInt(args[0]);
		new ServerCommunicator().run();
	}

}
