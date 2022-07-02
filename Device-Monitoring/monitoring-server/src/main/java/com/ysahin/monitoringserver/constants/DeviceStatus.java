package com.ysahin.monitoringserver.constants;

/**
 * @author yusufSahin
 *
 */
/*
 * please, look at DeviceStatus enum in device-pool module.
 */

public enum DeviceStatus {


	INACTIVE("INACTIVE"), ACTIVE("ACTIVE");

	private String desc;

	private DeviceStatus(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return desc;
	}

	public String getDesc() {
		return desc;
	}

}
