package com.FuegoQuasar.core.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.FuegoQuasar.core.model.FuegoQuasarRequest;
import com.FuegoQuasar.core.model.Satelite;
import com.FuegoQuasar.core.response.ErrorResponse;
import com.FuegoQuasar.core.response.FuegoQuasarResponse;

@Component
public class TopSecretService {

    @Autowired
    LocationService locationService;

    @Autowired
    MessageService messageService;

    private Float kenobiDistance = 0f;
    private Float skywalkerDistance = 0f;
    private Float satoDistance = 0f;
    private String[] kenobiMessage;
    private String[] skywalkerMessage;
    private String[] satoMessage;

    public ResponseEntity<FuegoQuasarResponse> topSecretService(FuegoQuasarRequest request) {

	FuegoQuasarResponse starship = new FuegoQuasarResponse();

	try {
	    for (Satelite satelite : request.getSatelites()) {
		if (satelite.getName().trim().equals("kenobi")) {
		    // System.out.print("Kenobi");
		    this.kenobiDistance = satelite.getDistance();
		    this.kenobiMessage = satelite.getMessage();
		}
		if (satelite.getName().trim().equals("skywalker")) {
		    // System.out.print("Skywalker");
		    this.skywalkerDistance = satelite.getDistance();
		    this.skywalkerMessage = satelite.getMessage();
		}
		if (satelite.getName().trim().equals("sato")) {
		    // System.out.print("Sato");
		    this.satoDistance = satelite.getDistance();
		    this.satoMessage = satelite.getMessage();
		}
	    }

	    starship.setPosition(locationService.getLocation(kenobiDistance, skywalkerDistance, satoDistance));
	    starship.setMessage(messageService.getMessage(kenobiMessage, skywalkerMessage, satoMessage));
	    return ResponseEntity.ok(starship);
	} catch (Exception ex) {
	    ex.printStackTrace();
	    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}

    }

    public ResponseEntity<?> topSecretSplitService(Satelite request) {
	try {
	    FuegoQuasarResponse starship = new FuegoQuasarResponse();
	    ErrorResponse errorResponse = new ErrorResponse();
	    String messageResponse = "";
	    if (request.getDistance().equals(0f)) {
		starship.setPosition(locationService.getSimpleLocation(request.getDistance(), request.getName()));
	    } else {
		errorResponse.setCodigoError(-1);
		errorResponse.setDescripcionError("ERROR: No hay suficiente informacion");
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
	    }

	    for (String message : request.getMessage()) {
		if (message.isEmpty() || message.equals("")) {
		    errorResponse.setCodigoError(-1);
		    errorResponse.setDescripcionError("ERROR: No hay suficiente informacion");
		    return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
		} else {
		    messageResponse += " " + message;
		}
	    }
	    starship.setMessage(messageResponse.trim());
	    return ResponseEntity.ok(starship);

	} catch (Exception ex) {
	    ex.printStackTrace();
	    ErrorResponse errorResponse = new ErrorResponse();
	    errorResponse.setCodigoError(-1);
	    errorResponse.setDescripcionError(ex.getMessage());
	    return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
    }
}
