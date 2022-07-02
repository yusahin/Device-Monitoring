package com.ysahin.devicepool.device;

/**
 * @author yusufSahin
 *
 */

/*
 * A device have a life cycle like java threads. it starts, performs some task.
 * Then sometimes, it waits in idle status. IDevice can send alert, health
 * status or its identity info.
 */
public interface IDevice extends Runnable {

	public boolean alert();

	public boolean register();

	public void healthCheck();

}
