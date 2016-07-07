package com.peak15.openjciv.player.action;

/**
 * The Class IllegalPlayerActionException.
 */
public class IllegalPlayerActionException extends Exception {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new illegal player action exception.
   */
  public IllegalPlayerActionException() {
    super();
  }

  /**
   * Instantiates a new illegal player action exception.
   *
   * @param message the message
   */
  public IllegalPlayerActionException(final String message) {
    super(message);
  }

  /**
   * Instantiates a new illegal player action exception.
   *
   * @param cause the cause
   */
  public IllegalPlayerActionException(final Throwable cause) {
    super(cause);
  }

  /**
   * Instantiates a new illegal player action exception.
   *
   * @param message the message
   * @param cause the cause
   */
  public IllegalPlayerActionException(final String message, final Throwable cause) {
    super(message, cause);
  }

  /**
   * Instantiates a new illegal player action exception.
   *
   * @param message the message
   * @param cause the cause
   * @param enableSuppression the enable suppression
   * @param writableStackTrace the writable stack trace
   */
  public IllegalPlayerActionException(final String message, final Throwable cause,
      final boolean enableSuppression, final boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

}
