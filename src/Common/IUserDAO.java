package Common;

import java.util.List;

import javafx.util.Pair;
import Model.*;

public interface IUserDAO {

	public void register(User user, AuthToken authToken);

	public void login(User user, AuthToken authToken);
	
	public List<User> loadUsers();
	
	public List<Pair<AuthToken, String>> loadAuthTokens();
	
	public void clear();
}
