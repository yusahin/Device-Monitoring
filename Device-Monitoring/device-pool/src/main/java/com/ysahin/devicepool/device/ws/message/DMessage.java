package com.ysahin.devicepool.device.ws.message;

import lombok.Data;

/**
 * @author yusufSahin
 *
 */
/*
 * DMessage is a protocol. it is used to communicate between device and server.
 * 
 */
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
