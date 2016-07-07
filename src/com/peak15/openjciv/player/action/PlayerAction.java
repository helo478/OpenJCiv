package com.peak15.openjciv.player.action;

import com.peak15.openjciv.player.Player;

public class PlayerAction {
  
  private Player player;

  private Type type;
  
  private MoveAction moveAction;
  
  public Player getPlayer() {
    return player;
  }

  public void setPlayer(final Player player) {
    this.player = player;
  }

  public Type getType() {
    return type;
  }

  public void setType(final Type type) {
    this.type = type;
  }

  public MoveAction getMoveAction() {
    return moveAction;
  }

  public void setMoveAction(final MoveAction moveAction) {
    this.moveAction = moveAction;
  }

  public static enum Type { //@formatter:off
    MOVE,
    END_TURN; //@formatter:on
  }
}
