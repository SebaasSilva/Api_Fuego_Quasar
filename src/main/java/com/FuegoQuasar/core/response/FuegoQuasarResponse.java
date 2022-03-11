package com.FuegoQuasar.core.response;

import com.FuegoQuasar.core.model.Position;

public class FuegoQuasarResponse {

    private Position position;
    private String message;

    public Position getPosition() {
	return position;
    }

    public void setPosition(Position position) {
	this.position = position;
    }

    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }

}
