package com.wzh.workstation.controller;

import com.wzh.workstation.bo.LoginReqBO;
import com.wzh.workstation.bo.LoginRspBO;
import com.wzh.workstation.bo.RegisterReqBO;
import com.wzh.workstation.dto.LoginReqDTO;
import com.wzh.workstation.dto.LoginRspDTO;
import com.wzh.workstation.service.IWorkUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wzhframework.workstation.common.NoBody;
import org.wzhframework.workstation.common.dto.GenericRspDTO;
import org.wzhframework.workstation.common.msg.MsgEnum;

@RestController
@RequestMapping("/workstation/user")
public class WorkUserController {
    @Autowired
    private IWorkUserService workUserService;

    @PostMapping("/login")
    public GenericRspDTO<LoginRspDTO> login(@RequestBody LoginReqDTO reqDTO) throws Exception {
        LoginReqBO reqBO = new LoginReqBO();
        BeanUtils.copyProperties(reqDTO, reqBO);
        LoginRspBO rspBO = workUserService.login(reqBO);
        LoginRspDTO rspDTO = new LoginRspDTO();
        BeanUtils.copyProperties(rspBO, rspDTO);
        return GenericRspDTO.newSuccessInstance(rspDTO);
    }

    @GetMapping("/register")
    public GenericRspDTO<NoBody> register(String account, String pwd) throws Exception {
        RegisterReqBO reqBO = new RegisterReqBO();
        reqBO.setAccount(account);
        reqBO.setPassword(pwd);
        workUserService.register(reqBO);
        return GenericRspDTO.newSuccessInstance();
    }

    @GetMapping("/validateLogin")
    public GenericRspDTO<LoginRspDTO> validateLogin() {
        LoginRspDTO rspDTO = new LoginRspDTO();
        LoginRspBO rspBO = workUserService.validateLogin();
        BeanUtils.copyProperties(rspBO, rspDTO);
        return GenericRspDTO.newSuccessInstance(rspDTO);
    }
}
