package com.FuegoQuasar.services;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.FuegoQuasar.core.model.FuegoQuasarRequest;
import com.FuegoQuasar.core.model.Position;
import com.FuegoQuasar.core.model.Satelite;
import com.FuegoQuasar.core.response.FuegoQuasarResponse;
import com.FuegoQuasar.core.services.LocationService;
import com.FuegoQuasar.core.services.MessageService;
import com.FuegoQuasar.core.services.TopSecretService;

@ExtendWith(MockitoExtension.class)
public class TopSecretServiceTest {

    @InjectMocks
    private TopSecretService topSecretService;
    @Mock
    private LocationService locationService;
    @Mock
    private MessageService messageService;

    @DisplayName("testCase1")
    @Test
    public void testCase1() throws Exception {

	/*
	 * HashMap<String, Float> distanceMap = new HashMap<>(); HashMap<String,
	 * String[]> messageMap = new HashMap<>(); distanceMap.put("kenobi", 485.41f);
	 * distanceMap.put("skywalker", 265.75f); distanceMap.put("sato", 600.52f);
	 * messageMap.put("kenobi", new String[] { "este", "", "", "mensaje", "" });
	 * messageMap.put("skywalker", new String[] { "", "es", "secreto", "", "" });
	 * messageMap.put("sato", new String[] { "este", "", "un", "", "" });
	 * 
	 * // THEN ResponseEntity<FuegoQuasarResponse> response = new
	 * ResponseEntity<FuegoQuasarResponse>(HttpStatus.ACCEPTED);
	 * 
	 * response.getBody().setPosition(locationService.getLocation(distanceMap.get(
	 * "kenobi"), distanceMap.get("skywalker"), distanceMap.get("sato")));
	 * response.getBody().setMessage(messageService.getMessage(messageMap.get(
	 * "kenobi"), messageMap.get("skywalker"), messageMap.get("sato")));
	 * 
	 * // THEN assertThat(response).isEqualTo(getMocketResponse());
	 */
    }

    private FuegoQuasarRequest getMocketRequest() {
	FuegoQuasarRequest fuegoQuasarRequest = new FuegoQuasarRequest();
	List<Satelite> sateliteList = new ArrayList<Satelite>();
	Satelite satelite = new Satelite();
	satelite.setName("kenobi");
	satelite.setDistance(485.41f);
	String[] message = new String[] { "este", "", "", "mensaje", "" };
	satelite.setMessage(message);
	sateliteList.add(satelite);
	satelite.setName("skywalker");
	satelite.setDistance(265.75f);
	message = new String[] { "", "es", "secreto", "", "" };
	satelite.setMessage(message);
	sateliteList.add(satelite);
	satelite.setName("sato");
	satelite.setDistance(600.52f);
	message = new String[] { "este", "", "un", "", "" };
	satelite.setMessage(message);
	sateliteList.add(satelite);
	fuegoQuasarRequest.setSatelites(sateliteList);
	return fuegoQuasarRequest;
    }

    private ResponseEntity<FuegoQuasarResponse> getMocketResponse() {
	FuegoQuasarResponse fuegoQuasarResponse = new FuegoQuasarResponse();
	Position satelitePosition = new Position();
	satelitePosition.setX(-99.99947f);
	satelitePosition.setY(74.9959f);
	fuegoQuasarResponse.setPosition(satelitePosition);
	fuegoQuasarResponse.setMessage("este es un mensaje secreto");
	return ResponseEntity.ok(fuegoQuasarResponse);
    }
}
