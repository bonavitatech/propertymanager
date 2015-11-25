/**
 * 
 */
package com.bonavita.property.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bonavita.property.services.service.StartupService;

/**
 * @author mukesh
 *
 */
@Controller
@RequestMapping("/property")
public class PropertyController {
	private static final String pwd = "hakunamatata";
	
	@Autowired
	private StartupService startupService;
	
	@RequestMapping("/reload")
	public String reload(@RequestParam("name") String name, @RequestParam("pwd") String pwd) {
		
		if(!PropertyController.pwd.equals(pwd)) {
			return "nothing";
		}
		
		String response = "reload successful";
		
		if("all".equals(name)) {
			startupService.reloadAll();
		} else {
			boolean isSuccess = startupService.reloadSelective(name);
			if(!isSuccess) {
				response = "no property found for " + name;
			}
		}
		
		return response;
	}
	
}
