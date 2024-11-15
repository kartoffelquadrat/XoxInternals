package eu.kartoffelquadrat.xoxinternals.controller;

import eu.kartoffelquadrat.xoxinternals.model.BoardReadOnly;
import eu.kartoffelquadrat.xoxinternals.model.Player;
import eu.kartoffelquadrat.xoxinternals.model.XoxInitSettings;
import java.util.Collection;

/**
 * Interface for managing instances of xox games and accessing their details.
 */
public interface XoxManager {
  /**
   * Getter for all currently instantiated xox games.
   *
   * @return collection of longs serving as game ids.
   */
  Collection<Long> getGames();

  /**
   * Removes an existing game, no matter the current state.
   *
   * @param gameId as long specifying id of game to remove.
   */
  void removeGame(long gameId);

  /**
   * Creates a new game entity with the parameters specified as payload. (players,
   * preferredColours). The game new game id is created randomly.
   *
   * @param initSettings as a settings bundle specifying details for the new game. (Player names,
   *                     player colours, creator)
   * @return id of the newly created game.
   */
  long addGame(XoxInitSettings initSettings);

  /**
   * Getter for the current game board state. Return null if no game is currently initialized.
   *
   * @param gameId as the identifier of the game to inspect, provided as long.
   * @return immutable snapshot of current board.
   */
  BoardReadOnly getBoard(long gameId);

  /**
   * Getter for static player objects (names, preferred colours) of the participants of the game
   * instance referenced by the provided game-id.
   *
   * @param gameId as the identifier of the game to inspect, provided as long.
   * @return immutably deep copy of players participating in game and their attributes (name,
   *     preferred colour). If no such game is initialized, null is returned.
   */
  Player[] getPlayers(long gameId);

  /**
   * Method to look up possible actions for a given player.
   *
   * @param player as the player requesting a set of available actions in a running. Will return an
   *               empty collection if the player is not recognized.
   * @param gameId as the identifier of the game to inspect, provided as long.
   * @return A map, indexing actions by the MD5 representation of their json string serialization .
   *     The index serves as key for later re-identification if an actions is selected. Returns null
   *     if no such game is currently initialized.
   */
  XoxClaimFieldAction[] getActions(long gameId, String player);

  /**
   * Blackboard-style way to allow a client select a specific operation. The operation is identified
   * by the MD5 representation of it's JSON serialization.
   *
   * @param player      as the player requesting to play an action
   * @param gameId      as the identifier of the game to inspect, provided as long.
   * @param actionIndex as the index of the selected action in the original actions array
   */
  void performAction(long gameId, String player, int actionIndex);

  /**
   * Returns current player scores as a serialized ranking object. The ranking object also tells if
   * the game has already ended.
   *
   * @param gameId as the identifier of the game to inspect, provided as long.
   * @return a ranking bundle object with details on the players and their scores.
   */
  Ranking getRanking(long gameId);
}
