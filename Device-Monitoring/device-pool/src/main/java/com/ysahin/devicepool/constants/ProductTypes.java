package com.ysahin.devicepool.constants;


/**
 * @author yusufSahin
 *
 */

/*
 * These are our products. They can communicate with server over dmessage. 
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
