package com.peak15.openjciv.board;

import static org.junit.Assert.*;

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
   * Coordinates at the A, B, C, D, E, and F direction at range 1 are adjacent.
   */
  @Test
  public void coordinatesToTheABCDEFDirectionAtRange1AreAdjacent() {
    
    final HexCoordinate origin = new HexCoordinate(0, 0);
    
    final HexCoordinate aAtRange1 = new HexCoordinate(-1, 0);
    final HexCoordinate bAtRange1 = new HexCoordinate(-1, 1);
    final HexCoordinate cAtRange1 = new HexCoordinate(0, 1);
    final HexCoordinate dAtRange1 = new HexCoordinate(1, 0);
    final HexCoordinate eAtRange1 = new HexCoordinate(1, -1);
    final HexCoordinate fAtRange1 = new HexCoordinate(0, -1);
    
    assertTrue(origin.isAdjacent(aAtRange1));
    assertTrue(origin.isAdjacent(bAtRange1));
    assertTrue(origin.isAdjacent(cAtRange1));
    assertTrue(origin.isAdjacent(dAtRange1));
    assertTrue(origin.isAdjacent(eAtRange1));
    assertTrue(origin.isAdjacent(fAtRange1));
  }
  
  /**
   * Coordinates in any direction but more than 1 range are not adjacent.
   */
  @Test
  public void coordinatesMoreThan1SpaceAwayAreNotAdjacent() {
    
    final HexCoordinate origin = new HexCoordinate(0, 0);
    
    final HexCoordinate aAtRange2 = new HexCoordinate(-2, 0);
    final HexCoordinate bAtRange2 = new HexCoordinate(-2, 2);
    final HexCoordinate cAtRange2 = new HexCoordinate(0, 2);
    final HexCoordinate dAtRange2 = new HexCoordinate(2, 0);
    final HexCoordinate eAtRange2 = new HexCoordinate(2, -2);
    final HexCoordinate fAtRange2 = new HexCoordinate(0, -2);
    
    assertFalse(origin.isAdjacent(aAtRange2));
    assertFalse(origin.isAdjacent(bAtRange2));
    assertFalse(origin.isAdjacent(cAtRange2));
    assertFalse(origin.isAdjacent(dAtRange2));
    assertFalse(origin.isAdjacent(eAtRange2));
    assertFalse(origin.isAdjacent(fAtRange2));
  }
  
  /**
   * Coordinates at the same location are not adjacent.
   */
  @Test
  public void theSameCoordinatesAreNotAdjacent() {
    final HexCoordinate origin = new HexCoordinate(0, 0);
    final HexCoordinate target = new HexCoordinate(0, 0);
    final boolean actual = origin.isAdjacent(target);
    assertFalse(actual);
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
