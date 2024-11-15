package eu.kartoffelquadrat.xoxinternals.model;

/**
 * Read Only board interface for Xox boards.
 *
 * @author Maximilian Schiedermeier
 */
public interface BoardReadOnly {
  /**
   * Determines whether the play area is entirely empty or not.
   *
   * @return boolean indicating if play area is empty.
   */
  boolean isEmpty();

  /**
   * Iterates of a board and returns true if all cells are populated.
   *
   * @return boolean telling whether the xox is full.
   */
  boolean isFull();

  /**
   * Tells whether a given position in x any is currently vacant.
   *
   * @param xPos as the horizontal position to test.
   * @param yPos as the horizontal position to test.
   * @return vacant status of specified cell.
   */
  boolean isFree(int xPos, int yPos);

  /**
   * Returns 2D array representation of xox board, indicating if individual cells are empty, or occupied by a player.
   *
   * @return int[][] where first dimension is x, second y.
   */
  int[][] getCells();
}
