/**
 * 
 */
package com.bonavita.property.services.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 
 * @author mukesh
 *
 */
public class NodeUtil {
	private static Logger LOG = LoggerFactory.getLogger(NodeUtil.class);
	
	public static String getNodeIP() {
		String ip = null;
		try {
			ip = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			LOG.error("error getting ip" ,e);
		}
		return ip;
	}
	
	public static String getNodeHost() {
		String host = null;
		try {
			host = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			LOG.error("error getting host name" ,e);
		}
		return host;
	}

}
