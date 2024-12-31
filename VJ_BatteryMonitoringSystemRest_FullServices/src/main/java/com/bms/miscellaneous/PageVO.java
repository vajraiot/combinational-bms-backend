package com.bms.miscellaneous;

import java.util.Date;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class PageVO {

	private int size;
	private long totalElements;
	private int totalPages;
	private int number;
	
	
	
}
