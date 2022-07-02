package com.ysahin.devicepool.constants;

/**
 * @author yusufSahin
 *
 */

/*
 * Device communicates with server over DMessage. 
 * DMessage means that Device Message(DMessage). 
 * Dmessage protocol has status filed. it can be success or Fail. 
 * And also when device sends a dmessage to server, it waits response about dmessage recieved or not.  
 * if Device wants response from server, device has to set dmessage status as REQUIRED_RESPONSE_STATUS when it sends message to server.
 * 
 */
public enum DMessageStatus {
	NO_REQUIRED_RESPONSE_STATUS, REQUIRED_RESPONSE_STATUS, FAIL_STATUS, SUCCESS_STATUS;
}
