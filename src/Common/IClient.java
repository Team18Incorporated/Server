package Common;



public interface IClient {


    /*updateJoinGameList updates the gameList of joinable games
    *
    * @pre gameList cannot be null
    * @post will update the JoinableGames GameList on the Client to match the incoming GameList
    * */
    public void updateJoinGameList(GameList gameList);

    /*updateCurrentGamesList updates list of game the player is currently in
    *
    * @pre gameList cannot be null
    * @post will update the PlayerGames GameList on the Client to match the incoming GameList
    * */
    public void updateCurrentGamesList(GameList gameList);

    /*updateUser updates the info stored in the client about the current user
    
    * @pre user cannot be null
    * @post stored User info will be updated
    * */
    public void updateUser(User user);

    /*updatePlayer updates info about the current Player

    *@pre player cannot be null
    *@post Player in the client will be updated
    * */
    public void updatePlayer(Player player);

    /*updateGame updates the current game in the client

    * @pre game cannot be null
    * @post current game will be updated
    *
    * */
    public void updateGame(Game game);

    /*updateGame updates parts of the game without passing over the whole Game object

    * @pre gameInfo cannot be null. CurrentGame on the client cannot be null
    * @post CurrentGame will be updated
    * */
    public void updateGame(GameInfo gameInfo);


}
