package com.bms.repositypck;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bms.entity.Rawdata;




@Repository
public interface RawdataRepos extends JpaRepository<Rawdata,Long> {

//	Page<Rawdata> findAllByOrderByServerTimeDesc(Pageable pageable);
	Page<Rawdata>  findAllByOrderByServerTimeDesc(Pageable  pageable);
	
	//Page<Rawdata> findByRawdataIsLike(String searchingKeyword,Pageable  pageable);
	Page<Rawdata> findByRawdataContaining(String searchingKeyword,Pageable  pageable);
}
