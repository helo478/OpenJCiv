package com.peak15.openjciv.player.action;

public interface PlayerActionListener {

  void handlePlayerAction(final PlayerAction playerAction) throws IllegalPlayerActionException;
}
