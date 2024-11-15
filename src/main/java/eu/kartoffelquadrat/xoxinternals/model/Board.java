package eu.kartoffelquadrat.xoxinternals.model;

interface Board extends BoardReadOnly {
  /**
   * Occupies cell at given position, by associating it with player.
   *
   * @param xpos        horizontal position of the cell to occupy.
   * @param ypos        vertical position of the cell to occupy.
   * @param firstPlayer boolean indicating whether the occupying player is first or not (i.e. is
   *                    second).
   * @throws ModelAccessException in case occupation of the specified position is not possible.
   */
  void occupy(int xpos, int ypos, boolean firstPlayer) throws ModelAccessException;
}
