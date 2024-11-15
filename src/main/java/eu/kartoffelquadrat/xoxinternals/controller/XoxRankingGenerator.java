package eu.kartoffelquadrat.xoxinternals.controller;

import eu.kartoffelquadrat.xoxinternals.model.Player;
import eu.kartoffelquadrat.xoxinternals.model.XoxGameImpl;
import eu.kartoffelquadrat.xoxinternals.model.XoxGameReadOnly;

/**
 * Xox specific implementation of the Ranking generator interface. Analyzes instances of Xox games
 * and provides a player ranking, based on their longest lines.
 *
 * @author Maximilian Schiedermeier
 */
public class XoxRankingGenerator implements RankingGenerator {
  /**
   * Default constructor for javadoc.
   */
  public XoxRankingGenerator() {
  }

  @Override
  public Ranking computeRanking(XoxGameReadOnly game) throws LogicException {
    // can only be applied on Xox games
    if (game.getClass() != XoxGameImpl.class) {
      throw new LogicException("Xox Ranking generator can only operate on Xox games.");
    }
    XoxGameImpl xoxGame = (XoxGameImpl) game;
    // Will only provide a ranking with non-0 scores, if the game has already ended.
    if (!((XoxGameImpl) game).isFinished()) {
      return new Ranking(game.getPlayers(), new int[] {0, 0}, false);
    }
    // Verify there actually is a player, if not:
    if (isDraw(xoxGame)) {
      return new Ranking(game.getPlayers(), new int[] {0, 0}, true);
    }
    // Winner (player with 3 in a row) gets 1 point, looser 0.
    int winnerInt = xoxGame.getModifiableBoard().getThreeInaLineCharIfExists();
    Player[] rankedPlayers = game.getPlayers();
    // If the non-creator won, overwrite with a ranking that is the inverse of the games player
    // listing.
    if (winnerInt != 1) {
      Player[] invertedRankedPlayers = new Player[2];
      invertedRankedPlayers[0] = rankedPlayers[1];
      invertedRankedPlayers[1] = rankedPlayers[0];
      rankedPlayers = invertedRankedPlayers;
    }
    return new Ranking(rankedPlayers, new int[] {1, 0}, true);
  }

  /**
   * Analyze if the provided game resulted in a draw (no winner).
   *
   * @return true if there is no winner, false otherwise.
   */
  private boolean isDraw(XoxGameImpl game) {
    if (!game.isFinished()) {
      return false;
    }
    return !game.getModifiableBoard().isThreeInaLine();
  }
}
