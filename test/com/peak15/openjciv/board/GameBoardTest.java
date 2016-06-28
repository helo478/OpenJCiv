package com.peak15.openjciv.board;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.peak15.openjciv.RootConfiguration;

/**
 * The JUnit test case for the interface game board.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class,
    classes = RootConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class GameBoardTest {

  /** The coordinates with which to test. */
  private static final int[] COORDINATES = { //@formatter:off
      Integer.MIN_VALUE,
      -1,
      0,
      1,
      Integer.MAX_VALUE      
  }; //@formatter:on

  /** The system under test. */
  @Autowired
  private GameBoard sut;

  /**
   * Get game pieces by hex should initially should return empty collection.
   */
  @Test
  public void getGamePiecesByHex_initiallyShouldReturnEmptyCollection() {

    final String message = "Initially, there should be no game pieces at any coordinate";
    final Iterator<HexCoordinate> hexCoordinates = getTestHexCoordinates().iterator();

    while (hexCoordinates.hasNext()) {
      final HexCoordinate hexCoordinate = hexCoordinates.next();
      final int expected = 0;
      final int actual = sut.getGamePiecesByHex(hexCoordinate).size();
      assertEquals(message, expected, actual);
    }
  }

  /**
   * Calling put game piece with a null hex coordinate should throw an illegal
   * argument exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void putGamePiece_nullHexCoordinate_shouldThrow() {
    final GamePiece gamePiece = new GamePiece();
    sut.putGamePiece(null, gamePiece);
  }

  /**
   * Calling put game piece with a null game piece should throw an illegal
   * argument exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void putGamePiece_nullGamePiece_shouldThrow() {
    final HexCoordinate hexCoordinate = new HexCoordinate(COORDINATES[0], COORDINATES[0]);
    sut.putGamePiece(hexCoordinate, null);
  }

  /**
   * Calling put game piece with null hex coordinate and game piece should throw
   * an illegal argument exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void putGamePiece_nullHexCoordinateAndGamePiece_shouldThrow() {
    sut.putGamePiece(null, null);
  }

  /**
   * After adding game pieces to a hex coordinate, get game pieces by hex, for
   * that hex, should return those game pieces.
   */
  @Test
  public void
      getGamePiecesByHex_afterAddingAGamePieceToHexCoordinate_shouldReturnThoseGamePieces() {
    
    final Iterator<HexCoordinate> hexCoordinates = getTestHexCoordinates().iterator();

    while (hexCoordinates.hasNext()) {
      final HexCoordinate hexCoordinate = hexCoordinates.next();
      final List<GamePiece> expected = new ArrayList<>();

      for (int i = 0; i < 5; ++i) {

        final GamePiece testGamePiece = new GamePiece();

        expected.add(testGamePiece);
        sut.putGamePiece(hexCoordinate, testGamePiece);

        final Collection<GamePiece> actual = sut.getGamePiecesByHex(hexCoordinate);

        assertThat(actual, containsInAnyOrder(expected.toArray()));
      }
    }
  }

  /**
   * Get coordinate should initially return null.
   */
  @Test
  public void getCoordinate_initially_shouldReturnNull() {
    final HexCoordinate actual = sut.getCoordinate(new GamePiece());
    assertNull(actual);
  }

  /**
   * After adding a game piece to a hex coordinate, get coordinate, for that
   * game piece, should return that coordinate.
   */
  @Test
  public void getCoordinate_afterAddingAGamePieceToAHexCoordinate_shouldReturnCoordinate() {
    final Iterator<HexCoordinate> hexCoordinates = getTestHexCoordinates().iterator();

    while (hexCoordinates.hasNext()) {
      final HexCoordinate hexCoordinate = hexCoordinates.next();

      for (int i = 0; i < 5; ++i) {

        final GamePiece testGamePiece = new GamePiece();

        sut.putGamePiece(hexCoordinate, testGamePiece);

        final HexCoordinate actual = sut.getCoordinate(testGamePiece);

        assertEquals(hexCoordinate, actual);
      }
    }
  }

  /**
   * After putting a game piece in a new coordinate the game piece should no
   * longer be at the old coordinate.
   */
  @Test
  public void afterPuttingAGamePieceInANewCoordinate_shouldNotBeAtOldCoordinate() {

    final Collection<HexCoordinate> hexCoordinates = getTestHexCoordinates();
    final Iterator<HexCoordinate> hexCoordinatesIterator = hexCoordinates.iterator();
    final GamePiece gamePiece = new GamePiece();

    while (hexCoordinatesIterator.hasNext()) {
      final HexCoordinate hexCoordinate = hexCoordinatesIterator.next();
      sut.putGamePiece(hexCoordinate, gamePiece);

      for (HexCoordinate h : hexCoordinates) {
        final Collection<GamePiece> gamePieces = sut.getGamePiecesByHex(h);
        if (h == hexCoordinate) {
          assertThat(gamePieces, hasItem(gamePiece));
        } else {
          assertThat(gamePieces, not(hasItem(gamePiece)));
        }
      }
    }
  }

  /**
   * After removing a game piece, the game piece should no longer be on the
   * board.
   * 
   * @throws GamePieceNotFoundException if the game piece to remove is not found
   */
  @Test
  public void afterRemovingAGamePiece_shouldNotBeOnBoard() throws GamePieceNotFoundException {

    final Collection<HexCoordinate> hexCoordinates = getTestHexCoordinates();
    final Iterator<HexCoordinate> hexCoordinatesIterator = hexCoordinates.iterator();
    final GamePiece gamePiece = new GamePiece();

    while (hexCoordinatesIterator.hasNext()) {
      final HexCoordinate hexCoordinate = hexCoordinatesIterator.next();
      sut.putGamePiece(hexCoordinate, gamePiece);
      sut.removeGamePiece(gamePiece);
      final Collection<GamePiece> actualGamePieces = sut.getGamePiecesByHex(hexCoordinate);
      final HexCoordinate actualHexCoordinate = sut.getCoordinate(gamePiece);
      assertThat(actualGamePieces, not(hasItem(gamePiece)));
      assertNull(actualHexCoordinate);
    }
  }

  /**
   * Trying to remove a game piece that is not on the board should throw a game
   * piece not found exception.
   * 
   * @throws GamePieceNotFoundException the expected exception
   */
  @Test(expected = GamePieceNotFoundException.class)
  public void removeGamePiece_gamePieceNotOnBoard_shouldThrow() throws GamePieceNotFoundException {
    final GamePiece gamePiece = new GamePiece();
    sut.removeGamePiece(gamePiece);
  }

  /**
   * Passing a null game piece into remove game piece should throw a game piece
   * not found exception.
   * 
   * @throws GamePieceNotFoundException the expected exception
   */
  @Test(expected = GamePieceNotFoundException.class)
  public void removeGamePiece_nullArgument_shouldThrow() throws GamePieceNotFoundException {
    sut.removeGamePiece(null);
  }
  
  /**
   * Reset moves remaining should reset the moves remaining of all game pieces on the board.
   */
  @Test
  public void resetMovesRemaining_shouldResetMovesRemainingForAllGamePieces() {
    
    final int t = 3;
    
    final HexCoordinate[] hexCoordinates = new HexCoordinate[t];
    final GamePiece[] gamePieces = new GamePiece[t];
    
    for (int i = 0; i < t; ++i) {
      hexCoordinates[i] = mock(HexCoordinate.class);
      gamePieces[i] = mock(GamePiece.class);
      sut.putGamePiece(hexCoordinates[i], gamePieces[i]);
    }
    
    sut.resetMovesRemaining();
    
    for (int i = 0; i < t; ++i) {
      verifyZeroInteractions(hexCoordinates[i]);
      verify(gamePieces[i], times(1)).resetMovesRemaining();
    }
  }

  /**
   * Gets the test hex coordinates.
   *
   * @return the test hex coordinates
   */
  private List<HexCoordinate> getTestHexCoordinates() {
    final List<HexCoordinate> hexCoordinates = new ArrayList<>();
    for (int i = 0; i < COORDINATES.length; ++i) {
      for (int j = 0; j < COORDINATES.length; ++j) {
        hexCoordinates.add(new HexCoordinate(i, j));
      }
    }
    return hexCoordinates;
  }
}
