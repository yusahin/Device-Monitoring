package com.ysahin.devicepool.constants;

/**
 * @author yusufSahin
 *
 */
/*
 * Device can be Active or Inactive. Device has to inform to its status to
 * Server
 */
public enum DeviceStatus {

	INACTIVE("INACTIVE"), ACTIVE("ACTIVE");

	private String value;

	private DeviceStatus(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}

	public String getValue() {
		return value;
	}
}
