package ca.uqam.xoxinternals.model;

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
   * @param xpos as the horizontal position to test.
   * @param ypos as the horizontal position to test.
   * @return vacant status of specified cell.
   */
  boolean isFree(int xpos, int ypos);

  /**
   * Returns 2D array representation of xox board, indicating if individual cells are empty, or
   * occupied by a player.
   *
   * @return int[][] where first dimension is x, second y.
   */
  int[][] getCells();

  /**
   * Inspects the cells and returns true if three equal symbols lie on row, column or main
   * diagonal.
   *
   * @return boolean telling whether on the current board there are three cells in a line, claimed
   *     by the same player.
   */
  boolean isThreeInaLine();

  /**
   * If there are three in a line, this method returns the character of those cells. If there are
   * not, it returns the space character.
   *
   * @return a character indicating the occupier of the line or a whitespace.
   */
  int getThreeInaLineCharIfExists();
}
