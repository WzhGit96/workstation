package com.wzh.workstation.dto;

public class LoginRspDTO {
    private Boolean success;

    private String uid;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
