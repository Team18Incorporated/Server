package Commands;

import Common.ICommand;
import Model.AuthToken;

/**
 * Created by abram on 10/2/2017.
 */

public class CreateCommand implements ICommand{

    private String username;
    private AuthToken token;

    public AuthToken getToken() {
        return token;
    }

    public void setToken(AuthToken token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public CreateCommand(String un, AuthToken t)
    {
        username = un;
        token = t;
    }

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

}
