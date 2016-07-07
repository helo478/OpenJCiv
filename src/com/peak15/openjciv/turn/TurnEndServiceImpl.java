package com.peak15.openjciv.turn;

import org.springframework.stereotype.Service;

import com.peak15.openjciv.board.GameBoard;
import com.peak15.openjciv.player.Player;

@Service
public class TurnEndServiceImpl implements TurnEndService {
  
  @Override
  public void endTurn(Player player, GameBoard gameBoard) {
    gameBoard.resetMovesRemaining();
  }

}
