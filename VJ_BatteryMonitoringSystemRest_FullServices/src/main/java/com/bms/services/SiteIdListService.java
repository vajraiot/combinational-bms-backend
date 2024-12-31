package com.bms.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bms.entity.GeneralData;
import com.bms.entity.Rawdata;
import com.bms.entity.SiteIdList;
import com.bms.entity.SiteLocation;
import com.bms.miscellaneous.ContentPageMaker;
import com.bms.miscellaneous.EntityToPojoConversion;
import com.bms.pojo.ApiResponSestatus;
import com.bms.pojo.GeneralDataDTO;
import com.bms.repositypck.GeneralDataRepos;
import com.bms.repositypck.RawdataRepos;
import com.bms.repositypck.SiteIdListRepos;
import com.bms.repositypck.SiteLocationRepos;

@Component
public interface SiteIdListService {

	public List<String> fetchAllSiteIds();

	public SiteIdList fetchSiteIdWithSerialNumbers(String siteid);

	public List<SiteIdList> fetchAllSiteIdWithSerialNumbers();

	public ApiResponSestatus postAddNewLocationToSiteId(SiteIdList _siteIdList);

	public ApiResponSestatus updateSiteLocationToSiteId(SiteIdList _siteIdList);

	public ApiResponSestatus deleteSiteLocationToSiteId(String siteId);
		
	@Service
	public class SiteIdListServiceClass implements SiteIdListService {
		@Autowired
		SiteIdListRepos siteIdListRepos;

		@Autowired
		SiteLocationRepos siteLocationRepos;

		@Transactional
		public List<String> fetchAllSiteIds() {

			List<SiteIdList> lstSiteIdList = siteIdListRepos.findAll();
			List<String> lstString = new ArrayList<String>();
			for (SiteIdList siteLst : lstSiteIdList) {
				lstString.add(siteLst.getSiteId());
			}

			return lstString;
		}

		public SiteIdList fetchSiteIdWithSerialNumbers(String siteid) {
			try {
				List<SiteIdList> lstSiteIdList = siteIdListRepos.findBySiteId(siteid);
				if (lstSiteIdList.isEmpty()) {
					return null;
				}
				return lstSiteIdList.get(0);
			} catch (Exception ex) {
				System.out.println(" Exception occured in fetchSiteIdWithSerialNumber is:" + ex.toString());
				return null;
			}
		}

		public List<SiteIdList> fetchAllSiteIdWithSerialNumbers() {
			try {
				List<SiteIdList> lstSiteidList = siteIdListRepos.findAll();

				return lstSiteidList;
			} catch (Exception ex) {
				System.out.println("exception Occured in fetchAllSiteIdWithSerialNumbers is:" + ex.toString());
				return null;
			}
		}

		public ApiResponSestatus postAddNewLocationToSiteId(SiteIdList _siteIdList) {
			try {
				if (_siteIdList == null) {
					return ApiResponSestatus.builder().message("Null input.").value(0).build();
				}
				if (_siteIdList.getSiteLocation() == null) {
					return ApiResponSestatus.builder().message("There is no SiteLocation is there.").value(0).build();
				}
				SiteLocation inptSiteLocation = _siteIdList.getSiteLocation();

				String strSiteId = _siteIdList.getSiteId();
				SiteIdList prsSiteIdList = fetchSiteIdWithSerialNumbers(strSiteId);
				if (prsSiteIdList == null) {
					return ApiResponSestatus.builder().message("Failed to retrieve SiteId").value(0).build();
				}
				if (prsSiteIdList.getSiteLocation() != null) {
					return ApiResponSestatus.builder().message("Sorry! Location DetailAlready Exist.").value(0).build();
				}
				inptSiteLocation.setSiteIdList(prsSiteIdList);
				siteLocationRepos.save(inptSiteLocation);

				if (inptSiteLocation.getId() == null) {
					return ApiResponSestatus.builder().message("Fail! to add New Location.").value(0).build();
				}
				return ApiResponSestatus.builder().message("Successfully Location Added").value(1).build();
				// siteLocationRepos.
			} catch (Exception ex) {
				return ApiResponSestatus.builder().message("Exception Occured:" + ex.toString()).value(0).build();
			}
		}

		public ApiResponSestatus updateSiteLocationToSiteId(SiteIdList _siteIdList) {
			try {
				if (_siteIdList == null) {
					return ApiResponSestatus.builder().message("Null input.").value(0).build();
				}
				if (_siteIdList.getSiteLocation() == null) {
					return ApiResponSestatus.builder().message("There is no SiteLocation is there.").value(0).build();
				}
				SiteLocation inptSiteLocation = _siteIdList.getSiteLocation();

				String strSiteId = _siteIdList.getSiteId();
				SiteIdList prsSiteIdList = fetchSiteIdWithSerialNumbers(strSiteId);

				if (prsSiteIdList == null) {
					return ApiResponSestatus.builder().message("Failed to retrieve SiteId").value(0).build();
				}

				if (prsSiteIdList.getSiteLocation() == null) {
					return ApiResponSestatus.builder()
							.message("There is no SiteLocation for this siteId please Add New Then Update.").value(0)
							.build();
				}
				inptSiteLocation.setId(prsSiteIdList.getSiteLocation().getId());
				inptSiteLocation.setSiteIdList(prsSiteIdList);
				siteLocationRepos.save(inptSiteLocation); // saving to database.

				if (inptSiteLocation.getId() == null) {
					return ApiResponSestatus.builder().message("Fail! to update Location.").value(0).build();
				}
				return ApiResponSestatus.builder().message("Successfully Location Updated.").value(1).build();
				// siteLocationRepos.
			} catch (Exception ex) {
				return ApiResponSestatus.builder().message("Exception Occured:" + ex.toString()).value(0).build();
			}
		}

		
		public ApiResponSestatus deleteSiteLocationToSiteId(String siteId) {
			try {
				if (siteId== null) {
					return ApiResponSestatus.builder().message("Null input.").value(0).build();
				}
				if (siteId == "") {
					return ApiResponSestatus.builder().message("Empy Input.").value(0).build();
				}
				SiteIdList prsSiteIdList = fetchSiteIdWithSerialNumbers(siteId);

				if (prsSiteIdList == null) {
					return ApiResponSestatus.builder().message("Failed to retrieve SiteId").value(0).build();
				}

				if (prsSiteIdList.getSiteLocation() == null) {
					return ApiResponSestatus.builder()
							.message("There is no SiteLocation for this siteId.").value(0)
							.build();
				}
				SiteLocation instSiteLocation=prsSiteIdList.getSiteLocation();
				siteLocationRepos.delete(instSiteLocation);
				return ApiResponSestatus.builder().message("Successfully Record Deleted.").value(1).build();
				// siteLocationRepos.
			} catch (Exception ex) {
				return ApiResponSestatus.builder().message("Exception Occured:" + ex.toString()).value(0).build();
			}
		}

	 /* public ApiResponSestatus deleteSiteLocationToSiteId(String strSiteId) {
			try {
				if (strSiteId == "") {
					return ApiResponSestatus.builder().message("Please select the SiteId").value(0).build();

				}
				if (strSiteId == null) {
					return ApiResponSestatus.builder().message("Please select the SiteId").value(0).build();
				}
				
				SiteIdList prsSiteIdList = fetchSiteIdWithSerialNumbers(strSiteId);
				
				
			} catch (Exception ex) {
				return ApiResponSestatus.builder().message("Exception Occured:" + ex.toString()).value(0).build();
			}

		}*/
		/*************************
		 * private Methods
		 ****************************************/

		/********************************************************************************/
	}

}
