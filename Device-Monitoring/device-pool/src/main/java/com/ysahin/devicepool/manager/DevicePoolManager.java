package com.ysahin.devicepool.manager;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ysahin.devicepool.device.ws.ServerEndPoint;
import com.ysahin.devicepool.products.Fridge;
import com.ysahin.devicepool.products.Ups;
import com.ysahin.devicepool.products.VacuumMop;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yusufSahin
 *
 */

/*
 * DevicePoolManager generetes devices according to count that is configurated
 * in application.properites
 * 
 */

@Component
@Slf4j
public class DevicePoolManager {

	@Value("${ups.device.count}")
	private int upsCount;

	@Value("${fridge.device.count}")
	private int fridgeCount;

	@Value("${vacuummop.device.count}")
	private int vacuumMop;

	@Autowired
	ServerEndPoint serverEndPoint;

	@PostConstruct
	private void init() {
		for (int index = 0; index < upsCount; index++) {
			Ups ups = new Ups(String.valueOf(index + 1));
			ups.setServerEndPoint(serverEndPoint);
			log.debug("device init : " + ups.toString());
			new Thread(ups, ups.getId()).start();
		}
		for (int index = 0; index < fridgeCount; index++) {
			Fridge fridge = new Fridge(String.valueOf(index + 1));
			fridge.setServerEndPoint(serverEndPoint);
			log.debug("device init : " + fridge.toString());
			new Thread(fridge, fridge.getId()).start();
		}
		for (int index = 0; index < vacuumMop; index++) {
			VacuumMop vaccuumMop = new VacuumMop(String.valueOf(index + 1));
			vaccuumMop.setServerEndPoint(serverEndPoint);
			log.debug("device init : " + vaccuumMop.toString());
			new Thread(vaccuumMop, vaccuumMop.getId()).start();
		}
	}
}
