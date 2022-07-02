package com.ysahin.devicepool.utils;

import java.util.Random;

/**
 * @author yusufSahin
 *
 */

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Utils {

	public static int genereteNumber() {
		Random r = new Random();
		int number = r.nextInt(100) + 1;
		log.debug("genereted number : " + number);
		return number;
	}

}
