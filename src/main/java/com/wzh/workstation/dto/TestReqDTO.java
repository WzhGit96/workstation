package com.wzh.workstation.dto;

import org.wzhframework.workstation.common.dto.GenericDTO;

/**
 * @author wzh
 * @since 2023/2/24
 */
public class TestReqDTO extends GenericDTO {
    private String param;

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    @Override
    public String toString() {
        return "TestReqDTO{" +
                "param='" + param + '\'' +
                '}';
    }
}
