package com.FuegoQuasar.core.services;

import org.springframework.stereotype.Service;

@Service
public class MessageService {

    public String getMessage(String[] kenobiMessage, String[] skywalkerMessage, String[] satoMessage) {
	String message = "";
	for (int i = 0; i < kenobiMessage.length; i++) {
	    if (i > 0) {
		message += " ";
	    }
	    if (!kenobiMessage[i].equals("") && !kenobiMessage[i].isEmpty()) {
		message += kenobiMessage[i];
	    } else if (!skywalkerMessage[i].equals("") && !skywalkerMessage[i].isEmpty()) {
		message += skywalkerMessage[i];
	    } else if (!satoMessage[i].equals("") && !satoMessage[i].isEmpty()) {
		message += satoMessage[i];
	    }
	}
	return message.trim();
    }
}
