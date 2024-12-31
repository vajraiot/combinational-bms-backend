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
import com.bms.entity.ManufacturerDetails;
import com.bms.entity.Rawdata;
import com.bms.entity.SerialNumberList;
import com.bms.entity.SiteIdList;
import com.bms.entity.SiteLocation;
import com.bms.miscellaneous.ContentPageMaker;
import com.bms.miscellaneous.EntityToPojoConversion;
import com.bms.pojo.ApiResponSestatus;
import com.bms.pojo.GeneralDataDTO;
import com.bms.pojo.PlainManufacturerDetails;
import com.bms.repositypck.GeneralDataRepos;
import com.bms.repositypck.ManufacturerDetailsRepos;
import com.bms.repositypck.RawdataRepos;
import com.bms.repositypck.SerialNumberListRepos;
import com.bms.repositypck.SiteIdListRepos;
import com.bms.repositypck.SiteLocationRepos;
import com.bms.utilities.ApplicationUtility;

@Component
public interface ManufacturerDetailsService {

	
	
	public List<PlainManufacturerDetails> fetchAllManufacturerDetails();
	
	public ManufacturerDetails fetchManufacturerDetailsBySiteidandSerialNumber(String siteId,String serialNumber);
	
	
	public ApiResponSestatus saveManufacturerDetails(PlainManufacturerDetails _plainManufacturerDetails);
	
	
	public ApiResponSestatus updateManufacturerDetails(PlainManufacturerDetails _plainManufacturerDetails);
	
	public ApiResponSestatus deleteManufacturerDetailsBySiteIdAndSerialNumber(String siteId,String serialNumber);
	
	
	@Service
	public class SiteIdListServiceClass implements ManufacturerDetailsService {
		@Autowired
		SiteIdListRepos siteIdListRepos;

		@Autowired
		SerialNumberListRepos serialNumberListRepos;
		
		

		@Autowired
		ManufacturerDetailsRepos manufacturerDetailsRepos;

		
	public SerialNumberList fetchSerailNumberEntityBySiteIdAndSerailNumber(String siteId,String serialNumber)
	{
		try {
		List<SerialNumberList>	lstSerialNumberList= serialNumberListRepos.findBySiteIdListSiteIdAndSerialNumber(siteId, serialNumber);
		
		return lstSerialNumberList.get(0);
		}
		catch(Exception ex)
		{
			return null;
		}
	}
		
	public ManufacturerDetails fetchManufacturerDetailsBySiteidandSerialNumber(String siteId,String serialNumber)
	{
		ManufacturerDetails rtnManufacturerDetails=new ManufacturerDetails();
	//	List<ManufacturerDetails> lstManufacturerDetails=manufacturerDetailsRepos.findBySerialNumberListSiteIdListSiteId(siteId);
		List<ManufacturerDetails> lstManufacturerDetails=manufacturerDetailsRepos.findBySerialNumberListSiteIdListSiteIdAndSerialNumberListSerialNumber(siteId,serialNumber);
		
		if(lstManufacturerDetails.isEmpty())
		{
			return null;
		}
		
		return lstManufacturerDetails.get(0);
	}
	public List<PlainManufacturerDetails> fetchAllManufacturerDetails()
	{ 
	try {
		PlainManufacturerDetails rtnPlainManufacturerDetails=new PlainManufacturerDetails();
		List<SiteIdList> lstSiteIdList=  siteIdListRepos.findAll();
		List<PlainManufacturerDetails> rtnLstPlainManufacturerDetails= ApplicationUtility.covertToPlainManufacturerDetails(lstSiteIdList);
		
		return rtnLstPlainManufacturerDetails;
	}
	catch(Exception ex)
	{
		System.out.println("Exceptin Occured:"+ex.toString());
		return null;
		
	}
	}
	
	public ApiResponSestatus saveManufacturerDetails(PlainManufacturerDetails _plainManufacturerDetails)
	{ 
	try {
		PlainManufacturerDetails rtnPlainManufacturerDetails=new PlainManufacturerDetails();
		
		if(validatePlainManufacturerDetails(_plainManufacturerDetails).getValue()==0)
		{
			return validatePlainManufacturerDetails(_plainManufacturerDetails);
		}
		
		
		SerialNumberList serialNumberList=fetchSerailNumberEntityBySiteIdAndSerailNumber(_plainManufacturerDetails.getSiteId(),_plainManufacturerDetails.getSerialNumber());
		if(serialNumberList.getManufacturerDetails()!=null)
		{
			return ApiResponSestatus.builder().message("Already Other Data Existing For this SiteId and SerialNumber.").value(0).build();
		}
		
		ManufacturerDetails manufacturerDetails=  ApplicationUtility.covertToManufacturerDetails(_plainManufacturerDetails);
		manufacturerDetails.setSerialNumberList(serialNumberList);
		
		manufacturerDetailsRepos.save(manufacturerDetails);
		if(manufacturerDetails.getId()!=null)
		{
			return ApiResponSestatus.builder().message("Successfully Record Saved In Database.").value(1).build();
		}
		else
		{
			return ApiResponSestatus.builder().message("Sorry Failed To Save Record In Database.").value(0).build();
		}
		
	}
	catch(Exception ex)
	{
		System.out.println("Exceptin Occured:"+ex.toString());
		return ApiResponSestatus.builder().message("Exception Occured"+ex.toString()).value(0).build();
		
	}
	}
	
	
	public ApiResponSestatus updateManufacturerDetails(PlainManufacturerDetails _plainManufacturerDetails)
	{ 
	try {
		PlainManufacturerDetails rtnPlainManufacturerDetails=new PlainManufacturerDetails();
		
		if(validatePlainManufacturerDetails(_plainManufacturerDetails).getValue()==0)
		{
			return validatePlainManufacturerDetails(_plainManufacturerDetails);
		}
		
		
		SerialNumberList serialNumberList=fetchSerailNumberEntityBySiteIdAndSerailNumber(_plainManufacturerDetails.getSiteId(),_plainManufacturerDetails.getSerialNumber());
		if((serialNumberList==null)||(serialNumberList.getManufacturerDetails()==null))
		{
			return ApiResponSestatus.builder().message("Data Doesnot Existing For this SiteId and SerialNumber.").value(0).build();
		}
		
		ManufacturerDetails manufacturerDetails=  ApplicationUtility.covertToManufacturerDetails(_plainManufacturerDetails);
		manufacturerDetails.setSerialNumberList(serialNumberList);
		manufacturerDetails.setId(serialNumberList.getManufacturerDetails().getId()); //Here Id should add while updating exiting one.
		
		manufacturerDetailsRepos.save(manufacturerDetails);
		if(manufacturerDetails.getId()!=null)
		{
			return ApiResponSestatus.builder().message("Successfully Record Updated In Database.").value(1).build();
		}
		else
		{
			return ApiResponSestatus.builder().message("Sorry Failed To Update Record In Database.").value(0).build();
		}
		
	}
	catch(Exception ex)
	{
		System.out.println("Exceptin Occured:"+ex.toString());
		return ApiResponSestatus.builder().message("Exception Occured"+ex.toString()).value(0).build();
		
	}
	}
	
	
	public ApiResponSestatus deleteManufacturerDetailsBySiteIdAndSerialNumber(String siteId,String serialNumber)
	{
	try {
		PlainManufacturerDetails rtnPlainManufacturerDetails=new PlainManufacturerDetails();
		
		SerialNumberList serialNumberList=fetchSerailNumberEntityBySiteIdAndSerailNumber(siteId,serialNumber);
		if((serialNumberList==null)||(serialNumberList.getManufacturerDetails()==null))
		{
			return ApiResponSestatus.builder().message("Data Doesnot Existing For this SiteId and SerialNumber.").value(0).build();
		}
		
		ManufacturerDetails manufacturerDetails= serialNumberList.getManufacturerDetails();
		
		manufacturerDetailsRepos.delete(manufacturerDetails);
		
		/**********After Deleting Checking in Database*************/
		serialNumberList=fetchSerailNumberEntityBySiteIdAndSerailNumber(siteId,serialNumber);
		if((serialNumberList==null)||(serialNumberList.getManufacturerDetails()==null))
		{
			return ApiResponSestatus.builder().message("Successfully Record Deleted From Database.").value(1).build();
		}
		else
		{
			return ApiResponSestatus.builder().message("Sorry! Failed To Delete Record From Database.").value(0).build();
		}
		
	}
	catch(Exception ex)
	{
		System.out.println("Exceptin Occured:"+ex.toString());
		return ApiResponSestatus.builder().message("Exception Occured"+ex.toString()).value(0).build();
		
	}
	}
	
	/*****************************Private Methods******************/
	private ApiResponSestatus validatePlainManufacturerDetails(PlainManufacturerDetails _plainManufacturerDetails)
	{
		if(_plainManufacturerDetails==null)
		{
			return ApiResponSestatus.builder().message("Null input.").value(0).build();
		}
		else if(_plainManufacturerDetails.getSiteId()==null)
		{
			return ApiResponSestatus.builder().message("No Site Id is Detected.").value(0).build();
		}
		else if(_plainManufacturerDetails.getSerialNumber()==null)
		{
			return ApiResponSestatus.builder().message("No  SerialNumber is Detected.").value(0).build();
		}
		else
		{
			return ApiResponSestatus.builder().message("").value(1).build();
		}
	}
	}

}
