package com.bms.repositypck;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bms.entity.LoginRoles;

import java.lang.String;
import java.util.List;




@Repository
public interface LoginRolesRepos extends JpaRepository<LoginRoles,Long> {
	
	List<LoginRoles> findByRole(String role);
	List<LoginRoles> findByRoleAndLstLoginCredentialsUserNameAndLstLoginCredentialsPassword(String role,String username,String password);

//	Page<Rawdata> findAllByOrderByServerTimeDesc(Pageable pageable);
//	List<VehicleRegistrationNumbers>  findAllByOrderByServerTimeDesc();
//	List<VehicleRegistrationNumbers> findByFencingAreaDetailIdOrderByServerTimeDesc(Long fencingAreaDetailId);
//	 List<VehicleRegistrationNumbers> findByVehicleReg(String vehicleReg);  
	        
}
