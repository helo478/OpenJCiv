package com.peak15.openjciv.board;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

/**
 * An implementation of game board that both tracks hex coordinates with a list
 * of the game pieces contained within and game pieces with their associated hex
 * coordinate.
 */
@Component
public class DoubleBoundGameBoard implements GameBoard {

  /** The map of game pieces within each hex coordinate. */
  private Map<HexCoordinate, Set<GamePiece>> gamePiecesByHexCoordinate = new HashMap<>();

  /** The hex coordinates for each game piece. */
  private Map<GamePiece, HexCoordinate> hexCoordinatesByGamePiece = new HashMap<>();

  /** the map of hex coordinates to their respective game pieces. */
  public Collection<GamePiece> getGamePiecesByHex(final HexCoordinate hexCoordinate) {
    final Set<GamePiece> gamePieces = this.gamePiecesByHexCoordinate.get(hexCoordinate);
    if (gamePieces == null) {
      return new HashSet<GamePiece>();
    } else {
      return gamePieces;
    }
  }

  @Override
  public void putGamePiece(final HexCoordinate hexCoordinate, final GamePiece gamePiece) {
    if (hexCoordinate == null || gamePiece == null) {
      throw new IllegalArgumentException();
    }

    if (gamePiecesByHexCoordinate.get(hexCoordinate) == null) {
      gamePiecesByHexCoordinate.put(hexCoordinate, new HashSet<GamePiece>());
    }

    final HexCoordinate oldHexCoordinate = hexCoordinatesByGamePiece.get(gamePiece);
    if (oldHexCoordinate != null) {
      gamePiecesByHexCoordinate.get(oldHexCoordinate).remove(gamePiece);
    }

    gamePiecesByHexCoordinate.get(hexCoordinate).add(gamePiece);
    hexCoordinatesByGamePiece.put(gamePiece, hexCoordinate);
  }

  @Override
  public HexCoordinate getCoordinate(GamePiece gamePiece) {
    return hexCoordinatesByGamePiece.get(gamePiece);
  }

  @Override
  public void removeGamePiece(GamePiece gamePiece) throws GamePieceNotFoundException {
    final HexCoordinate hexCoordinate = hexCoordinatesByGamePiece.remove(gamePiece);
    if (hexCoordinate == null) {
      throw new GamePieceNotFoundException();
    }
    gamePiecesByHexCoordinate.get(hexCoordinate).remove(gamePiece);
  }
}
