package com.peak15.openjciv.board;

/**
 * The Class GamePiece.
 */
public class GamePiece {

  /** The moves remaining. */
  private int movesRemaining;
  
  /**
   * Sets the number of moves remaining.
   *
   * @param movesRemaining the number of new moves remaining
   */
  public void setMovesRemaining(final int movesRemaining) {
    this.movesRemaining = movesRemaining;
  }

  /**
   * Gets the number of moves remaining.
   *
   * @return the number of moves remaining
   */
  public int getMovesRemaining() {
    return movesRemaining;
  }
}
