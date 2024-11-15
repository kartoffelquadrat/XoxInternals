package eu.kartoffelquadrat.xoxinternals.model;

/**
 * Represents the state of a Xox running game.
 *
 * @author Maximilian Schiedermeier
 */
public class XoxGameImpl implements XoxGame {
  // Read only access to the parameters of the two involved players.
  private final Player[] players = new Player[2];
  // Reference to current state of the board
  BoardImpl board;
  // Internal flag to indicate whether the game has already ended or still running.
  private boolean finished;
  // Internal index counter for the current player. Range: [0-1]
  private int currentPlayer;

  /**
   * Constructor for new xox game instances.
   *
   * @param startPlayer  as player object representing information on the first player.
   * @param secondPlayer as player object representing information on the second player.
   */
  public XoxGameImpl(Player startPlayer, Player secondPlayer) {
    players[0] = startPlayer;
    players[1] = secondPlayer;
    currentPlayer = 0;
    board = new BoardImpl();
  }

  @Override
  public boolean isFinished() {
    return finished;
  }

  @Override
  public void markAsFinished() {
    finished = true;
  }

  @Override
  public Player getPlayerByName(String name) {
    for (Player player : players) {
      if (player.getName().equals(name)) {
        return player;
      }
    }
    return null;
  }

  @Override
  public BoardImpl getModifiableBoard() {
    return board;
  }

  @Override
  public Player[] getPlayers() {
    Player[] deepCopy = new Player[players.length];
    deepCopy[0] = players[0];
    deepCopy[1] = players[1];
    return deepCopy;
  }

  @Override
  public Player getPlayerInfo(int index) {
    return players[index];
  }

  @Override
  public int getCurrentPlayerIndex() {
    return currentPlayer;
  }

  @Override
  public String getCurrentPlayerName() {
    return players[currentPlayer].getName();
  }

  @Override
  public void setCurrentPlayer(int nextCurrentPlayer) throws ModelAccessException {
    if (nextCurrentPlayer != 0 && nextCurrentPlayer != 1) {
      throw new ModelAccessException("Current player can not be set to a value other than 0 or 1.");
    }
    currentPlayer = nextCurrentPlayer;
  }

  @Override
  public boolean isFirstPlayer(Player player) {
    return getPlayers()[0].equals(player);
  }
}
