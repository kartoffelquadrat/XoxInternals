package eu.kartoffelquadrat.xoxinternals.model;

interface Board extends BoardReadOnly {
  /**
   * Occupies cell at given position, by associating it with player.
   *
   * @param xPos        horizontal position of the cell to occupy.
   * @param yPos        vertical position of the cell to occupy.
   * @param firstPlayer boolean indicating whether the occupying player is first or not (i.e. is second).
   * @throws ModelAccessException
   */
  void occupy(int xPos, int yPos, boolean firstPlayer) throws ModelAccessException;
}
