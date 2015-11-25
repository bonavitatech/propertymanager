/**
 * 
 */
package com.bonavita.property.services.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bonavita.property.services.entity.SystemProperty;

/**
 * @author mukesh
 *
 */
public interface SystemPropertyRepository extends JpaRepository<SystemProperty, String> {

}
