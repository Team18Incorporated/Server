package Common;

import Model.*;

public interface IDAOFactory {

	public IUserDAO createUserDAO();

	public IGameDAO createGameDAO();   

}