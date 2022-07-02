package com.ysahin.devicepool.constants;

/**
 * @author yusufSahin
 *
 */
/*
 * we can define lots of dmessageType. 
 * this case depends on device. if you want to send different messagetype to server, 
 * you have to define your dmessageType both device and server side
 */
public enum DMessageType {

	REGISTER_MSG(100), STATUS_MSG(101), CRITICAL_ALERT_MSG(102);

	private int id;

	DMessageType(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return String.valueOf(id);
	}

	public int getId() {
		return id;
	}
}
