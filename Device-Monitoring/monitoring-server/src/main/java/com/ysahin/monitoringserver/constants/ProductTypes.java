package com.ysahin.monitoringserver.constants;


/**
 * @author yusufSahin
 *
 */


/*
 * please, look at ProductTypes enum in device-pool module.
 */
public enum ProductTypes {


	UPS("Uninterrupted Power Supply"), FRIDGE("Frigde"), VACUUM_MOP("Vacuum Mop");

	private String type;

	private ProductTypes(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return type;
	}

	public String getValue() {
		return type;
	}



}
