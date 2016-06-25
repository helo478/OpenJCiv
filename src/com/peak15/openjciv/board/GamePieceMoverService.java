package com.peak15.openjciv.board;

/**
 * The interface for a game piece mover service.
 */
public interface GamePieceMoverService {

  /**
   * Move game piece.
   *
   * @param gamePiece the game piece to move
   * @param target the target hex coordinate of the move
   * @param gameBoard the game board on which to make the move
   * @throws GamePieceMovementException if the move cannot be made
   */
  void moveGamePiece(GamePiece gamePiece, HexCoordinate target, GameBoard gameBoard)
      throws GamePieceMovementException;
}
