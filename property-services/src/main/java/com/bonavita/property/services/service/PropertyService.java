/**
 * 
 */
package com.bonavita.property.services.service;

/**
 * @author mukesh
 *
 */
public interface PropertyService {

	public String getAppProperty(String name, String defaultValue);
	public String getAppProperty(String name);
	public String getSystemProperty(String name, String defaultValue);
	public String getSystemProperty(String name);
	public void loadAppProperties();
	public void loadSystemProperties();
	
}
