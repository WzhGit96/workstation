package com.wzh.workstation.bo;

/**
 * @author wzh
 * @since 2023/2/27
 */
public class RegisterReqBO {
    private String account;

    private String password;

    private String sign;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return "RegisterReqBO{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }
}
