package com.peak15.openjciv.board;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.peak15.openjciv.RootConfiguration;

import static org.mockito.Mockito.*;

/**
 * The JUnit test case for the game piece mover service interface.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class,
    classes = RootConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class GamePieceMoverServiceTest {

  /** The system under test. */
  @Autowired
  @InjectMocks
  private GamePieceMoverService sut;

  /** The mock game board. */
  @Mock
  private GameBoard mockGameBoard;

  /** The mock legal move service. */
  @Mock
  private LegalMoveService mockLegalMoveService;
  
  /** The mock game piece. */
  @Mock
  private GamePiece mockGamePiece;

  /**
   * Runs before each test.
   */
  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }
  
  /**
   * If the move is legal, then the game piece should have its remaining moves depleted by 1.
   *
   * @throws GamePieceMovementException if the game piece cannot be moved
   */
  @Test
  public void moveGamePiece_legalMove_shouldDepleteRemainingMoves() throws GamePieceMovementException {
    
    final HexCoordinate target = new HexCoordinate(0, 0);
    final int startingMovesRemaining = 1;
    final int expectedMovesRemaining = 0;

    when(mockLegalMoveService.isLegalMove(mockGamePiece, target, mockGameBoard)).thenReturn(true);
    when(mockGamePiece.getMovesRemaining()).thenReturn(startingMovesRemaining);

    sut.moveGamePiece(mockGamePiece, target, mockGameBoard);
    
    verify(mockLegalMoveService, times(1)).isLegalMove(mockGamePiece, target, mockGameBoard);
    verify(mockGamePiece, times(1)).getMovesRemaining();
    verify(mockGamePiece, times(1)).setMovesRemaining(expectedMovesRemaining);
  }
  
  /**
   * If the move is legal, then the game piece should be moved on the game board.
   *
   * @throws GamePieceMovementException if the game piece cannot be moved
   */
  @Test
  public void moveGamePiece_legalMove_shouldMoveGamePiece() throws GamePieceMovementException {
    
    final HexCoordinate target = new HexCoordinate(0, 0);
    
    when(mockLegalMoveService.isLegalMove(mockGamePiece, target, mockGameBoard)).thenReturn(true);
    
    sut.moveGamePiece(mockGamePiece, target, mockGameBoard);
    
    verify(mockLegalMoveService, times(1)).isLegalMove(mockGamePiece, target, mockGameBoard);
    verify(mockGameBoard, times(1)).putGamePiece(target, mockGamePiece);
  }
  
  /**
   * If the move is not legal, then move game piece should throw a game piece movement exception.
   *
   * @throws GamePieceMovementException if the game piece cannot be moved
   */
  @Test(expected = GamePieceMovementException.class)
  public void moveGamePiece_illegalMove_shouldThrow() throws GamePieceMovementException {

    final HexCoordinate target = new HexCoordinate(0, 0);
    when(mockLegalMoveService.isLegalMove(mockGamePiece, target, mockGameBoard)).thenReturn(false);
    sut.moveGamePiece(mockGamePiece, target, mockGameBoard);
    verify(mockLegalMoveService, times(1)).isLegalMove(mockGamePiece, target, mockGameBoard);
  }

}
