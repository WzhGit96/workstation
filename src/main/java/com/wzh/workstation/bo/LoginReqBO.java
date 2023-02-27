package com.wzh.workstation.bo;

/**
 * @author wzh
 * @since 2023/2/27
 */
public class LoginReqBO {
    private String account;

    private String password;

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

    @Override
    public String toString() {
        return "LoginReqBO{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
