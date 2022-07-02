package com.ysahin.devicepool.device.ws;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.ysahin.devicepool.constants.DMessageStatus;
import com.ysahin.devicepool.device.ws.message.DMessage;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yusufSahin
 *
 */
/*
 * Device send message using sendDmessage method.
 */

@Slf4j
@Service
public class ServerEndPoint {
	//server endpoint info
	@Value("${server.endpoint.url}")
	private String endPointUrl;

	@Autowired
	RestTemplate restTemplate;

	/*
	 * if any http exception happens in this method. propagate to exception to upper method. 
	 */
	public DMessage sendDMessage(DMessage request) throws RestClientException {
		HttpHeaders headers = new HttpHeaders();
		DMessage response = null;
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<DMessage> entity = new HttpEntity<DMessage>(request, headers);
		log.debug("DMessage is sending... :" + request.toString());
		ResponseEntity<DMessage> responseEntity = restTemplate.exchange(endPointUrl, HttpMethod.POST, entity,
				DMessage.class);
		if (responseEntity.getStatusCode() != HttpStatus.OK) {
			log.error("responseEntity is fail :" + request.toString());
			return null;
		}
		response = responseEntity.getBody();
		// if required response exits in request status , check response status if dmessage is sucess or not  
		if (request.getStatus() == DMessageStatus.REQUIRED_RESPONSE_STATUS.ordinal()) {
			if (Objects.isNull(response)) {
				log.error("message body null and message fail :" + request.toString());
				return null;
			}
			if (response.getStatus() != DMessageStatus.SUCCESS_STATUS.ordinal()) {
				log.error("message status fail :" + request.toString());
				return null;
			}
		}

		log.debug("DMessage is sent and response recieved : " + response.toString());
		return response;
	}

}
