package eu.kartoffelquadrat.xoxinternals.model;

/**
 * Custom Exception that is fired whenever model modifications are requested that would lead to an inconsistent
 * state.
 *
 * @author Maximilian Schiedermeier
 */
public class ModelAccessException extends Exception {
  /**
   * Constructor for custom exception.
   *
   * @param cause details for reasons of exception raised.
   */
  public ModelAccessException(String cause) {
    super(cause);
  }
}
