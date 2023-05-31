package com.wzh.workstation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.wzhframework.workstation.common.dto.GenericDTO;

public class WorkProjectReqDTO extends GenericDTO {
    @JsonProperty("page")
    private Integer pageNo;

    @JsonProperty("rows")
    private Integer pageSize;

    private String code;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
