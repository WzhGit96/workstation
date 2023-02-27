package com.wzh.workstation.dao;

import com.wzh.workstation.entity.WorkUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WorkUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WorkUser record);

    WorkUser selectByPrimaryKey(Long id);

    /**
     * 登录方法
     *
     * @param workUser
     * @return
     */
    WorkUser login(WorkUser workUser);

    int updateByPrimaryKeySelective(WorkUser record);

    int updateByPrimaryKey(WorkUser record);
}
