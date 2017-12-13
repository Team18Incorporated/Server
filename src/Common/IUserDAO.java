package Common;

import java.util.List;

import Model.*;

public interface IUserDAO {

	public void register(User user, AuthToken authToken);

	public void login(User user, AuthToken authToken);
	
	public List<User> loadUsers();
	
	public void clear();
}
