package Server;


import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URI;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import Common.ICommand;
import Common.ICommandAdapter;

public class Handler implements HttpHandler {
	
	private GsonBuilder builder = new GsonBuilder();
	
	public Handler() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		URI uri = exchange.getRequestURI();
		String path = uri.getPath();
		if(!path.startsWith("/Update") && !path.startsWith("/InGameCommands.Update"))System.out.println("Path: " + path + " URI: " + uri.toString());
		
		builder.registerTypeAdapter(ICommand.class, new ICommandAdapter<ICommand>());
		Gson gson = builder.create();

		
		InputStreamReader inputStreamReader = new InputStreamReader(exchange.getRequestBody());
		Object req = null;
		Object result =null;
		try {
			req = gson.fromJson(inputStreamReader, Class.forName("Commands." + path.substring(1)+"Command"));
			result = ((ICommand)req).execute();
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
		PrintWriter printWriter = new PrintWriter(exchange.getResponseBody());
		gson.toJson(result,printWriter);
		
		
		
		printWriter.close();
		
	}

}
