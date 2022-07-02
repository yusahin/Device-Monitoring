package com.ysahin.monitoringserver.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yusufSahin
 *
 */

/*
 * this controller is interface for UI. our home.html file is in resources/templates.
 * 
 * 
 * 
 */
@Controller
public class MonitoringService {

	@Autowired
	DMessageService dmessageService;

	@RequestMapping(value = { "", "/", "home" })
	public String displayHomePage(Model model) {
		model.addAttribute("allDeviceMetrics", dmessageService.getMetrics());
		return "home.html";
	}

	@RequestMapping("/refresh")
	public String refreshMetrics(Model model) {
		model.addAttribute("allDeviceMetrics", dmessageService.getMetrics());
		return "home.html";
	}

}
