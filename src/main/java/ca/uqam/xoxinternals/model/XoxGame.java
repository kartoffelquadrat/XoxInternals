package ca.uqam.xoxinternals.model;

/**
 * Interface extension defining potentially modifying access on xox game instances.
 *
 * @author Maximilian Schiedermeier
 */
public interface XoxGame extends XoxGameReadOnly {
  /**
   * Retrieves the modifiable board of a generic game.
   *
   * @return the modifiable board of a game.
   */
  BoardImpl getModifiableBoard();

  /**
   * Marks an existing running game instance as finished so no more modifications are possible.
   */
  void markAsFinished();

  /**
   * Modifies the index specifying the current player to the number provided as parameter.
   *
   * @param nextCurrentPlayer as int specifying the player to take current player status.
   * @throws ModelAccessException in case the provided value is out of range.
   */
  void setCurrentPlayer(int nextCurrentPlayer) throws ModelAccessException;
}
