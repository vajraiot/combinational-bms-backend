package com.bms.miscellaneous;


import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PagingSpecification {
	
	 public static Sort sortByIdAsc() {
		    return new Sort(Sort.Direction.ASC, "id");
		  }

		  /**
		   * Returns a new object which specifies the the wanted result page.
		   *
		   * @param pageIndex The index of the wanted result page
		   * @return
		   */
		  public static Pageable constructPageSpecification(int pageIndex, int pageSize, Sort sortingOrderSpec) {
		    Pageable pageSpecification = PageRequest.of(pageIndex, pageSize, sortingOrderSpec);
		    return pageSpecification;
		  }

		  /**
		   * Returns a Sort object which sorts persons in ascending order by using the last name.
		   * @return
		   */
	
	

}
