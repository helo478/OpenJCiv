package com.peak15.openjciv.player.action;

import com.peak15.openjciv.board.GamePiece;
import com.peak15.openjciv.board.HexCoordinate;

public class MoveAction {

  private GamePiece gamePiece;
  
  private HexCoordinate target;

  public GamePiece getGamePiece() {
    return gamePiece;
  }

  public void setGamePiece(final GamePiece gamePiece) {
    this.gamePiece = gamePiece;
  }

  public HexCoordinate getTarget() {
    return target;
  }

  public void setTarget(final HexCoordinate target) {
    this.target = target;
  }
}
