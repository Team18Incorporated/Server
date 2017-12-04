

package Common;

import Model.*;

public interface IGameDAO {


  	public void storeGame(Game game);

	public List<Game> loadGames();

	public void deleteGame(String gameID);

	public List<ICommand> loadCommands(String gameID);

	public void clearCommands(String gameID);

}