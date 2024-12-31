package com.bms.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bms.intefaces.GetAllVehiclesInterface;
import com.bms.miscellaneous.ContentPageMaker;
import com.bms.pojo.GeneralDataDTO;
import com.bms.pojo.TestingClass;


@CrossOrigin
@RestController
public class TestingControler {

	@CrossOrigin
	@GetMapping(value = "/testing")
	public String testing() {
		return "working Ok.";
	}
	
	
	@GetMapping(value = "/testingResponse")
	public ResponseEntity<TestingClass> testingResponse()
	{
		TestingClass rtnTestingClass=new TestingClass("sajeeth",21);
		int k=100/0;
		//rtnTestingClass=null;
	   return	ResponseEntity.ok(rtnTestingClass);
	}

	@GetMapping(value = "/testing2")
	public ResponseEntity<String> testingResponse2()
	{
		TestingClass rtnTestingClass=new TestingClass("sajeeth",21);
	//	int k=100/0;
		//rtnTestingClass=null;
	   return	ResponseEntity.ok("checking...");
	}
	
}
