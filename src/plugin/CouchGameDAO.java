package plugin;

import java.util.List;
import com.couchbase.lite.*;

import Common.ICommand;
import Common.IGameDAO;
import Model.Game;

public class CouchGameDAO implements IGameDAO {

	@Override
	public void storeGame(Game game) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Game> loadGames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteGame(String gameID) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ICommand> loadCommands(String gameID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clearCommands(String gameID) {
		// TODO Auto-generated method stub

	}

}
