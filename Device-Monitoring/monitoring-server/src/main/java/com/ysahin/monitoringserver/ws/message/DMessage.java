package com.ysahin.monitoringserver.ws.message;

import lombok.Data;

@Data
public class DMessage {

	private String id;
	private int type;
	private int status;
	private String deviceId;
	private String deviceName;
	private int deviceType;
	private String deviceStatus;
	private String message;


}
