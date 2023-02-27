package com.wzh.workstation.service.impl;

import com.wzh.workstation.bo.LoginReqBO;
import com.wzh.workstation.bo.LoginRspBO;
import com.wzh.workstation.bo.RegisterReqBO;
import com.wzh.workstation.bo.RegisterRspBO;
import com.wzh.workstation.dao.WorkUserMapper;
import com.wzh.workstation.entity.WorkUser;
import com.wzh.workstation.service.IWorkUserService;
import org.springframework.stereotype.Service;
import org.wzhframework.workstation.utils.PasswordUtils;

import javax.annotation.Resource;

/**
 * @author wzh
 * @since 2023/2/27
 */
@Service
public class WorkUserServiceImpl implements IWorkUserService {
    @Resource
    private WorkUserMapper workUserMapper;

    @Override
    public LoginRspBO login(LoginReqBO reqBO) throws Exception {
        WorkUser entity = new WorkUser();
        entity.setAccount(reqBO.getAccount());
        entity.setPwd(PasswordUtils.encrypt(reqBO.getPassword()));
        WorkUser result = workUserMapper.login(entity);
        LoginRspBO rspBO = new LoginRspBO();
        rspBO.setSuccess(result != null);
        return rspBO;
    }

    @Override
    public RegisterRspBO register(RegisterReqBO reqBO) {
        return null;
    }
}
