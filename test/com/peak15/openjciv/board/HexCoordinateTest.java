package com.peak15.openjciv.board;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

/**
 * The JUnit test case for the hex coordinate class.
 */
public class HexCoordinateTest {

  /** The number of times which tests for consistency should run. */
  private static final int CONSISTENCY_RUNS = 1000;

  /** The coordinates with which to test. */
  private static final int[] COORDINATES = { Integer.MIN_VALUE, -1, 0, 1, Integer.MAX_VALUE };

  /**
   * The method equals should return false if the argument is null.
   */
  @Test
  public void equals_shouldReturnFalseIfArgumentIsNull() {
    final Object argument = null;
    final String message = "Any HexCoordinate should not be equal to null";

    for (int i = 0; i < COORDINATES.length; ++i) {
      for (int j = 0; j < COORDINATES.length; ++j) {
        final HexCoordinate hexCoordinate = new HexCoordinate(i, j);
        final boolean actual = hexCoordinate.equals(argument);
        assertFalse(message, actual);
      }
    }
  }

  /**
   * The method equals should return false if the argument is not a hex
   * coordinate.
   */
  @Test
  public void equals_shouldReturnFalseIfArgumentIsNotInstanceOfHexCoordinate() {

    final Object argument = new Object();
    final String message
        = "Any HexCoordinate should not be equal to an object not of instance HexCoordinate";

    for (int i = 0; i < COORDINATES.length; ++i) {
      for (int j = 0; j < COORDINATES.length; ++j) {
        final HexCoordinate hexCoordinate = new HexCoordinate(i, j);
        final boolean actual = hexCoordinate.equals(argument);
        assertFalse(message, actual);
      }
    }
  }

  /**
   * The equals method should return true if and only if both the x and y
   * coordinates are equal.
   */
  @Test
  public void equals_shouldReturnTrueIfAndOnlyIfXAndYCoordinatesAreEqual() {

    for (int i = 0; i < COORDINATES.length; ++i) {
      for (int j = 0; j < COORDINATES.length; ++j) {
        final HexCoordinate h1 = new HexCoordinate(i, j);

        for (int h = 0; h < COORDINATES.length; ++h) {
          for (int k = 0; k < COORDINATES.length; ++k) {
            final HexCoordinate h2 = new HexCoordinate(h, k);

            if (i == h && k == j) {
              assertEquals(h1, h2);
            } else {
              assertNotEquals(h1, h2);
            }
          }
        }
      }
    }
  }

  /**
   * The equals method should be reflexive.
   */
  @Test
  public void equals_shouldBeReflexive() {
    final String message = "HexCoordinate objects which are the same instance should be equal";

    for (int i = 0; i < COORDINATES.length; ++i) {
      for (int j = 0; j < COORDINATES.length; ++j) {
        final HexCoordinate h1 = new HexCoordinate(i, j);
        assertEquals(message, h1, h1);
      }
    }
  }

  /**
   * The equals method should be symetric.
   */
  @Test
  public void equals_shouldBeSymetric() {
    for (int i = 0; i < COORDINATES.length; ++i) {
      for (int j = 0; j < COORDINATES.length; ++j) {
        final HexCoordinate h1 = new HexCoordinate(i, j);

        for (int h = 0; h < COORDINATES.length; ++h) {
          for (int k = 0; k < COORDINATES.length; ++k) {
            final HexCoordinate h2 = new HexCoordinate(h, k);

            if (h1.equals(h2)) {
              assertEquals(h2, h1);
            } else {
              assertNotEquals(h2, h1);
            }
          }
        }
      }
    }
  }

  /**
   * The equals method should be transitive.
   */
  @Test
  public void equals_shouldBeTransitive() {
    for (int i = 0; i < COORDINATES.length; ++i) {
      for (int j = 0; j < COORDINATES.length; ++j) {
        final HexCoordinate h1 = new HexCoordinate(i, j);

        for (int h = 0; h < COORDINATES.length; ++h) {
          for (int k = 0; k < COORDINATES.length; ++k) {
            final HexCoordinate h2 = new HexCoordinate(h, k);

            for (int g = 0; g < COORDINATES.length; ++g) {
              for (int l = 0; l < COORDINATES.length; ++l) {
                final HexCoordinate h3 = new HexCoordinate(g, l);

                if (h1.equals(h2) && h2.equals(h3)) {
                  final String message
                      = "If h1 equals h2 and h2 equals h3, then h1 should equal h3";
                  assertEquals(message, h1, h3);
                } else if (!h1.equals(h2) && h2.equals(h3)) {
                  final String message
                      = "If h1 does not equal h2 but h2 equals h3, then h1 should not equal h3";
                  assertNotEquals(message, h1, h3);
                } else if (h1.equals(h2) && !h2.equals(h3)) {
                  final String message
                      = "If h1 equals h2 but h2 does not equal h3, then h1 should not equal h3";
                  assertNotEquals(message, h1, h3);
                }
              }
            }
          }
        }
      }
    }
  }

  /**
   * The equals method should be consistent.
   */
  @Test
  public void equals_shouldBeConsistent() {
    final String message = "Equality between HexCoordinate objects should be consistent";

    for (int i = 0; i < COORDINATES.length; ++i) {
      for (int j = 0; j < COORDINATES.length; ++j) {
        final HexCoordinate h1 = new HexCoordinate(i, j);

        for (int h = 0; h < COORDINATES.length; ++h) {
          for (int k = 0; k < COORDINATES.length; ++k) {
            final HexCoordinate h2 = new HexCoordinate(h, k);

            final boolean expected = h1.equals(h2);

            for (int l = 0; l < CONSISTENCY_RUNS; ++l) {
              final boolean actual = h1.equals(h2);
              assertEquals(message, expected, actual);
            }
          }
        }
      }
    }
  }

  /**
   * The hash code of 2 hex coordinates should be equal if and only if the
   * objects are equal.
   */
  @Test
  public void hashCode_ShouldBeEqualIfAndOnlyIfObjectsAreEqual() {
    for (int i = 0; i < COORDINATES.length; ++i) {
      for (int j = 0; j < COORDINATES.length; ++j) {
        final HexCoordinate h1 = new HexCoordinate(i, j);

        for (int h = 0; h < COORDINATES.length; ++h) {
          for (int k = 0; k < COORDINATES.length; ++k) {
            final HexCoordinate h2 = new HexCoordinate(h, k);

            if (h1.equals(h2)) {
              assertEquals(h1.hashCode(), h2.hashCode());
            } else {
              assertNotEquals(h1.hashCode(), h2.hashCode());
            }
          }
        }
      }
    }
  }

  /**
   * The to string method should return the formatted x and y coordinates.
   */
  @Test
  public void toString_shouldReturnTheXAndYCoordinates() {
    for (int i = 0; i < COORDINATES.length; ++i) {
      for (int j = 0; j < COORDINATES.length; ++j) {
        final HexCoordinate h = new HexCoordinate(i, j);
        final String expected = "(" + h.getX() + ", " + h.getY() + ")";
        final String actual = h.toString();
        assertEquals(expected, actual);
      }
    }
  }
}
