package com.wzh.workstation.service;

import com.wzh.workstation.bo.WorkProjectReqBO;
import com.wzh.workstation.bo.WorkProjectRspBO;

public interface IWorkProjectService {
    /**
     * 分页查询需求列表
     *
     * @param reqBO
     * @return
     */
    WorkProjectRspBO pageList(WorkProjectReqBO reqBO);
}
