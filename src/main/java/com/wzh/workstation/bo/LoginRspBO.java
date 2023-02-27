package com.wzh.workstation.bo;

/**
 * @author wzh
 * @since 2023/2/27
 */
public class LoginRspBO {
    private Boolean success;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "LoginRspBO{" +
                "success=" + success +
                '}';
    }
}
