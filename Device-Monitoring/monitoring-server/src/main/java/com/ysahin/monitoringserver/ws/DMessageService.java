package com.ysahin.monitoringserver.ws;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ysahin.monitoringserver.constants.DMessageStatus;
import com.ysahin.monitoringserver.constants.DMessageType;
import com.ysahin.monitoringserver.constants.MonitoringServerConstants;
import com.ysahin.monitoringserver.metrics.DeviceMetrics;
import com.ysahin.monitoringserver.ws.message.DMessage;

import lombok.extern.slf4j.Slf4j;
/**
 * @author yusufSahin
 *
 */

/*
 * this controller consumes dmessages that comes from devices in receive method.
 * device metrics are stored in concurrentHashmap. Because this map is used from webservice to monitor.
 *  
 * 
 */

@Slf4j
@Controller
@RequestMapping(MonitoringServerConstants.REST_DMESSAGE)
public class DMessageService {

	private static ConcurrentHashMap<String, DeviceMetrics> DEVICE_METRICS = new ConcurrentHashMap<String, DeviceMetrics>();

	@RequestMapping(method = RequestMethod.POST, value = "/recieve", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody DMessage recieve(@RequestBody DMessage request) {
		if (request == null) {
			log.error("invalid request");
		}
		DMessage response = new DMessage();
		try {
			response.setId(request.getId());
			int messageType = request.getType();
			int messageResponseStatus = DMessageStatus.FAIL_STATUS.ordinal();
			String deviceId = request.getDeviceId();
			log.debug("dmessage recieved : " + request.toString());

			switch (messageType) {

			case DMessageType.REGISTER_MSG:
				if (DEVICE_METRICS.containsKey(deviceId)) {
					DEVICE_METRICS.get(deviceId).incrementRegisterRequestCount();
					messageResponseStatus = DMessageStatus.SUCCESS_STATUS.ordinal();
					log.warn("device already registered.");
					break;
				}
				DeviceMetrics metrics = new DeviceMetrics();
				metrics.setDeviceId(deviceId);
				metrics.setDeviceName(request.getDeviceName());
				metrics.setDeviceStatus(request.getDeviceStatus());
				metrics.setDeviceRegisterRequestCount(1);
				DEVICE_METRICS.put(deviceId, metrics);
				messageResponseStatus = DMessageStatus.SUCCESS_STATUS.ordinal();
				break;

			case DMessageType.STATUS_MSG:
				if (DEVICE_METRICS.containsKey(deviceId) == false) {
					messageResponseStatus = DMessageStatus.FAIL_STATUS.ordinal();
					log.debug("device must be registered");
					break;
				}
				DEVICE_METRICS.get(deviceId).setDeviceStatus(request.getDeviceStatus());
				DEVICE_METRICS.get(deviceId).incrementCheckHealthCount();
				messageResponseStatus = DMessageStatus.SUCCESS_STATUS.ordinal();
				break;

			case DMessageType.CRITICAL_ALERT_MSG:
				if (DEVICE_METRICS.containsKey(deviceId) == false) {
					messageResponseStatus = DMessageStatus.FAIL_STATUS.ordinal();
					log.debug("device must be registered");
					break;
				}
				DEVICE_METRICS.get(deviceId).setDeviceStatus(request.getDeviceStatus());
				DEVICE_METRICS.get(deviceId).incrementAlertCount();
				messageResponseStatus = DMessageStatus.SUCCESS_STATUS.ordinal();
				break;

			default:
				messageResponseStatus = DMessageStatus.FAIL_STATUS.ordinal();
				log.error("undefined messageType : " + messageType);
				break;
			}

			if (request.getStatus() == DMessageStatus.REQUIRED_RESPONSE_STATUS.ordinal()) {
				response.setStatus(messageResponseStatus);
			}
			return response;
		} catch (Exception e) {
			log.error("While message proccesing, an exception occured!", e);
			response.setStatus(DMessageStatus.FAIL_STATUS.ordinal());
			return response;
		}

	}

	@RequestMapping("/")
	public String displayHomePage() {
		return "home.html";
	}

	public List<DeviceMetrics> getMetrics() {
		ArrayList<DeviceMetrics> listDeviceMetrics = DEVICE_METRICS.values().stream()
				.collect(Collectors.toCollection(ArrayList::new));
		return listDeviceMetrics;
	}
}