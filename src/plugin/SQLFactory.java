package plugin;

import Common.IDAOFactory;
import Common.IGameDAO;
import Common.IUserDAO;

public class SQLFactory implements IDAOFactory {

	@Override
	public IUserDAO createUserDAO() {
		return new SQL_UserDAO();
	}

	@Override
	public IGameDAO createGameDAO() {
		return new SQL_GameDAO();
	}

}
