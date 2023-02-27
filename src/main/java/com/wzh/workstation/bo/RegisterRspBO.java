package com.wzh.workstation.bo;

/**
 * @author wzh
 * @since 2023/2/27
 */
public class RegisterRspBO {
    private Boolean success;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "RegisterRspBO{" +
                "success=" + success +
                '}';
    }
}
