package com.peak15.openjciv.board;

/**
 * The Class GamePieceMovementException.
 */
public class GamePieceMovementException extends Exception {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new game piece movement exception.
   */
  public GamePieceMovementException() {
    super();
  }

  /**
   * Instantiates a new game piece movement exception.
   *
   * @param message
   *          the message
   */
  public GamePieceMovementException(final String message) {
    super(message);
  }

  /**
   * Instantiates a new game piece movement exception.
   *
   * @param cause
   *          the cause
   */
  public GamePieceMovementException(final Throwable cause) {
    super(cause);
  }

  /**
   * Instantiates a new game piece movement exception.
   *
   * @param message
   *          the message
   * @param cause
   *          the cause
   */
  public GamePieceMovementException(final String message, final Throwable cause) {
    super(message, cause);
  }

  /**
   * Instantiates a new game piece movement exception.
   *
   * @param message
   *          the message
   * @param cause
   *          the cause
   * @param enableSuppression
   *          the enable suppression
   * @param writableStackTrace
   *          the writable stack trace
   */
  public GamePieceMovementException(final String message, final Throwable cause,
      final boolean enableSuppression, final boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

}
