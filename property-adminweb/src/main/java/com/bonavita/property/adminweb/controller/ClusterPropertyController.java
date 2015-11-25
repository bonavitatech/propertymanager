/**
 * 
 */
package com.bonavita.property.adminweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bonavita.property.services.entity.ClusterNode;
import com.bonavita.property.services.service.ClusterService;

/**
 * @author mukesh
 *
 */
@Controller
@RequestMapping("cluster/property")
public class ClusterPropertyController {
	
	@Autowired
	private ClusterService clusterService;
	
	@RequestMapping("/reload")
	public String reload(@RequestParam("name") String name, 
			@RequestParam(name="allNodes", required=false, defaultValue="false") boolean allNodes, 
			@RequestParam(name="ip", required=false) String[] ips) {
		
		List<ClusterNode> nodes = null;
		if(allNodes) {
			nodes = clusterService.getAllNodes();
		} else {
			
		}
		String response = null;
		
		return response;
	}
	
}