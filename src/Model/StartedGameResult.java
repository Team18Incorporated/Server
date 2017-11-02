package Model;

import Common.ClientGame;

public class StartedGameResult {
	

    private boolean started;
    private ClientGame game;

    public StartedGameResult(boolean started)
    {
        this.started=started;
    }
    
    public StartedGameResult(boolean started, Game game, String playerID)
    {
        this.started=started;
        this.game = new ClientGame(game, playerID);
    }

    public boolean hasStarted()
    {
        return started;
    }

    public ClientGame getGame() {
        return game;
    }
	
}
