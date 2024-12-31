package com.bms.repositypck;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bms.entity.AccessPermissions;

import java.lang.String;




@Repository
public interface AccessPermissionsRepos extends JpaRepository<AccessPermissions,Long> {

////	Page<Rawdata> findAllByOrderByServerTimeDesc(Pageable pageable);
//	List<VehicleRegistrationNumbers>  findAllByOrderByServerTimeDesc();
//	List<VehicleRegistrationNumbers> findByFencingAreaDetailIdOrderByServerTimeDesc(Long fencingAreaDetailId);
//	 List<VehicleRegistrationNumbers> findByVehicleReg(String vehicleReg);  
	        
}
