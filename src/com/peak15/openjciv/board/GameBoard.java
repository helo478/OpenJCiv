package com.peak15.openjciv.board;

import java.util.Collection;

/**
 * The Interface GameBoard.
 */
public interface GameBoard {

  /**
   * Gets the game pieces by hex.
   *
   * @param hexCoordinate
   *          the hex coordinate
   * @return the game pieces by hex
   */
  Collection<GamePiece> getGamePiecesByHex(final HexCoordinate hexCoordinate);

  /**
   * Put game piece.
   *
   * @param hexCoordinate
   *          the hex coordinate
   * @param gamePiece
   *          the game piece
   */
  void putGamePiece(HexCoordinate hexCoordinate, GamePiece gamePiece);

  /**
   * Gets the coordinate.
   *
   * @param gamePiece
   *          the test game piece
   * @return the coordinate
   */
  HexCoordinate getCoordinate(GamePiece gamePiece);

  /**
   * Removes the game piece.
   * 
   * @param gamePiece
   *          the game piece to remove
   * @throws GamePieceNotFoundException
   *           if the game piece is not on the board
   */
  void removeGamePiece(GamePiece gamePiece) throws GamePieceNotFoundException;
}
