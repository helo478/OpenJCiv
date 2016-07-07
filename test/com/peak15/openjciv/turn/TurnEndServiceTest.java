package com.peak15.openjciv.turn;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.peak15.openjciv.RootConfiguration;
import com.peak15.openjciv.board.GameBoard;
import com.peak15.openjciv.player.Player;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class,
classes = RootConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TurnEndServiceTest {

  @Autowired
  @InjectMocks
  private TurnEndService sut;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void endTurn_shouldCallGameBoard_resetMovesRemaining() {

    final Player player = mock(Player.class);
    final GameBoard mockGameBoard = mock(GameBoard.class);

    sut.endTurn(player, mockGameBoard);

    verify(mockGameBoard, times(1)).resetMovesRemaining();
  }

}
