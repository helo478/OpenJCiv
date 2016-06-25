package com.peak15.openjciv.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * A simple implementation of the game piece mover interface.
 */
@Service
public class SimpleGamePieceMoverService implements GamePieceMoverService {
  
  /** The legal move service. */
  @Autowired
  private LegalMoveService legalMoveService;

  @Override
  public void moveGamePiece(final GamePiece gamePiece, final HexCoordinate target,
      final GameBoard gameBoard) throws GamePieceMovementException {
    
    if (!legalMoveService.isLegalMove(gamePiece, target, gameBoard)) {
      throw new GamePieceMovementException("Illegal move");
    }
    
    gamePiece.setMovesRemaining(gamePiece.getMovesRemaining() - 1);
    gameBoard.putGamePiece(target, gamePiece);
  }

}
