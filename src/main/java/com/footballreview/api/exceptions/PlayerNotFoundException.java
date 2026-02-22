package com.footballreview.api.exceptions;

import java.io.Serial;

public class PlayerNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public PlayerNotFoundException (String message) {
        super(message);
    }
}
