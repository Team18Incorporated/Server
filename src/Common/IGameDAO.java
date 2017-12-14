

package Common;

import java.util.List;

import Model.*;

public interface IGameDAO {


  	public void storeGame(Game game);

	public List<Game> loadGames();

	public void deleteGame(String gameID);

	public List<ICommand> loadCommands(String gameID);

	public void clearCommands(String gameID);
	
	public void addCommand(ICommand command, String gameID);
	
	public void clear();
	
	public void updateGame(Game game);

}