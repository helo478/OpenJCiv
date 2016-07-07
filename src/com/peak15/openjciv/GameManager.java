package com.peak15.openjciv;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import com.peak15.openjciv.board.GameBoard;
import com.peak15.openjciv.board.GamePiece;
import com.peak15.openjciv.board.GamePieceMovementException;
import com.peak15.openjciv.board.GamePieceMoverService;
import com.peak15.openjciv.board.HexCoordinate;
import com.peak15.openjciv.player.Player;
import com.peak15.openjciv.player.action.IllegalPlayerActionException;
import com.peak15.openjciv.player.action.MoveAction;
import com.peak15.openjciv.player.action.PlayerAction;
import com.peak15.openjciv.player.action.PlayerActionListener;
import com.peak15.openjciv.turn.TurnEndService;

public class GameManager implements PlayerActionListener {

  private static final Logger logger = Logger.getLogger(GameManager.class.getName());

  @Autowired
  private GameBoard gameBoard;

  @Autowired
  private GamePieceMoverService gamePieceMoverService;
  
  @Autowired
  private TurnEndService turnEndService;

  @Override
  public void handlePlayerAction(final PlayerAction playerAction)
      throws IllegalPlayerActionException {

    final Player player = playerAction.getPlayer();
    final PlayerAction.Type type = playerAction.getType();
    final MoveAction movementAction = playerAction.getMoveAction();
    
    if (type == null) {
      throw new IllegalArgumentException("playerAction.type must not be null");
    }
    
    switch(type) {
    case MOVE:
      move(movementAction);
      break;
    case END_TURN:
      turnEndService.endTurn(player, gameBoard);
      break;    
    default:
      throw new IllegalArgumentException("Unexpected case for PlayerAction.Type: " + type);
    }
  }

  private void move(final MoveAction movementAction) {
    final GamePiece gamePiece = movementAction.getGamePiece();
    final HexCoordinate target = movementAction.getTarget();
    try {
      gamePieceMoverService.moveGamePiece(gamePiece, target, gameBoard);
    } catch (final GamePieceMovementException e) {
      logger.warning("Unable to move game piece; " + e.getMessage());
      logger.log(Level.FINEST, "Unable to move game piece", e);
    }
  }
}
