package com.wzh.workstation.bo;

/**
 * @author wzh
 * @since 2023/2/27
 */
public class LoginRspBO {
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

    @Override
    public String toString() {
        return "LoginRspBO{" +
                "success=" + success +
                ", uid='" + uid + '\'' +
                '}';
    }
}
