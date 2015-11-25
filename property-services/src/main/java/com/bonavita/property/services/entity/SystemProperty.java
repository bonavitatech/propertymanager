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
public class SystemProperty implements Serializable {
	private static final long serialVersionUID = 4968421453336832055L;
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
