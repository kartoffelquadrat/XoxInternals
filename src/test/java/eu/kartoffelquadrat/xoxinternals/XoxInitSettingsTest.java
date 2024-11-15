package eu.kartoffelquadrat.xoxinternals;

import eu.kartoffelquadrat.xoxinternals.model.Player;
import eu.kartoffelquadrat.xoxinternals.model.XoxInitSettings;
import java.util.LinkedList;
import org.junit.Assert;
import org.junit.Test;

public class XoxInitSettingsTest {
  /**
   * Tests init settings field setters.
   */
  @Test
  public void testSetters() {
    // Initialize settings object, default CTR
    XoxInitSettings settings = new XoxInitSettings();
    // Call setters and verify effect
    Player player1 = new Player("Max", "#CAFFEE");
    Player player2 = new Player("Moritz", "#1CE7EA");
    LinkedList<Player> players = new LinkedList<>();
    players.add(player1);
    players.add(player2);
    settings.setPlayers(players);
    settings.setCreator(player1.getName());
    // verify setter call was successful
    Assert.assertTrue("Creator not correctly set", settings.getCreator().equals(player1.getName()));
    Assert.assertTrue("Players not correctly set", settings.getPlayers() == players);
  }
}
