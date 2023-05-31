package com.wzh.workstation.dto;


import org.wzhframework.workstation.common.dto.GenericDTO;

public class LoginReqDTO extends GenericDTO {
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
}
