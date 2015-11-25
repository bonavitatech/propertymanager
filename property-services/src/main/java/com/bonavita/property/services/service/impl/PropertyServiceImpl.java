/**
 * 
 */
package com.bonavita.property.services.service.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.bonavita.property.services.entity.AppProperty;
import com.bonavita.property.services.entity.SystemProperty;
import com.bonavita.property.services.repository.AppPropertyRepository;
import com.bonavita.property.services.repository.SystemPropertyRepository;
import com.bonavita.property.services.service.PropertyService;

/**
 * @author mukesh
 *
 */
@Service
public class PropertyServiceImpl implements PropertyService {
	private Logger LOG = LoggerFactory.getLogger(PropertyServiceImpl.class);
	
	private String appPropertyOverrideFilePath = "/opt/conf/frontend-web/app.properties";
	private String systemPropertyOverrideFilePath = "/opt/conf/frontend-web/system.properties";
	
	private Properties appProperties = new Properties();
	private Properties systemProperties = new Properties();

	@Autowired
	private AppPropertyRepository appPropertyRepository;

	@Autowired
	private SystemPropertyRepository systemPropertyRepository;

	/* (non-Javadoc)
	 * @see com.bonavita.web.services.service.PropertyService#getAppProperty(java.lang.String, java.lang.String)
	 */
	@Override
	public String getAppProperty(String name, String defaultValue) {
		String value = getAppProperty(name);
		if(StringUtils.isEmpty(value)) {
			value = defaultValue;
		}
		return value;
	}

	/* (non-Javadoc)
	 * @see com.bonavita.web.services.service.PropertyService#getAppProperty(java.lang.String)
	 */
	@Override
	public String getAppProperty(String name) {
		return appProperties.getProperty(name);
	}

	/* (non-Javadoc)
	 * @see com.bonavita.web.services.service.PropertyService#getSystemProperty(java.lang.String, java.lang.String)
	 */
	@Override
	public String getSystemProperty(String name, String defaultValue) {
		String value = getSystemProperty(name);
		if(StringUtils.isEmpty(value)) {
			value = defaultValue;
		}
		return value;
	}

	/* (non-Javadoc)
	 * @see com.bonavita.web.services.service.PropertyService#getSystemProperty(java.lang.String)
	 */
	@Override
	public String getSystemProperty(String name) {
		return systemProperties.getProperty(name);
	}

	/* (non-Javadoc)
	 * @see com.bonavita.web.services.service.PropertyService#reloadAppProperties()
	 */
	@Override
	public void loadAppProperties() {
		Properties fileProps = loadPropertiesFromFile(appPropertyOverrideFilePath);
		List<AppProperty> dbProps = appPropertyRepository.findAll();
		
		for(AppProperty p: dbProps) {
			fileProps.putIfAbsent(p.getName(), p.getValue());
		}
		
		this.appProperties = fileProps;
	}

	/* (non-Javadoc)
	 * @see com.bonavita.web.services.service.PropertyService#reloadSystemProperties()
	 */
	@Override
	public void loadSystemProperties() {
		Properties fileProps = loadPropertiesFromFile(systemPropertyOverrideFilePath);
		List<SystemProperty> dbProps = systemPropertyRepository.findAll();
		
		for(SystemProperty p: dbProps) {
			fileProps.putIfAbsent(p.getName(), p.getValue());
		}
		
		this.systemProperties = fileProps;
	}

	private Properties loadPropertiesFromFile(String filePath) {
		Properties properties = new Properties();
		try(InputStream inStream = new FileInputStream(filePath)) {
			properties.load(inStream);
		} catch (IOException e) {
			LOG.warn("problem reading file", e);
		}
		return properties;
	}

	public void setAppPropertyOverrideFilePath(String appPropertyOverrideFilePath) {
		this.appPropertyOverrideFilePath = appPropertyOverrideFilePath;
	}

	public void setSystemPropertyOverrideFilePath(String systemPropertyOverrideFilePath) {
		this.systemPropertyOverrideFilePath = systemPropertyOverrideFilePath;
	}

}
