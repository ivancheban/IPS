package com.exceptions;

import static java.lang.String.format;

public class UserException extends RuntimeException{
    public UserException() {
        super();
    }

    public UserException(String message) {
        super(message);
    }

    public UserException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserException(Throwable cause) {
        super(cause);
    }

    private static UserException throwException(ExceptionType exceptionType, String messageTemplate, String... args) {
        switch (exceptionType) {
            case ENTITY_NOT_FOUND: {
                return new UserException(format(messageTemplate, args));
            }
            case DUPLICATE_ENTITY: {
                return new UserException(format(messageTemplate, args));
            }
            case ENTITY_EXCEPTION: {
                return new UserException(format(messageTemplate, args));
            }
            default: {
                return new UserException(format(messageTemplate, args));
            }
        }
    }

}
