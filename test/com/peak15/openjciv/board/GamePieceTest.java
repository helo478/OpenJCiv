package com.peak15.openjciv.board;

import static org.junit.Assert.*;

import org.junit.Test;

public class GamePieceTest {
  
/*  private GamePiece sut;
  
  @Before
  public void setUp() {
    sut = new GamePiece();
  }*/

  private static final int[] TEST_NUM_MOVES = { //@formatter:off
      Integer.MIN_VALUE,
      -1,
      0,
      1,
      Integer.MAX_VALUE      
  }; //@formatter:on

  @Test
  public void restMovesRemaining_shouldMakeMovesRemainingEqualMaxMoves() {
    
    for (int max : TEST_NUM_MOVES) {
      for (int remaining : TEST_NUM_MOVES) {
        final GamePiece gamePiece = new GamePiece();
        gamePiece.setMaxMoves(max);
        gamePiece.setMovesRemaining(remaining);
        gamePiece.resetMovesRemaining();
        assertEquals(gamePiece.getMaxMoves(), gamePiece.getMovesRemaining());
      }
    }
  }

}
