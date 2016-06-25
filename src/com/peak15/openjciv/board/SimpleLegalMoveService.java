package com.peak15.openjciv.board;

import org.springframework.stereotype.Service;

@Service
public class SimpleLegalMoveService implements LegalMoveService {

  @Override
  public boolean isLegalMove(final GamePiece gamePiece, final HexCoordinate target,
      final GameBoard gameBoard) {

    final HexCoordinate origin = gameBoard.getCoordinate(gamePiece);
    
    if (gamePiece.getMovesRemaining() > 0 && hexesAreAdjacent(origin, target)) {
      return true;
    } else {
      return false;
    }
  }

  private boolean hexesAreAdjacent(final HexCoordinate origin, final HexCoordinate target) {
    return origin.isAdjacent(target);
  }
}
