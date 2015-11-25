/**
 * 
 */
package com.bonavita.property.services.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bonavita.property.services.entity.ClusterNode;

/**
 * @author mukesh
 *
 */
public interface ClusterNodeRepository extends JpaRepository<ClusterNode, String> {

}
