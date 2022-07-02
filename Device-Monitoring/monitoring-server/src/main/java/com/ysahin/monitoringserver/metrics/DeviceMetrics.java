package com.ysahin.monitoringserver.metrics;

import lombok.Data;

/**
 * @author yusufSahin
 *
 */

/*
 * this class has device info, status and some device metrics. it is displayed on localhost:7070/
 * 
 */

@Data
public class DeviceMetrics {
	private String deviceId;
	private String deviceName;
	private String deviceStatus;
	private int deviceRegisterRequestCount;
	private int deviceCheckHealthCount;
	private int deviceAlertCount;

	public void incrementRegisterRequestCount() {
		this.deviceRegisterRequestCount++;
	}

	public void incrementCheckHealthCount() {
		this.deviceCheckHealthCount++;
	}

	public void incrementAlertCount() {
		this.deviceAlertCount++;
	}

}
