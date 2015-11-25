/**
 * 
 */
package com.bonavita.property.services.repository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonavita.property.services.entity.ClusterNode;
import com.bonavita.property.services.service.ClusterService;

/**
 * @author mukesh
 *
 */
@Service
public class ClusterServiceImpl implements ClusterService {

	@Autowired
	private ClusterNodeRepository clusterNodeRepository;
	
	/* (non-Javadoc)
	 * @see com.bonavita.property.services.service.ClusterService#getAllNodeIPs()
	 */
	@Override
	public List<ClusterNode> getAllNodes() {
		List<ClusterNode> nodes = clusterNodeRepository.findAll();
		return nodes;
	}
	
	/* (non-Javadoc)
	 * @see com.bonavita.property.services.service.ClusterService#getNodes()
	 */
	@Override
	public List<ClusterNode> getNodes(List<String> ips) {
		List<ClusterNode> nodes = clusterNodeRepository.findAll(ips);
		return nodes;
	}
	
	/* (non-Javadoc)
	 * @see com.bonavita.property.services.service.ClusterService#touchNode()
	 */
	@Override
	public void touchNode(String ip) {
		ClusterNode node = clusterNodeRepository.findOne(ip);
		Date now = new Date();
		if(node == null) {
			node = new ClusterNode();
			node.setHostIP(ip);
			node.setCreatedAt(now);
		}
		node.setUpdatedAt(now);
		clusterNodeRepository.save(node);
	}

	/* (non-Javadoc)
	 * @see com.bonavita.property.services.service.ClusterService#removeNotReachableNodes()
	 */
	@Override
	public void removeNotTouchedNodes() {
		List<ClusterNode> nodes = clusterNodeRepository.findAll();
		
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime nowMinusHr = now.minusHours(1l);
		List<ClusterNode> toRemove = new LinkedList<>();
		
		for(ClusterNode node: nodes) {
			LocalDateTime lastTouched = LocalDateTime.ofInstant(
					node.getUpdatedAt().toInstant(), ZoneId.systemDefault());
			if(lastTouched.isBefore(nowMinusHr)) {
				toRemove.add(node);
			}
		}
		
		clusterNodeRepository.deleteInBatch(toRemove);
	}

}
