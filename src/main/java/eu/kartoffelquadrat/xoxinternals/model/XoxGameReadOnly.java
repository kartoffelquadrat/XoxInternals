package eu.kartoffelquadrat.xoxinternals.model;

/**
 * Read-only interface for Xox game model.
 *
 * @author Maximilian Schiedermeier
 */
public interface XoxGameReadOnly {
  /**
   * Look up a player by name.
   *
   * @param name as the name of the player to look up.
   * @return PlayerReadOnly as the matching player object, if associated. Null otherwise.
   */
  Player getPlayerByName(String name);

  /**
   * Retrieves the array (in order of registration) of players associated to a game object. Only
   * returns a deep copy of the original players array.
   *
   * @return a deep copy of the player array associated to a game.
   */
  Player[] getPlayers();

  /**
   * Returns bundled information on a player, accessed by the player index within this game
   * instance.
   *
   * @param index to select the player. Either 0 or 1.
   * @return the player info bundle.
   */
  public Player getPlayerInfo(int index);

  /**
   * Tells whether no more moves are possible in a game.
   *
   * @return true if the game is finished, false otherwise.
   */
  boolean isFinished();

  /**
   * Returns index of current player.
   *
   * @return int value 0 or 1 for whoever is current player.
   */
  int getCurrentPlayerIndex();

  /**
   * Helper method that resolves a player object. Returns true if the provided player object is the
   * first player (creator) of the game.
   *
   * @param player as the object to test.
   * @return true if the provided player matches the creator of this game. False otherwise.
   */
  boolean isFirstPlayer(Player player);

  /**
   * Looks up player name for current player.
   *
   * @return String representation of current player name.
   */
  String getCurrentPlayerName();
}
