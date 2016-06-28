package com.peak15.openjciv.board;

/**
 * The Class GamePiece.
 */
public class GamePiece {
  
  /** The maximum number of moves the game piece can make in a turn. */
  private int maxMoves;

  /**
   * Sets the maximum number of moves.
   * 
   * @param maxMoves the maximum number of moves
   */
  public void setMaxMoves(final int maxMoves) {
    this.maxMoves = maxMoves;
  }
  
  /**
   * Gets the maximum number of moves.
   * 
   * @return the maximum number of moves
   */
  public int getMaxMoves() {
    return maxMoves;
  }

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

  /**
   * Resets the number of moves remaining to their maximum value.
   */
  public void resetMovesRemaining() {
    movesRemaining = maxMoves;
  }
}
