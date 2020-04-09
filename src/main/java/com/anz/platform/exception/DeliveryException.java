package com.anz.platform.exception;

import com.anz.platform.exception.base.UnknownException;

public class DeliveryException extends UnknownException {

  private static final long serialVersionUID = 1L;

  public DeliveryException(final String errorMessage) {
    super(errorMessage);
  }

  public DeliveryException(final String errorMessage, final String key) {
    super(errorMessage);
    this.setKey(key);
  }
}
