package plugin;

import Common.IDAOFactory;
import Common.IGameDAO;
import Common.IUserDAO;

public class CouchFactory implements IDAOFactory {

	@Override
	public IUserDAO createUserDAO() {
		return null;
	}

	@Override
	public IGameDAO createGameDAO() {
		return new CouchGameDAO();
	}

}
