package com.FuegoQuasar.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.FuegoQuasar.core.model.FuegoQuasarRequest;
import com.FuegoQuasar.core.model.Satelite;
import com.FuegoQuasar.core.response.ErrorResponse;
import com.FuegoQuasar.core.services.TopSecretService;

@RestController
@RequestMapping("api/v1")
@CrossOrigin(origins = "*", methods = { RequestMethod.POST })
public class FuegoQuasarController {

    @Autowired
    TopSecretService service;

    @PostMapping("/topsecret")
    public ResponseEntity<?> topSecret(@RequestBody FuegoQuasarRequest request) {
	try {
	    if (isValidRequest(request)) {
		return service.topSecretService(request);
	    } else {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setCodigoError(-1);
		errorResponse.setDescripcionError(HttpStatus.BAD_REQUEST.toString());
		ResponseEntity<ErrorResponse> response = new ResponseEntity<ErrorResponse>(errorResponse,
			HttpStatus.NOT_FOUND);
		return response;
	    }

	} catch (Exception ex) {
	    ex.printStackTrace();
	    ErrorResponse errorResponse = new ErrorResponse();
	    errorResponse.setCodigoError(-1);
	    errorResponse.setDescripcionError(ex.getMessage());
	    return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

    }

    @PostMapping("/topsecret_split/{satelite_name}")
    public ResponseEntity<?> topSecretSplit(@PathVariable("satelite_name") String name, @RequestBody Satelite request) {
	try {
	    request.setName(name);
	    if (isValidSatelite(request)) {
		return service.topSecretSplitService(request);
	    } else {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setCodigoError(-1);
		errorResponse.setDescripcionError(HttpStatus.BAD_REQUEST.toString());
		ResponseEntity<ErrorResponse> response = new ResponseEntity<ErrorResponse>(errorResponse,
			HttpStatus.NOT_FOUND);
		return response;
	    }

	} catch (Exception ex) {
	    ex.printStackTrace();
	    ErrorResponse errorResponse = new ErrorResponse();
	    errorResponse.setCodigoError(-1);
	    errorResponse.setDescripcionError(ex.getMessage());
	    return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

    }

    @GetMapping("/topsecret/{request}")
    public ResponseEntity<?> getTopSecret(@PathVariable("request") List<Satelite> request) {
	try {
	    if (isValidList(request)) {
		FuegoQuasarRequest fuegoQuasarRequest = new FuegoQuasarRequest();
		fuegoQuasarRequest.setSatelites(request);
		return service.topSecretService(fuegoQuasarRequest);
	    } else {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setCodigoError(-1);
		errorResponse.setDescripcionError(HttpStatus.BAD_REQUEST.toString());
		ResponseEntity<ErrorResponse> response = new ResponseEntity<ErrorResponse>(errorResponse,
			HttpStatus.NOT_FOUND);
		return response;
	    }

	} catch (Exception ex) {
	    ex.printStackTrace();
	    ErrorResponse errorResponse = new ErrorResponse();
	    errorResponse.setCodigoError(-1);
	    errorResponse.setDescripcionError(ex.getMessage());
	    return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

    }

    private Boolean isValidRequest(FuegoQuasarRequest request) {

	for (Satelite satelite : request.getSatelites()) {
	    if (satelite.getName() == null || satelite.getName().isEmpty() || satelite.getName().trim().equals("")) {
		return false;
	    }
	    if (satelite.getDistance() == null) {
		return false;
	    }
	}
	return true;
    }

    private Boolean isValidList(List<Satelite> request) {

	for (Satelite satelite : request) {
	    if (satelite.getName() == null || satelite.getName().isEmpty() || satelite.getName().trim().equals("")) {
		return false;
	    }
	    if (satelite.getDistance() == null) {
		return false;
	    }
	}
	return true;
    }

    private Boolean isValidSatelite(Satelite request) {

	if (request.getDistance() == null) {
	    return false;
	}

	return true;
    }
}
