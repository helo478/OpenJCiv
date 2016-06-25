package com.peak15.openjciv.board;

/**
 * A service that determines if a move is legal.
 */
public interface LegalMoveService {

  boolean isLegalMove(GamePiece gamePiece, HexCoordinate hexCoordinate, GameBoard gameBoard);
}
