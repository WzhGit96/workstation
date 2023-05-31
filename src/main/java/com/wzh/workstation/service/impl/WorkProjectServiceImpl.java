package com.wzh.workstation.service.impl;

import com.github.pagehelper.Page;
import com.wzh.workstation.bo.WorkProjectReqBO;
import com.wzh.workstation.bo.WorkProjectRspBO;
import com.wzh.workstation.dao.WorkProjectMapper;
import com.wzh.workstation.entity.WorkProject;
import com.wzh.workstation.service.IWorkProjectService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.wzhframework.workstation.utils.PageHelpUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class WorkProjectServiceImpl implements IWorkProjectService {
    @Resource
    private WorkProjectMapper workProjectMapper;

    @Override
    public WorkProjectRspBO pageList(WorkProjectReqBO reqBO) {
        WorkProject entity = new WorkProject();
        entity.setCode(reqBO.getCode());
        Page<WorkProject> page = PageHelpUtil.startPage(reqBO.getPageNo(), reqBO.getPageSize(), () -> {
            workProjectMapper.selectList(entity);
        });
        WorkProjectRspBO rspBO = new WorkProjectRspBO();
        List<WorkProject> list = page.getResult();
        if (CollectionUtils.isEmpty(list)) {
            rspBO.setRows(new ArrayList<>());
            rspBO.setTotal(0L);
            return rspBO;
        }
        rspBO.setRows(list);
        rspBO.setTotal(page.getTotal());
        return rspBO;
    }
}
