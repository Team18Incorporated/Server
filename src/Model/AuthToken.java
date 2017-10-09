package Model;

import java.util.UUID;

public class AuthToken {
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
