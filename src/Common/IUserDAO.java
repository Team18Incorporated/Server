package Common;

import Model.*;

public interface IUserDAO {

	public void register(User user, AuthToken authToken);

	public void login(User user, AuthToken authToken);
}
