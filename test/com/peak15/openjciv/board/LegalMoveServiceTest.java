package com.peak15.openjciv.board;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.peak15.openjciv.RootConfiguration;

/**
 * The JUnit test case for the interface legal move service.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class,
    classes = RootConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class LegalMoveServiceTest {

  @Autowired
  private LegalMoveService sut;
  
  @Autowired
  private GameBoard gameBoard;
  
  /**
   * If the game piece has no moves remaining, is legal move should return false.
   */
  @Test
  public void isLegalMove_noMovesRemain_shouldReturnFalse() {
    final GamePiece gamePiece = new GamePiece();
    gamePiece.setMovesRemaining(0);
    final HexCoordinate hexCoordinate = new HexCoordinate(0, 0);
    final boolean actual = sut.isLegalMove(gamePiece, hexCoordinate, gameBoard);
    assertFalse(actual);
  }
  
  /**
   * If the hex is adjacent to the game piece's current position and the game piece has moves
   * remaining, then is legal move should return true.
   */
  @Test
  public void isLegalMove_hexIsAdjacent_movesRemain_shouldReturnTrue() {
    final GamePiece gamePiece = new GamePiece();
    
    final HexCoordinate origin = new HexCoordinate(0, 0);
    final HexCoordinate target = new HexCoordinate(1, 0);
    
    gamePiece.setMovesRemaining(1);
    gameBoard.putGamePiece(origin, gamePiece);
        
    final boolean actual = sut.isLegalMove(gamePiece, target, gameBoard);
    
    assertTrue(actual);
  }

  /**
   * If the game piece has moves remaining but the hex coordinate is not adjacent then is legal
   * move should return false.
   */
  @Test
  public void isLegalMove_hexNotAdjacent_movesRemain_shouldReturnFalse() {
    final GamePiece gamePiece = new GamePiece();
    
    final HexCoordinate origin = new HexCoordinate(0, 0);
    final HexCoordinate target = new HexCoordinate(5, 0);
    
    gamePiece.setMovesRemaining(1);
    gameBoard.putGamePiece(origin, gamePiece);
        
    final boolean actual = sut.isLegalMove(gamePiece, target, gameBoard);
    
    assertFalse(actual);
  }
}
