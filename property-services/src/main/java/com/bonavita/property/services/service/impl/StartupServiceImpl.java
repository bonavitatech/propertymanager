/**
 * 
 */
package com.bonavita.property.services.service.impl;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonavita.property.services.service.PropertyService;
import com.bonavita.property.services.service.StartupService;

/**
 * WARNING: Please do not change name of functions that have prefix as "load". This class follows a pattern for method
 * names i.e. "load" + "{order}" + "cacheName". All functions with prefix "load" will automatically be picked during init. 
 * cacheName will be used for selectively refreshing cache.
 * 
 * @author mukesh
 *
 */
@Service
public class StartupServiceImpl implements StartupService {
	private Logger LOG = LoggerFactory.getLogger(StartupServiceImpl.class);

	private Map<String, Method> methodMap = new TreeMap<String, Method>();

	@Autowired
	private PropertyService propertyService;

	@PostConstruct
	public void initialize() {
		Method[] methods = this.getClass().getDeclaredMethods();
		for (Method method : methods) {
			if (method.getName().startsWith("load")) {
				methodMap.put(method.getName(), method);
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.bonavita.web.services.service.StartupService#reloadAll()
	 */
	@Override
	public void reloadAll() {
		for (String methodName : methodMap.keySet()) {
			try {
				long st = System.currentTimeMillis();
				LOG.info("Invoking {} @ {}", methodName, new Date());
				methodMap.get(methodName).invoke(this);
				long et = System.currentTimeMillis();
				LOG.info("Invoking {} successful. Time taken: {} ms", methodName, et - st);
			} catch (Exception e) {
				LOG.error("Error loading {} ", methodName);
				LOG.error(e.getMessage(), e);
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.bonavita.web.services.service.StartupService#loadSelective()
	 */
	@Override
	public boolean reloadSelective(String methodName) {
		boolean result = false;
		if (methodName == null)
			return result;
		for (String methodNameStr : methodMap.keySet()) {
			if (methodNameStr.endsWith(methodName)) {
				try {
					long st = System.currentTimeMillis();
					LOG.info("Selective Invoking {} @ {}", methodNameStr, new Date());
					methodMap.get(methodNameStr).invoke(this);
					long et = System.currentTimeMillis();
					LOG.info("Selective Invoking {} successful. Time taken: {} ms", methodNameStr, et - st);
					result = true;
				} catch (Exception e) {
					LOG.error("Error loading {} ", methodName);
					LOG.error(e.getMessage(), e);
				}
				break;
			}
		}
		if (!result) {
			LOG.info("No method found with name {}", methodName);
		}
		return result;
	}

	@SuppressWarnings("unused")
	private void load01AppProperties() {
		propertyService.loadAppProperties();
	}

	@SuppressWarnings("unused")
	private void load01SystemProperties() {
		propertyService.loadSystemProperties();
	}
	
}
