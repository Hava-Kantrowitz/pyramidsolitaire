/**
 * Interface for the view.
 */
public interface Interaction {

  /**
   * Models a user inputting to game.
   * @param in the input stream
   * @param out the expected output stream
   */
  public void apply(StringBuilder in, StringBuilder out);
}
