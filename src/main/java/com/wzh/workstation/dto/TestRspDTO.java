package com.wzh.workstation.dto;

/**
 * @author wzh
 * @since 2023/2/24
 */
public class TestRspDTO {
    private String returnStr;

    public String getReturnStr() {
        return returnStr;
    }

    public void setReturnStr(String returnStr) {
        this.returnStr = returnStr;
    }

    @Override
    public String toString() {
        return "TestRspDTO{" +
                "returnStr='" + returnStr + '\'' +
                '}';
    }
}
