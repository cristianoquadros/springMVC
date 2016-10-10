package com.crossover.service.exception;

public class UserAlreadyExistsException extends RuntimeException {
	private static final long serialVersionUID = 6831455433088029511L;

	public UserAlreadyExistsException(final String message) {
        super(message);
    }

}
