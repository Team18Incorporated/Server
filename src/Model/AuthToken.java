package Model;

import java.io.Serializable;
import java.util.UUID;

public class AuthToken implements Serializable{
	private String token;

	public AuthToken() {
		// TODO Auto-generated constructor stub
		token = UUID.randomUUID().toString();
	}
	
	public String getToken()
    {
    	return token;
    }

}
