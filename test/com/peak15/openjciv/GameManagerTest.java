package com.peak15.openjciv;

import static com.peak15.openjciv.player.action.PlayerAction.Type.END_TURN;
import static com.peak15.openjciv.player.action.PlayerAction.Type.MOVE;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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
import com.peak15.openjciv.turn.TurnEndService;

public class GameManagerTest {

  /** The system under test. */
  @Autowired
  @InjectMocks
  private GameManager sut;

  /** The mock game board. */
  @Mock
  private GameBoard mockGameBoard;

  @Mock
  private GamePieceMoverService mockGamePieceMoverService;

  @Mock
  private TurnEndService mockTurnEndService;

  /**
   * Set up before each test
   */
  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  /**
   * @throws GamePieceMovementException
   * @throws IllegalPlayerActionException
   * 
   */
  @Test
  public void handlePlayerAction_moveAction_shouldCallGamePieceMoverService()
      throws GamePieceMovementException, IllegalPlayerActionException {
    final MoveAction mockMoveAction = mock(MoveAction.class);
    final GamePiece mockGamePiece = mock(GamePiece.class);
    final HexCoordinate mockTarget = mock(HexCoordinate.class);
    // final Player mockPlayer = mock(Player.class);
    final PlayerAction mockPlayerAction = mock(PlayerAction.class);

    // when(mockPlayerAction.getPlayer()).thenReturn(mockPlayer);
    when(mockPlayerAction.getType()).thenReturn(MOVE);
    when(mockPlayerAction.getMoveAction()).thenReturn(mockMoveAction);

    when(mockMoveAction.getGamePiece()).thenReturn(mockGamePiece);
    when(mockMoveAction.getTarget()).thenReturn(mockTarget);

    sut.handlePlayerAction(mockPlayerAction);

    verify(mockGamePieceMoverService, times(1)).moveGamePiece(mockGamePiece, mockTarget,
        mockGameBoard);
    verifyZeroInteractions(mockGameBoard);
    verifyZeroInteractions(mockTarget);
    verifyZeroInteractions(mockGamePiece);
  }

  @Test
  public void handlePlayerAction_endTurn_shouldCallTurnEndServiceEndTurn()
      throws IllegalPlayerActionException {
    final PlayerAction mockPlayerAction = mock(PlayerAction.class);
    final Player mockPlayer = mock(Player.class);
    when(mockPlayerAction.getType()).thenReturn(END_TURN);
    when(mockPlayerAction.getPlayer()).thenReturn(mockPlayer);

    sut.handlePlayerAction(mockPlayerAction);

    verify(mockTurnEndService, times(1)).endTurn(mockPlayer, mockGameBoard);
    verifyZeroInteractions(mockGamePieceMoverService);

  }

}
