package Common;


import java.util.List;

import Model.*;

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

    /*updateTrainDeckSize updates the size of the train card deck in the specified game

     * @pre gameID matches current game in the model
     * @pre 0<=size<=maximum number of train cards
     * @post The specified game's train card deck will have the specified size.
     */
    public void updateTrainDeckSize(int size);

    /*updateDestinationDeckSize updates the size of the train card deck in the specified game

         * @pre gameID matches current game in the model
         * @pre 0<=size<=maximum number of train cards
         * @post The specified game's train card deck will have the specified size.
         */
    public void updateDestinationDeckSize(int size);

    /*updateScore updates a given player's score.

    * @pre gameID matches current game in the model
    * @pre playerID matches a player in that game
    * @pre points >= 0
    * @post The score of the player will be changed.
     */
    public void updateScore(int points);

    /*claimRoute marks a route with tokens of the player's color

     * @pre gameID matches current game in the model
     * @pre playerID matches a player in the game
     * @pre route is not null
     * @post The route will be marked with a player's color
     */
    public void claimRoute(String gameID, String playerID, Route route);

    /*updateTrainHand Adds two train cards to this player's hand.
    * @pre gameID matches model's current game
    * @pre neither card is null
    * @post The player's hand is given the two cards
     */
    public void updateTrainHand(TrainCard card1, TrainCard card2);

    /*updateDestinationHand Gives the player one to three destination cards.
    * @pre 0 < number of cards < 4
    * @pre gameID matches current game in the model

     */
    public void updateDestinationHand(List<DestinationCard> list);

    /*updateEnemyTrainHand updates the number of train cards an opponent has.
    *@ pre playerID matches a player in the model's current game.
    *@ pre size > 0
    *@ post opponent's number of train cards is changed.
     */
    public void updateEnemyTrainHand(String playerID, int size);

    /*updateEnemyDestinationhand updates the number of train cards an opponent has.
     * @pre playerID matches a player in the model's current game.
     * @pre size > 0
     * @post opponent's number of destination cards is changed.
     */
    public void updateEnemyDestinationHand(String playerID, int size);

    /*
    * @pre playerID matches a player in the model's current game.
    * @post opponent's score will be displayed with the correct value.
     */
    public void updateEnemyScore(String playerID, int score);

    /*
	* @pre 0 < authToken&&gameID < 10000
	* @post returns a command that will display the destination card choices taken from the deck.
     */
    public void showDestinationCardChoices(List<DestinationCard> list);

    /*
    * @pre list.size() <= 5
    * @post Changes the game's face-up card list to the one we give it.
     */
    public void updateFaceUp(List<TrainCard> list);

    /*
    * @pre chatHistory != null
    * @post the chatMessages in the passed-in history will be added to the game's chat history.
     */
    public void updateChatHistory(ChatHistory chatHistory);

}
