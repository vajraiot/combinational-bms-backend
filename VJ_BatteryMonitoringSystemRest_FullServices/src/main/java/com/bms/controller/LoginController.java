package com.bms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bms.entity.LoginRoles;
import com.bms.pojo.ApiResponSestatus;
import com.bms.pojo.PlainLoginDetailsDTO;
import com.bms.services.LoginService;

@CrossOrigin
@RestController
public class LoginController {

	@Autowired
	LoginService loginService;

	@GetMapping(value = "/GetAllLoginDetails")
	public List<LoginRoles> getAllLoginDetails() {
		List<LoginRoles> lstLoginRoles = loginService.getAllLoginsWithAccessPermissions();

		return lstLoginRoles;
	}

	@CrossOrigin
	@GetMapping(value = "/GetAllLoginDetailsByRoleName")
	public List<LoginRoles> getAllLoginDetailsByRoleName(String role) {
		List<LoginRoles> lstLoginRoles = loginService.getAllLoginsWithAccessPermissions(role);

		return lstLoginRoles;
	}

	@CrossOrigin
	@GetMapping(value = "/getListofLoginRoles")
	public List<String> getListofLoginRoles() {
		List<String> lststringLoginRoles = loginService.getAllListofLoginRoles();

		return lststringLoginRoles;
	}

	@CrossOrigin
	@GetMapping(value = "/GetAllLoginDetailsByCredentials")
	public LoginRoles getAllLoginDetailsByCredentials(String role, String username, String password) {
		LoginRoles rtnLoginRoles = loginService.getLoginWithAccessPermissiontByCredentials(role, username, password);

		return rtnLoginRoles;
	}

	/******************* for user management service *****************/
	@CrossOrigin
	@GetMapping(value = "/GetAllLoginDetailsInPlainLoginDetailFormate")
	public List<PlainLoginDetailsDTO> getAllLoginDetailsInPlainLoginDetailFormate() {
		List<PlainLoginDetailsDTO> lstLoginRoles = loginService.getAllLoginDetailsInPlainLoginDetailFormate();

		return lstLoginRoles;
	}

	@CrossOrigin
	@PostMapping(value = "/PostCreateNewLoginUser")
	public ApiResponSestatus postCreateNewLoginUser(@RequestBody LoginRoles loginRoles) {
		ApiResponSestatus apiResponSestatus = loginService.createNewUserLogin(loginRoles);

		return apiResponSestatus;
	}

	@CrossOrigin
	@PostMapping(value = "/PostUpdateLoginUser")
	public ApiResponSestatus postUpdateLoginUser(@RequestBody LoginRoles loginRoles) {
		// ApiResponSestatus
		// apiResponSestatus=loginService.createNewUserLogin(loginRoles);
		ApiResponSestatus apiResponSestatus = loginService.updateUserLoging(loginRoles);

		return apiResponSestatus;
	}

	@CrossOrigin
	@DeleteMapping(value = "/DeleteLoginUserByLoginCredId")
	public ApiResponSestatus deleteLoginUserByLoginCredId(Long loginCredId) {
		// ApiResponSestatus
		// apiResponSestatus=loginService.createNewUserLogin(loginRoles);
		ApiResponSestatus apiResponSestatus = loginService.deleteLoginDetailsByLoginCredId(loginCredId);

		return apiResponSestatus;
	}

	/***************************************************************/

}
