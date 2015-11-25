/**
 * 
 */
package com.bonavita.property.services.service;

import java.util.List;

import com.bonavita.property.services.entity.ClusterNode;

/**
 * @author mukesh
 *
 */
public interface ClusterService {
	
	public List<ClusterNode> getAllNodes();
	
	public List<ClusterNode> getNodes(List<String> ips);
	
	public void touchNode(String ip);
	
	public void removeNotTouchedNodes();

}
