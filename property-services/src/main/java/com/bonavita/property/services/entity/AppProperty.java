/**
 * 
 */
package com.bonavita.property.services.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author mukesh
 *
 */
@Entity
public class AppProperty implements Serializable {
	private static final long serialVersionUID = 8743563386675978736L;
	@Id
	private String name;
	private String value;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

}
