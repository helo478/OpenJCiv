package com.peak15.openjciv.turn;

import com.peak15.openjciv.board.GameBoard;
import com.peak15.openjciv.player.Player;

public interface TurnEndService {

  void endTurn(Player player, GameBoard gameBoard);
}
