package com.ysahin.devicepool.products;

import java.util.UUID;

import com.ysahin.devicepool.constants.DMessageStatus;
import com.ysahin.devicepool.constants.DMessageType;
import com.ysahin.devicepool.constants.DeviceStatus;
import com.ysahin.devicepool.constants.ProductTypes;
import com.ysahin.devicepool.device.Device;
import com.ysahin.devicepool.device.IDevice;
import com.ysahin.devicepool.device.ws.message.DMessage;
import com.ysahin.devicepool.utils.Utils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yusufSahin
 *
 */

/*
 * this is a product like others. First of all, each product is registed to
 * server for monitoring. then it sends health check status to server
 * periodically. Sometimes device generates alert message and it sends these
 * message to server. alert generate mechanism depends on Random number that is
 * between 0 and 100. if generated random number is grater then 90, alert
 * message are generated.
 * 
 * This flow is implemented in run method.
 */

@Slf4j
public class VacuumMop extends Device implements IDevice {

	public VacuumMop(String serialNumber) {
		this.setId(String.valueOf(ProductTypes.VACUUM_MOP.name() + "-" + serialNumber));
		this.setName(ProductTypes.VACUUM_MOP.getValue());
		this.setType(ProductTypes.VACUUM_MOP.ordinal());
		this.setStatus(DeviceStatus.ACTIVE);
		this.setRegistered(false);
		this.setNetworkAvailable(true);
	}

	@Override
	public void run() {
		if (isNetworkAvailable()) {
			if (this.register() == false) {
				log.error(this.getName() + " is not registered to Server.");
			} else {
				log.debug(this.getName() + " is registered successfully.");
			}

		}

		while (isNetworkAvailable()) {
			try {
				if (this.isRegistered() == false) {
					Thread.sleep(1000l);
					this.register();
					continue;
				}
				if (Utils.genereteNumber() > 90) {
					if (this.alert() == false) {
						log.error("Ups could not sent alert message");
					}
				}
				Thread.sleep(5000l);
				this.healthCheck();

			} catch (InterruptedException e) {
				log.error("InterruptedException");
			}
		}

	}

	public boolean register() {
		try {
			DMessage dmessage = new DMessage();
			dmessage.setId(UUID.randomUUID().toString());
			dmessage.setType(DMessageType.REGISTER_MSG.getId());
			dmessage.setStatus(DMessageStatus.REQUIRED_RESPONSE_STATUS.ordinal());
			dmessage.setDeviceId(getId());
			dmessage.setDeviceName(getName());
			dmessage.setDeviceType(getType());
			dmessage.setDeviceStatus(getStatus().getValue());
			if (this.getServerEndPoint().sendDMessage(dmessage) == null) {
				return false;
			}
			this.setRegistered(true);
			return true;
		} catch (Exception e) {
			log.error("Exception occured.", e);
			return false;
		}

	}

	public boolean alert() {
		try {
			DMessage dmessage = new DMessage();
			dmessage.setId(UUID.randomUUID().toString());
			dmessage.setType(DMessageType.CRITICAL_ALERT_MSG.getId());
			dmessage.setStatus(DMessageStatus.REQUIRED_RESPONSE_STATUS.ordinal());
			dmessage.setDeviceId(getId());
			dmessage.setDeviceName(getName());
			dmessage.setDeviceType(getType());
			dmessage.setDeviceStatus(DeviceStatus.INACTIVE.getValue());
			dmessage.setMessage("something goes wrong!");
			if (this.getServerEndPoint().sendDMessage(dmessage) == null) {
				return false;
			}
			return true;
		} catch (Exception e) {
			log.error("Exception occured.", e);
			return false;
		}

	}

	public void healthCheck() {
		try {
			DMessage dmessage = new DMessage();
			dmessage.setId(UUID.randomUUID().toString());
			dmessage.setType(DMessageType.STATUS_MSG.getId());
			dmessage.setStatus(DMessageStatus.NO_REQUIRED_RESPONSE_STATUS.ordinal());
			dmessage.setDeviceId(getId());
			dmessage.setDeviceName(getName());
			dmessage.setDeviceType(getType());
			dmessage.setDeviceStatus(getStatus().getValue());
			this.getServerEndPoint().sendDMessage(dmessage);
		} catch (Exception e) {
			log.error("Exception occured.", e);
		}

	}
}
