package com.bms.repositypck;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bms.entity.LoginCredentials;

import java.lang.String;

@Repository
public interface LoginCredentialsRepos extends JpaRepository<LoginCredentials,Long> {

	 //findByUserNameAndPasswordAndLoginRolesRole()
    List<LoginCredentials> findByLoginRolesRoleAndUserNameAndPassword(String role,String username,String password);
    
    List<LoginCredentials> findByUserName(String username);
    
//	Page<Rawdata> findAllByOrderByServerTimeDesc(Pageable pageable);
//	List<VehicleRegistrationNumbers>  findAllByOrderByServerTimeDesc();
//	List<VehicleRegistrationNumbers> findByFencingAreaDetailIdOrderByServerTimeDesc(Long fencingAreaDetailId);
//	 List<VehicleRegistrationNumbers> findByVehicleReg(String vehicleReg);  
	        
}
