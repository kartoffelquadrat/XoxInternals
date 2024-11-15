package eu.kartoffelquadrat.xoxinternals.model;

/**
 * Encodes minimal information required for launch instructions.
 *
 * @author Maximilian Schiedermeier
 */
public class Player {
  String name;
  String preferredColour;

  /**
   * Constructor for new player objects.
   *
   * @param name            as the human-readable player name.
   * @param preferredColour as description of the preferred colour (hexadecimal string prefixed by hash sign).
   */
  public Player(String name, String preferredColour) {
    this.name = name;
    this.preferredColour = preferredColour;
  }

  /**
   * Default constructor, required for JSON deserialization.
   */
  public Player() {
  }

  /**
   * Getter for player name.
   *
   * @return human-readable string representation of player name.
   */
  public String getName() {
    return name;
  }

  /**
   * Setter for player name.
   *
   * @param name human-readable string representation of player name.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Getter for player's preferred colour.
   *
   * @return string representation of hexadecimal value prefixed by hash.
   */
  public String getPreferredColour() {
    return preferredColour;
  }

  /**
   * Setter for players preferred colour.
   *
   * @param preferredColour string representation of hexadecimal value prefixed by hash.
   */
  public void setPreferredColour(String preferredColour) {
    this.preferredColour = preferredColour;
  }

  @Override
  public boolean equals(Object other) {
    if (other == null) {
      return false;
    }
    if (other.getClass() != Player.class) {
      return false;
    }
    return (name == ((Player) other).name) && (preferredColour == ((Player) other).preferredColour);
  }

  /**
   * builds JSON representation of the player object.
   *
   * @return JSON string
   */
  @Override
  public String toString() {
    return "{name:" + name + ",preferredColour:" + preferredColour + '}';
  }
}
