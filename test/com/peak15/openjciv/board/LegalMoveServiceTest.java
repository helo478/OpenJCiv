package com.peak15.openjciv.board;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

/**
 * The JUnit test case for the interface legal move service.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class,
    classes = RootConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class LegalMoveServiceTest {

  @Autowired
  @InjectMocks
  private LegalMoveService sut;

  @Mock
  private GameBoard mockGameBoard;

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
   * If the game piece has no moves remaining, is legal move should return
   * false.
   */
  @Test
  public void isLegalMove_noMovesRemain_shouldReturnFalse() {
    final GamePiece gamePiece = new GamePiece();
    gamePiece.setMovesRemaining(0);
    final HexCoordinate hexCoordinate = new HexCoordinate(0, 0);
    final boolean actual = sut.isLegalMove(gamePiece, hexCoordinate, mockGameBoard);
    assertFalse(actual);
  }

  /**
   * If the hex is adjacent to the game piece's current position and the game
   * piece has moves remaining, then is legal move should return true.
   */
  @Test
  public void isLegalMove_hexIsAdjacent_movesRemain_shouldReturnTrue() {
    final int movesRemaining = 1;
    final HexCoordinate origin = mock(HexCoordinate.class);
    final HexCoordinate target = mock(HexCoordinate.class);

    when(mockGameBoard.getCoordinate(mockGamePiece)).thenReturn(origin);
    when(mockGamePiece.getMovesRemaining()).thenReturn(movesRemaining);
    when(origin.isAdjacent(target)).thenReturn(true);

    final boolean actual = sut.isLegalMove(mockGamePiece, target, mockGameBoard);
    assertTrue(actual);

    verify(mockGameBoard, times(1)).getCoordinate(mockGamePiece);
    verify(mockGamePiece, times(1)).getMovesRemaining();
  }

  /**
   * If the game piece has moves remaining but the hex coordinate is not
   * adjacent then is legal move should return false.
   */
  @Test
  public void isLegalMove_hexNotAdjacent_movesRemain_shouldReturnFalse() {
    final int movesRemaining = 1;
    final HexCoordinate origin = mock(HexCoordinate.class);
    final HexCoordinate target = mock(HexCoordinate.class);

    when(mockGameBoard.getCoordinate(mockGamePiece)).thenReturn(origin);
    when(mockGamePiece.getMovesRemaining()).thenReturn(movesRemaining);
    when(origin.isAdjacent(target)).thenReturn(false);

    final boolean actual = sut.isLegalMove(mockGamePiece, target, mockGameBoard);
    assertFalse(actual);

    verify(mockGameBoard, times(1)).getCoordinate(mockGamePiece);
    verify(mockGamePiece, times(1)).getMovesRemaining();
  }
}
