package com.wzh.workstation.service.impl;

import com.wzh.workstation.dao.WorkUserMapper;
import com.wzh.workstation.entity.WorkUser;
import com.wzh.workstation.service.ITestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wzh
 * @since 2023/2/24
 */
@Service
public class TestServiceImpl implements ITestService {
    @Resource
    private WorkUserMapper workUserMapper;

    @Override
    public void testDao() {
        WorkUser user = workUserMapper.selectByPrimaryKey(1L);
    }
}
