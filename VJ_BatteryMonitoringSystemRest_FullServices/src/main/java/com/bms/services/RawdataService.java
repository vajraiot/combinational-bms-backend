package com.bms.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bms.entity.Rawdata;
import com.bms.miscellaneous.ContentPageMaker;
import com.bms.repositypck.RawdataRepos;

@Component
public interface RawdataService {

	public ContentPageMaker fetcthAllRawdataByServerTimePg(Pageable pageable);
	
	public ContentPageMaker fetchAllDataBySearchingKeyword(String searchingKeyword, Pageable pageable);

	public Rawdata fetchRawDataById(Long id);
	@Service
	public class RawdataServiceClass implements RawdataService
	{   @Autowired
		RawdataRepos rawdataRepos;
	   
		@Transactional
		public ContentPageMaker fetcthAllRawdataByServerTimePg(Pageable pageable)
		{
    	  ContentPageMaker contentPageMaker=new ContentPageMaker();
    	  Page<Rawdata> lstRawdata=rawdataRepos.findAllByOrderByServerTimeDesc(pageable);
			for(Rawdata lrdta :lstRawdata)
			{
				System.out.println(lrdta.toString());
			}
			return new ContentPageMaker(lstRawdata);
		}	
		
		@Transactional
		public ContentPageMaker fetchAllDataBySearchingKeyword(String searchingKeyword, Pageable pageable)
		{
    	  ContentPageMaker contentPageMaker=new ContentPageMaker();
    	  Page<Rawdata> lstRawdata=rawdataRepos.findByRawdataContaining(searchingKeyword, pageable);
			for(Rawdata lrdta :lstRawdata)
			{
				System.out.println(lrdta.toString());
			}
			return new ContentPageMaker(lstRawdata);
		}	
		
		@Transactional
		public Rawdata fetchRawDataById(Long id)
		{
    	  Optional<Rawdata> optRawdata=rawdataRepos.findById(id);
			
			return optRawdata.get();
		}	
		
		
	}
}
