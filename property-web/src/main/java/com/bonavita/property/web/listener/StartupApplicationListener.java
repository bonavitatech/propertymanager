/**
 * 
 */
package com.bonavita.property.web.listener;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.bonavita.property.services.service.ClusterService;
import com.bonavita.property.services.service.StartupService;
import com.bonavita.property.services.util.NodeUtil;

/**
 * ApplicationListener to initialize the app
 * 
 * @author mukesh
 *
 */
@Component
public class StartupApplicationListener implements ApplicationListener<ContextRefreshedEvent> {
	private Logger LOG = LoggerFactory.getLogger(StartupApplicationListener.class);
	private static boolean initialized = false;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		LOG.info("Context refresh event is received for {} ", event.getApplicationContext().getDisplayName());
		if (!initialized) {
			ApplicationContext applicationContext = event.getApplicationContext();
			StartupService startupService = applicationContext.getBean(StartupService.class);
			LOG.info("Invoking {} @ {} at startup", "reloadAll", new Date());
			startupService.reloadAll();
			
			ClusterService clusterService = applicationContext.getBean(ClusterService.class);
			String ip = NodeUtil.getNodeIP();
			LOG.info("Registering to cluster at startup with ip {}", ip);
			clusterService.touchNode(NodeUtil.getNodeIP());
			/*
			 * toggle flag so that reloadAll is not called more than once when 
			 * multiple child contexts are getting loaded
			 */
			initialized = true;
		}
	}
}