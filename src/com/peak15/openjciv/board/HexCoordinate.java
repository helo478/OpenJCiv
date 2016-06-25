package com.peak15.openjciv.board;

import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The Class HexCoordinate.
 */
public class HexCoordinate {

  /** The x coordinate. */
  private final int x;

  /** The y coordinate. */
  private final int y;

  /**
   * Instantiates a new hex coordinate.
   *
   * @param x
   *          the x coordinate
   * @param y
   *          the y coordinate
   */
  public HexCoordinate(final int x, final int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Gets the x coordinate.
   *
   * @return the x coordinate
   */
  public int getX() {
    return x;
  }

  /**
   * Gets the y coordinate.
   *
   * @return the y coordinate
   */
  public int getY() {
    return y;
  }

  @Override
  public boolean equals(final Object o) {

    if (o == null || !(o instanceof HexCoordinate)) {
      return false;
    }

    final HexCoordinate h = (HexCoordinate) o;

    return this.x == h.getX() && this.y == h.getY();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder() //@formatter:off
        .append(x)
        .append(y)
        .toHashCode(); //@formatter:on
  }

  @Override
  public String toString() {
    return "(" + x + ", " + y + ")";
  }
}
