/**
 * 
 */
package com.ysahin.devicepool.device;

import com.ysahin.devicepool.constants.DeviceStatus;
import com.ysahin.devicepool.device.ws.ServerEndPoint;

import lombok.Data;

/**
 * @author yusufSahin
 *
 */

/*
 * Each device has serialNumber, name, type, running status. And also it is avaliable for Internet or not.
 * These properties are defined here. Server access info are passed to device.
 */
@Data
public abstract class Device {

	private String id;
	private String name;
	private int type;
	private DeviceStatus status;
	private boolean networkAvailable;
	private boolean registered;
	private ServerEndPoint serverEndPoint;

}
