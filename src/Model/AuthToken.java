package Model;

import java.util.UUID;

public class AuthToken {
	String token;

	public AuthToken() {
		// TODO Auto-generated constructor stub
		token = UUID.randomUUID().toString();
	}

}
