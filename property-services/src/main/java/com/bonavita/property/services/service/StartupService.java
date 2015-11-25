/**
 * 
 */
package com.bonavita.property.services.service;

/**
 * @author mukesh
 *
 */
public interface StartupService {
	
	public void reloadAll();
	public boolean reloadSelective(String methodName);

}
