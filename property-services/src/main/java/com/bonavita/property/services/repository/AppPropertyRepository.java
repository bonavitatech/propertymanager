/**
 * 
 */
package com.bonavita.property.services.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bonavita.property.services.entity.AppProperty;

/**
 * @author mukesh
 *
 */
public interface AppPropertyRepository extends JpaRepository<AppProperty, String> {

}
