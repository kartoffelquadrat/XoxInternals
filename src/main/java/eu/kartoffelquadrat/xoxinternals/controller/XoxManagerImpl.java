package eu.kartoffelquadrat.xoxinternals.controller;

import eu.kartoffelquadrat.xoxinternals.model.BoardReadOnly;
import eu.kartoffelquadrat.xoxinternals.model.ModelAccessException;
import eu.kartoffelquadrat.xoxinternals.model.Player;
import eu.kartoffelquadrat.xoxinternals.model.XoxGameImpl;
import eu.kartoffelquadrat.xoxinternals.model.XoxInitSettings;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Random;

/**
 * Xox Controller Implementation. Acts as DAO for game state and provides endpoints to generate
 * actions that allow state modification. Indexes all running games and allows game-specific access
 * by game-id. Note that for simplicity the signatures of this controller do not support error
 * handling. In case of bad parameters, the controller will simply ignore a method call or return an
 * empty return object.
 *
 * @author Maximilian Schiedermeier
 */
public class XoxManagerImpl implements XoxManager {
  private static XoxManagerImpl singletonReference;
  private final XoxActionGenerator actionGenerator;
  private final ActionInterpreter actionInterpreter;
  private final HashMap<Long, XoxGameImpl> games;
  private final RankingGenerator rankingGenerator;

  /**
   * Private default constructor for singleton pattern. Initializes all required util classes and
   * start a new game with players "X" and "O".
   */
  private XoxManagerImpl() {
    actionGenerator = new XoxActionGenerator();
    actionInterpreter = new XoxActionInterpreter(actionGenerator, new XoxEndingAnalyzer());
    games = new LinkedHashMap<>();
    rankingGenerator = new XoxRankingGenerator();
    initializeSampleGame();
  }

  /**
   * Singleton access method to obtain the controller instance. First call implicitly initializes a
   * new game for players "X" and "O".
   *
   * @return unique singleton representative of this class.
   */
  public static XoxManagerImpl getInstance() {
    if (singletonReference == null) {
      singletonReference = new XoxManagerImpl();
    }
    return singletonReference;
  }

  @Override
  public Collection<Long> getGames() {
    return games.keySet();
  }

  @Override
  public void removeGame(long gameId) {
    games.remove(gameId);
  }

  @Override
  public long addGame(XoxInitSettings initSettings) {
    // Generate a new random game id
    long gameId = generateUniqueGameId();
    // If needed rearrange received array so that first player equals game creator
    if (!initSettings.getCreator().equals(initSettings.getPlayers().getFirst().getName())) {
      initSettings.getPlayers().add(initSettings.getPlayers().removeFirst());
    }
    games.put(gameId,
        new XoxGameImpl(initSettings.getPlayers().getFirst(), initSettings.getPlayers().getLast()));
    return gameId;
  }

  @Override
  public BoardReadOnly getBoard(long gameId) {
    if (!games.containsKey(gameId)) {
      return null;
    }
    return games.get(gameId).getModifiableBoard();
  }

  @Override
  public Player[] getPlayers(long gameId) {
    if (!games.containsKey(gameId)) {
      return null;
    }
    return games.get(gameId).getPlayers();
  }

  @Override
  public XoxClaimFieldAction[] getActions(long gameId, String player) {
    // Reject if no game is currently initialized
    if (!games.containsKey(gameId)) {
      return null;
    }
    // Look up player and build an action bundle. (only non empty for current player)
    Player playerObject = games.get(gameId).getPlayerByName(player);
    // Return empty map if the player is not recognized.
    // Error handling ignored for case study simplicity.
    if (playerObject == null) {
      return new XoxClaimFieldAction[] {};
    }
    try {
      return actionGenerator.generateActions(games.get(gameId), playerObject).values()
          .toArray(new XoxClaimFieldAction[] {});
    } catch (LogicException e) {
      // Error handling ignored for case study simplicity.
      return new XoxClaimFieldAction[] {};
    }
  }

  @Override
  public void performAction(long gameId, String player, int actionIndex) {
    // Reject if no such game is currently initialized
    if (!games.containsKey(gameId)) {
      return;
    }
    // Verify the selected action was actually offered
    XoxClaimFieldAction[] offeredActions = getActions(gameId, player);
    // Looks good - perform the action by passing it to the XoxActionInterpreter
    XoxClaimFieldAction selectedAction = offeredActions[actionIndex];
    try {
      actionInterpreter.interpretAndApplyAction(selectedAction, games.get(gameId));
    } catch (LogicException | ModelAccessException internalException) {
      // Error handling ignored for case study simplicity.
    }
  }

  @Override
  public Ranking getRanking(long gameId) {
    // Reject if no such game is currently initialized
    if (!games.containsKey(gameId)) {
      return null;
    }
    try {
      return rankingGenerator.computeRanking(games.get(gameId));
    } catch (LogicException e) {
      // Error handling ignored for case study simplicity.
      return null;
    }
  }

  private void initializeSampleGame() {
    // reject if map not empty
    if (!games.isEmpty()) {
      throw new RuntimeException("Sample game can only be added as first game");
    }
    // Initialize sample game object
    XoxGameImpl sampleGame =
        new XoxGameImpl(new Player("Max", "#CAFFEE"), new Player("Moritz", "#1CE7EA"));
    // Add sample game at fixed index ... (Note: all other game ID must be generated dynamically)
    games.put(Long.valueOf(42), sampleGame);
  }

  /**
   * Creates a random game ID that is not yet in use.
   */
  private long generateUniqueGameId() {
    long randomGameId = Math.abs(new Random().nextLong());
    while (games.containsKey(randomGameId)) {
      randomGameId = Math.abs(new Random().nextLong());
    }
    return randomGameId;
  }
}
