package plugin;

import Common.IDAOFactory;
import Common.IGameDAO;
import Common.IUserDAO;

public class CouchFactory implements IDAOFactory {

	@Override
	public IUserDAO createUserDAO() {
		return new CouchUserDAO();
	}

	@Override
	public IGameDAO createGameDAO() {
		return new CouchGameDAO();
	}

}
