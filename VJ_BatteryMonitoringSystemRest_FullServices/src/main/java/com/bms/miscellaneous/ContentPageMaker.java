package com.bms.miscellaneous;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import com.bms.entity.DeviceData;
import com.bms.pojo.ChargerDTO;
import com.bms.pojo.StringParamDTO;

import lombok.Builder;

@Builder
public class ContentPageMaker {

    private List<?> content;
    private PageVO page;

    public List<?> getContent() {
        return content;
    }

    public void setContent(List<?> content) {
        this.content = content;
    }

    public PageVO getPage() {
        return page;
    }

    public void setPage(PageVO page) {
        this.page = page;
    }

    // Existing constructors

    public ContentPageMaker(List<?> content, PageVO page) {
        super();
        this.content = content;
        this.page = page;
    }

    public ContentPageMaker() {
        super();
    }

    public ContentPageMaker(List<?> content, Page<?> pg) {
        this.content = content;
        this.page = PageVO.builder()
                .size(pg.getSize())
                .totalElements(pg.getTotalElements())
                .totalPages(pg.getTotalPages())
                .number(pg.getNumber())
                .build();
    }

    public ContentPageMaker(Page<?> pg) {
        this.content = pg.getContent();
        this.page = PageVO.builder()
                .size(pg.getSize())
                .totalElements(pg.getTotalElements())
                .totalPages(pg.getTotalPages())
                .number(pg.getNumber())
                .build();
    }

    // New constructor to accept both lists and a page for your use case
    public ContentPageMaker(List<StringParamDTO> stringParamDTOList, List<ChargerDTO> chargerDTOList, Page<DeviceData> deviceDataPage) {
        // Combine the two lists into one
        List<Object> combinedList = new ArrayList<>();
        combinedList.addAll(stringParamDTOList);
        combinedList.addAll(chargerDTOList);
        
        // Set the combined list as the content
        this.content = combinedList;
        
        // Set pagination details
        this.page = PageVO.builder()
                .size(deviceDataPage.getSize())
                .totalElements(deviceDataPage.getTotalElements())
                .totalPages(deviceDataPage.getTotalPages())
                .number(deviceDataPage.getNumber())
                .build();
    }

}
