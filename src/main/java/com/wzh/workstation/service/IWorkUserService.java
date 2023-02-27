package com.wzh.workstation.service;

import com.wzh.workstation.bo.LoginReqBO;
import com.wzh.workstation.bo.LoginRspBO;
import com.wzh.workstation.bo.RegisterReqBO;
import com.wzh.workstation.bo.RegisterRspBO;

/**
 * @author wzh
 * @since 2023/2/27
 */
public interface IWorkUserService {
    /**
     * 登录
     *
     * @param reqBO
     * @return
     */
    LoginRspBO login(LoginReqBO reqBO) throws Exception;

    /**
     * 注册
     *
     * @param reqBO
     * @return
     */
    RegisterRspBO register(RegisterReqBO reqBO);
}
