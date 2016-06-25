package com.peak15.openjciv.board;

/**
 * The Class GamePieceNotFoundException.
 */
public class GamePieceNotFoundException extends Exception {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new game piece not found exception.
   */
  public GamePieceNotFoundException() {
    super();
  }

  /**
   * Instantiates a new game piece not found exception.
   *
   * @param message the message
   */
  public GamePieceNotFoundException(final String message) {
    super(message);
  }

  /**
   * Instantiates a new game piece not found exception.
   *
   * @param cause the cause
   */
  public GamePieceNotFoundException(final Throwable cause) {
    super(cause);
  }

  /**
   * Instantiates a new game piece not found exception.
   *
   * @param message the message
   * @param cause the cause
   */
  public GamePieceNotFoundException(final String message, final Throwable cause) {
    super(message, cause);
  }

  /**
   * Instantiates a new game piece not found exception.
   *
   * @param message the message
   * @param cause the cause
   * @param enableSuppression the enable suppression
   * @param writableStackTrace the writable stack trace
   */
  public GamePieceNotFoundException(final String message, final Throwable cause,
      final boolean enableSuppression, final boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

}
