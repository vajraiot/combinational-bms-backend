package com.bms.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bms.entity.AccessPermissions;
import com.bms.entity.LoginCredentials;
import com.bms.entity.LoginRoles;
import com.bms.pojo.ApiResponSestatus;
import com.bms.pojo.PlainLoginDetailsDTO;
import com.bms.repositypck.AccessPermissionsRepos;
import com.bms.repositypck.LoginCredentialsRepos;
import com.bms.repositypck.LoginRolesRepos;

@Component
public interface LoginService {

	public List<LoginRoles> getAllLoginsWithAccessPermissions();

	public List<PlainLoginDetailsDTO> getAllLoginDetailsInPlainLoginDetailFormate();

	public List<LoginRoles> getAllLoginsWithAccessPermissions(String role);

	public LoginRoles getLoginWithAccessPermissiontByCredentials(String role, String username, String password);

	public List<String> getAllListofLoginRoles();

	public ApiResponSestatus createNewUserLogin(LoginRoles loginRoles);

	public ApiResponSestatus updateUserLoging(LoginRoles loginRoles);

	public ApiResponSestatus deleteLoginDetailsByLoginCredId(Long loginCredId);// this is for to delete the record by
																				// logincred id

	@Service
	public class VehicleRegistrationNumbersServiceClass implements LoginService {

		@Autowired
		AccessPermissionsRepos accessPermissionsRepos;

		@Autowired
		LoginCredentialsRepos loginCredentialsRepos;

		@Autowired
		LoginRolesRepos loginRolesRepos;

		@Transactional
		public List<LoginRoles> getAllLoginsWithAccessPermissions() {

			List<LoginRoles> lstLoginRoles = loginRolesRepos.findAll();
			return removeCircularDeppendencyforLoginRoles(lstLoginRoles);
		}

		@Transactional
		public List<PlainLoginDetailsDTO> getAllLoginDetailsInPlainLoginDetailFormate() {

			List<LoginRoles> lstLoginRoles = loginRolesRepos.findAll();
			List<PlainLoginDetailsDTO> rtnPlainLoginDetaisDTO = convertLoginRolesToPlainLoginDetailsDTO(lstLoginRoles);
			// ;

			// return removeCircularDeppendencyforLoginRoles(lstLoginRoles);

			return rtnPlainLoginDetaisDTO;
		}

		@Transactional
		public ApiResponSestatus createNewUserLogin(LoginRoles loginRoles) {

			try {

				List<LoginCredentials> lstLoginCredentialsForUserName = loginCredentialsRepos
						.findByUserName(loginRoles.getLstLoginCredentials().get(0).getUserName()); // this is for
																									// getting record
																									// with usename;

				if (lstLoginCredentialsForUserName != null)
					if (lstLoginCredentialsForUserName.size() > 0) {
						return ApiResponSestatus.builder()
								.message("This UserName Alread Exist in Database Please Try with different User name.")
								.value(0).build();
					}

				LoginRoles instLoginRoles = fetchLoginRoleByRoleName(loginRoles.getRole());// this is for getting the
																							// loginRoleDetail from
																							// database
				if (instLoginRoles == null) {
					return ApiResponSestatus.builder().message("There is no Role Defined in  Record ").value(0).build();
				}

				LoginCredentials instLoginCredentials = saveLoginCredWithLoginRoleReference(
						loginRoles.getLstLoginCredentials().get(0), instLoginRoles);// this is for saving the
																					// loginCredential to database

				saveAccessPermissionsWithLoginCredReference(
						loginRoles.getLstLoginCredentials().get(0).getAccessPermissions(), instLoginCredentials); // this
																													// is
																													// for
																													// save
																													// the
																													// login
																													// access
																													// permission
																													// in
																													// database.

				return ApiResponSestatus.builder().message("successfully Record Saved.").value(1).build();
			} catch (Exception ex) {

				return ApiResponSestatus.builder().message("Exception occured:" + ex.toString()).value(0).build();
			}
		}

		@Transactional
		public ApiResponSestatus updateUserLoging(LoginRoles loginRoles) {

			try {

				// loginRolesRepos. save(loginRoles);

				LoginRoles newLoginRoles = fetchLoginRoleByRoleName(loginRoles.getRole());// this is for getting the
																							// loginRoleDetail from
																							// database

				if (newLoginRoles == null) {
					return ApiResponSestatus.builder().message("There is no Role Defined in  Record ").value(0).build();
				}

				LoginCredentials newLoginCredentials = loginCredentialsRepos
						.findById(loginRoles.getLstLoginCredentials().get(0).getId()).get();

				Long accepermissionId = newLoginCredentials.getAccessPermissions().getId();
				System.out.println("accepermissionId:" + accepermissionId);

				AccessPermissions newAccessPermissions = accessPermissionsRepos.findById(accepermissionId).get();

				AccessPermissions oldAccesPermissions = loginRoles.getLstLoginCredentials().get(0)
						.getAccessPermissions();

				oldAccesPermissions.setId(newAccessPermissions.getId());
				oldAccesPermissions.setLoginCredentials(newAccessPermissions.getLoginCredentials());

				LoginCredentials oldLoginCredentials = loginRoles.getLstLoginCredentials().get(0);
				oldLoginCredentials.setLoginRoles(newLoginRoles);
				oldLoginCredentials.setAccessPermissions(oldAccesPermissions);

				loginCredentialsRepos.save(oldLoginCredentials);

				return ApiResponSestatus.builder().message("successfully Record updated.").value(1).build();
			} catch (Exception ex) {

				return ApiResponSestatus.builder().message("Exception occured:" + ex.toString()).value(0).build();
			}
		}

		@Transactional
		public ApiResponSestatus deleteLoginDetailsByLoginCredId(Long loginCredId) {
			try {
				if ((loginCredId == null) || (loginCredId < new Long(1))) {
					return ApiResponSestatus.builder().message("Please Select the record.").value(0).build();
				} else if (!loginCredentialsRepos.existsById(loginCredId)) {
					return ApiResponSestatus.builder().message("record doesnot exist in database.").value(0).build();

				}
				loginCredentialsRepos.deleteById(loginCredId);
				return ApiResponSestatus.builder().message("Successfully Record deleted.").value(1).build();
			} catch (Exception ex) {
				return ApiResponSestatus.builder().message("Some unwanted Exception occured." + ex.toString()).value(0)
						.build();
			}
		}

		@Transactional
		public List<LoginRoles> getAllLoginsWithAccessPermissions(String role) {

			List<LoginRoles> lstLoginRoles = loginRolesRepos.findByRole(role);
			return removeCircularDeppendencyforLoginRoles(lstLoginRoles);
		}

		@Transactional
		public List<String> getAllListofLoginRoles() {

			List<String> rtnLstLoginRoles = new ArrayList<String>();

			List<LoginRoles> lstLoginRoles = loginRolesRepos.findAll();
			for (LoginRoles inst : lstLoginRoles) {
				rtnLstLoginRoles.add(inst.getRole());
			}
			return (rtnLstLoginRoles);
		}

		/*
		 * @Transactional public List<LoginRoles>
		 * getAllLoginsWithAccessPermissions(String role) {
		 * 
		 * List<LoginRoles> lstLoginRoles=loginRolesRepos.findByRole(role); return
		 * removeCircularDeppendencyforLoginRoles(lstLoginRoles); }
		 */

		@Transactional
		public LoginRoles getLoginWithAccessPermissiontByCredentials(String role, String username, String password) {

			LoginRoles rtnLogingRoles = new LoginRoles();
			// List<LoginRoles>
			// lstLoginRoles=loginRolesRepos.findByRoleAndLstLoginCredentialsUserNameAndLstLoginCredentialsPassword(
			// role, username, password);
			List<LoginCredentials> lstLoginCredentials = loginCredentialsRepos
					.findByLoginRolesRoleAndUserNameAndPassword(role, username, password);
			// return removeCircularDeppendencyforLoginRoles(lstLoginRoles);

			if (lstLoginCredentials == null) {
				return null;
			} else if (lstLoginCredentials.size() < 1) {
				return null;
			}

			rtnLogingRoles = LoginRoles.builder().id(lstLoginCredentials.get(0).getLoginRoles().getId()).role(role)
					.lstLoginCredentials(removeCircularDeppendencyforLoginCredentials(lstLoginCredentials)).build();

			return rtnLogingRoles;
		}

		@Transactional
		public LoginRoles fetchLoginRoleByRoleName(String role) {
			LoginRoles rtnloginrole = new LoginRoles();
			List<LoginRoles> lstLoginRole = loginRolesRepos.findByRole(role);
			if (lstLoginRole == null) {
				return null;
			} else if (lstLoginRole.size() < 1) {
				return null;
			} else if (lstLoginRole.get(0).getId() < 1) {
				return null;
			}

			rtnloginrole = lstLoginRole.get(0);
			return rtnloginrole;

		}

		// @Transactional
		public LoginCredentials saveLoginCredWithLoginRoleReference(LoginCredentials _loginCredentials,
				LoginRoles _loginRoles) {

			/*
			 * List<LoginCredentials> lstLoginCrdentials=new ArrayList<LoginCredentials>();
			 * lstLoginCrdentials.add(_loginCredentials);
			 * 
			 * _loginRoles.setLstLoginCredentials(lstLoginCrdentials);
			 * loginRolesRepos.save(_loginRoles);
			 */

			_loginCredentials.setLoginRoles(_loginRoles);

			loginCredentialsRepos.save(_loginCredentials);
			return _loginCredentials;
		}

		// @Transactional
		public AccessPermissions saveAccessPermissionsWithLoginCredReference(AccessPermissions _accessPermissions,
				LoginCredentials _loginCredentials) {

			_accessPermissions.setLoginCredentials(_loginCredentials);

			accessPermissionsRepos.save(_accessPermissions);
			return _accessPermissions;
		}

		/************************************
		 * removeCircularDeppendency
		 *******************************************************/

		private List<LoginRoles> removeCircularDeppendencyforLoginRoles(List<LoginRoles> baseLstLoginRoles) {
			List<LoginRoles> rtnLstLoginRoles = new ArrayList<LoginRoles>();
			for (LoginRoles baseLstInst : baseLstLoginRoles) {
				List<LoginCredentials> existlstLoginCredentials = new ArrayList<LoginCredentials>();
				List<LoginCredentials> newlstLoginCredentials = new ArrayList<LoginCredentials>();

				if (existlstLoginCredentials != null)
					existlstLoginCredentials = baseLstInst.getLstLoginCredentials();

				newlstLoginCredentials = removeCircularDeppendencyforLoginCredentials(existlstLoginCredentials);

				LoginRoles newLoginRoles = LoginRoles.builder().id(baseLstInst.getId()).role(baseLstInst.getRole())
						.lstLoginCredentials(newlstLoginCredentials).serverTime(baseLstInst.getServerTime()).build();

				rtnLstLoginRoles.add(newLoginRoles);

			}

			return rtnLstLoginRoles;
		}

		private List<LoginCredentials> removeCircularDeppendencyforLoginCredentials(
				List<LoginCredentials> baseLstLoginCredentials) {
			List<LoginCredentials> rtnLstLoginCredentials = new ArrayList<LoginCredentials>();

			for (LoginCredentials baseLstInst : baseLstLoginCredentials) {
				AccessPermissions existAccessPermissions = baseLstInst.getAccessPermissions();
				AccessPermissions newAccessPermissions = new AccessPermissions();

				if (existAccessPermissions != null) {
					newAccessPermissions = AccessPermissions.builder().id(existAccessPermissions.getId())
							.dashBoard(existAccessPermissions.getDashBoard())
							.reportsHistorical(existAccessPermissions.getReportsHistorical())

							.serverTime(existAccessPermissions.getServerTime()).build();
				} else {
					newAccessPermissions = null;
				}

				LoginCredentials newLoginCredentials = new LoginCredentials();
				newLoginCredentials = LoginCredentials.builder().id(baseLstInst.getId())
						.userName(baseLstInst.getUserName()).password(baseLstInst.getPassword())
						.accessPermissions(newAccessPermissions).serverTime(baseLstInst.getServerTime()).build();

				rtnLstLoginCredentials.add(newLoginCredentials);

			}
			return rtnLstLoginCredentials;
		}

		private List<PlainLoginDetailsDTO> convertLoginRolesToPlainLoginDetailsDTO(List<LoginRoles> baselstLoginRoles) {
			List<PlainLoginDetailsDTO> rtnLstPlainLoginDetailsDTO = new ArrayList<PlainLoginDetailsDTO>();

			Long loginUserId = null;
			String setRole;
			for (LoginRoles instLoginRoles : baselstLoginRoles) {

				loginUserId = (instLoginRoles.getId());
				setRole = (instLoginRoles.getRole());

				List<LoginCredentials> ExistLoginCredentials = instLoginRoles.getLstLoginCredentials();

				if (ExistLoginCredentials != null) {
					for (LoginCredentials instLonginCred : ExistLoginCredentials) {

						PlainLoginDetailsDTO _plainLoginDetailsDTO = new PlainLoginDetailsDTO();
						_plainLoginDetailsDTO.setLoginUsersId(loginUserId);
						_plainLoginDetailsDTO.setRole(setRole);

						_plainLoginDetailsDTO.setLoginCredentialsId(instLonginCred.getId());
						_plainLoginDetailsDTO.setUserName(instLonginCred.getUserName());
						_plainLoginDetailsDTO.setPassword(instLonginCred.getPassword());

						AccessPermissions lcAccessPermissions = instLonginCred.getAccessPermissions();
						if (lcAccessPermissions != null) {

							_plainLoginDetailsDTO.setDashBoard(lcAccessPermissions.getDashBoard());

							_plainLoginDetailsDTO.setDashBoard(lcAccessPermissions.getDashBoard());
							_plainLoginDetailsDTO.setReportsHistorical(lcAccessPermissions.getReportsHistorical());

						}

						rtnLstPlainLoginDetailsDTO.add(_plainLoginDetailsDTO);

					}
				} else {
					// rtnLstPlainLoginDetailsDTO.add(_plainLoginDetailsDTO);
				}

			}
			return rtnLstPlainLoginDetailsDTO;
		}
	}
}
